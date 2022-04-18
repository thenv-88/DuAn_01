$(document).ready(function () {
    $("#addformrole").validate({
        rules: {
            name: {
                required : true,
            },

            au: {
                required: true,

            },

        },
        messages: {
            name:{
                required: "Bạn phải nhập tên"
            },
            au: {
                required: "Bạn phải chọn quyền",
            },


        }
    });
    $("#edit_role").validate({
        rules: {
            name: {
                required : true,
            },

            au: {
                required: true,

            },

        },
        messages: {
            name:{
                required: "Bạn phải nhập tên"
            },
            au: {
                required: "Bạn phải chọn quyền",
            },


        }
    });
    $("#add_role").click(function () {
        $('#modaladdrole').modal('show');
        $(".select2-init").select2({
            allowClear: true
        });
        $("#sb").click(function (e) {
            let name = $("#name").val();
            let au = $("#au").val();
            let user = [];
            console.log(au);
            console.log(name);
            let data = {
                "name": name,
                "authoritys": au,
                "users": user,
            }
            if($("#addformrole").valid()) {
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/admin/role/post",
                    dataType: "json",
                    data: JSON.stringify(data),
                    success: function (response) {
                        console.log(response);
                        location.reload();
                        $('#modaladdrole').modal("hide");
                    },
                    error:function (){
                        alert("Tên đã tồn tại");
                    }

                })
            }

        });

    });
    var id;
    $(".editrole").click(function () {
        $("#modaleditrole").modal("show");
        $(".select2-init").select2({
            allowClear: true
        });
        id = $(this).data("id");
        console.log(id);
        $.ajax({
            type:"GET",
            url : "/admin/role/get/" + id,
            success: function (response){
                $("#nameedit").val(response.name);
            }

        });

        $("#edit").click(function (){
            let name = $("#nameedit").val();
            let au = $("#auedit").val();
            let data = {
                "name": name,
                "authoritys": au,
                "users": [],
            };
            console.log(data);
            if($("#edit_role").valid()) {
                $.ajax({
                    type: "PUT",
                    contentType: "application/json",
                    url: "/admin/role/put/" + id,
                    dataType: "json",
                    data: JSON.stringify(data),
                    success: function () {
                        location.reload();
                        console.log("hưng");
                        $("#modaleditrole").modal("hide");
                    }
                })
            }
        })
    })

    $(".deleterole").click(function () {
        $("#modaldeleterole").modal("show");

        id = $(this).data("id");
        console.log(id);


    });
    $("#delete").click(function (){

        $.ajax({

            url: "/admin/role/delete/" + id,
            type: "DELETE",

            success: function (response){
                console.log(response);
                $("#"+id).remove();
                $("#modaldeleterole").modal("hide");
            },
            error: function (){
                alert("Không hợp lệ");
            }
        })

    });
});