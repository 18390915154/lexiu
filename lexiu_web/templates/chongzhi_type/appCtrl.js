angular.module("myapp")
    .controller("chongzhi_typeCtrl", function ($scope, $state, $http, $ionicPopup,$document,$stateParams) {
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
        var zhifubao=$document.find(".chongzhi_type input")[0];
        $scope.submit = function () {
            console.log($scope.money)
            if (zhifubao.checked==true) {
                //支付宝支付页面
                $.ajax({
                    url: "https://www.lerlex.com/lexiu/api/pay/addalipayUrl",
                    data: ({
                        money: $scope.money,
                        type:0
                    }),
                    type: "get",
                    dataType: "jsonp",
                    jsonp:"jsonpcallback",
                    success: function (data) {
                        console.log(data);
                        var obj=eval('(' + data[0].value + ')');
                        console.log(obj);
                        var order_new= obj;
                        var alipayClass = navigator.alipay;
                        var rsa = order_new.rsa_private;
                        var pubRsa =order_new.rsa_public;
                        alipayClass.pay({
                            "partner": order_new.pid,    //商户ID
                            "rsa_private": rsa,              //私钥
                            "rsa_public": pubRsa,                //公钥
                            "seller": "info@lerlex.com",    //收款支付宝账号或对应的支付宝唯一用户号
                            "subject": "充值",            //商品名称
                            "body": "乐人乐修",        //商品详情
                            "price": $scope.money,                  //金额
                            //"tradeNo":tradeNo,  //订单号
                            "tradeNo": order_new.ordernum,  //订单号
                            "timeout": "30m",                //超时设置
                            "notifyUrl": order_new.url//回调url，这个很重要，后台提供，这个是提供给支付宝的回调url，支付宝根据这个url返给后台支付结果，当然如果这个写错前端也是能否调起支付宝的，就是后台接受不到支付宝的反馈结果，不能做相应的订单状态处理。
                        }, function (resultStatus) {
                            if (resultStatus == 9000) {
                                $ionicPopup.alert({
                                    title: '提示',
                                    template: '充值成功',
                                    okText: '确定'
                                });
                                $state.go("wallet");
                            } else {
                                $ionicPopup.alert({
                                    title: '提示',
                                    template: '充值失败',
                                    okText: '确定'
                                })
                            }
                        }, function (message) {
                            $ionicLoading.show({
                                template: "支付宝支付失败＝" + message,
                                noBackdrop: true,
                                duration: 500
                            });
                        });
                    }
                })

            }

        }
    })