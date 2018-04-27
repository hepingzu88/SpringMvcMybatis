$(function () {
    _returnData($("#pageNum").val());
    $("#refresh").click(function () {
        location.reload();
    });
})

function jump(url,pageNum) {
    if(pageNum!=null && pageNum!=''){
        $("#pageNum").val(pageNum);
    }
    $.ajax({
        url : url,
        type: "POST",
        data: $("#_form").serialize(),
        success: function(data){
            $("#form_content").html(data);
        }
    })

}


