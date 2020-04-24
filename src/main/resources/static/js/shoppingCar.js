// $(document).ready(function(){
//     $(".sp").change(
//         function () {
//             var e = $(".sp");
//             if (e.checkbox)
//                 alert(e.checkbox)
//         }
//     );
// });

function checkO(id) {
    var e = $(document.getElementById(id));
    var checkedOfAll = e.prop("checked");
    if (checkedOfAll === true) {
        var parame = $("#paySome");
        var param = parame.val();
        parame.val(param + ',' + e.val());
    }
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

function pay() {
    if (confirm("是否进行下单结算？") === false)
        return;
    var parame = $("#paySome").val();
    var isAll = $(document.getElementById("#isAll")).prop("checked");
    var nkName = getCookie(ACCOUNT);
    var shd = $("#shd").val().substr(5);
    if (shd === '' || shd === null) {
        alert("请选择收货地址");
        return;
    }
    if (parame.split(",").length === 1) {
        alert("请选择商品结算");
        return;
    }
    $.ajax({
        url: URL + "/collections/creatOrder",
        data: {nkName: nkName, isAll: isAll, ids: parame, consigneeId: shd},
        dataType: "json",
        type: "post",
        async: false,
        cache: false,
        success: function (data) {
            if (data['code'] === 200) {
                var payPass = md5(prompt("下单成功，请输入支付密码"));
                var orderIds = data['data'];
                alert(data['data'].toString());
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
                            window.location.href = "http://localhost:8080/shopping/collections?nkName=" + nkName;
                        } else {
                            alert(d['message'])
                        }
                    },
                    error: function () {
                        alert("支付失败！！！")

                    }
                });

            }

            // window.location.href = "http://localhost:8080/shopping/collections?nkName=" + nkName;
        },
        error: function () {
            alert("系统繁忙请稍后再试！！！")
        }
    });
}

function removeFromShoppingCar(id) {
    var nkName = getCookie(ACCOUNT);
    var userCarCommodityIde = 'userCarCommodityId' + id;
    var userCarCommodityId = $(document.getElementById(userCarCommodityIde)).val();
    $.ajax({
        url: URL + "/collections/remove",
        data: {nkName: nkName, userCarCommodityId: userCarCommodityId},
        dataType: "json",
        type: "post",
        async: false,
        cache: false,
        success: function (data) {
            if (data['code'] === 200) {
                window.location.href = "http://localhost:8080/shopping/collections?nkName=" + nkName;
            } else {
                alert("商品不存在购物车")
            }
        },
        error: function () {
            alert("系统繁忙请稍后再试！！！")
        }
    });

}
