angular.module("myapp")
    .controller("doneCtrl", function ($scope, $http, $ionicPopup) {
        //ajax方法
        $.ajax({
            url: "https://www.lerlex.com/lexiu/api/order/doneorder",
            type: "get",
            dataType: "jsonp",
            jsonp:"jsonpcallback",
            success: function (data) {
                console.log(data);
                var Obj0 = eval('(' + data[0].value + ')');
                var Obj1 = eval('(' + data[1].value + ')');
                console.log(Obj1);
                if (data.message == null || data.message == "成功") {
                    if(Obj1 == null || Obj1 ==""){
                		$('.ywc').show();
                	}else{
                		$('.ywc').hide();
                	}
                    $scope.orders = Obj1;
                    $scope.number = Obj1.length;
                }else {
                    $ionicPopup.alert({
                        title: '',
                        template: data.message,
                        okText: '确定'
                    });
                }
            },
            error: function (data) {
                $ionicPopup.alert({
                    title: '',
                    template: '请求超时',
                    okText: '确定'
                });
                console.log(data);
            }
        });
        $scope.phone = function($event){
            $event.stopPropagation();      //在函数体内加上这句代码就好
        }
    });