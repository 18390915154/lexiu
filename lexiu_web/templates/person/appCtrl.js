angular.module("myapp")
    .controller("personalCtrl",function($scope,$stateParams){
        var id=$stateParams.id;
        $scope.img="images/my.png";
        $.ajax({
            url:"https://www.lerlex.com/lexiu/api/mine/seluser",
            /*data:({
                userid:id
            }),*/
            type: "get",
            dataType: "jsonp",
            jsonp:"jsonpcallback",
            success: function (data) {
                console.log(data);
                var Obj = eval('(' + data[1].value + ')');
                $scope.products = Obj;
                $scope.number = Obj.length;
                console.log(Obj);
                if(data.message == null || data.message == "成功") {
                    if(Obj.avatar==""){
                        $scope.img="images/my.png"
                    }else{
                        $scope.img ="https://www.lerlex.com/"+ Obj.avatar;
                    }
                    $scope.name = Obj.nickname;
                    $scope.sex = Obj.sex;
                    $scope.age = Obj.age;
                    $scope.birthday = Obj.formatbirthday;
                    $scope.phone = Obj.phone;
                }
                $scope.$apply();
            },
            error:function (data) {
                console.log(data);
            }
        });
        /*$.ajax({
            url:"https://www.lerlex.com/lexiu/api/mine/userPerfect",
            type: "get",
            dataType: "jsonp",
            jsonp:"jsonpcallback",
            success: function (data) {
                /!*console.log(data);
                 console.log(data.length);
                 var Obj = eval('(' + data[0].value + ')');
                 $scope.products = Obj;
                 console.log($scope);
                 $scope.number = Obj.length;
                 console.log($scope);*!/
                if (data.message == null || data.message == "成功") {
                    $scope.nickname=data.nickname;
                    $scope.birthday=data.birthday;
                    $scope.sex=data.sex;
                    $scope.age=data.age;
                    $scope.phone=data.phone;
                }
            }

        })*/
    })