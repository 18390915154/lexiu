angular.module("myapp")
    .controller("jz_companyCtrl", function ($scope,$ionicPopup,$stateParams) {
        //ajax方法
        $scope.type=$stateParams.type;
        console.log($scope.type);
        $.ajax({
            url: "https://www.lerlex.com/lexiu/api/company/nearbycompany",
            data:({
                type:$scope.type
            }),
            type: "get",
            dataType: "jsonp",
            jsonp:"jsonpcallback",
            success: function (data) {
                console.log(data);
                var Obj = eval('(' + data[1].value + ')');
                $scope.pruducts = Obj;
                $scope.number =Obj.length;
                console.log($scope.pruducts);
                distance();
                $scope.$apply()
            },
            error: function (data) {
                $ionicPopup.alert({
                    title: '',
                    template: '请求超时',
                    okText: '确定'
                });
                console.log(data);
            }
        });
        // 下拉刷新
        $scope.doRefersh = function () {
         //ajax方法
             $.ajax({
                url: "https://www.lerlex.com/lexiu/api/company/nearbycompany",
                 data:({
                     type:$scope.type
                 }),
                type: "get",
                dataType: "jsonp",
                jsonp:"jsonpcallback",
                success: function (data) {
                    var Obj = eval('(' + data[1].value + ')');
                    $scope.pruducts = Obj;
                    $scope.number = Obj.length;
                    console.log($scope.pruducts);
                    $scope.number = Obj.length;
                    distance();
                    if (data.message == null || data.message == "成功") {
                        $scope.$broadcast("scroll.refreshComplete");
                     }
                 },
                error: function (data) {
                    console.log("Error");
                    console.log(data);
                    $ionicPopup.alert({
                        title: '',
                        template: '请求超时',
                        okText: '确定'
                    });
                }
             });
         };
        //计算我与师傅之间的距离
        function distance(){
            var geoCtrl = new BMap.Geolocation();
            geoCtrl.getCurrentPosition(function(result){
                if(this.getStatus() == BMAP_STATUS_SUCCESS) {
                    longitude = result.point.lng;//获取到的经度
                    latitude = result.point.lat;//获取到的纬度
                    var map = new BMap.Map;
                    angular.forEach($scope.pruducts,function(data,index,array){
                        var point=new BMap.Point(data.longitude,data.latitude);
                        var point1=new BMap.Point(longitude,latitude);
                        $scope.distance= parseInt(map.getDistance(point,point1).toFixed(1)) +'公里';
                        console.log($scope.distance);
                        data.distance=$scope.distance;
                    });
                    console.log($scope.pruducts);
                }
            });
        }

    });