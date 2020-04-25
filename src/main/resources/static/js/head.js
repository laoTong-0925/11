var URL = "http://localhost:8080/shopping";

$(document).ready(
    setTimeout(function () {
        var account = getCookie(ACCOUNT);
        if (account !== "") {
            $("#account").text(account);
            $("#userAfter").css("display", "inline");
            $("#userCentre_li").attr("href", URL + '/userCentre?nkName=' + account);
            $("#my_shopping_car").attr("href", URL + '/collections?nkName=' + account);
            $("#products_list").attr("href", URL + '/commodityList?nkName=' + account)
            $("#searchNkName").val(account);
        } else {
            $("#user_play").css("display", "inline");
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
    expires = " + exdate.toGMTString() + ";
    path = "/";
}

// function search() {
//     var account = getCookie(ACCOUNT);
//     var searchName=$("#searchName").val().trim();
//     $.ajax({
//         url: URL + "/commodityList",
//         data: {nkName: account, spName: searchName},
//         dataType: "json",
//         type: "post",
//         async: false,
//         cache: false,
//         error: function () {
//             alert("系统繁忙！！！")
//
//         }
//     });
// }