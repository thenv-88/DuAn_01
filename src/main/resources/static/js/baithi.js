$(document).ready(function () {
    $("#addformbaithi").validate({
        rules: {
            diem: {
                required : true,
            },

            sinhvien: {
                required: true,

            },

            monhoc: {
                required: true,

            },


        },
        messages: {
            diem:{
                required: "Điểm không được trống"
            },
            sinhvien:{
                required: "Sinh viên không được trống"
            },
            monhoc: {
                required: "Môn học không được trống",
            },


        }
    });
    $("#edit_baithi").validate({
        rules: {
            diemedit: {
                required : true,
            },

            sinhvienedit: {
                required: true,

            },

            monhocedit: {
                required: true,

            },


        },
        messages: {
            diemedit:{
                required: "Điểm không được trống"
            },
            sinhvienedit:{
                required: "Sinh viên không được trống"
            },
            monhocedit: {
                required: "Môn học không được trống",
            },


        }
    });
    $("#btn_add_baithi").click(function () {
        $('#modaladdbaithi').modal('show');

        $("#sb").click(function (e) {
            let diem = $("#diem").val();
            let mamonhoc = $("#monhoc").val();
            let masinhvien = $("#sinhvien").val();
            let data = {
                "diem": diem,
                "maMonHoc": mamonhoc ,
                "maSinhVien": masinhvien,
            };
            console.log(data);
            if($("#addformbaithi").valid()) {
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/admin/baithi/post",
                    dataType: "json",
                    data: JSON.stringify(data),
                    success: function (response) {
                        console.log(response);
                        location.reload();
                        $('#modaladdbaithi').modal("hide");
                    },
                    error:function (r){
                        console.log("ko đúng ");
                    }
                })
            }

        });

    });
    var id;
    $(".editbaithi").click(function () {
        $("#modaleditbaithi").modal("show");

        id = $(this).data("id");
        console.log(id);
        $.ajax({
            type:"GET",
            url : "/admin/baithi/get/" + id,
            success: function (response){
                console.log(response);
                $("#diemedit").val(response.diem);
                $("#monhocedit").val(response.maMonHoc);
                $("#sinhvienedit").val(response.maSinhVien);
            }

        });

        $("#edit").click(function (){
            let diem = $("#diemedit").val();
            let mamonhoc = $("#monhocedit").val();
            let masinhvien = $("#sinhvienedit").val();
            let data = {
                "diem": diem,
                "maMonHoc": mamonhoc ,
                "maSinhVien": masinhvien,
            };
            console.log(data);
            if($("#edit_baithi").valid()) {
                $.ajax({
                    type: "PUT",
                    contentType: "application/json",
                    url: "/admin/baithi/put/" + id,
                    dataType: "json",
                    data: JSON.stringify(data),
                    success: function () {
                        location.reload();
                        console.log("hưng");
                        $("#modaleditbaithi").modal("hide");
                    }
                })
            }
        })
    })

    $(".deletebaithi").click(function () {
        $("#modaldeletebaithi").modal("show");

        id = $(this).data("id");
        console.log(id);


    });
    $("#delete").click(function (){

        $.ajax({

            url: "/admin/baithi/delete/" + id,
            type: "DELETE",

            success: function (response){
                console.log(response);
                $("#"+id).remove();
                $("#modaldeletebaithi").modal("hide");
            },
            error: function (){
                alert("Không hợp lệ");
            }
        })

    });
});