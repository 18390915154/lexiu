angular.module("myapp")
    .controller("payment_sfCtrl",function($scope){
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
    })