angular.module("myapp")
    .controller("add_bank_sfCtrl",function($scope,$state,$http,$stateParams, $ionicPopup){
    	var id = $stateParams.id;
		$scope.pattern = /^\d{16}|\d{19}$/;
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
        
        $scope.next=function(){
        //alert($scope.bankname);
            if($scope.bankname == null){
				$ionicPopup.alert({
					template: '请选择卡', // String (可选)。放在弹窗body内的html模板。
					okText: '确定' // String (默认: 'OK')。OK按钮的文字。
				});
            }else if($scope.name == null){
				$ionicPopup.alert({
					template: '请输入姓名', // String (可选)。放在弹窗body内的html模板。
					okText: '确定' // String (默认: 'OK')。OK按钮的文字。
				});
            }else if($scope.cardnumber==null || !$scope.pattern.test($scope.cardnumber)){
				$ionicPopup.alert({
					template: '请输入卡号', // String (可选)。放在弹窗body内的html模板。
					okText: '确定' // String (默认: 'OK')。OK按钮的文字。
				});
            }else{
            	$state.go("add_bank1_sf",{bkname:$scope.bankname,name:$scope.name,card:$scope.cardnumber});
            }
//	        $http({
//	            url: "https://192.168.88.130:8080/lexiu/api/worker/addbank",
//	            type: "post",
//	            dataType: "jsonp",
//	            params:{
//	            	cardnumber:	$scope.cardnumber,		//银行卡号
//	            	bankname:$scope.bankname,			//银行名
//	            	name:$scope.name,			//姓名
//	            	workerid:1 //师傅ID
//	            }
//	        }).success(function (data) {
//	            console.log(data);
//	            //alert($scope.cardnumber,$scope.bankname,$scope.name);
//	            if (data.message == null || data.message == "成功") {
//	            	alert("提交成功!!!");
//	            	$state.go("add_bank1_sf");
//	            }
//	        });
        }
    })