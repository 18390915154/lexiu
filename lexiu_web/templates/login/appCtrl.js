angular.module("myapp")
    .controller("loginCtrl",function($scope,$state,$ionicPopup,$rootScope){
        $scope.login=function(){
            if(!$scope.uname){
                $ionicPopup.alert({
                    title: '提示',
                    template: '请输入账号',
                    okText: '确定'
                });
            }else if(!$scope.upassd){
                $ionicPopup.alert({
                    title: '提示',
                    template: '请输入密码',
                    okText: '确定'
                });
            }else{
                $.ajax({
                    url:"https://www.lerlex.com/lexiu/api/user/login",
                    data:({
                        phone:$scope.uname,
                        password:$scope.upassd,
                        type:"0"
                    }),
                    type: "get",
                    dataType: "jsonp",
                    jsonp:"jsonpcallback",
                    success:function(data) {
                        localStorage.setItem("phone",$scope.uname);
                        localStorage.setItem("upassd",$scope.upassd);
                        localStorage.setItem("types","0");
                        localStorage.setItem("userid",data[2].value);
                        console.log(data);
                        console.log(data[2].value);
                        /*var Obj = eval('(' + data[1].value + ')');*/
                        if(data[0].value==null||data[0].value=="成功"){
                            if(data[4].value=="{}" || data[4].value==null){
                                $state.go("tabs.home") ;
                            }else{
                                var obj= eval('(' + data[4].value + ')');
                                var obj_workerid= obj.workerid ;
                                console.log(obj)
                                if(obj_workerid==0){
                                    //自动清除订单
                                    console.log(obj.orderid)
                                    console.log(obj.contents)
                                    $.ajax({
                                        url: "https://www.lerlex.com/lexiu/api/order/cancelorder",
                                        data: ({
                                            orderid: obj.orderid,
                                            reason: "下单超时"
                                        }),
                                        type: "post",
                                        success: function (data) {
                                            $state.go("tabs.home") ;
                                        }
                                    })

                                }else{
                                    //师傅已接单。给提示
                                    $ionicPopup.alert({
                                        title: '',
                                        template: '你有待完成订单',
                                        okText: '确定'
                                    });
                                    $state.go("tabs.order.todone")
                                }
                            }

                        }else {
                            $ionicPopup.alert({
                                title: '',
                                template: data[0].value,
                                okText: '确定'
                            });
                        }
                        $scope.$apply();
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
            }
        }
    });