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
/*
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

                    var str = "";

                    console.log(data);
                    console.log(checkImageType(data));

                    if(checkImageType(data)) {
                        str = "<div>"
                            + "<a href='displayFile?fileName="+getImageLink(data)+"'>"
                            + "<img src='displayFile?fileName="+data+"'/>"
                            + getImageLink(data) + "</a></div>";

                    }else{
                        str = "<div><a href='displayFile?fileName="+data+"'>"
                            + getOriginalName(data) + "</a></div>";
                            +"</div>";
                    }
                    $(".uploadedList").append(str);
                    alert(data);
                }
            });
        });*/

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

                    var str = "";

                    console.log(data);
                    console.log(checkImageType(data));

                    if(checkImageType(data)) {
                        str = "<div>"
                            + "<a href='displayFile?fileName="+getImageLink(data)+"'>"
                            + "<img src='displayFile?fileName="+data+"'/>"
                            + "</a><small data-src="+data+">X</small></div>";

                    }else{
                        str = "<div><a href='displayFile?fileName="+data+"'>"
                            + getOriginalName(data)+"</a>"
                            +"<small data-src="+data+">X</small></div></div>";
                    }
                    $(".uploadedList").append(str);
                    //alert(data);
                }
            });
        });

        $(".uploadedList").on("click", "small", function (event) {

            var that = $(this);

            $.ajax({
                url :"/deleteFile",
                type :"post",
                data : {fileName:$(this).attr("data-src")},
                dataType : "text",
                success : function (result) {
                    if(result == 'deleted') {
                        that.parent("div").remove();
                    }
                }
            })
        })

        function checkImageType(fileName) {

            var pattern = /jpg|gif|png|jpeg/i;
            return fileName.match(pattern);
        }

        //파일 링크처리
        function getOriginalName(fileName) {

            if(checkImageType(fileName)) {
                return;
            }

            var idx = fileName.indexOf("_") + 1;
            return fileName.substr(idx);
        }

        //이미지 파일 원본 찾기
        function getImageLink(fileName) {

            if(!checkImageType(fileName)) {
                return;
            }
            var front = fileName.substr(0,12);
            var end = fileName.substr(14);
            console.log(front+end);
            return front+end;
        }
    </script>
</body>
</html>
