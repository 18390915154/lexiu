/**
 * Created by My on 2017/3/2.
 */
angular.module("myapp")
    .controller("todoneCtrl", function ($scope, $http, $state, $document,$ionicPopup) {
        //ajax
        $.ajax({
            url: "https://www.lerlex.com/lexiu/api/order/undoneorder",
            type: "get",
            dataType: "jsonp",
            jsonp:"jsonpcallback",
            success: function (data) {
                console.log(data);
                console.log(eval('(' + data[1].value + ')'));
                var Obj = eval('(' + data[1].value + ')');
                if (data.message == null || data.message == "成功") {
                    $scope.orders = Obj;
                    if(Obj == null || Obj ==""){
                    	$('.dwc').show();
                    }else{
                    	$('.dwc').hide();
                    }
                    console.log(data[0].value);
                    $scope.show = function () {
                        var index = this.$index;
                        var ortype = $scope.orders[index].type;
                        if (ortype == 0) {
                            $ionicPopup.alert({
                                title: '提示',
                                template: '订单尚未生成',
                                okText: '确定'
                            });
                        } else if (ortype == 1) {
                            $ionicPopup.alert({
                                title: '提示',
                                template: '订单尚未报结',
                                okText: '确定'
                            });
                        } else if (ortype == 2) {
                            $state.go("todonedetail", {orderid: $scope.orders[index].orderid})
                        } else if (ortype == 3) {
                            $state.go("todonedetail", {orderid: $scope.orders[index].orderid})
                        } else if (ortype == 4) {
                            $ionicPopup.alert({
                                title: '提示',
                                template: '订单已完成',
                                okText: '确定'
                            });
                        }
                    }

                } else {
                    $ionicPopup.alert({
                        title: '提示',
                        template: data.message,
                        okText: '确定'
                    });
                }
                $scope.$apply();
            },
            error:function (data) {
                $ionicPopup.alert({
                    title: '提示',
                    template: '请求超时',
                    okText: '确定'
                });
                console.log(data);
            }
        });
        //阻止冒泡
        $scope.phone = function($event){
            $event.stopPropagation();      //在函数体内加上这句代码就好
        }


    });