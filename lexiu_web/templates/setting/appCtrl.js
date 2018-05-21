angular.module("myapp")
    .controller("settingCtrl",function($scope, $state, $http, $stateParams,$ionicPopup){
        var id = $stateParams.id;

        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
        $scope.back4=function () {
            $state.go("tabs.person",{id:id});
        };

        $scope.exit=function () {
            localStorage.removeItem("phone");
            localStorage.removeItem("upassd");
            localStorage.removeItem("types");
            localStorage.removeItem("userid");
            $.ajax({
                url: "https://www.lerlex.com/lexiu/api/system/exituser",
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

        };
        //缓存大小
        //清除缓存
        $scope.clear_cache=function(){
            $ionicPopup.alert({
                title: '提示', // String. 弹窗的标题。
                template: '清除成功', // String (可选)。放在弹窗body内的html模板。
                okText: '确定' // String (默认: 'OK')。OK按钮的文字。
            });
        }

    });