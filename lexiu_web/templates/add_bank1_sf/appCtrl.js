angular.module("myapp")
	.controller("add_bank1_sfCtrl", function($scope,$ionicPopup, $stateParams, $interval, $state, $http) {
		var bkname = $stateParams.bkname;
		var name = $stateParams.name;
		var card = $stateParams.card;
		//      手机正则表达式
		$scope.pattern = /^1[3|4|5|8]\d{9}$/;
		$scope.time = "获取验证码";
		$scope.spot = false;
		/*获取验证码,60倒计时*/
		$scope.get = function() {
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
			//      依赖注入$htttp协议;
			/*var url="https://192.168.100.116:8080/lexiu/api/user/sendvcode?phone="+$scope.user;*/

			//          var url="https://www.lerlex.com/lexiu/api/user/sendvcode?phone="+$scope.user;
			//          dataType: 'JSONP',
			//              $http.get(url)
			//                  .success( function(data) {
			//                      $scope.idcode = data;
			//                  })
			$.ajax({
				url: "https://www.lerlex.com/lexiu/api/user/sendvcode?phone=" + $scope.user,
				type: "post",
				dataType: "jsonp",
				success: function(data) {
					console.log(data);
				}
			});

		}
		//    next按钮
		$scope.ok = function() {
			//          console.log($scope.idcode)
			//          //        下一步跳转到register2_2
			//          if($scope.idcode.vcode==$scope.code){
			//              alert("验证码效验成功")
			//              $state.go("");
			//          }
			$.ajax({
				url: "https://www.lerlex.com/lexiu/api/worker/addbank",
				data: ({
					cardnumber: card,
					bankname: bkname,
					name: name,
					phone:$scope.user,
					vcode:$scope.code ,
				}),
				type: "get",
				dataType: "jsonp",
				jsonp:"jsonpcallback",
				success: function(data) {
					console.log(data);
					if(data[0].value == null || data[0].value == "成功") {
						$ionicPopup.alert({
							title: '提示',
							template: data[0].value,
							okText: '确定'
						});
					} else {
						$ionicPopup.alert({
							title: '提示',
							template: data[0].value,
							okText: '确定'
						});
					}
				},
				error: function(data) {
					$ionicPopup.alert({
						title: '提示',
						template: '请求超时',
						okText: '确定'
					});
					console.log(data);
				}
			});

		}

	})