angular.module("myapp")
	.controller("comment1_sfCtrl", function ($scope, $state, $http, $stateParams,$ionicPopup) {
		var id = $stateParams.id;
		$scope.back2=function () {
			$state.go("tabs_sf.person_sf");
		};
		//ajax
		$.ajax({
			url:"https://www.lerlex.com/lexiu/api/worker/myassess",
			type: "get",
			dataType: "jsonp",
			jsonp:"jsonpcallback",
			success: function (data) {
				console.log(data);
				var Obj1 = eval('(' + data[1].value + ')');
				console.log(Obj1);
				$scope.products=Obj1;
				// 准备五星数据
				$scope.starArr = [
					{value: 1, icon: "ion-ios-star-outline"},
					{value: 2, icon: "ion-ios-star-outline"},
					{value: 3, icon: "ion-ios-star-outline"},  // val: 3   index: 2
					{value: 4, icon: "ion-ios-star-outline"},
					{value: 5, icon: "ion-ios-star-outline"}
				];
				$scope.newArray=[];
				angular.forEach($scope.products,function(data,index){
					/*$(".all .icon").eq(data.grade).removeClass("ion-ios-star-outline").addClass("ion-ios-star");*/
					if(data.grade<=1){
						$scope.how="非常不满意";
					}else if(data.grade<=2){
						$scope.how="不满意";
					}else if(data.grade<=3){
						$scope.how="满意";
					}else if(data.grade<=4){
						$scope.how="很满意";
					}else if(data.grade<=5){
						$scope.how="非常满意";
					}
					data.how=$scope.how;

					data.newArray=[];
					for(var i=1;i<=data.grade;i++){
						$scope.new=[];
						$scope.new[i]={icon:"ion-ios-star"};
						data.newArray[i]=$scope.new[i];
					};
					for(var i=data.grade+1;i<=5;i++){
						$scope.new1=[];
						$scope.new1[i]={icon:"ion-ios-star-outline"};
						data.newArray[i]=$scope.new1[i];
					}
				});
				$scope.$apply();
				if (data.message== null || data.message== "成功") {

				}
			},
			error:function (data) {
				$ionicPopup.alert({
					title: '',
					template: '请求超时',
					okText: '确定'
				});
				console.log(data);
			}

		})

	});