function check(id) {
    var e = $(document.getElementById(id));

    if (e.attr("class") === "") {
        e.attr("class", "guige-cur");
    } else {
        e.attr("class", "");
    }
    //商品属性
    $("#pro").val(id);
}

function shChange(id) {
    var e = $(document.getElementById(id));
    var checkedOfAll = e.prop("checked");
    var p = $("#shd").val();
    if (checkedOfAll === true && p === '') {
        $("#shd").val(e.val());
    }
    if (checkedOfAll === false && p !== '') {
        $("#shd").val('');
    }

}

$(document).ready(
    setTimeout(function () {
        var isT = $("#isTicket").val();
        if (isT === '1') {
            $("#isTicketCheckBox").css("display", "inline");
        }
    }, 200)
);


function intoPoll() {
    var shd = $("#shd").val();
    if (shd === '' || shd === null) {
        alert("请选择收货地址");
        return;
    }
    var nkName = getCookie(ACCOUNT);
    var cId = $("#commodityId").val();
    var shdxxId = shd.substr(5);
    var shdxx = $(document.getElementById(shdxxId)).text();
    $.ajax({
        url: URL + "/into-activityPoll",
        data: {nkName: nkName, cId: cId, consignee: shdxx},
        dataType: "json",
        type: "post",
        async: false,
        cache: false,
        success: function (d) {
            alert(d['message'])
        },
        error: function () {
            alert("支付失败！！！")

        }
    });

}


function pay() {
    var shd = $("#shd").val();
    if (shd === '' || shd === null) {
        alert("请选择收货地址");
        return;
    }
    var pro = $("#pro").val();
    if (pro === '' || pro === null) {
        alert("请选择商品属性");
        return;
    }
    var sum = $("#sum").val();
    if (sum === '' || sum === null || sum === 0) {
        alert("请选择数量");
        return;
    }
    var shdxxId = shd.substr(5);
    var shdxx = $(document.getElementById(shdxxId)).text();

    if (confirm("商品属性：" + pro + "\n商品数量：" + sum + "\n收货地址：" + shdxx + "\n是否进行下单结算？") === false)
        return;
    var nkName = getCookie(ACCOUNT);
    var commodityId = $("#commodityId").val();
    //todo xi dan
    // var payPass = md5(prompt("下单成功，请输入支付密码"));
    // var orderIds = data['data'];
    $.ajax({
        url: URL + "/order/creat",
        data: {nkName: nkName, sum: sum, commodityId: commodityId, properties: pro, consigneeId: shdxxId},
        dataType: "json",
        type: "post",
        async: false,
        cache: false,
        success: function (data) {
            if (data['code'] === 200) {
                if (confirm("下单成功，是否进行支付") === false)
                    return;
                var payPass = md5(prompt("下单成功，请输入支付密码"));
                var orderIds = data['data'];
                $.ajax({
                    url: URL + "/order/pay",
                    data: {nkName: nkName, passWord: payPass, orderIds: orderIds},
                    dataType: "json",
                    type: "post",
                    async: false,
                    cache: false,
                    success: function (d) {
                        if (d['code'] === 200) {
                            alert("支付成功，感谢购买!!!")
                            // window.location.href = "http://localhost:8080/shopping/collections?nkName=" + nkName;
                        } else {
                            alert(d['message'])
                        }
                    },
                    error: function () {
                        alert("支付失败！！！")

                    }
                });
            } else {
                alert(d['message'])
            }
        },
        error: function () {
            alert("支付失败！！！")

        }
    });
}