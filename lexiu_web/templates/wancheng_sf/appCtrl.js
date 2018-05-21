angular.module("myapp")
    .controller("done_sfCtrl",function($scope, $http, $ionicPopup){
        //ajax方法
        $.ajax({
            url: "https://www.lerlex.com/lexiu/api/worker/doneorder",
            type: "get",
            dataType: "jsonp",
            jsonp:"jsonpcallback",
            success: function (data) {
                console.log(data);
                var Obj0 = eval('(' + data[0].value + ')');
                //console.log(Obj0);
                if (Obj0 == null || Obj0 == "成功") {
                	var Obj1 = eval('(' + data[1].value + ')');
                	//console.log(Obj1);
                	if(data[1].value == "[]"){
                		$('.ywc').show();
                	}else{
                		$('.ywc').hide();
                	}
                    $scope.orders = Obj1;
                    $scope.number = Obj1.length;
                }else {
                    $ionicPopup.alert({
                        title: '提示',
                        template: Obj0,
                        okText: '确定'
                    });
                }
                $scope.$apply();
            },
            error: function (data) {
                console.log(data);
            }
        });
    });