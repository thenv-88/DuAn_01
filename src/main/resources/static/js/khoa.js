$(document).ready(function () {
    $("#addformkhoa").validate({
        rules: {
            makhoa: {
                required : true,
            },
            name: {
                required : true,
            },

            diachi: {
                required: true,

            },

        },
        messages: {
            makhoa:{
                required: "Mã khoa không được bỏ trống"
            },
            name:{
                required: "Bạn phải nhập tên"
            },
            diachi: {
                required: "Bạn phải nhập địa chỉ",
            },


        }
    });
    $("#edit_khoa").validate({
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
    $("#add_khoa").click(function () {
        $('#modaladdkhoa').modal('show');
        $(".select2-init").select2({
            allowClear: true
        });
        $("#sb").click(function (e) {
            let name = $("#name").val();
            let makhoa = $("#makhoa").val();
            let diachi = $("#diachi").val();
            let data = {
                "maKhoa": makhoa,
                "tenKhoa": name,
                "diaChi": diachi,
            };
            console.log(data);
            if($("#addformkhoa").valid()) {
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/admin/khoa/post",
                    dataType: "json",
                    data: JSON.stringify(data),
                    success: function (response) {
                        console.log(response);
                        location.reload();
                        $('#modaladdkhoa').modal("hide");
                    },
                    error:function (){
                        alert("Khoa đã tồn tại");
                    }

                })
            }

        });

    });
    var id;
    $(".editkhoa").click(function () {
        $("#modaleditkhoa").modal("show");
        $(".select2-init").select2({
            allowClear: true
        });
        id = $(this).data("id");
        console.log(id);
        $.ajax({
            type:"GET",
            url : "/admin/khoa/get/" + id,
            success: function (response){
                $("#nameedit").val(response.tenKhoa);
                $("#diachiedit").val(response.diaChi);
            }

        });

        $("#edit").click(function (){
            let name = $("#nameedit").val();
            let diachi = $("#diachiedit").val();
            let data = {
                "tenKhoa": name,
                "diaChi": diachi,
            }
            console.log(data);
            if($("#edit_khoa").valid()) {
                $.ajax({
                    type: "PUT",
                    contentType: "application/json",
                    url: "/admin/khoa/put/" + id,
                    dataType: "json",
                    data: JSON.stringify(data),
                    success: function () {
                        location.reload();
                        console.log("hưng");
                        $("#modaleditkhoa").modal("hide");
                    }
                })
            }
        })
    })

    $(".deletekhoa").click(function () {
        $("#modaldeletekhoa").modal("show");

        id = $(this).data("id");
        console.log(id);


    });
    $("#delete").click(function (){

        $.ajax({

            url: "/admin/khoa/delete/" + id,
            type: "DELETE",

            success: function (response){
                console.log(response);
                $("#"+id).remove();
                $("#modaldeletekhoa").modal("hide");
            },
            error: function (){
                alert("Không hợp lệ");
            }
        })

    });
});