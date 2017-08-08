<%--
  Created by IntelliJ IDEA.
  User: kss
  Date: 2017-07-25
  Time: 오후 5:19
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../include/header.jsp"%>
<style>
    .fileDrop {
        width: 80%;
        height: 100px;
        border: 1px dotted darkgray;
        background-color: lightslategrey;
        margin: auto;
    }
</style>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/upload.js"></script>
<!-- Main content -->
<section class="content">
    <div class="row">
        <!-- left column -->
        <div class="col-md-12">
            <!-- general form elements -->
            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">REGISTER BOARD</h3>
                </div>
                <!-- /.box-header -->

                <form id="registerForm" role="form" method="post">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="">Title</label>
                            <input type="text"
                                   name='title' class="form-control" placeholder="Enter Title">
                        </div>
                        <div class="form-group">
                            <label for="">Content</label>
                            <textarea class="form-control" name="content" rows="3"
                                      placeholder="Enter ..."></textarea>
                        </div>
                        <div class="form-group">
                            <label for="">Writer</label>
                            <input type="text"
                                   name="writer" class="form-control" placeholder="Enter Writer">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="">File DROP HERE</label>
                        <div class="fileDrop"></div>
                    </div>
                    <!-- /.box-body -->

                    <div class="box-footer">
                        <div>
                            <hr>
                        </div>

                        <ul class="mailbox-attachments clearfix uploadedList"></ul>

                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>

                </form>
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
                <script>
                    var template = Handlebars.compile($("#template").html());

                    $(".fileDrop").on("dragenter dragover", function (event) {
                        event.preventDefault();
                    })

                    $(".fileDrop").on("drop", function (event) {
                        event.preventDefault();

                        var files = event.originalEvent.dataTransfer.files;

                        var file = files[0];

                        var formData = new FormData();

                        formData.append("file", file);

                        $.ajax({
                            url: "/uploadAjax",
                            data: formData,
                            dataType: "text",
                            processData: false,
                            contentType: false,
                            type: "POST",
                            success: function (data) {

                                var fileInfo = getFileInfo(data);

                                var html = template(fileInfo);

                                $(".uploadedList").append(html);
                            }
                        });
                    })


                    //등록
                    $("#registerForm").submit(function (event) {

                        event.preventDefault();

                        var that = $(this);

                        var str = "";

                        $(".uploadedList .delbtn").each(function (index) {
                            str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href") +"'>";
                        });

                        that.append(str);

                        that.get(0).submit();
                    })
                </script>

            </div>
            <!-- /.box -->
        </div>
        <!--/.col (left) -->

    </div>
    <!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@include file="../include/footer.jsp"%>
