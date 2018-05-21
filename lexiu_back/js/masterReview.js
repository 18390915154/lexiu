
$(function () {
    var id=$.getUrlParam('id');
    var phone=$.getUrlParam('phone');
    console.log(phone);
    function Convert(Unix) {
        var Time = new Date(Unix * 1000);
        return (Time.getFullYear() + "-" + (Time.getMonth() + 1) + "-" + Time.getDate() + " " + Time.getHours() + ":" + Time.getMinutes() + ":" + Time.getSeconds());
    }
    $.ajax({
        url: "https://www.lerlex.com/lexiu/manage/worker/workerdetail",
        data: ({
            phone:phone
        }),
        type: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);

	            if(data.work.typeid==0){
	                data.work.typeid="普通电工";
	            }
	            if(data.work.typeid==1){
	                data.work.typeid="普通电工";
	            }
	            if(data.work.typeid==2){
	                data.work.typeid="资深电工";
	            }
	            if(data.work.typeid==3){
	                data.work.typeid="管道工";
	            }
	            if(data.work.typeid==4){
	                data.work.typeid="空调工";
	            }

            var newData = data.work;
            console.log(newData);
            
            $("#idImgA").attr("src","https://www.lerlex.com/"+newData.identityFront);
            $("#idImgB").attr("src","https://www.lerlex.com/"+newData.identityRear);
            $("#idImgC").attr("src","https://www.lerlex.com/"+newData.credentialsFront);
            $("#idImgD").attr("src","https://www.lerlex.com/"+newData.credentialsRear);            
            $("#masterName").val(newData.nickname);
            $("#masterTime").val(newData.formatbirthday);
            $("#masterSet").val(newData.sex);
            $("#masterTel").val(newData.phone);
            $("#masterTell").val(newData.tel);
            $("#masterCompnay").val(newData.companyname);
            $("#masterCompnaytel").val(newData.companiesTel);
            $("#masterId").val(newData.idcard);
            $("#masterType").val(newData.typeid);
            $("#masterContact").val(newData.skill);          
        },
        error: function (res) {
            console.log(res);
        }
    });
    $("#back").on("click",function () {
        window.location.replace("master.html");
    });
    $("#submit").on("click",function () {
        $.ajax({
                url: "https://www.lerlex.com/lexiu/manage/worker/editisok",
                data: ({
                    phone:$("#masterTel").val()
                }),
                type: "post",
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    if(data.message=="成功"){
                        alert("审核成功");
                        window.location.replace("master.html");
                    }else {
                        alert(data.message);
                    }

                },
                error:function (res) {
                    console.log(res);
                }
            });
    });


});
