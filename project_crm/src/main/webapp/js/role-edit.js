$(document).ready(function (){

    $("#updateRole").click(function (){

        $.ajax({
            method: "post",
            url: "http://localhost:8080/crm/api/role-edit",
            data:{
                name : $("#name").val(),
                description :$("#description").val(),
                roleId :$("#roleId").val(),
            }
        }).done(function(data) {
            // alert(data.description+ "123")
            // alert("1+2")
            if(data.success){
                alert("Update thành công")
            }else{
                alert("Update thất bại")
            }
            window.location.href ="http://localhost:8080/crm/role-page";
        });
    })

})