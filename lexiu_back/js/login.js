/*2017.04.19 00:00*/
$(document).ready(function () {
    function login() {
        var userName = $("#userName").val();
        var userPassword = $("#userPassword").val();
        //console.log("用户名：" + userName);
        //console.log("密码：" + userPassword);
        if (userName == "" || userName == null) {
            alert("请输入账户名");
        } else if (userPassword == "" || userPassword == null) {
            alert("请输入密码")
        }else {
            $.ajax({
                url: "https://www.lerlex.com/lexiu/manage/user/login",
                data: ({
                    username:userName,
                    password:userPassword
                }),
                type: "post",
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    if(data.message==null||data.message=="成功"){
                        window.location.href = "index.html?id="+userName+"";
                    }else{
                        alert(data.message);
                    }
                },
                error: function (res) {
                    console.log(res);                   
                }
            });
        }
    }

    $("#btnIn").on("click", function () {
        login();
    });
    $("#loginBox").keydown(function (event) {
        if (event.keyCode == 13) login();
    });

});
