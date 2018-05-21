angular.module("myapp")
	.controller("suggest_sfCtrl", function($scope, $ionicModal, $ionicPopup, $interval, $state) {
		$scope.$on('$ionicView.beforeEnter', function(event, viewData) {
			viewData.enableBack = true;
		});
		$ionicModal.fromTemplateUrl('my-modal.html', {
			scope: $scope,
			animation: 'slide-in-up'
		}).then(function(modal) {
			$scope.modal = modal;
		});
		$scope.openModal = function() {
			$scope.modal.show();
		};
		$scope.closeModal = function() {
			$scope.modal.hide();
		};
		/*problem类型*/
		$scope.order = function() {
			$scope.problem = "订单问题";
			$scope.modal.hide();
		};
		$scope.service = function() {
			$scope.problem = "服务问题";
			$scope.modal.hide();
		};
		$scope.app = function() {
			$scope.problem = "APP相关问题";
			$scope.modal.hide();
		};
		$scope.others = function() {
			$scope.problem = "其他问题";
			$scope.modal.hide();
		};

		if($scope.problem == "订单问题") {
			$scope.types = 0;
		} else if($scope.problem = "服务问题") {
			$scope.types = 1;
		} else if($scope.problem = "APP相关问题") {
			$scope.types = 2;
		} else if($scope.problem = "其他问题") {
			$scope.types = 3;
		}
		$scope.type = function() {
			$.ajax({
				url: "https://www.lerlex.com/lexiu/api/mine/savefeedback",
				data: ({
					type: $scope.types,
					content: $scope.content,
					code: 1
				}),
				type: "get",
				dataType: "jsonp",
				jsonp: "jsonpcallback",
				success: function(data) {
					console.log(data)
					if(data.message == null || data.message == "成功") {
						var show = $ionicPopup.alert({
							title: "",
							template: '<div class="modal2"><i class="fa fa-check"></i><div class="submit">提交成功(<span>3{{number}}</span>s)</div><p>我们的客服人员将在3个工作日内联系您，请耐心等待</p></div>',
							buttons: {
								text: ""
							}
						})
						$scope.number = 3;
						var timer = $interval(function() {
							$scope.number--;
							$scope.time = 3;
							if($scope.number == 0) {
								show.close(); //由于某种原因3秒后关闭弹出
								$interval.cancel(timer)
								$state.go("setting_sf")
							}
						}, 1000);
					} else {
						$ionicPopup.alert({
							template: '登录超时', // String (可选)。放在弹窗body内的html模板。
							okText: '确定', // String (默认: 'OK')。OK按钮的文字。
						});
					}
				}
			})

		}
	})