angular.module("myapp")
    .controller("register2_2Ctrl", function ($scope, $state, $http, $stateParams, $ionicPopup) {
        var user = $stateParams.user;
        var idcode = $stateParams.idcode;
        console.log(idcode);
        $scope.submit = function () {
            var req = /^(\w|\d){6,16}$/;
            if (!req.test($scope.password1)) {
                $ionicPopup.alert({
                    title: '',
                    template: '密码格式不正确',
                    okText: '确定'
                });
            } else if ($scope.password1 !== $scope.password2) {
                $ionicPopup.alert({
                    title: '',
                    template: '密码不一致',
                    okText: '确定'
                });
            } else if ($scope.password1 == null || $scope.password1 == "" || $scope.password2 == null || $scope.password2 == "") {
                $ionicPopup.alert({
                    title: '',
                    template: '密码不能为空',
                    okText: '确定'
                });
            } else {
                /*$http方法*/
                /*$http({
                 url: "https://www.lerlex.com/lexiu/api/user/userRegister",
                 params: {
                 phone: user,     //手机号
                 vcode: idcode,
                 password: $scope.password1,  //密码
                 type: 0          //类型
                 },
                 type: "post",
                 dataType: "jsonp"
                 }).success(function (data) {
                 if(data.message==null||data.message=="成功"){
                 $ionicPopup.alert({
                 template: '注册成功'
                 });
                 $state.go("tabs.person");
                 }else {
                 alert(data.message);
                 }

                 });*/
                /*ajax方法*/
                $.ajax({
                    url: "https://www.lerlex.com/lexiu/api/user/userRegister",
                    data: ({
                        phone: user,
                        vcode: idcode,
                        password: $scope.password1,
                        type: 0
                    }),
                    type: "get",
                    dataType: "jsonp",
                    jsonp: "jsonpcallback",
                    success: function (data) {
                        console.log(data)
                        if (data[0].value == null || data[0].value == "成功") {
                            localStorage.setItem("phone",user);
                            localStorage.setItem("upassd",$scope.password1);
                            localStorage.setItem("types","0");
                            $ionicPopup.alert({
                                title: '',
                                template: '注册成功',
                                okText: '确定'
                            });
                            $state.go("tabs.person");
                        } else {
                            $ionicPopup.alert({
                                title: '',
                                template: data[0].value,
                                okText: '确定'
                            });
                        }
                    },
                    error: function (data) {
                        console.log(data);
                        $ionicPopup.alert({
                            title: '',
                            template: '请求超时',
                            okText: '确定'
                        });

                    }
                });
            }


        }
    });