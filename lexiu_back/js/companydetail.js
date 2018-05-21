
$(document).ready(function () {
    var id=$.getUrlParam('id');
    var companyid=$.getUrlParam('companyid');
    console.log(companyid);
    $.ajax({
        url: "https://www.lerlex.com/lexiu/manage/company/companydetail",
        data: ({
            companyid:companyid
        }),
        type: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);
            var newData = data.company;
            console.log(newData);
            $(".companyPoto").attr("src","https://www.lerlex.com/"+newData.picurl);
            $("#companyName").html(newData.name);
            $("#companyAssd").html(newData.address);
            $("#companyContact").html(newData.contact);
            $("#companyTel").html(newData.phone);
        },
        error: function (res) {
            console.log(res);
        }
    });
    $("#back").on("click",function () {
        window.location.replace("companyinformation.html");
    });
});
