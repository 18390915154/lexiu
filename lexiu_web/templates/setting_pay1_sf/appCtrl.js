angular.module("myapp")
	.controller("setting_pay2_sfCtrl", function($scope, $http, $state, $stateParams,$ionicPopup) {
		var idcode = $stateParams.idcode;
		var user = $stateParams.user;
		$scope.sub = function() {
			/*$http({
			    url: "https://www.lerlex.com/lexiu/api/user/editpaypassword",
			    params: {
			        phone: user,     //手机号
			        vcode: idcode,
			        password: $scope.zfpassd,  //密码
			        type: 1          //类型
			    },
			    type: "post",
			    dataType: "json"

            }).success(function (data) {
                if (data.message == null || data.message == "成功") {
                    console.log(data);
                    alert(data.message);
                    $state.go("safe_sf");
                } else {
                    alert(data.message);
                }

			})*/
			$.ajax({
				url: "https://www.lerlex.com/lexiu/api/user/editpaypassword",
				data: ({
					phone: user, //手机号
					vcode: idcode,
					password: $scope.zfpassd, //密码
					type: 1 //类型
				}),
				type: "post",
				dataType: "jsonp",
				jsonp: "jsonpcallback",
				success: function(data) {
					console.log(data);
					if(data.message == null || data.message == "成功") {
						$ionicPopup.alert({
							template: '修改成功', // String (可选)。放在弹窗body内的html模板。
							okText: '确定', // String (默认: 'OK')。OK按钮的文字。
						});
					} else {
						$ionicPopup.alert({
							template: '登录超时', // String (可选)。放在弹窗body内的html模板。
							okText: '确定', // String (默认: 'OK')。OK按钮的文字。
						});
					}
				},
				error: function() {
					console.log('Error');
				}
			});

        }
    });