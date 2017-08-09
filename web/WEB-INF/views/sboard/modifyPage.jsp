<%--
  Created by IntelliJ IDEA.
  User: kss
  Date: 2017-07-26
  Time: 오후 5:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@include file="../include/header.jsp" %>
<style>
    .fileDrop {
        width: 80%;
        height: 100px;
        border: 1px dotted gray;
        background-color: lightslategrey;
        margin: auto;
    }
</style>

<!-- Main content -->
<section class="content">
    <div class="row">
        <!-- left column -->
        <div class="col-md-12">
            <!-- general form elements -->
            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">READ BOARD</h3>
                </div>
                <!-- /.box-header -->

                <form role="form" action="modifyPage" method="post">

                    <!-- 수정시 목록 페이지 정보 유지 -->
                    <input type="hidden" name="page" value="${cri.page}">
                    <input type="hidden" name="perPageNum" value="${cri.perPageNum}">
                    <input type="hidden" name="searchType" value="${cri.searchType}">
                    <input type="hidden" name="keyword" value="${cri.keyword}">

                    <div class="box-body">

                        <div class="form-group">
                            <label for="">BNO</label>
                            <input type="text" name='bno' class="form-control" value="${boardVO.bno}"
                                   readonly="readonly">
                        </div>

                        <div class="form-group">
                            <label for="">Title</label>
                            <input type="text" name='title' class="form-control" value="${boardVO.title}">
                        </div>
                        <div class="form-group">
                            <label for="">Content</label>
                            <textarea class="form-control" name="content" rows="3">${boardVO.content}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="">Writer</label>
                            <input type="text" name="writer" class="form-control" value="${boardVO.writer}">
                        </div>
                        <div class="form-group">
                            <label for="">File Drop Here</label>
                            <div class="fileDrop"></div>
                        </div>
                    </div>
                    <!-- /.box-body -->
                </form>

                <!-- 이미지 보여주기 위한 숨김 div -->
                <div class='popup back' style="display: none"></div>
                <div id="popup_front" class='popup front' style="display: none;">
                    <img id="popup_img">

                </div>

                <div class="box-footer">
                    <div>
                        <hr>
                    </div>
                    <!-- 파일 업로드 -->
                    <ul class="mailbox-attachments clearfix uploadedList">

                    </ul>
                    <button class="btn btn-primary" id="btn_modify">SAVE</button>
                    <button class="btn btn-warning" id="btn_list">CANCEL</button>
                </div>

                <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
                <script src="${pageContext.request.contextPath}/resources/js/upload.js"></script>

                <script id="template" type="text/x-handlebars-template">
                    <li>
                        <span class="mailbox-attachment-icon has-img">
                            <img src="{{imgsrc}}" alt="Attachment">
                        </span>
                        <div class="mailbox-attachment-info">
                            <a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
                            <a href="{{fullName}}" class="btn btn-default btn-xs pull-right delbtn">
                                <i class="fa fa-fw fa-remove"></i>
                            </a>
                        </div>
                    </li>
                </script>

            </div>
            <!-- /.box -->
        </div>
        <!--/.col (left) -->

    </div>
    <!-- /.row -->

    <script>
        $(document).ready(function () {

            var formObj = $("form[role='form']");
            console.log(formObj);

            $("#btn_modify").on("click",function (event) {

                var that = formObj;

                var str = "";

                $(".uploadedList .delbtn").each(function (index) {
                    str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href") +"'>";
                });

                that.append(str);

                console.log(str);

                that.get(0).submit();
            });

            $("#btn_list").on("click", function () {
                self.location = "/sboard/list?page=${cri.page}&perPageNum=${cri.perPageNum}"
                    + "&searchType=${cri.searchType}&keyword=${cri.keyword}";
            });
        });


        var template = Handlebars.compile($("#template").html());

        $(".fileDrop").on("dragenter dragover", function(event){
            event.preventDefault();
        });

        $(".fileDrop").on("drop", function(event){
            event.preventDefault();

            var files = event.originalEvent.dataTransfer.files;

            var file = files[0];
            //console.log(file);

            var formData = new FormData();

            formData.append("file", file);

            $.ajax({
                url: '/uploadAjax',
                data: formData,
                dataType:'text',
                processData: false,
                contentType: false,
                type: 'POST',
                success: function(data){

                    var fileInfo = getFileInfo(data);

                    var html = template(fileInfo);

                    $(".uploadedList").append(html);
                }
            });
        });

        $(".uploadedList").on("click", ".delbtn", function(event){

            event.preventDefault();

            var that = $(this);

            $.ajax({
                url:"/deleteFile",
                type:"post",
                data: {fileName:$(this).attr("href")},
                dataType:"text",
                success:function(result){
                    if(result == 'deleted'){
                        that.closest("li").remove();
                    }
                }
            });
        });

        var bno = ${boardVO.bno};
        var template = Handlebars.compile($("#template").html());

        //파일 업로드된거 뿌려주는 코드
        $.getJSON("/sboard/getAttach/"+bno,function(list){
            $(list).each(function(){

                var fileInfo = getFileInfo(this);

                var html = template(fileInfo);

                $(".uploadedList").append(html);

            });
        });

        $(".uploadedList").on("click", ".mailbox-attachment-info a", function(event){

            var fileLink = $(this).attr("href");

            if(checkImageType(fileLink)){

                event.preventDefault();

                var imgTag = $("#popup_img");
                imgTag.attr("src", fileLink);

                console.log(imgTag.attr("src"));

                $(".popup").show('slow');
                imgTag.addClass("show");
            }
        });

        $("#popup_img").on("click", function(){

            $(".popup").hide('slow');

        });
    </script>
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@include file="../include/footer.jsp" %>
