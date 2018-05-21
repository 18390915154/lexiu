angular.module("myapp")
    .controller("donedetailCtrl", function ($scope, $stateParams, $http,$ionicPopup) {
        var orderid = $stateParams.orderid;
        console.log(orderid);
        /*显示后退按钮*/
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
        //$http方法
        $http({
            url: "https://www.lerlex.com/lexiu/api/order/orderdetail",
            params: {
                orderid: orderid
            },
            type: "post",
            dataType: "json"
        }).success(function (data) {
            console.log(data);
            if (data.message == null || data.message == "成功") {
                $scope.order = data.order;
                $scope.worker = data.worker;
                $scope.assess = data.assess;
                console.log($scope.voice);
                console.log($scope.order);
                console.log($scope.worker);
                console.log($scope.assess);
            } else {
                $ionicPopup.alert({
                    title: '',
                    template: data.message,
                    okText: '确定'
                });
            }

        }).error(function (data) {
            $ionicPopup.alert({
                title: '',
                template: '请求超时',
                okText: '确定'
            });
            console.log(data);
        });

    });