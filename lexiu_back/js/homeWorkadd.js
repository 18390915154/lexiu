
$(function () {
    var id=$.getUrlParam('id');
    var shopid=$.getUrlParam('shopid');
    console.log(shopid);
    $("#back").on("click",function () {
        window.location.replace("homeWork.html");
    });
    $("#submit").on("click",function () {
        var formData = new FormData(document.getElementById("form"));
        console.log(formData);
        if($("#workName").val()==""||$("#workName").val()==null){
            alert("请输入工种名称");
        }else{
            $.ajax({
                url: "https://www.lerlex.com/lexiu/manage/system/insertcompanytype",
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
                        window.location.replace("homeWork.html");
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
