angular.module("myapp")
    .controller("rechargeCtrl",function($scope){
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });

    })