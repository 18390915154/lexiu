angular.module("myapp")
    .controller("setting_entry2Ctrl", function ($scope, $http, $state, $stateParams,$ionicPopup) {
        var idcode = $stateParams.idcode;
        var user = $stateParams.user;
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
        $scope.sub = function () {
            /*$http({
                url: "https://www.lerlex.com/lexiu/api/user/changePassword",
                params: {
                    phone: user,     //手机号
                    vcode: idcode,
                    password: $scope.newpassd,  //密码
                    type: 0          //类型
                },
                type: "post",
                dataType: "json"

            }).success(function (data) {
                if (data.message != null || data.message == "成功") {
                    console.log(data);
                    alert(data.message);
                    $state.go("safe");
                } else {
                    alert("修改失败");
                }

            })*/
            $.ajax({
                url: "https://www.lerlex.com/lexiu/api/user/changePassword",
                data: ({
                    phone:user,     //手机号
                    vcode:idcode,
                    password:$scope.zfpassd,  //密码
                    type:0          //类型
                }),
                type: "post",
                dataType: "jsonp",
                jsonp:"jsonpcallback",
                success: function (data) {
                    console.log(data);
                    if (data.message == null || data.message == "成功") {
                        $ionicPopup.alert({
                            title: '',
                            template: '修改成功',
                            okText: '确定'
                        });
                        $state.go("safe");
                    }
                },
                error: function (data) {
                    $ionicPopup.alert({
                        title: '',
                        template: '请求超时',
                        okText: '确定'
                    });
                    console.log(data);
                }
            });
        }


    })