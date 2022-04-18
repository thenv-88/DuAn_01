$(document).ready(function () {
    $("#addformmonhoc").validate({
        rules: {
            mamonhoc: {
                required : true,
            },

            tenmonhoc: {
                required: true,

            },



        },
        messages: {

            mamonhoc:{
                required: "Mã môn học không được trống",
            },
            tenmonhoc: {
                required: "Tên môn học không được trống",
            },


        }
    });
    $("#edit_monhoc").validate({
        rules: {
            tenmonhocedit: {
                required: true,

            },


        },
        messages: {

            tenmonhocedit: {
                required: "Tên môn học không được trống",
            },

        }
    });
    $("#btn_add_monhoc").click(function () {
        $('#modaladdmonhoc').modal('show');

        $("#sb").click(function (e) {

            let data = {
                "maMonHoc": $("#mamonhoc").val(),
                "tenMonHoc": $("#tenmonhoc").val() ,
            };
            console.log(data);
            if($("#addformmonhoc").valid()) {
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/admin/monhoc/post",
                    dataType: "json",
                    data: JSON.stringify(data),
                    success: function (response) {
                        console.log(response);
                        location.reload();
                        $('#modaladdmonhoc').modal("hide");
                    },
                    error:function (){
                        alert("Môn học đã tồn tại");
                    }

                })
            }

        });

    });
    var id;
    $(".editmonhoc").click(function () {
        $("#modaleditmonhoc").modal("show");

        id = $(this).data("id");
        console.log(id);
        $.ajax({
            type:"GET",
            url : "/admin/monhoc/get/" + id,
            success: function (response){
                console.log(response);
                $("#tenmonhocedit").val(response.tenMonHoc);
            }

        });

        $("#edit").click(function (){

            let data = {
                "tenMonHoc": $("#tenmonhocedit").val() ,
            }
            console.log(data);
            if($("#edit_monhoc").valid()) {
                $.ajax({
                    type: "PUT",
                    contentType: "application/json",
                    url: "/admin/monhoc/put/" + id,
                    dataType: "json",
                    data: JSON.stringify(data),
                    success: function () {
                        location.reload();
                        console.log("hưng");
                        $("#modaleditmonhoc").modal("hide");
                    }
                })
            }
        })
    })

    $(".deletemonhoc").click(function () {
        $("#modaldeletemonhoc").modal("show");

        id = $(this).data("id");
        console.log(id);


    });
    $("#delete").click(function (){

        $.ajax({

            url: "/admin/monhoc/delete/" + id,
            type: "DELETE",

            success: function (response){
                console.log(response);
                $("#"+id).remove();
                $("#modaldeletemonhoc").modal("hide");
            },
            error: function (){
                alert("Không hợp lệ");
            }
        })

    });
});