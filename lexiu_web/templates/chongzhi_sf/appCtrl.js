angular.module("myapp")
    .controller("recharge_sfCtrl",function($scope){
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });

    })