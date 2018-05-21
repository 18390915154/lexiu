
$(function () {
    var id=$.getUrlParam('id');
    var shopid=$.getUrlParam('shopid');
    console.log(shopid);
    $("#back").on("click",function () {
        window.location.replace("workType.html");
    });
    $("#submit").on("click",function () {
        var opVal=$('#workName1 option:selected').val();
        var formData = new FormData(document.getElementById("form"));
        formData.append("typenum", opVal);
        console.log(formData);
        if($("#workName2").val()==""||$("#workName2").val()==null){
            alert("请输入二级工种名称");
        }else if(opVal==""||opVal==null){
            alert("请选择一级工种名称");
        }else{
            $.ajax({
                url: "https://www.lerlex.com/lexiu/manage/system/inserttype",
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
                        window.location.replace("workType.html");
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
