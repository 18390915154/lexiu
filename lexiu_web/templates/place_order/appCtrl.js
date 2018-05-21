angular.module('myapp')
    .controller("place_orderCtrl",function($scope,$stateParams,$ionicPopup,$ionicModal,$document,$interval,$state){
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
        $scope.company_longitude=$stateParams.company_longitude;
        $scope.company_latitude=$stateParams.company_latitude;
        $scope.userid=(localStorage.getItem("userid"));
        $scope.type=$stateParams.type;
        $scope.specific_address=$stateParams.new_address;
        $scope.latitude=$stateParams.latitude;
        $scope.longitude=$stateParams.longitude;
        $scope.companyid=$stateParams.companyid;
        /*接收时间控件*/
        var timer=$interval(function(){
            $scope.starttime=$document.find(".clock").html();
            if($scope.starttime){
                $interval.cancel(timer)
            }
        },1000);
        $scope.type_jz="家政便民-"+$scope.type;
        $scope.jump=function(){
            $scope.address=$scope.specific_address+$scope.specific_address_1;
            var add_pics=$(".add_pic").last();
            var input=add_pics.find("input").val();
            if(input==""){
                add_pics.remove();
            }
            if(!$scope.specific_address){
                $ionicPopup.alert({
                    title: '提示',
                    template: "请输入服务地址",
                    okText: '确定'
                });
            }else if(!$scope.specific_address_1){
                $ionicPopup.alert({
                    title: '提示',
                    template: "请完善单元及门牌号",
                    okText: '确定'
                });
            }else if(!$scope.starttime){
                $ionicPopup.alert({
                    title: '提示',
                    template: "请输入时间",
                    okText: '确定'
                });
            }/*else if(!$("#uploadFile").val()){
                $ionicPopup.alert({
                    title: '提示',
                    template: "请添加图片",
                    okText: '确定'
                });
                console.log($("#uploadFile").val())
            }*/else if(!$scope.detail){
                $ionicPopup.alert({
                    title: '提示',
                    template: "请输入备注说明",
                    okText: '确定'
                });
            }else{
                $("#typeid").val($scope.type);
                $("#address").val($scope.address);
                $("#starttime").val($scope.starttime);
                $("#detail").val($scope.detail);
                $("#longitude").val($scope.longitude);
                $("#latitude").val($scope.latitude);
                $("#userid").val($scope.userid);
                $("#companyid").val($scope.companyid);
                var form = new FormData(document.getElementById("place_order"));
                $.ajax({
                    url: "https://www.lerlex.com/lexiu/api/company/createorder",
                    type: "post",
                    data: form,
                    processData: false,
                    contentType: false,
					/*datetype:"jsonp",
					jsonp:"jsonpcallback",*/
                    success: function(data) {
                        console.log(data);
                        var obj  = eval('(' + data + ')');
                        console.log(obj);
                        if(obj.message== null || obj.message=="成功") {
                            $ionicPopup.alert({
                                title: '提示',
                                template: '下单成功',
                                okText: '确定'
                            });
                            $state.go("tabs.order.todone")
                        }else{
                            $ionicPopup.alert({
                                title: '提示',
                                template: '请完善信息',
                                okText: '确定'
                            });
                        }
                    }
                });
            }
        };
    });


