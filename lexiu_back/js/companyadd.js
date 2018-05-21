
$(function () {
    var id=$.getUrlParam('id');
    var companyid=$.getUrlParam('companyid');
    var typeid;
    console.log(companyid);

    $.ajax({
            url: "https://www.lerlex.com/lexiu/manage/company/selecttype",
            type: "post",
            dataType: "json",
            success: function (data) {
                console.log(data);
                if(data!=="null"||data!==null){
                    var newDate=data.typelist;
                    console.log(newDate);
                    var choseBox=document.getElementById("choseBox");
                    for(var i=0;i<newDate.length;i++){
                        /*if((i+1)%3==0){
                            var html="<input type='checkbox' name='comtype' value="+newDate[i].companytypeid+">"+newDate[i].name+"<br/>";
                        }else{
                            var html="<input type='checkbox' name='comtype' value="+newDate[i].companytypeid+">"+newDate[i].name;
                        }*/
                        var html="<input type='checkbox' name='typeid' value="+newDate[i].companytypeid+">"+newDate[i].name+"<br/>";
                        choseBox.innerHTML+=html;
                    }
                }else {
                    console.log("失败");
                }

            },
            error: function (res) {
                console.log(res);
            }
        });

    /*function fun(){
        obj = document.getElementsByName("typeid");
        check_val = [];
        for(k in obj){
            if(obj[k].checked)
                check_val.push(obj[k].value);
        }
        console.log(check_val);
        typeid=check_val;
    }*/
    
    $("#back").on("click",function () {
        window.location.replace("companyinformation.html");
    });
    //显示图片-----------------------------------------------
    $("#photo").on("change",function(){
        var obj=$("#userHead");
        getPhoto(this,obj);
    });

    $("#submit").on("click",function () {
        var formData = new FormData(document.getElementById("form"));
        console.log(formData);
        if($("#companyName").val()==""||$("#companyAssd").val()==""||$("#companyContact").val()==""||$("#companyTel").val()==""){
            alert("请完善必填信息");
        }else if(!(/^1[34578]\d{9}$/.test($("#companyTel").val()))){
            alert("请输入正确的手机号");
        }else if(!(/^[\u4e00-\u9fa5]{0,}$/.test($("#companyAssd").val()))){
            alert("请输入正确的地址");
        }else{
            $.ajax({
                url: "https://www.lerlex.com/lexiu/manage/company/insertcompany",
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
                        window.location.replace("companyinformation.html");
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
