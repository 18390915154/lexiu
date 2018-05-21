angular.module("myapp")
    .controller("aboutCtrl",function($scope){
        /*设置返回按钮*/
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });

    });