angular.module("myapp")
	.controller("workorder_sfCtrl", function($scope,$state, $stateParams, $http, $ionicPopup) {
		var id = $stateParams.orderid;
		//返回待完成详情页
		$scope.jump=function(){
			$state.go("todonedetail_sf",{orderid:id})
		}

		$scope.submit = function() {
			//alert($scope.price)
			if($scope.content=="" || $scope.content==undefined){
				$ionicPopup.alert({
					title: '提示',
					template: "请输入维修内容",
					okText: '确定'
				});
			}else if($scope.fault=="" || $scope.fault==undefined){
				$ionicPopup.alert({
					title: '提示',
					template: "请输入故障详情",
					okText: '确定'
				});
			}else{
				$.ajax({
					url: "https://www.lerlex.com/lexiu/api/worker/retaliate",
					data: ({
						content: $scope.content, //服务内容
						fault: $scope.fault, //故障详情
						//price: $scope.price, //订单价格
						orderid: id //订单ID
					}),
					type: "get",
					dataType: "jsonp",
					jsonp: "jsonpcallback",
					success: function (data) {
						console.log(data);
						$ionicPopup.alert({
							title: '提示',
							template: "报结成功",
							okText: '确定'
						});
						$state.go("tabs_sf.order_sf.todone_sf")
					}
				});
			}

		}
	});