angular.module("myapp")
	.controller("register2_2_sfCtrl", function($scope, $state, $http, $stateParams, $ionicPopup) {
		var idcode = $stateParams.idcode;
		var user = $stateParams.user;
		$scope.submit = function() {
			var req = /^(\w|\d){6,16}$/;
			if(!req.test($scope.password1)) {
				$ionicPopup.alert({
					template: '密码格式不正确', // String (可选)。放在弹窗body内的html模板。
					okText: '确定', // String (默认: 'OK')。OK按钮的文字。
				});
			} else if($scope.password1 !== $scope.password2) {
				$ionicPopup.alert({
					template: '密码不一致', // String (可选)。放在弹窗body内的html模板。
					okText: '确定', // String (默认: 'OK')。OK按钮的文字。
				});
			} else if($scope.password1 == null || $scope.password1 == "" || $scope.password2 == null || $scope.password2 == "") {
				$ionicPopup.alert({
					template: '密码不能为空', // String (可选)。放在弹窗body内的html模板。
					okText: '确定', // String (默认: 'OK')。OK按钮的文字。
				});
			} else {
				/*$http({
				    url: "https://www.lerlex.com/lexiu/api/user/userRegister",
				    params: {
				        phone: user,     //手机号
				        vcode: idcode,
				        password: $scope.password1,  //密码
				        type: 1          //类型
				    },
				    type: "post",
				    dataType: "json"
				}).success(function (data) {
				    if(data.message==null||data.message=="成功"){
				        $state.go("id_sf");
				    }else {
				        alert(data.message);
				    }
                })*/
                /*ajax方法*/
				$.ajax({
					url: "https://www.lerlex.com/lexiu/api/user/userRegister",
					data: ({
						phone:user,
						vcode:idcode,
						password:$scope.password1,
						type:1
					}),
					type: "get",
					dataType: "jsonp",
					jsonp: "jsonpcallback",
					success: function(data) {
						console.log(data)
						if(data[0].value == null || data[0].value == "成功") {
                            localStorage.setItem("phone",user);
                            localStorage.setItem("upassd",$scope.password1);
                            localStorage.setItem("types","1");
							$ionicPopup.alert({
								title:'提示',
								template: '注册成功',
								okText: '确定', // String (默认: 'OK')。OK按钮的文字。
							});
							$state.go("id_sf");
						} else {
							//alert(data.message);
							$ionicPopup.alert({
								title:'提示',
								template: data[0].value, // String (可选)。放在弹窗body内的html模板。
								okText: '确定', // String (默认: 'OK')。OK按钮的文字。
							});
							$state.go("register2_sf")
						}
					},
					error:function(data){
						console.log(data)
					}
				});
			}


        }
    }) ;