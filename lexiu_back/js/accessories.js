
$(document).ready(function () {
    var id=$.getUrlParam('id');
    var shopid=$.getUrlParam('shopid');
    console.log(shopid);
    $.ajax({
        url: "https://www.lerlex.com/lexiu/manage/shop/shopdetail",
        data: ({
            shopid:shopid
        }),
        type: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);
            var newData = data.shopdetail;
            console.log(newData);
            $(".shopPoto").attr("src","https://www.lerlex.com/"+newData.picurl);
            $("#shopName").html(newData.name);
            $("#shopAssd").html(newData.address);
            $("#shopContact").html(newData.contact);
            $("#shopTel").html(newData.tel);
        },
        error: function (res) {
            console.log(res);
        }
    });
    $("#back").on("click",function () {
        window.location.replace("storeinformation.html");
    });
});
