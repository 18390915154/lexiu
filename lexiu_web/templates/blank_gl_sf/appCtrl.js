angular.module("myapp")
	.controller("bank_manage_sfCtrl", function($scope, $ionicModal, $ionicPopup, $stateParams, $http) {
		var id = $stateParams.id;
		/*设置返回按钮*/
		$scope.$on('$ionicView.beforeEnter', function(event, viewData) {
			viewData.enableBack = true;
		});
		//      $scope.banks = [
		//          {img: "images/ny.png", type: "中国农业银行", card: "储蓄卡",card_number:"9520"},
		//          {img: "images/js.png", type: "中国建设银行", card: "储蓄卡",card_number:"9520"},
		//          {img: "images/gs.png", type: "中国工商银行", card: "储蓄卡",card_number:"9520"}
		//      ];

		//		$http({
		//			url: "https://192.168.88.130:8080/lexiu/api/worker/mybank?workerid=1",
		//			type: "post",
		//			dataType: "json"
		//		}).success(function(data) {
		//			console.log(data);
		//			if(data.message == null || data.message == "成功") {
		//				$scope.banks = data.cardlist;
		//				//$scope.number = data.shoplist.length;
		//				// 删除一个item
		//				$scope.delete = function(bank) {
		//					var index=this.$index;
		//					var cardids = $scope.banks[index].cardid;
		//					//alert(cardids);
		//					//alert($scope.banks[index].cardid);
		//					$http({
		//						url: "https://192.168.88.130:8080/lexiu/api/worker/delbank",
		//						type: "post",
		//						dataType: "json",
		//						params: {cardid:cardids}
		//
		//					}).success(function(data) {
		//						console.log(data);
		//						if(data.message == null || data.message == "成功") {
		//							$state.go("bank_manage_sf");
		//							console.log(data.message);
		//						} else {
		//							alert(data.message);
		//						}
		//
		//					})
		//
		//				};
		//			}
		//
		//		});
		$.ajax({
			url: "https://www.lerlex.com/lexiu/api/worker/mybank",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpcallback",
			success: function(data) {
				console.log(data);
				console.log(data.length);
				var Obj = eval('(' + data[1].value + ')');
				$scope.banks = Obj;
				console.log(Obj);
				if(data[0].value == null || data[0].value == "成功") {
					//$state.go("");
				}
			},
		});
		// 删除一个item
		$scope.delete = function(bank) {
			var index = this.$index;
			var cardids = $scope.banks[index].cardid;
			// 先找到要删除对象在数组中的索引位置
			//var index = $scope.banks.indexOf(bank);
			// 执行删除操作
			//$scope.banks.splice(index,1);
			//var index = this.$index;
			//alert(index);
			//			$http({
			//				url: "https://192.168.88.130:8080/lexiu/api/worker/delbank",
			//				type: "post",
			//				dataType: "json",
			//				params: {
			//					cardid:
			//				}
			//
			//			}).success(function(data) {
			//				console.log(data);
			//				if(data.message == null || data.message == "成功") {
			//					//var index = $scope.banks.indexOf(bank);
			//					//$scope.banks.splice(index,1);
			//					console.log(data.message);
			//				} else {
			//					alert(data.message);
			//				}
			//
			//			})
			$.ajax({
				url: "https://www.lerlex.com/lexiu/api/worker/delbank",
				data: ({
					cardid: cardids,
				}),
				type: "get",
				dataType: "json",
				success: function(data) {
					console.log(data);
					console.log(data.message);
					if(data.message == null || data.message == "成功") {
						$ionicPopup.alert({
							title: '提示',
							template: data.message,
							okText: '确定'
						});
						$state.go("bank_manage_sf");
					} else {
						$ionicPopup.alert({
							title: '提示',
							template: data.message,
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

		};
	})