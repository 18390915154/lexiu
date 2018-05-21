angular.module("myapp")
    .controller("forgetCode2_sfCtrl", function ($scope, $http, $state, $stateParams,$ionicPopup) {
        var idcode = $stateParams.idcode;
        var user = $stateParams.user;
        $scope.submit = function () {
            var req = /^(\w|\d){6,16}$/;
            if (!req.test($scope.password1)) {
                $ionicPopup.alert({
                    title: '提示',
                    template: '密码格式不正确',
                    okText: '确定'
                });
            } else if ($scope.password1 !== $scope.password2) {
                $ionicPopup.alert({
                    title: '提示',
                    template: '两次密码不相同',
                    okText: '确定'
                });
            } else if ($scope.password1 == null || $scope.password1 == "" || $scope.password2 == null || $scope.password2 == "") {
                $ionicPopup.alert({
                    title: '提示',
                    template: '密码不能为空',
                    okText: '确定'
                });
            } else {
                /*$http({
                    url: "https://192.168.88.128:8080/lexiu/api/user/forgetPassword",
                    params: {
                        phone: user,     //手机号
                        vcode: idcode,
                        password: $scope.password1,  //密码
                        type: 1          //类型
                    },
                    type: "post",
                    dataType: "json"

                }).success(function (data) {
                    if (data.message == null || data.message == "成功") {
                        console.log(data);
                        alert(data.message);
                        $state.go("id_sf")
                    } else {
                        alert("修改失败");
                    }

                })*/
                $.ajax({
                    url: "https://www.lerlex.com/lexiu/api/user/forgetPassword",
                    data: ({
                        phone:user,     //手机号
                        vcode:idcode,
                        password:$scope.password1,  //密码
                        type:1         //类型
                    }),
                    type: "post",
                    dataType: "jsonp",
                    jsonp:"jsonpcallback",
                    success: function (data) {
                        console.log(data);
                        if (data.message == null || data.message == "成功") {
                            $ionicPopup.alert({
                                title: '提示',
                                template: '修改成功',
                                okText: '确定'
                            });
                            $state.go("id_sf");
                        }
                    },
                    error: function (data) {
                        console.log(data);
                    }
                });
            }

        }
    })