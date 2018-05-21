angular.module("myapp")
    .controller("shopCtrl", function ($scope, $http, $stateParams,$ionicPopup) {
        var shopid = $stateParams.shopid;
        $scope.distance = $stateParams.distance;
        /*返回按钮*/
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
        //$http方法
        /*$http({
         url: "https://www.lerlex.com/lexiu/api/shop/shopdetail",
         params: {
         shopid: shopid
         },
         type: "post",
         dataType: "json"
         }).success(function (data) {
         console.log(data);
         if (data.message == null || data.message == "成功") {
         $scope.address = data.shop.address;
         $scope.tel = data.shop.tel;
         $scope.latitude = data.shop.latitude;
         $scope.goodlists = data.goodlist
         }

         })*/
        //ajax方法
        $.ajax({
            url: "https://www.lerlex.com/lexiu/api/shop/shopdetail",
            data: ({
                shopid: shopid
            }),
            type: "get",
            dataType: "json",
            success: function (data) {
                console.log(data);
                console.log(data.bannerlist);
                console.log(data.goodlist);
                console.log(data.shop);
                $scope.pics=data.bannerlist;
                $scope.address = data.shop.address;
                $scope.tel = data.shop.tel;
                $scope.latitude = data.shop.latitude;
                $scope.goodlists = data.goodlist;
                $scope.$apply();
                if (data.message == null || data.message == "成功") {

                }
            },
            error:function (data) {
                $ionicPopup.alert({
                    title: '',
                    template: '请求超时',
                    okText: '确定'
                });
            }
        });

    });