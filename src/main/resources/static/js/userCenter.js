function getCookie(name) {
    var cookieArray = document.cookie.split("; ");//得到分割的cookie名值对
    for (var i = 0; i < cookieArray.length; i++) {
        var arr = cookieArray[i].split("=");//将名和值分开
        if (arr[0] == name) return unescape(arr[1]);//如果是指定的cookie，则返回它的值
    }
    return "";
}
var URL = "http://localhost:8080/shopping";
var ACCOUNT = "account";

//构建url
$(document).ready(
    setTimeout(function () {
        var account = getCookie(ACCOUNT);
        if (account !== "" ) {
            $("#account").text(account);
            $("#userAfter").css("display","inline");
            $("#userCentre_li").attr("href",URL+'/userCentre?nkName='+account);
            $("#user1").text(account);
            $("#myOrder").attr("href",URL+'/myOrder?nkName='+account);
            $("#myConsignee").attr("href",URL+'/myConsignee?nkName='+account);
            $("#userInfo").attr("href",URL+'/userInfo?nkName='+account);
            $("#collections").attr("href",URL+'/collections?nkName='+account);
            $("#balance").attr("href",URL+'/balance?nkName='+account);
            $("#consumeLog").attr("href",URL+'/consumeLog?nkName='+account);
            $("#hidden_nkName").attr("value",account);
        }
        $.ajax({
            url: URL + "/get-user-img",
            data: {nkName: account},
            dataType: "json",
            type: "post",
            async: false,
            cache: false,
            success: function (data) {
                    $("#user_img").attr("src",data['data']);
            },
            error: function () {
            }
        });
    }, 500)
);

