angular.module("myapp")
	.controller("map5_sfCtrl", function($scope,$ionicPopup,$state,$http,$stateParams,$interval,$state) {
		var orderid = $stateParams.id;
		/*alert(id);*/

		$('.sj1').delay(600000).hide(0);
		$('.sj').delay(600000).show(0);
		//alert(interval)
		// 百度地图API功能
		var map = new BMap.Map("allmap5", { // 创建地图实例
			enableHighResolution: true //是否开启高清
		});
		var point = new BMap.Point(120.135489, 30.319016); // 创建点坐标
		map.centerAndZoom(point, 15);
		map.setCurrentCity("杭州"); // 设置地图显示的城市 此项是必须设置的
		map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
		var longitude=localStorage.getItem("longitude");
		var latitude=localStorage.getItem("latitude");
		$http({
			url: "https://www.lerlex.com/lexiu/api/order/orderdetail",
			params: {
				orderid: orderid
			},
			type: "post",
			dataType: "json"
		}).success(function (data) {
			console.log(data);
			if(data.message == null || data.message == "成功") {
				$scope.yh_longitude=data.order.longitude;
				$scope.yh_latitude=data.order.latitude;
				$scope.username=data.order.username;
				$scope.userphone=data.order.userphone;
				$scope.address=data.order.address;
				$scope.voice = data.order.voice;
				if(data.order.avatar=="" || data.order.avatar==null){
					$scope.avatar="images/shifu.jpg"
				}else{
					$scope.avatar="https://www.lerlex.com/"+ data.order.avatar;
				}
				var point2 = new BMap.Point($scope.yh_longitude,$scope.yh_latitude);
				var myIcon = new BMap.Icon("images/red.png", new BMap.Size(23, 25));
				var marker2 = new BMap.Marker(point2, {icon: myIcon});
				var label2 = new BMap.Label("用户的位置", {offset: new BMap.Size(20, -10)}); //标注标签
				label2.setStyle({
					border: "none",
					width: "100px",
					height: "40px",
					lineHeight: "32px",
					fontSize: "15px",
					textAlign: "center",
					borderRadius: "5px",
					marginLeft: "5px",
					background: "url(images/map_border.png) no-repeat",
					backgroundSize: "100% 100%",
					padding: "6px",
					textIndent: "1vw"
				});
				marker2.setLabel(label2) ;//设置标注说明
				map.addOverlay(marker2);

				var point_my = new BMap.Point(longitude,latitude);
				var icon = new BMap.Icon("images/green.png", new BMap.Size(23, 25));
				var mk = new BMap.Marker(point_my, {icon: icon});//创建一个覆盖物
				var label = new BMap.Label("我的位置", {offset: new BMap.Size(20, -10)}); //标注标签
				label.setStyle({
					border: "none",
					width: "100px",
					height: "40px",
					lineHeight: "32px",
					fontSize: "15px",
					textAlign: "center",
					borderRadius: "5px",
					marginLeft: "5px",
					background: "url(images/map_border.png) no-repeat",
					backgroundSize: "100% 100%",
					padding: "6px",
					textIndent: "1vw"
				});
				mk.setLabel(label) //设置标注说明
				map.addOverlay(mk);//增加一个标示到地图上
				//距离
				$scope.distance=((map.getDistance(point_my,point2))/1000).toFixed(1)+'公里';
				var points = [point_my, point2];
				var view = map.getViewport(eval(points));
				var mapZoom = view.zoom;
				var centerPoint = view.center;
				map.centerAndZoom(centerPoint,mapZoom-1);
				console.log(centerPoint);
				console.log(mapZoom);

				$.ajax({
					url: "https://www.lerlex.com/lexiu/api/worker/updategps",
					data:({
						longitude:longitude,
						latitude:latitude
					}),
					type: "get",
					dataType: "jsonp",
					jsonp: "jsonpcallback",
					success: function(data) {
						console.log(data)
						console.log(longitude)
						console.log(latitude)
					},
					error: function(data) {
						console.log(data)
					}
				})
				//判断用户是否取消订单
				timer2=$interval(function(){
					$.ajax({
						url: "https://www.lerlex.com/lexiu/api/order/orderdetail",
						data: ({
							orderid: orderid
						}),
						type: "get",
						dataType: "json",
						success:function(data){
							console.log(data)
							if(data.order.delflg==1){
								$ionicPopup.alert({
									title: '提示',
									template: '很抱歉，用户由于某种原因取消订单,请重新抢单',
									okText: '确定'
								});

								$interval.cancel(timer2);
								map.removeOverlay(mk);
								map.removeOverlay(marker2);
								$state.go("tabs_sf.home_s")
							}
						}
					})
				},2000);
				//听取留言
				$scope.radio="https://www.lerlex.com/"+$scope.voice;
				var audio = new Audio($scope.radio);
				console.log($scope.voice) ;
				$scope.play=function(){
					audio.play();
				};
			}else{
				$ionicPopup.alert({
					title: '提示',
					template: data.message, // String (可选)。放在弹窗body内的html模板。
					okText: '确定', // String (默认: 'OK')。OK按钮的文字。
				});
			}
		});
		//取消订单
		$scope.cancel = function(){
			$state.go("cancelOrder_sf",{id:orderid});
			$interval.cancel(timer2)
		};

		/*$.ajax({
				url: "https://www.lerlex.com/lexiu/api/worker/orderdetail",
				data:({

					}),
				type: "get",
				dataType: "jsonp",
				jsonp: "jsonpcallback",
				success: function(data) {
					console.log(data);
					console.log(data.length);
					var Obj = eval('(' + data[1].value + ')');
					$scope.products = Obj;
					console.log(Obj);
					if(data.message == null || data.message == "成功") {
						
					}else{
						$ionicPopup.alert({
							title: '提示',
							template: data.message, // String (可选)。放在弹窗body内的html模板。
							okText: '确定', // String (默认: 'OK')。OK按钮的文字。
						});
					}
				},
				error: function() {
					console.log('Error');
				}
			})*/


	});