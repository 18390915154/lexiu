/*2017.04.19 00:00*/
$(document).ready(function () {
    //登录显示账户名--------------------------
    var id=$.getUrlParam('id');
    console.log(id);
    if(id==null||id==""){
        window.location.replace("login.html");
    }
    $("#adminName").html(id);
    //导航切换---------------------------------
    var show=true;
    showHide($(".secondList li"),"bction");
    //显示二级菜单------------------------------------------------------------------------------------------------------
    $("#fistList li").on("click",function () {
       var index=$(this).index();
        $(this).find(".secondList").show();
        $("#fistList li .secondList").hide();
        $("#fistList li a").removeClass("action");
        $(this).find("a").first().addClass("action");
        $("#fistList li .fa_second").removeClass("fa-angle-down").addClass("fa-angle-right");
       if(show==true){
           $(this).find(".fa_second").removeClass("fa-angle-right").addClass("fa-angle-down");
           $(this).find(".secondList").show();
           show=false;
       }else if(show==false){
           $(this).find(".fa_second").removeClass("fa-angle-down").addClass("fa-angle-right");
           $(this).find(".secondList").hide();
           show=true;
       }
    });
    //页面切换----------------------------------------------------------------------------------------------------------
    $("#home").on("click",function () {
        $("#iframInter").attr("src","home.html?id="+id+"");
    });
    $("#userList li").eq(0).on("click",function () {
        $("#iframInter").attr("src","user.html?id="+id+"");
    });
    $("#userList li").eq(1).on("click",function () {
        $("#iframInter").attr("src","master.html?id="+id+"");
    });
    $("#taskManagement").on("click",function () {
        $("#iframInter").attr("src","taskmanagement.html?id"+id+"");
    });
    $("#settLement").on("click",function () {
        $("#iframInter").attr("src","settlementratio.html?id="+id+"");
    });
    $("#feedback").on("click",function () {
        $("#iframInter").attr("src","feedback.html?id"+id+"");
    });
    $("#moneyList li").eq(0).on("click",function () {
        $("#iframInter").attr("src","recharge.html?id="+id+"");
    });
    $("#moneyList li").eq(1).on("click",function () {
        $("#iframInter").attr("src","bankcard.html?id="+id+"");
    });
    $("#moneyList li").eq(2).on("click",function () {
        $("#iframInter").attr("src","masterrecharge.html?id="+id+"");
    });
    $("#moneyList li").eq(3).on("click",function () {
        $("#iframInter").attr("src","masterbankcard.html?id="+id+"");
    });
    $("#shopList").on("click",function () {
        $("#iframInter").attr("src","storeinformation.html?id="+id+"");
    });
    $("#companyList").on("click",function () {
        $("#iframInter").attr("src","companyinformation.html?id="+id+"");
    });
    $("#workType").on("click",function () {
        $("#iframInter").attr("src","workType.html?id="+id+"");
    });
    $("#homeWork").on("click",function () {
        $("#iframInter").attr("src","homeWork.html?id="+id+"");
    });
    $("#bannerList").on("click",function () {
        $("#iframInter").attr("src","banner.html?id="+id+"");
    });
    $("#proporTion").on("click",function () {
        $("#iframInter").attr("src","settlementtime.html?id="+id+"");
    });

    //退出登录----------------------------------------------------------------------------------------------------------
    $("#exitBtn").on("click",function () {
        //replace防止浏览器的后退按钮再次退回原来的界面。
        window.location.replace("login.html");
    })
});