angular.module("myapp")
    .controller("price_guideCtrl",function($scope,$document){
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });
        var oA=$document.find(".price_grade_inner").find("li")
        /*console.log(oA.length)*/
        $(".price_list").eq(0).show();
        oA.each(function(){
            $(this).on("click",function(){
                var index=$(this).index();
                $(this).addClass("active").siblings().removeClass("active");
                 $(".price_list").eq(index).show().siblings(".price_list").hide();
            })
        })
    });