angular.module("myapp")
    .controller("setting_pay2Ctrl",function($scope,$http,$state,$stateParams){
        var idcode = $stateParams.idcode;
        var user = $stateParams.user;
        $scope.sub=function(){
            /*$http({
                url:"https://www.lerlex.com/lexiu/api/user/editpaypassword",
                params:{
                    phone:user,     //手机号
                    vcode:idcode,
                    password:$scope.zfpassd,  //密码
                    type:0          //类型
                },
                type: "post",
                dataType: "json"

            }) .success( function(data) {
                if(data.message==null||data.message=="成功"){
                    console.log(data);
                    alert(data.message);
                    $state.go("safe");
                }else {
                    alert(data.message);
                }

            })*/
            $.ajax({
                url: "https://www.lerlex.com/lexiu/api/user/editpaypassword",
                data: ({
                    phone:user,     //手机号
                    vcode:idcode,
                    password:$scope.zfpassd,  //密码
                    type:0          //类型
                }),
                type: "post",
                dataType: "jsonp",
                jsonp:"jsonpcallback",
                success: function (data) {
                    console.log(data);
                    if (data.message == null || data.message == "成功") {
                        alert("修改成功");
                    }
                },
                error: function (data) {
                    console.log(data);
                }
            });
        };
        //ajax方法

    });