angular.module("myapp")
    .controller("tixian_type_sfCtrl",function($scope){
        $scope.submit=function(){
            console.log($scope.price)
            $.ajax({
                url:"https://www.lerlex.com/lexiu/api/system/saveuserwithdraw",
                data:({
                    monery:$scope.price,
                    account:"11111"
                }),
                type: "post",
                dataType: "jsonp",
                jsonp: "jsonpcallback",
                success: function (data) {
                    console.log(data);
                }
            });
        } ;
    });