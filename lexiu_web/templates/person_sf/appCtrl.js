angular.module("myapp")
	.controller("personal_sfCtrl", function($scope, $http, $state, $ionicPopup) {
		$scope.img="images/my.png";
		$.ajax({
			url: "https://www.lerlex.com/lexiu/api/worker/gominepage",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpcallback",
			success: function(data) {
				console.log(data);
				var Obj = eval('(' + data[1].value + ')');
				$scope.products = Obj;
				$scope.number = Obj.length;
				console.log(Obj);
				if(data.message == null || data.message == "成功") {
					if(Obj.avatar==""){
						$scope.img="images/my.png"
					}else{
						$scope.img ="https://www.lerlex.com/"+ Obj.avatar;
					}
					$scope.name = Obj.nickname;
					$scope.sex = Obj.sex;
					$scope.age = Obj.age;
					$scope.birthday = Obj.formatbirthday;
					$scope.phone = Obj.phone;
					$scope.soscontact = Obj.contactTel;
					$scope.idsfz = Obj.idcard;
					$scope.work = Obj.worktype;
					$scope.skill = Obj.skill;
				}else{
				$ionicPopup.alert({
					template: '登录超时', // String (可选)。放在弹窗body内的html模板。
					okText: '确定', // String (默认: 'OK')。OK按钮的文字。
				});
				}
				$scope.$apply();
			},
			error: function() {
				console.log('Error');
			}
		});
	})