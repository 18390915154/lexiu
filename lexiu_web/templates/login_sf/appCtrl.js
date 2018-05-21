angular.module("myapp")
	.controller("login_sfCtrl", function($scope,$stateParams, $state, $http, $ionicPopup) {
        var workid = $stateParams.workid;
		$scope.login = function() {
			if(!$scope.uname){
				$ionicPopup.alert({
					title: '提示',
					template: '请输入账号',
					okText: '确定'
				});
			}else if(!$scope.upassd){
				$ionicPopup.alert({
					title: '提示',
					template: '请输入密码',
					okText: '确定'
				});
			}else{
				$.ajax({
					url: "https://www.lerlex.com/lexiu/api/user/login",
					data: ({
						phone: $scope.uname,
						password: $scope.upassd,
						type: 1
					}),
					type: "get",
					dataType: "jsonp",
					jsonp: "jsonpcallback",
					success: function(data) {
						/*localStorage.setItem($scope.unames,"dsdsdsdsdsdsd");*/
						localStorage.setItem("phone",$scope.uname);
						localStorage.setItem("upassd",$scope.upassd);
						localStorage.setItem("types","1");
						console.log(data);
						//var workid = data[3].value;
						if(data[0].value == null || data[0].value == "成功" ) {
							if(data[3].value != 'null' && data[3].value!=""){
								//抢单页面
								/*$ionicPopup.alert({
								 title: '提示',
								 template: data[0].value, // String (可选)。放在弹窗body内的html模板。
								 okText: '确定' // String (默认: 'OK')。OK按钮的文字。
								 });*/
								$state.go("tabs_sf.home_s", {id: $scope.uname});
							}else{
								//完善资料页面
								$ionicPopup.alert({
									title: '提示',
									template: "请完善信息", // String (可选)。放在弹窗body内的html模板。
									okText: '确定', // String (默认: 'OK')。OK按钮的文字。
								});
								$state.go("id_sf",{workid:data[2].value});
							}
							//$scope.lotext = data.user.nickname;
							//console.log(data[0].value);
							//alert(data[1].value);
							//console.log(date);
						} else {
							$ionicPopup.alert({
								title: '提示',
								template: data[0].value, // String (可选)。放在弹窗body内的html模板。
								okText: '确定', // String (默认: 'OK')。OK按钮的文字。
							});
						}
						$scope.$apply();
					},
					error: function() {
						console.log('Error');
						$ionicPopup.alert({
							title: '提示',
							template: '请求超时',
							okText: '确定'
						});
					}
				})
			}
			//      账号密码是否正确
			//alert("登陆成功")
			//$state.go("tabs.home_sf")
			//alert($scope.uname);
			//          $http({
			//              url:"https://www.lerlex.com/lexiu/api/user/login?phone="
			//              +$scope.uname+"&password="+$scope.upassd+"&type=1",
			//
			//              type: "post",
			//              dataType: "json",
			//				//params:{phone:}
			//          }) .success( function(data) {
			//              console.log(data);
			//              if(data.message==null||data.message=="成功"){
			//                  $state.go("tabs_sf.home_sf",{id:$scope.uname}) ;
			//                  $scope.lotext=data.user.nickname;
			//                  /*$scope.spot=true;
			//                  $scope.lotext="已登录";*/
			//                  console.log(data.message);
			//              }else {
			//                  alert(data.message);
			//              }

			//})
			//ajax

		}

	});