angular.module("myapp")
    .controller("add_bankCtrl",function($scope,$state){
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
        $scope.next=function(){
            $state.go("add_bank1");
        }
    });