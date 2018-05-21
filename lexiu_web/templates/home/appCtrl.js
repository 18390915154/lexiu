angular.module("myapp")
    .controller("homeCtrl",function($scope,$state,$ionicPopup){
        $scope.goShop=function(){
            $state.go("tabs.shop");
        };
        /*$scope.expect=function(){
            $ionicPopup.alert({
                title: '提示',
                template: '敬请期待',
                okText: '确定'
            });
        };*/
        /*请求轮播图数据*/
        $.ajax({
            url: "https://www.lerlex.com/lexiu/api/user/homebannerlist",
            type: "get",
            dataType: "json",
            success: function (data) {
                console.log(data);
                $scope.pics=data.bannerlist;
                $scope.pic0='https://www.lerlex.com/'+$scope.pics[0].picurl;
                $scope.pic1='https://www.lerlex.com/'+$scope.pics[1].picurl;
                $scope.pic2='https://www.lerlex.com/'+$scope.pics[2].picurl;
                $scope.$apply()
            }
        })

    });