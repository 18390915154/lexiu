angular.module("myapp")
    .controller("jzCtrl",function($scope){
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
        $.ajax({
            url: "https://www.lerlex.com/lexiu/manage/system/seachcompanytype",
            type: "get",
            dataType: "json",
            success: function (data) {
            console.log(data);
            console.log(data.typelist);
                /*var obj= eval('(' + data + ')');*/
                $scope.type=data.typelist;
                console.log($scope.type[0])
            }
        })
    });