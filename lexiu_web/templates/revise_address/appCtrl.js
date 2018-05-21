angular.module("myapp")
    .controller("revise_addressCtrl",function($scope,$state){
        $scope.map1=function(){
            $.ajax({
                url:"https://www.lerlex.com/lexiu/api/user/editaddress",
                data:({
                    address:$scope.address
                }),
                type: "get",
                dataType: "jsonp",
                jsonp:"jsonpcallback",
                success:function(data) {
                    console.log(data);
                    $state.go("map",{address:$scope.address})
                },
            })
        }
    })