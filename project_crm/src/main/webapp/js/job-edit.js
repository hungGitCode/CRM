$(document).ready(function (){

    $("#updateJob").click(function (){
        // alert("test")
        $.ajax({
            method: "post",
            url: "http://localhost:8080/crm/api/groupwork-edit",
            data:{
                id : $("#jobId").val(),
                name : $("#name").val(),
                startDate :$("#startDate").val(),
                endDate :$("#endDate").val(),
            }
        }).done(function(data) {
            // alert(data.description+ "123")
            // alert("1+2")
            if(data.success){
                alert("Update thành công")
            }else{
                alert("Update thất bại")
            }
            window.location.href ="http://localhost:8080/crm/groupwork"
        });
    })

})