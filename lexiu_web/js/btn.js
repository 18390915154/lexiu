/**
 * Created by My on 2017/4/11.
 */
// 等待加载PhoneGap
document.addEventListener("deviceready", onDeviceReady, false);
// PhoneGap加载完毕
function onDeviceReady() {
//按钮事件
    document.addEventListener("backbutton", eventBackButton, false); //返回键
    document.addEventListener("menubutton", eventMenuButton, false); //菜单键
    document.addEventListener("searchbutton", eventSearchButton, false); //搜索键
}
//返回键
function eventBackButton(){
/*confirm("再点击一次退出!");*/                              
    window.plugins.ToastPlugin.show_short('再点击一次退出!');
    document.removeEventListener("backbutton", eventBackButton, false); //注销返回键
    //3秒后重新注册
    var intervalID = window.setInterval(
        function() {
            window.clearInterval(intervalID);
            document.addEventListener("backbutton", eventBackButton, false); //返回键
        },
        3000
    );
}
eventBackButton();
//菜单键
function eventMenuButton(){
    window.plugins.ToastPlugin.show_short('点击了 菜单 按钮!');
}
//搜索键
function eventSearchButton(){
    window.plugins.ToastPlugin.show_short('点击了 搜索 按钮!');
}