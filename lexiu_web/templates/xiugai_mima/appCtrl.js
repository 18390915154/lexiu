angular.module("myapp")
    .controller("setting_entryCtrl", function ($scope, $interval, $state, $http,$ionicPopup) {

        $scope.pattern = /^1[3|4|5|8]\d{9}$/;
        $scope.time = "获取验证码";
        $scope.spot = false;
        $scope.get = function () {
            if (!$scope.pattern.test($scope.user) && $scope.user.length > 0) {
                $ionicPopup.alert({
                    title: '提示',
                    template: '请输入正确的手机号',
                    okText: '确定'
                });
            } else if ($scope.forgetphone.length == 0) {
                $ionicPopup.alert({
                    title: '提示',
                    template: '手机号不能为空',
                    okText: '确定'
                });
            } else {
                $scope.number = 59;
                $scope.time = $scope.number + "s";
                var timer = $interval(function () {
                    $scope.number--;
                    if ($scope.number == 0) {
                        $interval.cancel(timer);
                        $scope.time = "重新获取";
                        $scope.spot = false;
                        return $scope.time;
                    }
                    $scope.time = $scope.number + "s";
                }, 1000);
                $scope.spot = true;
            }

            /*$http({
                url: "https://www.lerlex.com/lexiu/api/user/sendvcode?phone=" + $scope.user,
                type: "post",
                dataType: "json"
            }).success(function (data) {
                console.log(data);
            })*/
            $.ajax({
                url: "https://www.lerlex.com/lexiu/api/user/sendvcode?phone="+$scope.user,
                type: "post",
                dataType: "jsonp",
                jsonp:"jsonpcallback",
                success: function (data) {
                    console.log(data);
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

        };


        $scope.next = function () {
            console.log($scope.code);
            if ($scope.code == "" || $scope.code == null || $scope.code.length < 6) {
                alert("请输入正确的验证码");
            } else {
                $state.go("setting_entry2", {idcode: $scope.code, user: $scope.user});
            }

        }
    });