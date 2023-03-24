$(document).ready(function (){

    $("#updateTask").click(function (){
        // alert("test")
        $.ajax({
            method: "post",
            url: "http://localhost:8080/crm/api/task-edit",
            data:{
                id : $("#taskId").val(),
                name : $("#taskName").val(),
                startDate :$("#startDate").val(),
                endDate :$("#endDate").val(),
                userId :$("#userID").val(),
                jobId :$("#jobID").val(),
                statusId :$("#statusID").val(),
            }
        }).done(function(data) {
            // alert(data.description+ "123")
            // alert("1+2")
            if(data.success){
                alert("Update thành công")
            }else{
                alert("Update thất bại")
            }
            window.location.href ="http://localhost:8080/crm/task-page"
        });
    })

})