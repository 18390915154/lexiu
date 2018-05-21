angular.module("myapp")
    .controller("about_sfCtrl",function($scope){
        /*设置返回按钮*/
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
            
        });

    })                 