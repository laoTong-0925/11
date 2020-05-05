var URL = "http://localhost:8080/shopping";
var ACCOUNT = "account";

$(document).ready(
    setTimeout(function () {
        var account = getCookie(ACCOUNT);
        if (account !== "") {
            $("#user_account_r2").text(account);
            $("#user_account_r1").text(account);
            $("#userCentre_li").attr("href", URL + '?nkName=' + account);
        }
    }, 200),
);

function subInfo() {
    var nkName = getCookie(ACCOUNT);
    var phone = $("#phone").val();
    var eMail = $("#eMail").val();
    $.ajax({
        url: "http://localhost:8080/shopping/userInfo-update",
        data: {nkName: nkName, phone: phone, eMail: eMail},
        dataType: "json",
        type: "post",
        async: false,
        cache: false,
        success: function (data) {
            if (data['code'] === 200) {
                window.location.href = "http://localhost:8080/shopping/userInfo?nkName="+nkName;
            } else {
                alert("修改失败")
            }
        },
        error: function () {
            alert("系统繁忙请稍后再试！！！")
        }
    });
}

$(function () {
    $("#randCode").change(function () {
        var code = $("#randCode").val();
        if (code.length == 4) {
            var cookie = getCookie('bs-verifyCode');
            var element = $(document.getElementsByClassName("focusa"));
            $.ajax({
                url: URL + "/verify-code-img",
                data: {code: code, sessionCode: cookie},
                dataType: "json",
                type: "post",
                async: false,
                cache: false,
                success: function (data) {
                    if (data['code'] == 200) {
                        element.html(data['data']);
                        element.css("color", "green");
                    }
                    if (data['code'] == 400) {
                        element.html(data['data']);
                        element.css("color", "red");
                    }
                },
                error: function () {
                    alert("系统繁忙请稍后再试！！！");
                }
            })
        }
    });
});

function getCookie(name) {
    var cookieArray = document.cookie.split("; ");//得到分割的cookie名值对
    for (var i = 0; i < cookieArray.length; i++) {
        var arr = cookieArray[i].split("=");//将名和值分开
        if (arr[0] == name) return unescape(arr[1]);//如果是指定的cookie，则返回它的值
    }
    return "";
}