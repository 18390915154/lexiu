angular.module("myapp")
    .controller("safe_sfCtrl",function($scope){
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
    })