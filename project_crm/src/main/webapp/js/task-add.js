$(document).ready(function (){

    $("#addTask").click(function (){
    // alert("testing...")
        $.ajax({
            method: "post",
            url: "http://localhost:8080/crm/api/task-add",
            data:{

                name : $("#taskName").val(),
                startDate :$("#startDate").val(),
                endDate :$("#endDate").val(),
                userId :$("#userID").val(),
                jobId :$("#jobID").val(),
                statusId :'1'
            }
        }).done(function(data) {

            if(data.success){
                alert("Thêm thành công")
            }else{
                alert("Thêm thất bại")
            }
            window.location.href ="http://localhost:8080/crm/task-page"
        });
    })

})