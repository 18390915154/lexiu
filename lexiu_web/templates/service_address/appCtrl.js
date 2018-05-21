angular.module("myapp")
    .controller("service_addressCtrl",function($scope,$interval,$document,$state,$ionicPopup,$stateParams){
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
        $scope.type=$stateParams.type;
        /*获取附近的公司信息*/
        $.ajax({
            url: "https://www.lerlex.com/lexiu/api/company/nearbycompany",
            data: ({
                type: $scope.type
            }),
            type: "get",
            dataType: "jsonp",
            jsonp: "jsonpcallback",
            success: function (data) {
                console.log(data);
                var Obj = eval('(' + data[1].value + ')');
                $scope.companys=Obj;
            }
        });
        $scope.companyid=$stateParams.companyid;
        $scope.company_longitude=$stateParams.company_longitude;
        $scope.company_latitude=$stateParams.company_latitude;
        console.log($scope.company_longitude)
        console.log($scope.company_latitude)
        // 百度地图API功能
        var map = new BMap.Map("allmap_service",{    // 创建地图实例
            enableHighResolution: true //是否开启高清
        });
        map.setCurrentCity("杭州");          // 设置地图显示的城市 此项是必须设置的
        map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
        function my_piont(){
            point1 = new BMap.Point($scope.company_longitude,$scope.company_latitude);//默认 可以通过Icon类来指定自定义图标
            var myIcon = new BMap.Icon("images/red.png", new BMap.Size(23,25));
            var marker = new BMap.Marker(point1, {icon: myIcon});
            map.addOverlay(marker);
        }
        my_piont();
        map.centerAndZoom(point1, 14);
        //关键字搜索智能提示
        // 百度地图API功能
        function G(id) {
            return document.getElementById(id);
        }
        var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
            {"input" : "search_address"
                ,"location" : map
            });
        var myValue;
        ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
            var _value = e.item.value;
            myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
            $scope.new_address=myValue;
            search($scope.new_address);
            map.clearOverlays();
        });
        var mk;
        function search(myValue){
            function myFun(){
                var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
                map.centerAndZoom(pp, 16);
                var icon = new BMap.Icon("images/green.png", new BMap.Size(23,25));
                mk = new BMap.Marker(pp,{icon:icon});//创建一个覆盖物
                var label1 = new BMap.Label("<div class='company_nearby' style='width: 100%;height: 100%;'></div>", {
                    offset: new BMap.Size(20, -10)
                }); //标注标签
                label1.setStyle({
                    border: "none",
                    height: "40px",
                    lineHeight: "32px",
                    fontSize: "15px",
                    textAlign: "center",
                    borderRadius: "5px",
                    marginLeft: "5px",
                    background: "url(images/map_border.png) no-repeat",
                    backgroundSize: "100% 100%",
                    padding: "6px",
                    textIndent: "1vw",
                    paddingLeft:"20px"
                });
                mk.setLabel(label1) //设置标注说明
                map.addOverlay(mk);//增加一个标示到地图上
                my_piont();
                $document.find(".company_nearby").html("我在"+$scope.new_address.substr(0,9)+"附近");
                console.log(myValue);
                longitudes = pp.lng;//获取到的经度
                latitudes  = pp.lat;//获取到的纬度
                var points = [pp, point1];
                var view = map.getViewport(eval(points));
                var mapZoom = view.zoom;
                var centerPoint = view.center;
                map.centerAndZoom(centerPoint,mapZoom-1);
                console.log(centerPoint);
                console.log(mapZoom);
                console.log(longitudes)
                console.log(latitudes)

            }
            var local = new BMap.LocalSearch(map, { //智能搜索
                onSearchComplete: myFun
            });
            local.search(myValue);
        }
        function prompts(){
            var timer_address=$interval(function(){
                var prompt=$document.find(".tangram-suggestion-main");
                console.log(prompt)
                console.log(prompt.length)
                if(!prompt) return;
                prompt.css("z-index","2000");
                $interval.cancel(timer_address);
            },10);
        }
        /*改变input的值调用关键字搜索*/
        $scope.address_change=function(){
            prompts();
        };
        /*点击附近的公司*/
        $scope.jump=function(index){
            $state.go("place_order",{type:$scope.type,longitude:$scope.companys[index].longitude,latitude:$scope.companys[index].latitude,companyid:$scope.companyid,new_address:$scope.companys[index].address,company_longitude:$scope.company_longitude,company_latitude:$scope.company_latitude})
        };
        /*关键字搜索完成之后*/
        $scope.sure=function(){
            if($scope.new_address){
                $state.go("place_order",{type:$scope.type,longitude:longitudes,latitude:latitudes,companyid:$scope.companyid,
new_address:$scope.new_address,company_longitude:$scope.company_longitude,company_latitude:$scope.company_latitude})
            }else{
                $ionicPopup.alert({
                    title: '提示',
                    template: "请输入地址",
                    okText: '确定'
                });
            }

        };


        /*显示当前距离附近的公司*/
       /* $.ajax({
            url:"https://www.lerlex.com/lexiu/api/order/workerbytype",
            type: "get",
            dataType: "jsonp",
            jsonp:"jsonpcallback",
            success:function(data){
                console.log(data);
                if(data.message==null || data.message=="成功") {
                    var json=data[1].value;
                    var a = eval(json)
                    console.log(a)
                    angular.forEach(a,function(data,index,array){
                        console.log(data.latitude+'='+array[index].latitude);
                        var point = new BMap.Point(data.longitude,data.latitude);//默认 可以通过Icon类来指定自定义图标
                        var myIcon = new BMap.Icon("images/red.png", new BMap.Size(23,25));
                        var marker = new BMap.Marker(point, {icon: myIcon});
                        map.addOverlay(marker);
                        if((map.getDistance(point_my,point)).toFixed(2)>=3000){
                            /!*alert((map.getDistance(point_my,point)).toFixed(2));*!/
                            map.removeOverlay(marker);
                        }
                        var points = [point_my, point];
                        var view = map.getViewport(eval(points));
                        var mapZoom = view.zoom;
                        var centerPoint = view.center;
                        map.centerAndZoom(centerPoint,mapZoom);
                        console.log(centerPoint);
                        console.log(mapZoom);
                    })
                }
            }
        });*/
    });