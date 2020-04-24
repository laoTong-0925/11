var URL = "http://localhost:8080/shopping";

$(document).ready(
    setTimeout(function () {
        var account = getCookie(ACCOUNT);
        if (account !== "" ) {
            $("#account").text(account);
            $("#userAfter").css("display","inline");
            $("#userCentre_li").attr("href",URL+'/userCentre?nkName='+account);
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

function clearCookie() {
    setCookie(ACCOUNT, "", -1);
    window.location.href = "http://localhost:8080/shopping/index"
}
function setCookie(c_name, value, expiredays) {
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + expiredays);
    document.cookie = c_name + "=" + escape(value) + "";
    expires=" + exdate.toGMTString() + ";
    path="/";
}