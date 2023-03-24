$(document).ready(function (){

    $("#addUser").click(function (){

        $.ajax({
            method: "post",
            url: "http://localhost:8080/crm/api/user-add",
            data:{
                fullname : $("#fullname").val(),
                email :  $("#email").val(),
                password :  $("#password").val(),
                phoneNum :$("#phoneNum").val(),
                roleID :$("#roleID").val(),
                country :$("#country").val(),
            }
        }).done(function(data) {

            if(data.success){
                alert("Thêm thành công")
            }else{
                alert("Thêm thất bại")
            }
            window.location.href="http://localhost:8080/crm/user-page"
        });
    })

})