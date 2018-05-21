angular.module("myapp")
	.controller("idCtrl", function($scope, $document,$stateParams, $ionicModal, $http, $state, $ionicPopup) {
		var userid = $stateParams.userid;
		console.log(userid) ;
		$scope.$on('$ionicView.beforeEnter', function(event, viewData) {
			viewData.enableBack = true;
		});
		$.ajax({
			url: "https://www.lerlex.com/lexiu/api/system/selecttype",
			type: "get",
			dataType: "json",
			success: function(data){
				$scope.workers=data.typelist;
				console.log($scope.workers);
			}
		});
		/*擅长技能多选遍历*/
		var all;
		var b;
		var ability = $document.find(".ability").find(".col")
		ability.each(function() {
			$scope.n = 0;
			$(this).on("click", function() {
				if($scope.n == 0) {
					$(this).addClass("active");
					$scope.n = 1;
				} else if($scope.n == 1) {
					$(this).removeClass("active");
					$scope.n = 0;

				}
			})
		})
		/*日期下拉框*/
		//$scope.date = 1976.10;
		/*性别下拉框*/
		$ionicModal.fromTemplateUrl('sex.html', {
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
		$scope.man = function() {
			$scope.people = "男";
			$scope.modal.hide();
		}
		$scope.woman = function() {
			$scope.people = "女";
			$scope.modal.hide();
		}
		/*工种下拉框*/
		$ionicModal.fromTemplateUrl('work.html', {
			scope: $scope,
			animation: 'slide-in-up'
		}).then(function(modal) {
			$scope.modal2 = modal;
		});
		$scope.openModal2 = function() {
			$scope.modal2.show();
		};
		$scope.closeModal2 = function() {
			$scope.modal2.hide();
		};
		$scope.work = function($index) {
			$scope.work_number=$scope.workers[$index].typeid;
			$scope.work_name=$scope.workers[$index].name;
			/*$scope.work_number =this.$index+1;*/
			console.log($scope.work_number);
			console.log($scope.work_name);
			$scope.modal2.hide();
		};
		/*身份证正反面加载图片*/
		$scope.id_front = function() {
			alert("请添加身份证正面照片")
		};
		$scope.id_opposite = function() {
			alert("请添加身份证反面照片")
		};
		/*提交按钮*/
		$scope.submit = function() {
			if(!$scope.nickname || !$scope.people || !$scope.phone || !$scope.id_card || !$scope.work) {
				$ionicPopup.alert({
					title: '提示',
					template: '不能为空',
					okText: '确定'
				});
			} else {
				var UUserCard = $("#UUserCard").val();
				var birthday = UUserCard.substring(6, 10) + "-" + UUserCard.substring(10, 12) + "-" + UUserCard.substring(12, 14);
				//txt = $document.find(".active");
				/*all=txt.eq(0).text()+","+txt.eq(2).text()+","+txt.eq(3).text();*/
				all = $document.find(".id .active").text();
				//console.log(test);
				$("#skill").val(all);
				console.log(all);
				$("#birthday").val(birthday);
				console.log(birthday);
				$("#sex").val($scope.people);
				console.log($scope.people);
				$("#worktype").val($scope.work_number);
				$("#userid").val(userid);
				var form = new FormData(document.getElementById("id_sf"));
				$.ajax({
					url: "https://www.lerlex.com/lexiu/api/mine/UserToWorker",
					type: "post",
					data: form,
					processData: false,
					contentType: false,
					success: function(data){
						console.log(data)
						var obj=JSON.parse(data);
						console.log(obj);
						if(obj.message == null || obj.message == "成功"){
							$ionicPopup.alert({
								title: '提示',
								template: obj.message,
								okText: '确定'
							});
							$state.go("tabs.person");
						}
					},
					error: function(data) {
						//alert("错误！！");
						console.log(data);
						$ionicPopup.alert({
							title: '提示',
							template: data.message,
							okText: '确定'
						});
					}
				});
				//get(); //此处为上传文件的进度条
			}
		}
	});