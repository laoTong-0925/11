
$(document).ready(
    function () {
        var account = getCookie(ACCOUNT);
        var nextPage = $("#nextPage_input").val();
        var beforePage = $("#beforePage_input").val();
        var lastPage = $("#lastPage_input").val();
        if (account !== "" ) {
            $("#account").text(account);
            $("#nextPage").attr("href",'/shopping/consumeLog?nkName='+account+'&index='+nextPage);
            $("#beforePage").attr("href",'/shopping/consumeLog?nkName='+account+'&index='+beforePage);
            $("#lastPage").attr("href",'/shopping/consumeLog?nkName='+account+'&index='+lastPage);
            $("#headPage").attr("href",'/shopping/consumeLog?nkName='+account+'&index=0');
        } else {
            $("#nextPage").attr("href",'/shopping/consumeLog?index='+nextPage);
            $("#beforePage").attr("href",'/shopping/consumeLog?index='+beforePage);
            $("#lastPage").attr("href",'/shopping/consumeLog?index='+lastPage);
            $("#headPage").attr("href",'/shopping/consumeLog?index=0');
            $("#user_play").css("display","inline");
        }
    }
);