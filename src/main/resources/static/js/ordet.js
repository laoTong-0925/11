$(document).ready(
    function () {
        var account = getCookie("account");
        var nextPage = $("#nextPage_input").val();
        var beforePage = $("#beforePage_input").val();
        var lastPage = $("#lastPage_input").val();
        if (account !== "") {
            $("#account").text(account);
            $("#nextPage").attr("href", '/shopping/myOrder?nkName=' + account + '&index=' + nextPage);
            $("#beforePage").attr("href", '/shopping/myOrder?nkName=' + account + '&index=' + beforePage);
            $("#lastPage").attr("href", '/shopping/myOrder?nkName=' + account + '&index=' + lastPage);
            $("#headPage").attr("href", '/shopping/myOrder?nkName=' + account + '&index=0');
        } else {
            $("#nextPage").attr("href", '/shopping/myOrder?index=' + nextPage);
            $("#beforePage").attr("href", '/shopping/myOrder?index=' + beforePage);
            $("#lastPage").attr("href", '/shopping/myOrder?index=' + lastPage);
            $("#headPage").attr("href", '/shopping/myOrder?index=0');
            $("#user_play").css("display", "inline");
        }
    }
);


function pay() {
    var account = getCookie("account");
    var payPass = md5(prompt("请输入支付密码"));
    var orderIds = $("#orderId").val();
    $.ajax({
        url: URL + "/order/pay",
        data: {nkName: account, passWord: payPass, orderIds: orderIds},
        dataType: "json",
        type: "post",
        async: false,
        cache: false,
        success: function (d) {
            if (d['code'] === 200) {
                alert("支付成功，感谢购买!!!")
                window.location.href = "http://localhost:8080/shopping/myOrder?nkName=" + account;
            } else {
                alert(d['message'])
            }
        },
        error: function () {
            alert("系统繁忙！！！")

        }
    });

}

function remove() {
    var orderIds = $("#orderId").val();
    $.ajax({
        url: URL + "/order/remove",
        data: {id: orderIds},
        dataType: "json",
        type: "post",
        async: false,
        cache: false,
        success: function (d) {
            if (d['code'] === 200) {
                alert("取消订单成功")
                window.location.href = "http://localhost:8080/shopping/myOrder?nkName=" + account;
            } else {
                alert(d['message'])
            }
        },
        error: function () {
            alert("支付失败！！！")

        }
    });

}
var account = getCookie("account");

function complete() {
    var orderIds = $("#orderId").val();
    $.ajax({
        url: URL + "/order/complete",
        data: {id: orderIds},
        dataType: "json",
        type: "post",
        async: false,
        cache: false,
        success: function (d) {
            if (d['code'] === 200) {
                alert("取消订单成功")
                window.location.href = "http://localhost:8080/shopping/myOrder?nkName=" + account;
            } else {
                alert(d['message'])
            }
        },
        error: function () {
            alert("支付失败！！！")

        }
    });

}
