$(document).ready(function (){

    $("#addJob").click(function (){
    // alert("testing...")
        $.ajax({
            method: "post",
            url: "http://localhost:8080/crm/api/groupwork-add",
            data:{
                name : $("#name").val(),
                startDate :  $("#startDate").val(),
                endDate :  $("#endDate").val(),
            }
        }).done(function(data) {

            if(data.success){
                alert("Thêm thành công")
            }else{
                alert("Thêm thất bại")
            }
            window.location.href ="http://localhost:8080/crm/groupwork"
        });
    })

})