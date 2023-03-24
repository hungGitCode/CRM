$(document).ready(function (){

    $("#updateUser").click(function (){
        // alert("testing....5")
        $.ajax({
            method: "post",
            url: "http://localhost:8080/crm/api/user-edit",
            data:{
                email : $("#email").val(),
                fullName :$("#fullName").val(),
                phone: $("#phone").val(),
                country: $("#country").val(),
                roleId :$("#roleId").val(),
                id :$("#userId").val(),
            }
        }).done(function (data) {
                if(data.success){
                    alert("Update thành công")
                }else{
                    alert("Update thất bại")
                }
            window.location.href ="http://localhost:8080/crm/user-page"
        })
        // var test = "test"
        // $.ajax({
        //     method: "get",
        //     url: "http://localhost:8080/crm/api/user-edit?name="+test,
        // }).done(function(data) {
        //     alert("message" + test)
        // });

    })

})