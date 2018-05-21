angular.module("myapp")
    .controller("mapCtrl", function ($ionicPopup, $state, $document, $scope, $interval, $http) {
        /*$ionicNavBarDelegate.showBar(false)*/
        var dian=document.getElementsByClassName("dian")[0];
        var guandao=document.getElementsByClassName("guandao")[0];
        var kongtiao=document.getElementsByClassName("kongtiao")[0];
        var dg_img=dian.getElementsByTagName("img")[0];
        var gdg_img=guandao.getElementsByTagName("img")[0];
        var ktg_img=kongtiao.getElementsByTagName("img")[0];
        var dian_type=document.getElementsByClassName("dian_detail")[0];
        var putong=dian_type.getElementsByClassName("putong")[0];
        var zishen=dian_type.getElementsByClassName("zishen")[0];
        dian_type.style.display="none";
        putong.style.color="#e86000";
        dian.style.color="#e86000";
        dian_type.style.display="block";
        $scope.type=1;
        dian.onclick=function(){
            this.style.color="#e86000";
            guandao.style.color="#333";
            kongtiao.style.color="#333";
            dg_img.src="images/dg.png";
            gdg_img.src="images/gdg.png";
            ktg_img.src="images/ktg.png";
            dian_type.style.display="block";
            putong.style.color="#e86000";
            zishen.style.color="#333";
            $scope.type=1;
            appear();
        };
        guandao.onclick=function(){
            this.style.color="#e86000";
            dian.style.color="#333";
            kongtiao.style.color="#333";
            dg_img.src="images/dg_1.png";
            gdg_img.src="images/gdg_1.png";
            ktg_img.src="images/ktg.png";
            dian_type.style.display="none";
            $scope.type=3;
            putong.style.color="#333";
            zishen.style.color="#333";
            appear();
        };
        kongtiao.onclick=function(){
            this.style.color="#e86000";
            dian.style.color="#333";
            guandao.style.color="#333";
            dg_img.src="images/dg_1.png";
            gdg_img.src="images/gdg.png";
            ktg_img.src="images/ktg_1.png";
            dian_type.style.display="none";
            $scope.type=4;
            putong.style.color="#333";
            zishen.style.color="#333";
            appear();
        };
        console.log($scope.type)
        putong.onclick=function(){
            if(putong.style.color=="rgb(232, 96, 0)"){
                this.style.color="rgb(232, 96, 0)";
            }else{
                this.style.color="#e86000";
                zishen.style.color="#333";
                $scope.type=1;
                appear();
            }
        };
        zishen.onclick=function(){
            if(zishen.style.color=="rgb(232, 96, 0)"){
                this.style.color="rgb(232, 96, 0)";
            }else{
                this.style.color="#e86000";
                putong.style.color="#333";
                $scope.type=2;
                appear();
            }
        };
        // 百度地图API功能

        var longitude;
        var latitude;
        var timer2;
        var map = new BMap.Map("allmap", {    // 创建地图实例
            enableHighResolution: true //是否开启高清
        });
        map.setCurrentCity("杭州");          // 设置地图显示的城市 此项是必须设置的
        map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
         function appear(){
             map.clearOverlays();    //清除地图上所有覆盖物
             if (navigator.geolocation){
                 navigator.geolocation.getCurrentPosition(showgps,//成功回调函数
                     function(error){ //失败回调函数
                         showError(error);
                     },
                     {enableHighAcuracy: true, timeout:5000,maximumAge: 0}); // 这里设置超时为5000毫秒，即1秒
             };
             function showError(error){
                 switch(error.code) {
                     case error.PERMISSION_DENIED:
                         alert("定位失败,用户拒绝请求地理定位");
                         break;
                     /*case error.POSITION_UNAVAILABLE:
                      alert("定位失败,位置信息是不可用");
                      break;
                      case error.TIMEOUT:
                      alert("定位失败,请求获取用户位置超时");
                      break;*/
                     case error.UNKNOWN_ERROR:
                         alert("定位失败,定位系统失效");
                         break;
                 }
             };
             function showgps(position){
                 var lng = position.coords.longitude;
                 var lat = position.coords.latitude;
                 var ggPoint = new BMap.Point(lng,lat);
                 map.addControl(new BMap.NavigationControl());
                 //坐标转换完之后的回调函数
                 translateCallback = function (data){
                     if(data.status === 0) {
                         var icon = new BMap.Icon("images/green.png", new BMap.Size(23, 25));
                         var marker = new BMap.Marker(data.points[0], {icon: icon});  // 创建标注'
                         map.addOverlay(marker);
                         //map.setCenter(data.points[0]);
                         map.centerAndZoom(data.points[0], 16);
                         this_lng=marker.point.lng;
                         this_lat=marker.point.lat ;
                         var point_my = new BMap.Point(this_lng, this_lat);//默认 可以通过Icon类来指定自定义图标
                         $.ajax({
                             url: "https://www.lerlex.com/lexiu/api/system/seachworkbytypeid",
                             type: "get",
                             data:({
                                 typeid:$scope.type
                             }),
                             dataType: "json",
                             success: function (data) {
                                 console.log(data);
                                 if (data.message == null || data.message == "成功") {
                                     /*var json = data[0].value;*/
                                     var a = eval(data.gpslist);
                                     console.log(a);
                                     //-------------------------------------
                                     angular.forEach(a, function (data, index, array) {
                                         console.log(data.latitude + '=' + array[index].latitude);
                                         var point = new BMap.Point(data.longitude, data.latitude);//默认 可以通过Icon类来指定自定义图标
                                         var myIcon = new BMap.Icon("images/red.png", new BMap.Size(23, 25));
                                         var marker = new BMap.Marker(point, {icon: myIcon});
                                         map.addOverlay(marker);
                                         //大于五公里外的不再显示
                                         if ((map.getDistance(point_my, point)).toFixed(2) >= 5000) {
                                             /*alert((map.getDistance(point_my,point)).toFixed(2));*/
                                             map.removeOverlay(marker);
                                         }
                                         var points = [point_my, point];
                                         var view = map.getViewport(eval(points));
                                         var mapZoom = view.zoom;
                                         var centerPoint = view.center;
                                         map.centerAndZoom(point_my, mapZoom);
                                         console.log("师傅：");
                                         console.log(point);
                                         console.log(mapZoom);
                                     })

                                 }
                             }
                         });
                     }
                 };
                 var convertor = new BMap.Convertor();
                 var pointArr = [];
                 pointArr.push(ggPoint);
                 convertor.translate(pointArr, 1, 5, translateCallback)
             }
         }
        
        appear();
        //定位后才能跳转
        $scope.map1 = function () {
            if (this_lng!=null ||  this_lng!="") {
                map1.show();
                Map.hide();
                map3.hide();
                quxiao.hide();
                revise_address.hide();
            } else {
                $ionicPopup.alert({
                    title: '',
                    template: "正在定位",
                    okText: '确定'
                });
            }
        }
        var result = $document.find("#r-result");
        var Map = $document.find(".Map");
        var map1 = $document.find(".map1");
        var map3 = $document.find(".map3");
        var map4 = $document.find(".map4");
        var map5 = $document.find(".map5");
        var tk = $document.find(".tk");
        var quxiao = $document.find(".cancelOrder");
        var revise_address = $document.find(".revise_address");
        var n = 0;
        /*var person=$document.find(".item");
         person.each(function(){
         $(this).on("click",function(){
         $(this).addClass("active").siblings().removeClass("active");
         })
         });*/
        result.show();
        map1.hide();
        map3.hide();
        map4.hide();
        map5.hide();
        tk.hide();
        quxiao.hide();
        revise_address.hide();

        $scope.Map = function () {
            $scope.new_address="";
            Map.show();
            quxiao.hide();
            map1.hide();
            map3.hide();
            map4.hide();
            map5.hide();
            revise_address.hide();
            newmap();
        };
        $scope.map3 = function () {
            map1.hide();
            map3.show();
            quxiao.hide();
            revise_address.hide();
            map4.hide();
        };
        $scope.cancelOrder = function () {
            $scope.new_address="";
            quxiao.show();
            map3.hide();
            Map.hide();
            n = 0;
        };
        $scope.cancelOrder1 = function () {
            $scope.new_address="";
            quxiao.show();
            map3.hide();
            Map.hide();
            n = 1;
        };
        $scope.jump_address = function () {
            revise_address.show();
            map1.hide();
            map3.hide();
        };
        //重新定位
        function newmap() {
            map.clearOverlays();    //清除地图上所有覆盖物
            var geoCtrl = new BMap.Geolocation();
            geoCtrl.getCurrentPosition(function (result) {
                if (this.getStatus() == BMAP_STATUS_SUCCESS) {
                    var icon = new BMap.Icon("images/green.png", new BMap.Size(23, 25));
                    var mk = new BMap.Marker(result.point, {icon: icon});//创建一个覆盖物
                    map.addOverlay(mk);//增加一个标示到地图上
                    map.centerAndZoom(result.point, 14);
                }
            })
        }

        //关键字搜索智能提示
        // 百度地图API功能
        function G(id) {
            return document.getElementById(id);
        }

        var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
            {
                "input": "suggestId"
                , "location": map
            });
        var myValue;
        ac.addEventListener("onconfirm", function (e) {    //鼠标点击下拉列表后的事件
            var _value = e.item.value;
            myValue = _value.province + _value.city + _value.district + _value.street + _value.business;
            $scope.new_address = myValue;
            map1.hide();
            map3.show();
            quxiao.hide();
            revise_address.hide();
            map4.hide();
            search($scope.new_address);
            prompts()
        });
        var mk;
        var localSearch = null;

        function search(myValue) {
            map.clearOverlays();    //清除地图上所有覆盖物
            function myFun() {
                /*if(myValue.getStatus() == BMAP_STATUS_SUCCESS){*/
                var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
                map.centerAndZoom(pp, 16);
                var icon = new BMap.Icon("images/green.png", new BMap.Size(23, 25));
                mk = new BMap.Marker(pp, {icon: icon});//创建一个覆盖物
                map.addOverlay(mk);//增加一个标示到地图上
                console.log(myValue);
                console.log(pp);
                longitudes = pp.lng;//获取到的经度
                latitudes = pp.lat;//获取到的纬度
            }

            var local = new BMap.LocalSearch(map, { //智能搜索
                onSearchComplete: myFun
            });
            local.search(myValue);
        }

        /*改变input的值调用关键字搜索*/
        $scope.address_change = function () {
            prompts();
        };
        function prompts() {
            var timer_address = $interval(function () {
                var prompt = $document.find(".tangram-suggestion-main");
                console.log(prompt.length)
                if (!prompt) return;
                $interval.cancel(timer_address);
                prompt.css("z-index", "1000");
            }, 1)
        }
        function tey() {
            var timer_address = $interval(function () {
                var prompt = $document.find(".tangram-suggestion-main").eq(0);
                if (!prompt) return;
                $interval.cancel(timer_address);
                prompt.css("z-index", "0");
            }, 1)
        }

        /*prompts();*/
        var comment_address = document.getElementById("comment_address");
        $scope.address_submit = function () {
            $.ajax({
                url: "https://www.lerlex.com/lexiu/api/user/editaddress",
                data: ({
                    address: $scope.address
                }),
                type: "get",
                dataType: "jsonp",
                jsonp: "jsonpcallback",
                success: function (data) {
                    console.log(data);
                    comment_address.innerHTML = $scope.address;
                    map1.show();
                    Map.hide();
                    map3.hide();
                    quxiao.hide();
                    revise_address.hide();
                }
            })
        };
        /*常用地址设置*/
        $.ajax({
            url: "https://www.lerlex.com/lexiu/api/user/selectaddress",
            type: "get",
            dataType: "jsonp",
            jsonp: "jsonpcallback",
            success: function (data) {
                console.log(data[1].value);
                if (data[1].value == "null" || !data[1].value) {
                    $scope.use_address = "常用地址";
                } else {
                    $scope.use_address = data[1].value;
                }
            },
            error: function (data) {
                console.log(data);
            }
        });
        /*历史地址设置*/
        $.ajax({
            url: "https://www.lerlex.com/lexiu/api/user/historyaddress",
            type: "get",
            dataType: "jsonp",
            jsonp: "jsonpcallback",
            success: function (data) {
                console.log(data);
                var Obj = eval('(' + data[1].value + ')');
                console.log(Obj);
                $scope.products = Obj;
                console.log($scope.products);
            }
        });
        //清除历史记录
        $scope.clear=function(){
            var confirmPopup=$ionicPopup.confirm({
                template: '你确定清除历史记录?',
                okText: '确定',
                cancelText: '取消'
            });
            confirmPopup.then(function (res) {
                if (res) {
                    $ionicPopup.alert({
                        title: '提示',
                        template: '清除成功',
                        okText: '确定'
                    });
                    //获取登录账号
                    $scope.usename=(localStorage.getItem("phone"));
                    /*$.ajax({
                     url: "",
                     type: "get",
                     data:({

                     }),
                     dataType: "jsonp",
                     jsonp: "jsonpcallback",
                     success: function (data) {
                     if(data[0].value==null || data[0].value=="成功"){
                     username:$scope.usename
                     }
                     }
                     })*/
                }
            });
        };
        /*点击常用地址跳转到预订页面*/
        $scope.send_btn1 = function () {
            $scope.new_address = comment_address.innerHTML;
            search($scope.new_address);
        };
        /*点击历史地址跳转到预订页面*/
        $scope.send_btn = function () {
            var index = this.$index;
            $scope.new_address = $scope.products[index].address;
            search($scope.new_address)
        }
        $scope.map4 = function () {
            //添加标记
            var point = new BMap.Point(longitudes, longitudes);//默认 可以通过Icon类来指定自定义图标
            /*var myIcon1 = new BMap.Icon("images/green.png", new BMap.Size(23,25));
             var marker = new BMap.Marker(point, {icon: myIcon1});*/
            var label = new BMap.Label("<div class='timers'></div>", {offset: new BMap.Size(20, -10)});//标注标签
            label.setStyle({
                border: "none",
                width: "70px",
                height: "35px",
                lineHeight: "15px",
                fontSize: "15px",
                textAlign: "center",
                borderRadius: "5px",
                marginLeft: "5px",
                background: "url(images/map_box.png) no-repeat",
                backgroundSize: "100% 100%",
                padding: "6px",
                textIndent: "1vw"
            })
            mk.setLabel(label)//设置标注说明
            /*map.addOverlay(mk);*/
            //给标注设置样式
            var timer = $interval(function () {
                var oLabel = $document.find(".BMapLabel")
                if (!oLabel) return;
                $interval.cancel(timer);
                console.log(oLabel);
                oLabel.css("left", "-30px");
                oLabel.css("top", "-36px");
            }, 1);
            map3.hide();
            map4.show();
            tk.hide();
            quxiao.hide();
            console.log(longitude);
            console.log(latitude);
            //创建订单
            $.ajax({
                /*https://www.lerlex.comurl: "https://www.lerlex.com/lexiu/api/order/createorder",*/
                url: "https://www.lerlex.com/lexiu/api/order/createorder",
                data: ({
                    address: $scope.new_address,
                    worktype: $scope.type,
                    longitude: longitudes,
                    latitude: latitudes
                }),
                type: "get",
                dataType: "jsonp",
                jsonp: "jsonpcallback",
                success: function (data) {
                    console.log(data);
                    console.log($scope.type);
                    if (data.message == null || data.message == "成功") {
                        var Obj = eval('(' + data[1].value + ')');
                        $scope.orderlist = Obj.orderid;
                        console.log($scope.orderlist);
                        timer1 = $interval(function () {
                            $.ajax({
                                url: "https://www.lerlex.com/lexiu/api/order/orderdetail",
                                data: ({
                                    orderid: $scope.orderlist
                                }),
                                type: "get",
                                dataType: "json",
                                success: function (data) {
                                    console.log(data)
                                    console.log(data.order.workerid);
                                    var obj = data.order.workerid
                                    //判断是否已被接单
                                    if (obj != "" || obj != 0) {
                                        $interval.cancel(timer1);
                                        $interval.cancel(order_timers);
                                        tk.show();
                                        $scope.number = 2;
                                        var timer = $interval(function () {
                                            $scope.number--;
                                            if ($scope.number == 0) {
                                                map.removeOverlay(mk);
                                                tk.hide();
                                                map4.hide();
                                                map5.show();
                                                $interval.cancel(timer);
                                                $interval.cancel(order_timers);
                                                $scope.avatar = "https://www.lerlex.com/" + data.worker.avatar;
                                                $scope.nickname = data.worker.nickname;
                                                $scope.userphone = data.worker.phone;
                                                $scope.skill = data.worker.skill;
                                                //我的位置图标
                                                var point1 = new BMap.Point(longitudes, latitudes);
                                                var myIcon1 = new BMap.Icon("images/green.png", new BMap.Size(23, 25));
                                                var marker1 = new BMap.Marker(point1, {
                                                    icon: myIcon1
                                                });
                                                var label1 = new BMap.Label("我的位置", {
                                                    offset: new BMap.Size(20, -10)
                                                }); //标注标签
                                                label1.setStyle({
                                                    border: "none",
                                                    width: "100px",
                                                    height: "40px",
                                                    lineHeight: "32px",
                                                    fontSize: "15px",
                                                    textAlign: "center",
                                                    borderRadius: "5px",
                                                    marginLeft: "5px",
                                                    background: "url(images/map_border.png) no-repeat",
                                                    backgroundSize: "100% 100%",
                                                    padding: "6px",
                                                    textIndent: "1vw"
                                                })
                                                marker1.setLabel(label1) //设置标注说明
                                                map.addOverlay(marker1);
                                                //师傅的位置图标
                                                var my_longitude = data.gps.longitude;
                                                var my_latitude = data.gps.latitude;
                                                var point2 = new BMap.Point(my_longitude, my_latitude); //默认 可以通过Icon类来指定自定义图标
                                                console.log(point2)
                                                var myIcon2 = new BMap.Icon("images/red.png", new BMap.Size(23, 25));
                                                var marker2 = new BMap.Marker(point2, {
                                                    icon: myIcon2
                                                });
                                                var label2 = new BMap.Label("师傅的位置", {
                                                    offset: new BMap.Size(20, -10)
                                                }); //标注标签
                                                label2.setStyle({
                                                    border: "none",
                                                    width: "100px",
                                                    height: "40px",
                                                    lineHeight: "32px",
                                                    fontSize: "15px",
                                                    textAlign: "center",
                                                    borderRadius: "5px",
                                                    marginLeft: "5px",
                                                    background: "url(images/map_border.png) no-repeat",
                                                    backgroundSize: "100% 100%",
                                                    padding: "6px",
                                                    textIndent: "1vw"
                                                })
                                                marker2.setLabel(label2) //设置标注说明

                                                var points = [point1, point2];
                                                var view = map.getViewport(eval(points));
                                                var mapZoom = view.zoom;
                                                var centerPoint = view.center;
                                                map.centerAndZoom(centerPoint, mapZoom - 1);

                                                map.addOverlay(marker2);
                                                $scope.distance = ((map.getDistance(point1, point2)) / 1000).toFixed(1) + '公里';
                                                console.log($scope.distance)
                                                //判断师傅是否取消订单
                                                timer2 = $interval(function () {
                                                    $.ajax({
                                                        url: "https://www.lerlex.com/lexiu/api/order/orderdetail",
                                                        data: ({
                                                            orderid: $scope.orderlist
                                                        }),
                                                        type: "get",
                                                        dataType: "json",
                                                        success: function (data) {
                                                            if (data.order.delflg == 1) {
                                                                $ionicPopup.alert({
                                                                    title: '提示',
                                                                    template: '很抱歉，师傅由于某种原因取消订单,请重新下单',
                                                                    okText: '确定'
                                                                });
                                                                Map.show();
                                                                map5.hide();
                                                                map.removeOverlay(marker1);
                                                                map.removeOverlay(marker2);
                                                                newmap();
                                                                //清除五分钟自动取消定时器
                                                                $interval.cancel(order_timers);
                                                                //清除s师傅是否取消定时器
                                                                $interval.cancel(timer2);

                                                            }
                                                        }
                                                    })
                                                }, 1000);
                                                //判断订单是否已被师傅报结
                                                var timer3 = $interval(function () {
                                                    $.ajax({
                                                        url: "https://www.lerlex.com/lexiu/api/order/orderdetail",
                                                        data: ({
                                                            orderid: $scope.orderlist
                                                        }),
                                                        type: "get",
                                                        dataType: "json",
                                                        success: function (data) {
                                                            if (data.order.type == 2) {
                                                                $ionicPopup.alert({
                                                                    title: '提示',
                                                                    template: '订单已报结，请在订单页支付',
                                                                    okText: '确定'
                                                                });
                                                                $interval.cancel(timer3);
                                                                Map.show();
                                                                map5.hide();
                                                                map.removeOverlay(marker1);
                                                                map.removeOverlay(marker2);
                                                                newmap();
                                                            }
                                                        }
                                                    })
                                                }, 10000)
                                            }
                                        }, 1000);
                                    }
                                }
                            })
                        }, 5000)
                    }
                },
                error: function (data) {
                    console.log(data);
                }
            });
            //五分钟订单未抢，默认取消
            var oTimer = document.getElementsByClassName("timers")[0];
            var order_timer = 300;
            oTimer.innerHTML = "05:00";
            order_timers = $interval(function () {
                order_timer--;
                if (order_timer > 0) {
                    var m = (String)(Math.floor(order_timer / 60));
                    var s = (String)(order_timer % 60);
                    if (m.length < 2) m = "0" + m;
                    if (s.length < 2) s = "0" + s;
                    oTimer.innerHTML = m + ":" + s;
                    return;
                } else {
                    $interval.cancel(timer1);
                    $interval.cancel(order_timers);
                    Map.show();
                    quxiao.hide();
                    map1.hide();
                    map3.hide();
                    map4.hide();
                    map5.hide();
                    revise_address.hide();
                    map.clearOverlays();
                    newmap();
                    $http({
                        url: "https://www.lerlex.com/lexiu/api/order/cancelorder",
                        type: "post",
                        dataType: "jsonp",
                        params: {
                            orderid: $scope.orderlist,
                            reason: "下单超时"
                        }
                    }).success(function (data) {
                        console.log(data);
                        $scope.type=1;
                        if (data.message == null || data.message == "成功") {
                            $ionicPopup.alert({
                                title: '提示',
                                template: '暂无师傅接单，请重新下单',
                                okText: '确定'
                            });
                            var oLabel = $document.find(".BMapLabel").eq(1);
                            oLabel.hide();
                            $interval.cancel(order_timers)
                        } else {
                            $ionicPopup.alert({
                                title: '提示',
                                template: data.message,
                                okText: '确定'
                            });
                        }
                    });
                }
            }, 1000);
        };
        /*取消订单中点错了按钮返回判断*/
        $scope.save = function () {
            /*window.history.back() */            //返回上一页
            if (n == 0) {
                map1.hide();
                map3.hide();
                quxiao.hide();
            } else {
                map5.show();
                map4.hide();
                quxiao.hide();
            }
        };
        /*取消订单原因*/
        var content;
        var persons = $document.find(".cancelOrder .item")
        /*console.log("persons.length")*/
        persons[0].addClass = "active";
        persons.each(function () {
            $(this).on("click", function () {
                $(this).addClass("active").siblings().removeClass("active");
            })
        });
        $scope.submit_reason = function () {
            $scope.new_address="";
            content = $document.find(".cancelOrder .active").text();
            if (content == null || content == "" && $scope.other_reason == null || $scope.other_reason == "") {
                $ionicPopup.alert({
                    title: '提示',
                    template: '请输入原因',
                    okText: '确定'
                });
            } else {
                if ($scope.other_reason == "" || $scope.other_reason == null) {
                    $scope.contents = content;
                } else {
                    $scope.contents = $scope.other_reason
                }
                console.log($scope.contents);
                $http({
                    url: "https://www.lerlex.com/lexiu/api/order/cancelorder",
                    type: "post",
                    dataType: "jsonp",
                    params: {
                        orderid: $scope.orderlist,
                        reason: $scope.contents
                    }
                }).success(function (data) {
                    console.log(data);
                    $scope.type=1;
                    if (data.message == null || data.message == "成功") {
                        $interval.cancel(order_timers);
                        $interval.cancel(timer1);
                        $interval.cancel(timer2);
                        map.removeOverlay(mk.getLabel());
                        $ionicPopup.alert({
                            title: '提示',
                            template: '取消成功',
                            okText: '确定'
                        });
                        quxiao.hide();
                        Map.show();
                        map4.hide();
                        var oLabel = $document.find(".BMapLabel").eq(1);
                        oLabel.hide();
                        newmap();
                        map5.hide();
                    } else {
                        $ionicPopup.alert({
                            title: '提示',
                            template: data.message,
                            okText: '确定'
                        });
                    }
                });
            }
        };
        /*map3*/
        map.enableScrollWheelZoom(true);
        //录音文件
        $scope.isrec = false;
        $scope.isOk2 = false;
        //长按录制声音
        $scope.rec_voice = function(){
            $scope.isrec = true;
            window.plugins.audioRecorderAPI.record(function(msg) {
                // complete
                console.log('ok: ' + msg);
            }, function(msg) {
                // failed
                console.log('ko: ' + msg);
            },10);
        };
        $scope.endrec_voice = function() {
            //$scope.isrec = false;
            /*window.plugins.audioRecorderAPI.stop(function (message) {
                $.ajax({
                    url: "https://www.lerlex.com/lexiu/api/order/onloadvoice",
                    data: ({
                        orderid: $scope.orderlist,
                        voice: "file:///"+message
                    }),
                    type: "get",
                    dataType: "json",
                    success: function () {
                    }
                });
            }, function (msg) {
                $ionicPopup.alert({
                    title: '提示',
                    template: '请设置录音权限',
                    okText: '确定'
                });
                // failed
                console.log('ko: ' + msg);
                $scope.isrec = false;
                $scope.$digest();
            })*/
            /*window.plugins.audioRecorderAPI.stop(function (message) {
                alert(message);

            }, function (msg) {
                $ionicPopup.alert({
                    title: '提示',
                    template: '请设置录音权限',
                    okText: '确定'
                });
                // failed
                console.log('ko: ' + msg);
                $scope.isrec = false;
                $scope.$digest();
            });
            //复制文件
            window.plugins.audioRecorderAPI.record(function(savedFilePath) {
                var fileName = savedFilePath.split('/')[savedFilePath.split('/').length - 1];
                //alert(fileName);
                var directory;
                if (cordova.file.documentsDirectory) {
                    directory = cordova.file.documentsDirectory; // for iOS
                } else {
                    directory = cordova.file.externalRootDirectory; // for Android
                    //alert(directory);
                }
                //alert(cordova.file.dataDirectory);
                $cordovaFile.copyFile(
                    cordova.file.dataDirectory, fileName,
                    directory, "new_file.m4a"
                    )
                    .then(function (success) {
                        alert(JSON.stringify(success));
                    }, function (error) {
                        alert(JSON.stringify(error));
                    });
            }, function(msg) {
                alert('ko: ' + msg);
            }, 3);
            */
            window.plugins.audioRecorderAPI.stop(function(file) {
                $scope.isrec = false;
                /*alert(file);*/
                var options = new FileUploadOptions();
                options.fileKey = "file";
                options.fileName = file.substr(file.lastIndexOf('/') + 1);
                var ft = new FileTransfer();
                ft.onprogress = function(progressEvent){
                    //console.log("上传中");
                    //console.log(progressEvent.loaded);
                };
                var params = {
                    orderid:$scope.orderlist
                };
                options.params = params;
                ft.upload('file:///' + file, encodeURI("https://www.lerlex.com/lexiu/api/order/onloadvoice"), function(re){
                        //console.log(JSON.stringify(re));
                        //console.log(re.responseCode);
                        var status = JSON.parse(re.response).status;
                        //console.log("aa:" + status);
                        if (status == 1){
                            //上传成功
                            //$scope.isrec = false;
                            //$scope.isOk = true;
                            //$scope.isOk2 = true;
                            //$scope.$digest();
                            //console.log("上传完成");
                            alert('Success');
                            //console.log($scope.isOk);
                            //var voice = JSON.parse(re.response).data;
                            //console.log(voice);
                            //$scope.chat.ct = voice;
                            //$scope.chat.tag = "voice";
                            //$scope.send();
                            //$scope.sendMsg_button = true;
                            //$scope.sendMsg_input = true;
                        }
                    }, function(e){
                        alert(JSON.stringify(e));
                        alert('Failure');
                    }, options
                );
            }, function () {
                $ionicPopup.alert({
                    title: '提示',
                    template: '请设置录音权限',
                    okText: '确定'
                });
            });
        }
    });
