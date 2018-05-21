/**
 * Created by my on 2017/4/24.
 */
$(document).ready(function () {
    var id = $.getUrlParam('id');
    var shopid = $.getUrlParam('shopid');
    console.log(shopid);
    //加载首页banner轮播图---------------------------------------------------------
    $.ajax({
        url: "https://www.lerlex.com/lexiu/manage/banner/seachbanner",
        data: ({}),
        type: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $("#userHeadAa").attr("src","https://www.lerlex.com/"+data.bannerlist[0].picurl);
            $("#userHeadBb").attr("src","https://www.lerlex.com/"+data.bannerlist[1].picurl);
            $("#userHeadCc").attr("src","https://www.lerlex.com/"+data.bannerlist[2].picurl);
            $("#bannerAaimgid").val(data.bannerlist[0].bannerid);
            $("#bannerBbimgid").val(data.bannerlist[1].bannerid);
            $("#bannerCcimgid").val(data.bannerlist[2].bannerid);
        },
        error: function (res) {
            console.log(res);
        }
    });
    //加载商城banner轮播图---------------------------------------------------------
    $.ajax({
        url: "https://www.lerlex.com/lexiu/manage/shop/seachbanner",
        data: ({
            shopid:shopid
        }),
        type: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);
            $("#shopuserHeadAa").attr("src","https://www.lerlex.com/"+data.bannerlist[0].picurl);
            $("#shopuserHeadBb").attr("src","https://www.lerlex.com/"+data.bannerlist[1].picurl);
            $("#shopuserHeadCc").attr("src","https://www.lerlex.com/"+data.bannerlist[2].picurl);
            $("#shopbannerAaimgid").val(data.bannerlist[0].shopBannerid);
            $("#shopbannerBbimgid").val(data.bannerlist[1].shopBannerid);
            $("#shopbannerCcimgid").val(data.bannerlist[2].shopBannerid);
        },
        error: function (res) {
            console.log(res);
        }
    });
    //点击更换轮播图方法1----------------------------------------------------
    function  changeImg(objc1,objc2) {
        var formData = new FormData(document.getElementById(objc1));
        var imgId=objc2.val();
        formData.append("bannerid", imgId);
        $.ajax({
            url: 'https://www.lerlex.com/lexiu/manage/banner/updatebanner',
            dataType: 'json',
            data: formData,
            type: "post",
            cache: false,
            processData: false,
            contentType: false,
            success: function (data, status) {
                console.log(data);
                alert(data.message);
                window.location.reload();
            },
            error: function (data, status, e) {
                // alert(e);
                console.log(data)
            }
        })
    }
    //点击更换轮播图方法2----------------------------------------------------
    function  changeshopImg(objc1,objc2) {
        var formData = new FormData(document.getElementById(objc1));
        var imgId=objc2.val();
        formData.append("bannerid", imgId);
        $.ajax({
            url: 'https://www.lerlex.com/lexiu/manage/shop/updatebanner',
            dataType: 'json',
            data: formData,
            type: "post",
            cache: false,
            processData: false,
            contentType: false,
            success: function (data, status) {
                console.log(data);
                alert(data.message);
                window.location.reload();
            },
            error: function (data, status, e) {
                // alert(e);
                console.log(data)
            }
        })
    }
    //更换欢迎页----------------------------------------------------------------------
    $("#photoAa").on("change", function () {
        changeImg("formAa",$("#bannerAaimgid"));
        var obj=$("#userHeadAa");
        getPhoto(this,obj);

    });
    $("#photoBb").on("change", function () {
        changeImg("formBb",$("#bannerBbimgid"));
        var obj=$("#userHeadBb");
        getPhoto(this,obj);

    });
    $("#photoCc").on("change", function () {
        changeImg("formCc",$("#bannerCcimgid"));
        var obj=$("#userHeadCc");
        getPhoto(this,obj);

    });
    //更换商城页----------------------------------------------------------------------
    $("#shopphotoAa").on("change", function () {
        changeshopImg("shopformAa",$("#shopbannerAaimgid"));
        var obj=$("#shopuserHeadAa");
        getPhoto(this,obj);
    });
    $("#shopphotoBb").on("change", function () {
        changeshopImg("shopformBb",$("#shopbannerBbimgid"));
        var obj=$("#shopuserHeadBb");
        getPhoto(this,obj);
    });
    $("#shopphotoCc").on("change", function () {
        changeshopImg("shopformCc",$("#shopbannerCcimgid"));
        var obj=$("#shopuserHeadCc");
        getPhoto(this,obj);
    });

    $("#backBtn").on("click",function(){
        window.location.replace("storeinformation.html");
    })

});
