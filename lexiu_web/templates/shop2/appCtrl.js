angular.module("myapp")
    .controller("shop2Ctrl", function($scope, $http, $ionicPopup) {
        $.ajax({
            url: "https://www.lerlex.com/lexiu/api/shop/nearbyshop",
            type: "get",
            dataType: "jsonp",
            jsonp: "jsonpcallback",
            success: function(data) {
                if(data.message == null || data.message == "成功") {
                    var Obj = eval('(' + data[0].value + ')');
                    $scope.products = Obj;
                    console.log($scope.products);
                    $scope.number = Obj.length;
                    console.log($scope.number);
                    distance();
                } else {
                    $ionicPopup.alert({
                        template: '登录超时', // String (可选)。放在弹窗body内的html模板。
                        okText: '确定' // String (默认: 'OK')。OK按钮的文字。
                    });
                }
            },
            error: function() {
                console.log('Error');
            }
        });
        $scope.doRefersh = function() {
            //ajax方法
            $.ajax({
                url: "https://www.lerlex.com/lexiu/api/shop/nearbyshop",
                type: "get",
                dataType: "jsonp",
                jsonp: "jsonpcallback",
                success: function(data) {
                    console.log(data);
                    var Obj = eval('(' + data[0].value + ')');
                    $scope.products = Obj;
                    console.log($scope.products);
                    $scope.number = Obj.length;
                    distance();
                    if(data.message == null || data.message == "成功") {
                        $scope.$broadcast("scroll.refreshComplete");
                    }
                },
                error: function() {
                    console.log('Error');
                }
            })
        };
        //计算我与商家之间的距离
        function distance(){
            var geoCtrl = new BMap.Geolocation();
            geoCtrl.getCurrentPosition(function(result){
                if(this.getStatus() == BMAP_STATUS_SUCCESS) {
                    longitude = result.point.lng;//获取到的经度
                    latitude = result.point.lat;//获取到的纬度
                    var map = new BMap.Map;
                    console.log($scope.products);
                    angular.forEach($scope.products,function(data,index,array){
                        var point=new BMap.Point(data.longitude,data.latitude);
                        var point1=new BMap.Point(longitude,latitude);
                        $scope.distance=parseInt(map.getDistance(point,point1).toFixed(1));
                        console.log($scope.distance);
                        data.distance=$scope.distance;
                    });
                    $scope.$apply();
                }
            });
        }
    });