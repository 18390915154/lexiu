angular.module("myapp")
    .controller("tourCtrl",function($scope,$interval,$state,$ionicPopup){
        $scope.old_unames=(localStorage.getItem("phone"));
        $scope.old_upassds=(localStorage.getItem("upassd"));
        $scope.old_types=(localStorage.getItem("types"));
        console.log($scope.old_unames)
        console.log($scope.old_upassds)
        console.log($scope.old_types)
        if($scope.old_unames==null || $scope.old_unames==""){
            $state.go("tour1");
        }else{
            $.ajax({
                url: "https://www.lerlex.com/lexiu/api/user/login",
                data: ({
                    phone: $scope.old_unames,
                    password: $scope.old_upassds,
                    type: $scope.old_types
                }),
                type: "get",
                dataType: "jsonp",
                jsonp: "jsonpcallback",
                success: function(data) {
                    console.log(data)
                    if (data[0].value == null || data[0].value == "成功") {
                        //自动登录后判断是否有未完成订单
                        console.log(data[4].value);
                        //还有未支付订单
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
                                type: "post"
                            });
                            if($scope.old_types==0){
                                $state.go("tabs.home");
                            }else{
                                $state.go("tabs_sf.home_s");
                            }

                        }else if(obj_workerid==1){
                            //师傅已接单。给提示
                            $ionicPopup.alert({
                                title: '',
                                template: '你有待完成订单',
                                okText: '确定'
                            });
                            $state.go("tabs.order.todone")
                        }else{
                            if($scope.old_types==0){
                                $state.go("tabs.home");
                            }else{
                                $state.go("tabs_sf.home_s");
                            }
                        }
                    }
                }
            })
        }

    });