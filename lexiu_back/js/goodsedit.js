
$(function () {
    var id=$.getUrlParam('id');
    var goodsid=$.getUrlParam('goodsid');
    var shopid=$.getUrlParam('shopid');
    console.log(goodsid);
    console.log(shopid);
    $("#shopId").val(goodsid);
    $.ajax({
        url: "https://www.lerlex.com/lexiu/manage/shop/goodsdetail",
        data: ({
            goodsid:goodsid
        }),
        type: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);
            var newData = data.goods;
            console.log(newData);
            $(".shopPoto").attr("src","https://www.lerlex.com/"+newData.picurl);
            $("#shopName").val(newData.name);
            $("#shopScore").val(newData.score);
            $("#shopJianjie").val(newData.profile);
        },
        error: function (res) {
            console.log(res);
        }
    });
    $("#back").on("click",function () {
        window.location.replace("shopfitting.html?shopid="+shopid+"");
    });
    //显示图片-----------------------------------------------
    $("#photo").on("change",function(){
        var obj=$("#userHead");
        getPhoto(this,obj);
    });
    $("#submit").on("click",function () {
        var formData = new FormData(document.getElementById("form"));
        console.log(formData);
        if($("#shopName").val()==""||$("#shopScore").val()==""){
            alert("请完善必填信息");
        }else{
            $.ajax({
                url: "https://www.lerlex.com/lexiu/manage/shop/updategoods",
                data:formData,
                type: "post",
                dataType: "json",
                cache: false,
                processData: false,
                contentType: false,
                success: function (data) {
                    console.log(data);
                    if(data.message=="成功"){
                        alert(data.message);
                        window.location.replace("shopfitting.html?shopid="+shopid+"");
                    }else {
                        alert(data.message);
                    }
                },
                error: function (res) {
                    console.log(res);
                }
            });
        }

    });


});
