angular.module("myapp")
    .controller("todonedetailCtrl", function ($scope, $stateParams, $http, $state, $ionicPopup) {
        $scope.hidezf = false;
        $scope.hidepj = false;
        var orderid = $stateParams.orderid;
        console.log(orderid);
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
        //ajax
        $.ajax({
            url: "https://www.lerlex.com/lexiu/api/order/orderdetail",
            data: {
                orderid: orderid
            },
            type: "get",
            dataType: "json",
            success: function (data) {
                console.log(data);
                $scope.order = data.order;
                $scope.worker = data.worker;
                $scope.voice = data.order.voice;
                //听取留言
                $scope.radio="https://www.lerlex.com/"+$scope.voice;
                var audio = new Audio($scope.radio);
                console.log($scope.voice) ;
                $scope.play=function(){
                    audio.play();
                };
                if (data.message == null || data.message == "成功") {
                    var ortype = $scope.order.type;
                    if (ortype == 0) {
                        console.log(ortype);
                    } else if (ortype == 1) {
                        console.log(ortype);
                    } else if (ortype == 2) {
                        $scope.hidezf = false;
                        $scope.hidepj = true;
                        console.log(ortype);
                    } else if (ortype == 3) {
                        $scope.hidezf = true;
                        $scope.hidepj = false;
                        console.log(ortype);
                    } else if (ortype == 4) {
                        console.log(ortype);
                    }

                    $scope.goPay = function () {
                        $state.go("payment", {orderid: $scope.order.orderid});
                    };
                    $scope.goPing = function () {
                        $state.go("comment", {orderid: $scope.order.orderid});
                    };

                } else {
                    alert(data.message);
                }
                $scope.$apply();
            },
            error: function (data) {
                console.log(data);
                $ionicPopup.alert({
                    title: '',
                    template: '请求超时',
                    okText: '确定'
                });
            }
        });

    });