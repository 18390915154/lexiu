
$(function () {
    var id=$.getUrlParam('id');
    var goodsid=$.getUrlParam('goodsid');
    var shopid=$.getUrlParam('shopid');
    console.log(goodsid);
    console.log(shopid);
    $("#shopId").val(shopid);
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
        //formData.append("shopid", shopid);
        console.log(formData);
        if($("#shopName").val()==""||$("#shopScore").val()==""){
            alert("请完善必填信息");
        }else{
            $.ajax({
                url: "https://www.lerlex.com/lexiu/manage/shop/creategoods",
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
                        window.location.replace("storeinformation.html");
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
