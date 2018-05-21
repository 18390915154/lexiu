angular.module("myapp")
	.controller("home_sfCtrl", function($scope, $document, $state, $http, $ionicPopup) {
		$.ajax({
			url: "https://www.lerlex.com/lexiu/api/worker/getorder",
			type: "get",
			dataType: "jsonp",
			jsonp: "jsonpcallback",
			success: function(data) {
				console.log(data);
				console.log(data.length);
				var Obj = eval('(' + data[1].value + ')');
				if(data[0].value == "null" || data[0].value == "成功") {
					$scope.product = Obj;
					console.log($scope.product);
					//实时定位获取到的经纬度
					var longitude=localStorage.getItem("longitude");
					var latitude=localStorage.getItem("latitude");
					angular.forEach($scope.product,function(data,index){
						//调用百度地图插件
						var map = new BMap.Map;
						var point=new BMap.Point(data.longitude,data.latitude);
						var point_my=new BMap.Point(longitude,latitude);
						$scope.distance= map.getDistance(point,point_my).toFixed(1);
						console.log($scope.distance);
						if(map.getDistance(point,point_my).toFixed(1)<=3000){
							$scope.products=[];
							$scope.products.push(data);
							console.log("三公里以内的单"+$scope.products);
						}
					})
				} else {
					$ionicPopup.alert({
						title: '提示',
						template:data[0].value, // String (可选)。放在弹窗body内的html模板。
						okText: '确定' // String (默认: 'OK')。OK按钮的文字。
					});
					$state.go("tabs_sf.order_sf.todone_sf")
				}
				$scope.$apply();
			},
			error: function() {
				console.log('Error');
			}
		});
		$scope.map5 = function() {
			var index = this.$index;
			var orderids = $(".li").eq(index).text();
			$.ajax({
				url: "https://www.lerlex.com/lexiu/api/worker/haveorder",
				data: ({
					orderid: orderids,
				}),
				type: "get",
				dataType: "jsonp",
				jsonp: "jsonpcallback",
				success: function(data) {
					console.log(data);
					console.log(data.length);
					if(data.message == null || data.message == "成功") {
						$state.go("map5_sf", {
							id: orderids
						});
					} else {
						$ionicPopup.alert({
							title: '提示',
							template: data[0].value, // String (可选)。放在弹窗body内的html模板。
							okText: '确定', // String (默认: 'OK')。OK按钮的文字。
						});
					}
				},
				error: function() {
					console.log('Error');
				}
			})
		};
		//返回休息页面
		$scope.rest = function() {
			$state.go("tabs_sf.home_s");
		}
	});