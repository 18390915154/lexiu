angular.module("myapp")
    .controller("add_bank1Ctrl",function($scope,$interval,$state,$http){
//      手机正则表达式
        $scope.pattern=/^1[3|4|5|8]\d{9}$/;
        $scope.time="获取验证码";
        $scope.spot=false;
        /*获取验证码,60倒计时*/
        $scope.get=function(){
            /*alert(typeof $scope.user)*/
            if(!$scope.pattern.test($scope.user) && $scope.user.length>0){
                alert("请输入正确的手机号")
            }else if($scope.user.length==0){
                alert("手机号不能为空")
            }else{
                $scope.number=60;
                $scope.time=$scope.number+"s";
                var timer=$interval(function(){
                    $scope.number--
                    if($scope.number==0){
                        $interval.cancel(timer);
                        $scope.time="重新获取";
                        $scope.spot=false;
                        return $scope.time;
                    }
                    $scope.time=$scope.number+"s";
                },1000)
                $scope.spot=true;
            }
            //      依赖注入$htttp协议;
            /*var url="https://192.168.100.116:8080/lexiu/api/user/sendvcode?phone="+$scope.user;*/

            var url="https://www.lerlex.com/lexiu/api/user/sendvcode?phone="+$scope.user;
            dataType: 'JSONP',
                $http.get(url)
                    .success( function(data) {
                        $scope.idcode = data;
                    })

        }
        //    next按钮
        $scope.next=function(){
            console.log($scope.idcode)
            //        下一步跳转到register2_2
            if($scope.idcode.vcode==$scope.code){
                alert("验证码效验成功")
                $state.go("");
            }

        }

    })