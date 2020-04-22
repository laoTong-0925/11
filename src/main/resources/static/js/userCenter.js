

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

