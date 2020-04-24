var URL = "http://localhost:8080/shopping";


function release() {
    var name = $("#name").val();
    var pro1 = $("#pro1").val();
    var pro1KC = $("#pro1Kc").val();
    var pro1money = $("#pro1money").val();
    var pro2 = $("#pro2").val();
    var pro2KC = $("#pro2Kc").val();
    var pro2money = $("#pro2money").val();
    var pro3 = $("#pro3").val();
    var pro3KC = $("#pro3Kc").val();
    var pro3money = $("#pro3money").val();

    var detail = $("#detail").val();
    // var file = $("#file").val();
//todo参数判断

    $.ajax({
        url: URL + "/admin/release",
        data: {
            name: name, detail: detail, pro1: pro1, pro1Kc: pro1KC, pro1m: pro1money,
            pro2: pro2, pro2Kc: pro2KC, pro2m: pro2money,
            pro3: pro3, pro3Kc: pro3KC, pro3m: pro3money
        },
        dataType: "json",
        type: "post",
        async: false,
        cache: false,
        success: function (data) {
            // if (data['code'] === 200) {
            //     window.location.href = "http://localhost:8080/shopping/collections?nkName=" + nkName;
            // } else {
            //     alert("商品不存在购物车")
            // }
        },
        error: function () {
            alert("系统繁忙请稍后再试！！！")
        }
    });
}