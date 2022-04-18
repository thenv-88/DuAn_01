$(document).ready(function () {
    $("#addform").validate({
        rules: {
            name: {
                required : true,
            },

            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 6
            },
        },
        messages: {
            name:{
                required: "Bạn phải nhập tên"
            },
            email: {
                required: "Bạn phải nhập email",
                email: "Chưa đúng định dạng email"
            },
            password: {
                required: "Bạn phải nhập mật khẩu",
                minlength: "Mật khẩu tối thiểu 6 ký tự"
            }

        }
    });
    $("#editu").validate({
        rules: {
            nameedit: {
                required : true,
            },

            emailedit: {
                required: true,
                email: true
            },
            passwordedit: {
                required: true,
                minlength: 6
            },
        },
        messages: {
            nameedit:{
                required: "Bạn phải nhập tên"
            },
            emailedit: {
                required: "Bạn phải nhập email",
                email: "Chưa đúng định dạng email"
            },
            passwordedit: {
                required: "Bạn phải nhập mật khẩu",
                minlength: "Mật khẩu tối thiểu 6 ký tự"
            }

        }
    });

    $('#btn_add_user').click(function () {
        $('#modaladduser').modal('show');
        $(".select2-init").select2({
            allowClear: true
        });
        $("#sb").click(function (e) {
            let name = $("#name").val();
            let password = $("#password").val();
            let email = $("#email").val();
            let role = $("#roles").val();
            console.log(role);
            let data = {
                "name": name,
                "email": email,
                "password": password,
                "roles": role
            }
            if($("#addform").valid()) {
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/admin/user/post",
                    dataType: "json",
                    data: JSON.stringify(data),
                    success: function (response) {
                        console.log(response);
                        location.reload();
                        $('#modaladduser').modal("hide");
                    },
                    error:function (){
                        alert("Email đã tồn tại");
                    }
                })
            }

        });

    });

    var id;
    $(".edituser").click(function () {
        $("#modaledituser").modal("show");
        $(".select2-init").select2({
            allowClear: true
        });
        id = $(this).data("id");
        console.log(id);
        $.ajax({
            type:"GET",
            url : "/admin/user/get/" + id,
            success: function (response){
                $("#nameedit").val(response.name);
                $("#emailedit").val(response.email);
            }

        });

        $("#edit").click(function (){
            let name = $("#nameedit").val();
            let password = $("#passwordedit").val();
            let email = $("#emailedit").val();
            let role = $("#rolesedit").val();
            let data = {
                "name": name,
                "email": email,
                "password": password,
                "roles": role
            };
            if($("#editu").valid()) {
                $.ajax({
                    type: "PUT",
                    contentType: "application/json",
                    url: "/admin/user/put/" + id,
                    dataType: "json",
                    data: JSON.stringify(data),
                    success: function () {
                        location.reload();
                        console.log("hưng");
                        $("#modaledituser").modal("hide");
                    }
                })
            }
        })
    })

    $(".deleteuser").click(function () {
        $("#modaldeleteuser").modal("show");

        id = $(this).data("id");
        console.log(id);


    });
    $("#delete").click(function (){

        $.ajax({

            url: "/admin/user/delete/" + id,
            type: "DELETE",

            success: function (response){
                console.log(response);
                $("#"+id).remove();
                $("#modaldeleteuser").modal("hide");
            }
        })

    });
});