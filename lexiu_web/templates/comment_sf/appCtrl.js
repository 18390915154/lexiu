angular.module("myapp")
    .controller("comment_sfCtrl",function($scope,$document){
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
            var stars = $scope.stars;

            // 遍历stars数组，设置每一个元素的icon
            angular.forEach(stars, function (item) {
                if (item.id == id) {
                    angular.forEach(item.content, function (arr, index) {
                        arr.icon = index < val ? "ion-ios-star" : "ion-ios-star-outline";
                    });
                }
            });
        };
        /*评价多选*/
        var cols=$document.find(".col");
        var cols_style=cols.css('color')
        cols.each(function(){
            $(this).click(function(){
                if($(this).css('color')=="#e86000"){
                    $(this).removeClass("active");
                }else{
                    $(this).addClass("active");
                }

            })
        })
    })