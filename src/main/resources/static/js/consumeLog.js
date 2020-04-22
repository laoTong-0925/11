$(document).ready(
    function () {
        var account = getCookie(ACCOUNT);
        if (account !== "" ) {
            $("#account").text(account);
            var nextPage = $("#nextPage_input").val();
            var beforePage = $("#beforePage_input").val();
            var lastPage = $("#lastPage_input").val();
            $("#nextPage").attr("href",'/shopping/consumeLog?nkName='+account+'&index='+nextPage);
            $("#beforePage").attr("href",'/shopping/consumeLog?nkName='+account+'&index='+beforePage);
            $("#lastPage").attr("href",'/shopping/consumeLog?nkName='+account+'&index='+lastPage);
            $("#headPage").attr("href",'/shopping/consumeLog?nkName='+account+'&index=0');
        } else {
            $("#user_play").css("display","inline");
        }
    }
);
