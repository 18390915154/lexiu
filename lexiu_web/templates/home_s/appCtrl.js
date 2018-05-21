angular.module("myapp")
	.controller("home_sCtrl", function($scope, $state,$ionicPopup) {
		$scope.qd_btn = function() {
			$.ajax({
				url: "https://www.lerlex.com/lexiu/api/worker/getorder",
				type: "get",
				dataType: "jsonp",
				jsonp: "jsonpcallback",
				success: function (data) {
					console.log(data);
					var Obj = eval('(' + data[1].value + ')');
					if(data[0].value=="null" || data[0].value=="成功"){
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
							if(map.getDistance(point,point_my).toFixed(1)<=5000){
								$scope.products=[];
								$scope.products.push(data);
								console.log("三公里以内的单"+$scope.products);
							}
						});
						if(data[1].value == "" || $scope.products==undefined){
							$ionicPopup.alert({
								title: '提示',
								template:'暂无订单可抢', // String (可选)。放在弹窗body内的html模板。
								okText: '确定' // String (默认: 'OK')。OK按钮的文字。
							});
						}else{
							$state.go("tabs_sf.home_sf");
						}
					}else{
						$ionicPopup.alert({
							title: '提示',
							template:data[0].value, // String (可选)。放在弹窗body内的html模板。
							okText: '确定' // String (默认: 'OK')。OK按钮的文字。
						});
					}
				}
			})

		}
	})