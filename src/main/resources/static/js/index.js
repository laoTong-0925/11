$(document).ready(
    setTimeout(function () {
        var account = getCookie(ACCOUNT);
        if (account !== "" ) {
            $("#account").text(account);
        } else {
            $("#user_play").css("display","inline");
        }
    }, 500)
);

var ACCOUNT = "account";


function getCookie(name) {
    var cookieArray = document.cookie.split("; ");//得到分割的cookie名值对
    for (var i = 0; i < cookieArray.length; i++) {
        var arr = cookieArray[i].split("=");//将名和值分开
        if (arr[0] == name) return unescape(arr[1]);//如果是指定的cookie，则返回它的值
    }
    return "";
}