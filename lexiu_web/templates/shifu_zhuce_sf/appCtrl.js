angular.module("myapp")
    .controller("register2_sfCtrl", function ($scope,$stateParams, $interval, $state, $http, $ionicPopup) {
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
//      手机正则表达式
        /*$scope.pattern = /^1[3|4|5|8]\d{9}$/;*/
        $scope.pattern = /^1\d{10}$/;
        $scope.time = "获取验证码";
        $scope.spot = false;
        /*获取验证码,60倒计时*/
        $scope.get = function () {
            if (!$scope.pattern.test($scope.user) && $scope.user.length > 0) {
				$ionicPopup.alert({
					template: '请输入正确的手机号', // String (可选)。放在弹窗body内的html模板。
					okText: '确定', // String (默认: 'OK')。OK按钮的文字。
				});
            } else if ($scope.user.length == 0) {
				$ionicPopup.alert({
					template: '手机号不能为空', // String (可选)。放在弹窗body内的html模板。
					okText: '确定', // String (默认: 'OK')。OK按钮的文字。
				});
            } else {
                $scope.number = 60;
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
            //ajax
            $.ajax({
                url:"https://www.lerlex.com/lexiu/api/user/sendvcode?phone="+$scope.user,
                type: "post",
                dataType: "jsonp",
                success: function (data) {
                    console.log(data);
                }
            });

        };

        $scope.next = function () {
            console.log($scope.code);
            if ($scope.code == "" || $scope.code == null || $scope.code < 6) {
				$ionicPopup.alert({
					template: '请输入正确的验证码', // String (可选)。放在弹窗body内的html模板。
					okText: '确定', // String (默认: 'OK')。OK按钮的文字。
				});
            } else {
                $state.go("register2_2_sf", {idcode: $scope.code, user: $scope.user});
            }

        }


    })