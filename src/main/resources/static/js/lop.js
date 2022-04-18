$(document).ready(function () {
    $("#addformlop").validate({
        rules: {
            malop: {
                required : true,
            },
            tenlop: {
                required : true,
            },

            khoa: {
                required: true,

            },

        },
        messages: {
            malop:{
                required: "Mã lớp không được bỏ trống"
            },
            tenlop:{
                required: "Bạn phải nhập tên lớp"
            },
            khoa: {
                required: "Lớp phải thuộc khoa",
            },


        }
    });
    $("#edit_lop").validate({
        rules: {
            tenlop: {
                required : true,
            },

            khoa: {
                required: true,

            },

        },
        messages: {
            tenlop:{
                required: "Bạn phải nhập tên"
            },
            khoa: {
                required: "Lớp phải thuộc khoa",
            },


        }
    });
    $("#btn_add_lop").click(function () {
        $('#modaladdlop').modal('show');

        $("#sb").click(function (e) {
            let tenlop = $("#tenlop").val();
            let malop = $("#malop").val();
            let khoa = $("#khoa").val();
            let data = {
                "maLop": malop,
                "tenLop": tenlop ,
                "tenKhoa": khoa,
            };
            console.log(data);
            if($("#addformlop").valid()) {
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/admin/lop/post",
                    dataType: "json",
                    data: JSON.stringify(data),
                    success: function (response) {
                        console.log(response);
                        location.reload();
                        $('#modaladdlop').modal("hide");
                    },
                    error:function (){
                        alert("Lớp đã tồn tại");
                    }

                })
            }

        });

    });
    var id;
    $(".editlop").click(function () {
        $("#modaleditlop").modal("show");

        id = $(this).val();
        console.log(id);
        $.ajax({
            type:"GET",
            url : "/admin/lop/get/" + id,
            success: function (response){
                console.log(response);
                $("#nameedit").val(response.tenLop);
                $("#khoaedit").val(response.tenKhoa);
            }

        });

        $("#edit").click(function (){
            let tenlop = $("#nameedit").val();
            let khoa = $("#khoaedit").val();
            let data = {
                "tenLop": tenlop ,
                "tenKhoa": khoa,
            }
            console.log(data);
            if($("#edit_lop").valid()) {
                $.ajax({
                    type: "PUT",
                    contentType: "application/json",
                    url: "/admin/lop/put/" + id,
                    dataType: "json",
                    data: JSON.stringify(data),
                    success: function () {
                        location.reload();
                        console.log("hưng");
                        $("#modaleditlop").modal("hide");
                    }
                })
            }
        })
    })
    $(".deletelop").click(function () {
        $("#modaldeletelop").modal("show");
        id = $(this).val();
        console.log(id);

    });
    $("#delete").click(function (){

        $.ajax({

            url: "/admin/lop/delete/" + id,
            type: "DELETE",

            success: function (response){
                console.log(response);
                $("#modaldeletelop").modal("hide");
                $("#"+id).remove();

            },
            error: function (){
                alert("Không hợp lệ");
            }
        })

    })
});