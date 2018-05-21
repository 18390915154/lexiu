
$(function () {
    var id=$.getUrlParam('id');
    var shopid=$.getUrlParam('shopid');
    console.log(shopid);
    $("#back").on("click",function () {
        window.location.replace("storeinformation.html");
    });
    //显示图片-----------------------------------------------
    $("#photo").on("change",function(){
        var obj=$("#userHead");
        getPhoto(this,obj);
    });
    $("#submit").on("click",function () {
        var formData = new FormData(document.getElementById("form"));
        console.log(formData);
        if($("#shopName").val()==""||$("#shopAssd").val()==""||$("#shopContact").val()==""||$("#shopTel").val()==""){
            alert("请完善必填信息");
        }else if(!(/^1[34578]\d{9}$/.test($("#shopTel").val()))){
            alert("请输入正确的手机号");
        }else if(!(/^[\u4e00-\u9fa5]{0,}$/.test($("#shopAssd").val()))){
            alert("请输入正确的地址");
        }else{
            $.ajax({
                url: "https://www.lerlex.com/lexiu/manage/shop/createshop",
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
