angular.module("myapp")
    .controller("setting_sfCtrl",function($scope,$ionicPopup,$state){
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });

        $scope.exit=function () {
            localStorage.removeItem("phone");
            localStorage.removeItem("upassd");
            localStorage.removeItem("types");
            $.ajax({
                url: "https://www.lerlex.com/lexiu/api/system/exitworker",
                type: "post",
                success: function(data) {
                    $ionicPopup.alert({
                        title: '', // String. 弹窗的标题。
                        template: '退出成功', // String (可选)。放在弹窗body内的html模板。
                        okText: '确定' // String (默认: 'OK')。OK按钮的文字。
                    });
                    $state.go("tour1");
                }
            });
        }
        //清除缓存
        $scope.clear_cache=function(){
            $ionicPopup.alert({
                title: '提示', // String. 弹窗的标题。
                template: '清除成功', // String (可选)。放在弹窗body内的html模板。
                okText: '确定' // String (默认: 'OK')。OK按钮的文字。
            });
        }
    });