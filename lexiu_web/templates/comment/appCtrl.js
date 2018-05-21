angular.module("myapp")
    .controller("commentCtrl", function ($scope, $document, $stateParams, $http,$ionicPopup,$state,$timeout) {
        var orderid = $stateParams.orderid;
        var starslength;
        var content;
        var content1;

        console.log(orderid);
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
        // 准备五星数据
        var starArr = [
            {value: 1, icon: "ion-ios-star-outline"},
            {value: 2, icon: "ion-ios-star-outline"},
            {value: 3, icon: "ion-ios-star-outline"},  // val: 3   index: 2
            {value: 4, icon: "ion-ios-star-outline"},
            {value: 5, icon: "ion-ios-star-outline"}
        ];
        // 准备card商品数据
        $scope.stars = [
            {id: 1, content: angular.copy(starArr)}
        ];
        // 响应click事件
        $scope.setRating = function (id, val) {
            stars = $scope.stars;
            // 遍历stars数组，设置每一个元素的icon
            angular.forEach(stars, function (item) {
                if (item.id == id) {
                    angular.forEach(item.content, function (arr, index) {
                        arr.icon = index < val ? "ion-ios-star" : "ion-ios-star-outline";
                    });
                }
            });
            starslength = val;
        };
        /*评价多选*/
        var cols = $document.find(".col");
        angular.forEach(cols,function(data) {
            data.onclick =function() {
                if(data.className=="col") {
                    data.className="actives col";
                    content= $document.find(" .actives").text();
                } else if(data.className=="actives col") {
                    data.className="col";
                }
            }
        });
        $scope.newArray=[];
        //ajax
        $.ajax({
            url: "https://www.lerlex.com/lexiu/api/order/orderdetail",
            data: ({
                orderid: orderid
            }),
            type: "get",
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (data.message == null || data.message == "成功") {
                    $scope.order = data.order;
                    $scope.worker=data.worker;
                    $scope.grade=parseInt($scope.worker.grade*100) /100;
                    $scope.avatar="https://www.lerlex.com/"+$scope.worker.avatar;
                    console.log($scope.worker.grade);
                    for(var i=1;i<$scope.worker.grade;i++){
                        $scope.new=[];
                        $scope.new[i]={icon:"ion-ios-star"};
                        $scope.newArray[i]=$scope.new[i];
                    }
                    for(var i=0;i<5-$scope.worker.grade;i++){
                        $scope.new1=[];
                        $scope.new1[i]={icon:"ion-ios-star-outline"};
                        $scope.newArray[5-i]=$scope.new1[i];
                    }
                    console.log($scope.newArray);
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
                    title: '',
                    template: '请求超时',
                    okText: '确定'
                });
                console.log(data);
            }
        });

        /*提交评价*/

        $scope.sub = function () {
            console.log(orderid);
            console.log(content);
            console.log(starslength);
            //ajax
            $.ajax({
                //url:"https://www.lerlex.com/lexiu/api/order/savecomment",
                url: "https://www.lerlex.com/lexiu/api/order/savecomment",
                data: ({
                    orderid: orderid,
                    grade: starslength,
                    content: content,
                    detail:$scope.nav
                }),
                type: "get",
                dataType: "jsonp",
                jsonp:"jsonpcallback",
                success: function (data) {
                    console.log(data);
                    if (data[0].value == null || data[0].value == "成功") {
                        $ionicPopup.alert({
                            title: '',
                            template: '提交成功',
                            okText: '确定'
                        });
                        $state.go("comment1")
                    } else {
                        $ionicPopup.alert({
                            title: '',
                            template: "请评价订单",
                            okText: '确定'
                        });
                    }
                },
                error:function (data) {
                    console.log(data);
                    $ionicPopup.alert({
                        title: '',
                        template: '请求超时',
                        okText: '确定'
                    });
                }
            });
        }
    });