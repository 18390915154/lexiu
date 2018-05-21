angular.module("myapp")
	.controller("person_sfCtrl", function($scope, $stateParams, $http, $state, $ionicPopup) {
		$scope.myVal = false;
		$scope.img="images/my.png";
		var id = $stateParams.id;
		//$scope.id = id;
		if(id || id == "") {
			$scope.myVal = true;
		} else {
			$scope.myVal = false;
		};
		$.ajax({
			url: "https://www.lerlex.com/lexiu/api/worker/gominepage",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpcallback",
			success: function(data) {
				//alert(data[0].value)
				console.log(data);
				console.log(data.length);
				var Obj = eval('(' + data[1].value + ')');
				if(data.message == null || data.message == "成功") {
					//alert(data[0].value.nickname);
					console.log(data[0].value);
					$scope.name = Obj.nickname;
					if(Obj.avatar){
						$scope.img="https://www.lerlex.com/"+Obj.avatar;
					}else{
						$scope.img="images/my.png";
					}
				} else {
					console.log(data[0].value);
					//alert(data[0].value);
					$ionicPopup.alert({
						template: '登录超时', // String (可选)。放在弹窗body内的html模板。
						okText: '确定', // String (默认: 'OK')。OK按钮的文字。
					});
					$scope.name = "乐人乐修";
				}
				$scope.$apply();
			},
			error: function() {
				console.log('Error');
			}
		});
        //判断是否登录
        $scope.name = "乐人乐修";
//      if(id) {
//          $scope.name = id;
//      } else {
//          $scope.name = "乐人乐修";
//      }
		$scope.login = function() {
			if(!id) {
				$state.go("login_sf")
			}
		}
		$scope.goto=function () {
			$state.go("personal_sf");
		};
	});