angular.module("myapp")
    .controller("donedetail_sfCtrl",function($scope, $stateParams,$http,$ionicPopup){
        var orderid = $stateParams.orderid;
        console.log(orderid);
        /*显示后退按钮*/
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
        $http({
            url: "https://www.lerlex.com/lexiu/api/worker/orderdetail",
            params: {
                orderid: orderid
            },
            type: "post",
            dataType: "json"
        }).success(function (data) {
            console.log(data);
            if (data.message == null || data.message == "成功") {
                $scope.orderdetail = data.orderdetail;
                $scope.assess = data.assess;
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
            alert("ok")
        })
        //ajax方法
       /* $.ajax({
         url: "https://www.lerlex.com/lexiu/api/order/orderdetail",
         data: ({
         orderid: orderid
         }),
         type: "get",
         dataType: "jsonp",
         jsonp:"jsonpcallback",
         success: function (data) {
         console.log(data);
         var Obj = eval('(' + data[1].value + ')');
         $scope.products = Obj;
         $scope.number = Obj.length;
         if (data.message == null || data.message == "成功") {
             $scope.order = data.order;
             $scope.worker = data.worker;
             $scope.assess = data.assess;
             console.log($scope.order);
             console.log($scope.worker);
             console.log($scope.assess);
         }
         },
         error: function () {
         console.log('Error');
         }
         });*/
    });