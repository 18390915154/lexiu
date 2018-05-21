angular.module("myapp")
	.controller("setting_pay_sfCtrl", function($scope, $interval, $state, $http, $ionicPopup) {

		$scope.pattern = /^1[3|4|5|8]\d{9}$/;
		$scope.time = "获取验证码";
		$scope.spot = false;
		$scope.get = function() {
			if(!$scope.pattern.test($scope.user) && $scope.user.length > 0) {
				$ionicPopup.alert({
					template: '请输入正确的手机号', // String (可选)。放在弹窗body内的html模板。
					okText: '确定', // String (默认: 'OK')。OK按钮的文字。
				});
			} else if($scope.user.length == 0) {
				$ionicPopup.alert({
					template: '手机号不能为空', // String (可选)。放在弹窗body内的html模板。
					okText: '确定', // String (默认: 'OK')。OK按钮的文字。
				});
			} else {
				$scope.number = 59;
				$scope.time = $scope.number + "s";
				var timer = $interval(function() {
					$scope.number--;
					if($scope.number == 0) {
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
                url:"https://www.lerlex.com/lexiu/api/user/sendvcode?phone="+$scope.user,
                type: "post",
                dataType: "json"
            }) .success( function(data) {
                console.log(data);
            })*/
            $.ajax({
                url: "https://www.lerlex.com/lexiu/api/user/sendvcode?phone="+$scope.user,
                data: ({

                }),
                type: "post",
                dataType: "jsonp",
                jsonp:"jsonpcallback",
                success: function (data) {
                    console.log(data);
                },
                error: function (data) {
                    console.log(data);
                }
            });

        };

		$scope.next = function() {
			console.log($scope.code);
			if($scope.code == "" || $scope.code == null || $scope.code.length < 6) {
				$ionicPopup.alert({
					template: '请输入正确的验证码', // String (可选)。放在弹窗body内的html模板。
					okText: '确定', // String (默认: 'OK')。OK按钮的文字。
				});
				
			} else {
				$state.go("setting_pay2_sf", {
					idcode: $scope.code,
					user: $scope.user
				});
			}

        }
    });