$(document).ready(function () {
    $("#addformsinhvien").validate({
        rules: {
            masinhvien: {
                required : true,
            },
            tensinhvien: {
                required : true,
            },

            date: {
                required: true,

            },
            diachi: {
                required: true,

            },
            lop: {
                required: true,

            },

        },
        messages: {
            masinhvien:{
                required: "Mã sinh viên không được bỏ trống"
            },
            tensinhvien:{
                required: "Bạn phải nhập tên sinh viên"
            },
            diachi: {
                required: "Bạn phải nhập địa chỉ",
            },
            date: {
                required: "Bạn phải nhập ngày sinh",
            },
            lop: {
                required: "Lớp không được trống",
            },


        }
    });
    $("#edit_sinhvien").validate({
        rules: {
            tensinhvien: {
                required : true,
            },

            date: {
                required: true,

            },
            diachi: {
                required: true,

            },
            lop: {
                required: true,

            },

        },
        messages: {
            tensinhvien:{
                required: "Bạn phải nhập tên sinh viên"
            },
            diachi: {
                required: "Bạn phải nhập địa chỉ",
            },
            date: {
                required: "Bạn phải nhập ngày sinh",
            },
            lop: {
                required: "Lớp không được trống",
            },


        }
    });
    $("#btn_add_sinhvien").click(function () {
        $('#modaladdsinhvien').modal('show');

        $("#sb").click(function (e) {
            let data = new FormData($('#addformsinhvien')[0]);

            console.log(data.getAll('multipartFile'));
            if($("#addformsinhvien").valid()) {
                $.ajax({
                    type: "POST",
                    data: data,
                    url: "/admin/sinhvien/post",
                    processData: false,
                    contentType: false,
                    cache:false,
                    success: function () {
                        location.reload();
                        console.log("hưng");
                        $("#modaladdsinhvien").modal("hide");
                    },
                    error: function () {
                        alert("sinh viên đã tồn tại");
                    }

                })
            }


        });

    });
    var id;
    $(".editsinhvien").click(function () {
        $("#modaleditsinhvien").modal("show");

        id = $(this).data("id");
        console.log(id);
        $.ajax({
            type:"GET",
            url : "/admin/sinhvien/get/" + id,
            success: function (response){
                console.log(response);
                $("#tensinhvienedit").val(response.tenSV);
                $("#lopedit").val(response.maLop);
                $("#dateedit").val(response.date);
                $("#diachiedit").val(response.diaChi);
                $("#imageedit").val(response.image);


            }

        });

        $("#edit").click(function (){

            let data = new FormData($('#edit_sinhvien')[0]);
            console.log(data);
            if($("#edit_sinhvien").valid()) {
                $.ajax({
                    type: "PUT",
                    url: "/admin/sinhvien/put/" + id,
                    processData: false,
                    contentType: false,
                    cache:false,
                    data: data,
                    success: function () {
                        location.reload();
                        console.log("hưng");
                        $("#modaleditsinhvien").modal("hide");
                    }
                })
            }
        })
    })

    $(".deletesinhvien").click(function () {
        $("#modaldeletesinhvien").modal("show");

        id = $(this).data("id");
        console.log(id);


    });
    $("#delete").click(function (){

        $.ajax({

            url: "/admin/sinhvien/delete/" + id,
            type: "DELETE",
            success: function (response){
                $("#"+id).remove();
                $("#modaldeletesinhvien").modal("hide");
            },
            error: function (){
                alert("Không hợp lệ");
            }
        })

    });
});