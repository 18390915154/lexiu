angular.module("myapp")
    .controller("personCtrl",function($scope,$stateParams,$http,$state){
        $scope.name="乐人乐修";
        $scope.src="images/my.png";
        //请求用户数据
        $.ajax({
            url:"https://www.lerlex.com/lexiu/api/mine/gominepage",
            type: "get",
            dataType: "jsonp",
            jsonp:"jsonpcallback",
            success:function(data) {
                console.log(data);
                var Obj = eval('(' + data[1].value + ')');
                $scope.userid= Obj.userid;
                console.log(Obj);
                /*$scope.src="https://www.lerlex.com/"+Obj.avatar;*/
                if(Obj.avatar){
                    $scope.src="https://www.lerlex.com/"+Obj.avatar;
                }else{
                    $scope.src="images/my.png";
                }
                $scope.name=Obj.nickname;
                if(data[2].value == "" || data[2].value =="null"){
                	$(".lg").css('display','block');
                	$(".llg").css('display','none');
                }else{
                	$('.llg').css('display','block');
                	$(".lg").css('display','none');
                };
				console.log(data[2].value);
                $scope.$apply();
            },
            error:function (data) {
                $scope.name="乐人乐修";
                console.log(data);
                $scope.src="images/my.png"
            }
        });
        /*$scope.data=true;
        $scope.myVal=false;
        var id = $stateParams.id;
        $scope.id=id;
        if(id||id==""){
            $scope.myVal=true;
        }else{
            $scope.myVal=false;
        }*/
        /*$scope.login=function(){
            $state.go("login");
        };*/
        //点击头像进入
        $scope.goto=function () {
            $state.go("personal");
        };
        //进入钱包
        $scope.gtWallet=function () {
            $state.go("wallet");
        };
        //进入我的评价
        $scope.gtComment=function () {
            $state.go("comment1");
        };
        //进入注册零工
        $scope.gtId=function () {
            $state.go("id",{userid:$scope.userid});
        };
        //进入分享
        $scope.share=function () {
            $state.go("share");
        };
        //进入我的评价
        $scope.gtSetting=function () {
            $state.go("setting");
        };
    });