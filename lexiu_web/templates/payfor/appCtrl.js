angular.module("myapp")
    .controller("paymentCtrl",function($scope,$stateParams,$document,$ionicModal,$state,$ionicPopup,$ionicLoading){
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
        var orderid=$stateParams.orderid;
        $scope.close_all=function(){
            $scope.modal.hide()
        };
        $.ajax({
            url: "https://www.lerlex.com/lexiu/api/order/orderdetail",
            data: ({
                orderid:orderid
            }),
            type: "get",
            dataType: "json",
            success: function (data) {
                console.log(data)
                $scope.money=data.money;
                $scope.price=data.order.price;
                $scope.ordernum=data.order.ordernum;
                $scope.content=data.order.content;
                $scope.formatover=data.order.formatover;
                $scope.orderNumber=data.order.ordernum;
                if($scope.money<$scope.price){
                    balance.checked=false;
                    zhifubao.checked=true;
                    $scope.surplus_money="(余额不足:"+$scope.money +"元)";
                    $scope.set=true;
                }
            }
        });
        var balance=$document.find(".payment input")[0];
        var zhifubao=$document.find(".payment input")[1];
        var tk=$(".mima_html");
        tk.hide();
        $scope.close_tk=function(){
            tk.hide();
            $scope.isOpen1= false;
        };
        $scope.jump=function(){
            //判断选择是余额支付还是支付宝支付
            if(balance.checked==true){
                tk.show();
                //input密码框获取焦点
                $scope.isOpen1= true;
                //监听密码是否改变
               $scope.zhifu=function(){
                   var oZhifu=$document.find("#zhifu").val();
                   if(oZhifu.length==6){
                       //数据请求，判断密码是否正确
                       $.ajax({
                           url: "https://www.lerlex.com/lexiu/api/order/balancepay",
                           data: ({
                               orderid:orderid,
                               paypassword:oZhifu
                           }),
                           type: "get",
                           dataType: "jsonp",
                           jsonp: "jsonpcallback",
                           success: function (data) {
                               $scope.isOpen1= false;
                               if(data[0].value=="成功" || data[0].value==null){
                                   $ionicPopup.alert({
                                       title: '提示',
                                       template: '付款成功',
                                       okText: '确定'
                                   });
                                   $scope.choose=true;
                                   $state.go("tabs.order.done")
                               }else{
                                   $ionicPopup.alert({
                                       title: '提示',
                                       template: data[0].value,
                                       buttons:[{text: '确定'}]
                                   }).then(function(){
                                       $scope.isOpen1= true;
                                       oZhifu=$document.find("#zhifu").val("")
                                   });
                               }
                               /*$scope.$apply();*/
                           },error: function (data) {
                               console.log(data)
                           }

                       });
                   }
               };
            }else{
                //支付宝支付页面
                $.ajax({
                    url: "https://www.lerlex.com/lexiu/api/pay/alipayUrl",
                    data:({
                        orderid:orderid
                    }),
                    type: "get",
                    success: function (data) {
                        var obj=JSON.parse(data);
                        var alipayClass = navigator.alipay;
                        var rsa=obj.map.rsa_private;
                        var pubRsa=obj.map.rsa_public;

                        alipayClass.pay({
                            "partner":"2088621617325003",    //商户ID
                            "rsa_private":rsa,              //私钥
                            "rsa_public":pubRsa,                //公钥
                            "seller":"info@lerlex.com",    //收款支付宝账号或对应的支付宝唯一用户号
                            "subject":"支付",            //商品名称
                            "body":"乐人乐修",        //商品详情
                            "price":$scope.price,                  //金额
                            //"tradeNo":tradeNo,  //订单号
                            "tradeNo":$scope.orderNumber,  //订单号
                            "timeout":"30m",                //超时设置
                           /* "apiname":obj.map.apiname,
                            "app_id":obj.map.app_id,
                            "app_name":obj.map.app_name,
                            "auth_type":obj.map.auth_type,
                            "biz_type":obj.map.biz_type,
                            "product_id":obj.map.product_id,
                            "scope":obj.map.scope,
                            "sign_type":obj.map.sign_type,*/
                            "notifyUrl":obj.map.url//回调url，这个很重要，后台提供，这个是提供给支付宝的回调url，支付宝根据这个url返给后台支付结果，当然如果这个写错前端也是能否调起支付宝的，就是后台接受不到支付宝的反馈结果，不能做相应的订单状态处理。
                        },function(resultStatus){
                            if(resultStatus==9000){
                                $ionicPopup.alert({
                                    title: '提示',
                                    template: '支付成功',
                                    okText: '确定'
                                });
                                $state.go("tabs.order.done");
                            }else{
                                $ionicPopup.alert({
                                    title: '提示',
                                    template: '支付失败',
                                    okText: '确定'
                                })
                            }
                        },function(message){
                            $ionicLoading.show({
                                template:"支付宝支付失败＝" + message,
                                noBackdrop: true,
                                duration: 500
                            });
                        });
                    }

                       /* cordova.plugins.alipay.payment(payInfo,function success(e){
                           alert('成功'+ e.resultStatus+e.result+e.memo)
                        },function error(e){
                            alert('失败'+e.resultStatus+e.result+e.memo)
                        });*/
                        //var obj = JSON.parse(data[0].value);
                        /*var obj = eval('(' + data[0].value + ')');
                        console.log(obj);
                        $scope.rsa_private=obj.linkString.private_key;       //私钥
                        $scope.rsa_public=obj.linkString.ali_public_key;        //公钥
                        alert($scope.rsa_public);
                        $scope.partner=obj.linkString.partner;      //商户ID
                        $scope.notify_url=obj.linkString.notify_url;        //回调url
                        $scope.seller=obj.linkString.seller_id;     //收款支付宝    */

                        /*var myDate = new Date();
                        var tradeNo = myDate.getTime();//这个应该是订单号，到时候可以换成后台提供的订单号，这里先用别的代替
                        var alipay = navigator.alipay;
                        alipay.pay({
                            "seller":"info@lerlex.com",
                            "subject":"测试支付",
                            "body":"测试支付宝支付",
                            "price":"0.01",
                            "tradeNo":"tradeNo",
                            "timeout":"30m",
                            "notifyUrl":"https://www.lerlex.com/lexiu/api/pay/alipayReturn"
                        },function(resultStatus){
                            $ionicLoading.show({
                                template:"支付宝测试返回结果＝" + resultStatus,
                                noBackdrop: true,
                                duration: 500
                            });
                        },function(message){
                            $ionicLoading.show({
                                template:"支付宝支付失败＝" + message,
                                noBackdrop: true,
                                duration: 500
                            });
                        });*/
                })
            }
        }
    });
