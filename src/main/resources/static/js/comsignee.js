function update(id) {
    var shdId = id.substr(6);
    $(document.getElementById('shd' + shdId)).attr("disabled", false);
    $(document.getElementById('shr' + shdId)).attr("disabled", false);
    $(document.getElementById('phone' + shdId)).attr("disabled", false);
}

var nkName = getCookie(ACCOUNT);


function submitUpdate(id) {
    var shdId = id.substr(12);
    var shd = $(document.getElementById("shd" + shdId)).val();
    var shr = $(document.getElementById('shr' + shdId)).val();
    var phone = $(document.getElementById('phone' + shdId)).val();
    $.ajax({
        url: URL + "/myConsignee/update",
        data: {nkName: nkName, consigneeId: shdId, consignee: shd, consigneeMan: shr, phone: phone},
        dataType: "json",
        type: "post",
        async: false,
        cache: false,
        success: function (d) {
            if (d['code'] === 200)
                alert("修改成功");
            window.location.href = URL + "/myConsignee?nkName=" + nkName;

        },
        error: function () {
            alert("修改失败！！！")

        }
    });
}

function remove(id) {
    var shdId = id.substr(6);
    if (confirm("是否删除此地址？") === false)
        return;
    $.ajax({
        url: URL + "/myConsignee/del",
        data: {nkName: nkName, consigneeId: shdId},
        dataType: "json",
        type: "post",
        async: false,
        cache: false,
        success: function (d) {
            if (d['code'] === 200)
                alert("删除成功");
            window.location.href = URL + "/myConsignee?nkName=" + nkName;

        },
        error: function () {
            alert("删除失败！！！")

        }
    });

}

function add(){
    var shr = $("#shr").val();
    var shdxx = $("#shdxx").val();
    var phone = $("#phone").val();
    if (shr === '' || shdxx===''||phone===''){
        alert("请填写完整")
        return;
    }
    $.ajax({
        url: URL + "/myConsignee/add",
        data: {nkName: nkName, consignee: shdxx, consigneeMan: shr, phone: phone},
        dataType: "json",
        type: "post",
        async: false,
        cache: false,
        success: function (d) {
            if (d['code'] === 200)
                alert(d['message']);
            window.location.href = URL + "/myConsignee?nkName=" + nkName;

        },
        error: function () {
            alert("添加失败！！！")

        }
    });

}