angular.module("myapp")
    .controller("shop_sfCtrl", function ($scope, $http, $stateParams, $ionicPopup) {
        var shopid = $stateParams.shopid;
        /*返回按钮*/
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
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
        //ajax
        $.ajax({
            url: "https://www.lerlex.com/lexiu/api/shop/shopdetail",
            data: ({
                shopid: shopid
            }),
            type: "get",
            dataType: "json",
            success: function (data) {
                console.log(data);
                $scope.pics=data.bannerlist;
                $scope.address = data.shop.address;
                $scope.tel = data.shop.tel;
                $scope.latitude = data.shop.latitude;
                $scope.goodlists = data.goodlist;
                $scope.products = data.bannerlist;
                //var Obj = eval('(' + data.bannerlist + ')');
				//$scope.products = Obj;
				console.log(data.bannerlist[0].picurl);
                if (data.message == null || data.message == "成功") {
                    /*$scope.address = data.shop.address;
                    $scope.tel = data.shop.tel;
                    $scope.latitude = data.shop.latitude;
                    $scope.goodlists = data.goodlist*/
                }else{
				$ionicPopup.alert({
					template: '登录超时', // String (可选)。放在弹窗body内的html模板。
					okText: '确定', // String (默认: 'OK')。OK按钮的文字。
				});
                }
            }
        });
    });