<%--
  Created by IntelliJ IDEA.
  User: sskim
  Date: 2017. 8. 6.
  Time: PM 6:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        iframe {
            width: 0px;
            height: 0px;
            border: 0px;
        }
    </style>
</head>
<body>
    <form action="/uploadForm" id="form1" method="post" enctype="multipart/form-data" target="zeroFrame">
        <input type="file" name="file">
        <input type="submit">
    </form>
    <iframe name="zeroFrame"></iframe>
    <script>
        function addFilePath(msg) {

            alert(msg);
            document.getElementById("form1").reset();
        }
    </script>
</body>
</html>
