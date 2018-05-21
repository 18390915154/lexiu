/**
 * Created by My on 2017/3/6.
 */
angular.module("myapp")
	.controller("cancelOrder_sfCtrl", function($scope, $document ,$stateParams,$state,$http, $ionicPopup) {
		var id = $stateParams.id;
		$scope.$on('$ionicView.beforeEnter', function(event, viewData) {
			viewData.enableBack = true;
		});
		var person = $document.find(".item")
		console.log("person.length")
		person.each(function() {
			$(this).on("click", function() {
				$(this).addClass("active").siblings().removeClass("active");
			})
		})
		$scope.set = 1;
		var txt;
		$scope.Map = function() {
			if( $("ion-item:last").hasClass("active") == false ){
				txt = $('.active').text();
			}else if( $("ion-item:last").hasClass("active") == true ){
				txt = $("input[ type='text' ] ").val();
			}else{
				$ionicPopup.alert({
					title: '提示',
					template: '请写入完整信息', // String (可选)。放在弹窗body内的html模板。
					okText: '确定', // String (默认: 'OK')。OK按钮的文字。
				});
			}
//			$.ajax({
//				url: "https://www.lerlex.com/lexiu/api/order/cancelorder",
//				data: ({
//					orderid:id,
//					reason:txt,
//				}),
//				type: "get",
//				dataType: "jsonp",
//				jsonp: "jsonpcallback",
//				success: function(data) {
//					alert(id);
//					if(data[0].value == null || data[0].value == "成功") {
//						$ionicPopup.alert({
//							title: '提示',
//							template: '提交成功', // String (可选)。放在弹窗body内的html模板。
//							okText: '确定', // String (默认: 'OK')。OK按钮的文字。
//						});
//						$state.go("tabs_sf.home_sf");
//					} else {
//						$ionicPopup.alert({
//							title: '提示',
//							template: data[0].value, // String (可选)。放在弹窗body内的html模板。
//							okText: '确定', // String (默认: 'OK')。OK按钮的文字。
//						});
//					}
//				}
//			});
			$http({
				url: "https://www.lerlex.com/lexiu/api/order/cancelorder",
				type: "post",
				dataType: "jsonp",
				params: {
					orderid:id,
					reason:txt,
				}
			}).success(function(data) {
				console.log(data);
				if(data.message == null || data.message == "成功") {
					$ionicPopup.alert({
						title: '提示',
						template: data.message,
						okText: '确定'
					});
					$state.go("tabs_sf.home_s");
				} else{
					$ionicPopup.alert({
						title: '提示',
						template: "请选择原因",
						okText: '确定'
					});
				}
			});
		}
		$scope.Maps = function() {
			$.ajax({
				url: "https://www.lerlex.com/lexiu/api/worker/haveorder",
				data: ({
					orderid: id,
				}),
				type: "get",
				dataType: "jsonp",
				jsonp: "jsonpcallback",
				success: function(data) {
					console.log(data);
					console.log(data.length);
					if(data.message == null || data.message == "成功") {
						$state.go("map5_sf",{id:id});
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
			})
		}
	})