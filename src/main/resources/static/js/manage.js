function subModify(id) {
    if (confirm("是否进行修改？") === false)
        return;
    var pro1 = $("#pro0").val();
    var pro2 = $("#pro1").val();
    var pro3 = $("#pro2").val();
    var pro1Kc = $("#in0").val();
    var pro2Kc = $("#in1").val();
    var pro3Kc = $("#in2").val();
    var pro1m = $("#m0").val();
    var pro2m = $("#m1").val();
    var pro3m = $("#m2").val();
    var is_ticket = $("#is_ticket").val();

    $.ajax({
        url: URL + "/admin-sp-modify",
        data: {
            cId: id, pro1: pro1, pro1Kc: pro1Kc, pro1m: pro1m,
            pro2: pro2, pro2Kc: pro2Kc, pro2m: pro2m,
            pro3: pro3, pro3Kc: pro3Kc, pro3m: pro3m, isT: is_ticket
        },
        dataType: "json",
        type: "post",
        async: false,
        cache: false,
        success: function (data) {
            if (data['code'] === 200) {
                window.location.href = "http://localhost:8080/shopping/commodityManage";
            } else {
                alert("修改失败")
            }
        },
        error: function () {
            alert("系统繁忙请稍后再试！！！")
        }
    });
}

var URL = "http://localhost:8080/shopping";
