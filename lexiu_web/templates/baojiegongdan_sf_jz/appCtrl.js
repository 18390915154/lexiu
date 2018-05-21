angular.module("myapp")
	.controller("workorder_sf_jzCtrl", function($scope,$state, $stateParams, $http, $ionicPopup) {
		var id = $stateParams.orderid;
		$scope.jump=function(){
			$state.go("todonedetail_sf",{orderid:id})
		}

		$scope.submit = function() {
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
			}else if($scope.price=="" || $scope.price==undefined){
				$ionicPopup.alert({
					title: '提示',
					template: "请输入金额",
					okText: '确定'
				});
			}else{
				$.ajax({
					url: "https://www.lerlex.com/lexiu/api/worker/retaliate",
					data: ({
						content: $scope.content, //服务内容
						fault: $scope.fault, //故障详情
						price: $scope.price, //故障详情
						//price: $scope.price, //订单价格
						orderid: id //订单ID
					}),
					type: "get",
					dataType: "jsonp",
					jsonp: "jsonpcallback",
					success: function (data) {
						console.log(data);
						if(data[0].value==null || data[0].value=="成功"){
							$ionicPopup.alert({
								title: '提示',
								template: "报结成功",
								okText: '确定'
							});
							$state.go("tabs_sf.order_sf.todone_sf")
						}else{
							$ionicPopup.alert({
								title: '提示',
								template: "请输入完整信息",
								okText: '确定'
							});
						}
					}
				});
			}
		}
	});