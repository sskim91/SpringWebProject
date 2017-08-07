<%--
  Created by IntelliJ IDEA.
  User: sskim
  Date: 2017. 8. 7.
  Time: PM 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .fileDrop {
            width: 100%;
            height: 200px;
            border: 1px dotted blue;
        }
        small {
            margin-left: 3px;
            font-weight: bold;
            color: darkgray;
        }
    </style>
</head>
<body>
    <h3>Ajax File Upload</h3>
    <div class="fileDrop"></div>

    <div class="uploadedList"></div>

    <script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script>
        $(".fileDrop").on("dragenter dragover", function (event) {
            event.preventDefault();
        });

        $(".fileDrop").on("drop", function (event) {
            event.preventDefault();

            var files = event.originalEvent.dataTransfer.files;

            var file = files[0];

            //console.log(file);
            var formData = new FormData();

            formData.append("file", file);

            $.ajax({
                url : "/uploadAjax",
                data :formData,
                dataType : "text",
                processData : false,
                contentType : false,
                type : "POST",
                success : function (data) {
                    alert(data);
                }
            });
        });
    </script>
</body>
</html>