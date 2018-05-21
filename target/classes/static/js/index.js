$(function () {
    btnSubmit();
})

function btnSubmit() {
    $("#btnSubmit").click(function () {
        var name = $("#name").val();
        var age = $("#age").val();
        var data = {
            "name":name,
            "age":age
        };
        $.ajax({
            url:"/putBaseUser",
            data:data,
            type:"PUT",
            success:function (data) {

            },
            error:function () {

            }
        })
    });
}