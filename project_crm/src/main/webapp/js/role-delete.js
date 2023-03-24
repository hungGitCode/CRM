$(document).ready(function (){

    $(".btn-danger").click(function (){
        var roldId = $("#roleId").val()
        if(roldId!=3){
        // alert( "id"+id)

        var id = $(this).attr("DeleteId")
        var This = $(this)
        // alert("id" + id)
        $.ajax({
            url: "http://localhost:8080/crm/api/deleteRoleById?id="+id,
            method: "get",
                // data: { name: "John", location: "Boston" }
            success: function(result) {
                if(result.success){
                    This.closest("tr").remove()
                    alert("Xoá thành công")
                }else {
                    alert("Xoá thất bại")
                }
            }
            })

        }else{
            window.location.href ="http://localhost:8080/crm/404.html"
        }
    })
    //
    // // $(".btn-primary").click(function (){
    //     var id = $(this).attr("EditID")
    //     // alert("id = " + id)
    //     $.ajax({
    //         type:"get",
    //         url: "http://localhost:8080/crm/role-edit?id="+id,
    //         // type:"post",
    //         // url: "http://localhost:8080/crm/role-edit",
    //         // data : { "id" : id}
    //     })
    // })




})