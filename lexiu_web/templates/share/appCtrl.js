angular.module("myapp")
    .controller("shareCtrl",function($scope,$ionicModal){
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });

        $ionicModal.fromTemplateUrl('share.html', {
            scope: $scope,
            animation: 'slide-in-up'
        }).then(function(modal) {
            $scope.modal = modal;
        });
        $scope.openModal = function() {
            $scope.modal.show();
        };
        $scope.closeModal = function() {
            $scope.modal.hide();
        };

        document.addEventListener('deviceready',function() {
            $scope.friend=function() {
                //调用插件
                navigator.WechatShare.showToast('请稍后...');
                shareToWeixin(false);
            };
            $scope.friends=function() {
                //调用插件
                navigator.WechatShare.showToast('请稍后...');
                shareToWeixin(true);
            };
        function shareToWeixin(bool){
            //这里以分享一个网页为例
            var args = {
                type: 'webpage',
                url:'https://www.baidu.com',
                imgUrl:'https://www.baidu.com/img/bdlogo.png',
                title:'文章标题',
                desc:'文章简介',
                isSendToTimeline:bool //true表示分享到朋友圈，false分享给好友
            };

            navigator.WechatShare.send(args);
        }

            navigator.WechatShare.sendCallBack = function(result){
                switch(result){
                    case 1:
                        //分享成功
                        //...编写你的业务逻辑
                        navigator.WechatShare.showToast('分享成功');
                        break;

                    case 2:
                        //取消分享
                        //...
                        navigator.WechatShare.showToast('取消分享');
                        break;

                    case 3:
                        //验证失败
                        navigator.WechatShare.showToast('验证失败');
                        //...
                        break;

                    case 4:
                        //未知错误
                        //...
                        navigator.WechatShare.showToast('未知错误');
                        break;
                }
            }

        },false)




        /*$scope.share = function(title, desc, url, thumb) {
            $ionicActionSheet.show({
                buttons: [
                   { text:'<b>分享至微信朋友圈</b>' },
                   { text:'分享给微信好友' }
                ],
                titleText: '分享',
                cancelText:'取消',
                cancel: function() {
                // 取消时执行
                    },buttonClicked: function(index) {
                        if(index == 0) {
                            $scope.shareViaWechat(WeChat.Scene.timeline, title, desc, url, thumb);
                        }
                       if(index ==1 ) {
                            $scope.shareViaWechat(WeChat.Scene.session, title, desc, url, thumb);
                        }
                    }
            });

            $scope.shareViaWechat = function(scene, title, desc, url, thumb) {
                          // 创建消息体
               var msg = {
                    title:title ? title : "行者无疆",
                    description: desc ? desc : "A real traveller's province isboundless.",
                    url: url ?url : "https://zgw520.club",
                    thumb:thumb ? thumb : null
                };
                WeChat.share(msg, scene, function() {
                    $ionicPopup.alert({
                            title: '分享成功',
                            template: '感谢您的支持！',
                            okText:'关闭'
                    });
                }, function(res) {
                    $ionicPopup.alert({
                            title: '分享失败',
                            template: '错误原因：' + res + '。',
                            okText:'我知道了'
                    });
                });
            };
        };*/
    });