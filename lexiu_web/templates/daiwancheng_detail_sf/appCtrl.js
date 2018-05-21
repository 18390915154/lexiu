angular.module("myapp")
	.controller("todonedetail_sfCtrl", function($scope, $stateParams, $http, $state, $ionicPopup) {
		$scope.plays = false;
		$scope.time = "报结";
		$scope.$on('$ionicView.beforeEnter', function(event, viewData) {
			viewData.enableBack = true;
		});
		var orderid = $stateParams.orderid;
		console.log(orderid)
		$http({
			url: "https://www.lerlex.com/lexiu/api/order/orderdetail",
			params: {
				orderid: orderid
			},
			type: "post",
			dataType: "json"
		}).success(function(data) {
			console.log(data);
			//alert(data.order.type)
			$scope.order = data.order;
			$scope.order1 = data.worker;
			$scope.voice=data.order.voice;
			if(data.order.type == 1) {
				$scope.knot = function() {
					if(data.order.companyid=="" || data.order.companyid==0){
						$state.go("workorder_sf", {
							orderid: orderid
						})
					}else{
						$state.go("workorder_sf_jz", {
							orderid: orderid
						})
					}

				}
			} else {
				$scope.plays = true;
				$scope.time = "已报结"
			}
			//听取留言
			$scope.radio="https://www.lerlex.com/"+$scope.voice;
			var audio = new Audio($scope.radio);
			console.log($scope.voice) ;
			$scope.play=function(){
				audio.play();
			};
		});
	});