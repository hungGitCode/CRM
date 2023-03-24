$(document).ready(function (){
    // $(document).on('click', "#addRole", function() {

    $("#addRole").click(function (){

            var roleName = $("#rolename").val()
            var roleDescription = $("#roledescription").val()
        $.ajax({
            method: "get",
            url: "http://localhost:8080/crm/api/role-add?rolename="+roleName+ "&roledescription="+roleDescription,
        }).done(function(data) {
            if(data.success){
                alert("Thêm thành công")
            }else{
                alert("Thêm thất bại")
            }
            window.location.href="http://localhost:8080/crm/role-page"
        })
    })

})