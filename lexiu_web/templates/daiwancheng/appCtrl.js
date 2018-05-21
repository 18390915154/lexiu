angular.module("myapp")
    .controller("themeCtrl",function($scope,$document){
        var oA=$document.find(".theme").find("a")
        /*console.log(oA.length)*/
        oA.eq(0).addClass("active")
        oA.each(function(){
            $(this).on("click",function(){
                $(this).addClass("active").siblings().removeClass("active");
            })
        })
    })