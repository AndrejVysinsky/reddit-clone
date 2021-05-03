<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
    </head>
    <body>
        <%@include file="navbar.jsp"%>

        <%
            String isModelValid = request.getParameter("isModelValid");

            String modelMessage = "";
            if (isModelValid != null)
            {
                modelMessage = "Zadané hodnoty sú neplatné!";
            }
        %>

        <form style="margin: 10px"  action="insert-sweet" id="formular">

            <span class="text-danger font-weight-bold"><%=modelMessage%></span>

            <div class="form-group row">
                <div class="form-group col-5">
                    <label class="font-weight-bold">Názov:</label>
                    <input class="form-control" value="" name="name">
                </div>
            </div>

            <div class="form-group row">
                <div class="form-group col-5">
                    <label class="font-weight-bold">Kategoria:</label>
                    <select class="form-control" name="categoryName" id="categoryName">
                    </select>
                </div>
            </div>

            <div class="form-group row">
                <div class="form-group col-5">
                    <label class="font-weight-bold">Hmotnosť:</label>
                    <input class="form-control" type="number" step="0.01" name="weight">
                </div>
            </div>

            <div class="form-group row">
                <div class="form-group col-5">
                    <label class="font-weight-bold">Cena:</label>
                    <input type="number" class="form-control" value="" name="price" step="0.01">
                </div>
            </div>

            <div class="form-group row">
                <div class="form-group col-5">
                    <label class="font-weight-bold">Alergény:</label>
                    <textarea class="form-control" name="allergens" cols="30" rows="10"></textarea>
                </div>
            </div>

            <button formmethod="post" class="btn btn-primary">Odoslať</button>

        </form>
    </body>

    <script>
        $( document ).ready(function() {

            $.ajax({
                url: 'select-category',
                type: 'POST',
                data: {
                    "categoryName": $('#categoryName').val()
                },
                success: function (data) {
                    var categories = "";

                    $.each(data, function(i, item) {
                        console.log(item);
                        categories += '<option value="' + item.categoryId + '">' + item.name + '</option>';
                    });

                    $('#categoryName').html(categories);
                },
                error: function (data) {
                    console.log(data);
                }
            })

            $('#formular').validate({
                rules: {
                    name: {
                        required: true,
                        rangelength: [5, 50]
                    },
                    weight: {
                        required: true,
                        range: [0.01, 999]
                    },
                    price: {
                        required: true,
                        range: [0.01, 999]
                    }
                }
            });
        });
    </script>
</html>