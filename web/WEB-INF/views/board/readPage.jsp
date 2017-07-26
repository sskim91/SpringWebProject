<%--
  Created by IntelliJ IDEA.
  User: kss
  Date: 2017-07-26
  Time: 오후 5:12
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../include/header.jsp" %>

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

                <form role="form" method="post">

                    <input type='hidden' name='bno' value="${boardVO.bno}">
                    <!-- page 목록 정보 유지시에 필요 -->
                    <input type="hidden" name="page" value="${cri.page}">
                    <input type="hidden" name="perPageNum" value="${cri.perPageNum}">

                </form>

                <div class="box-body">
                    <div class="form-group">
                        <label for="">Title</label> <input type="text" name='title' class="form-control"  value="${boardVO.title}" readonly="readonly">
                    </div>
                    <div class="form-group">
                        <label for="">Content</label>
                        <textarea class="form-control" name="content" rows="3" readonly="readonly">${boardVO.content}</textarea>
                    </div>
                    <div class="form-group">
                        <label for="">Writer</label>
                        <input type="text" name="writer" class="form-control" value="${boardVO.writer}" readonly="readonly">
                    </div>
                </div>
                <!-- /.box-body -->

                <div class="box-footer">
                    <button type="submit" class="btn btn-warning" id="btn_modify">Modify</button>
                    <button type="submit" class="btn btn-danger" id="btn_remove">REMOVE</button>
                    <button type="submit" class="btn btn-primary" id="btn_list">LIST ALL</button>
                </div>

                <script>
                    $(document).ready(function () {

                        var formObj = $("form[role='form']");

                        console.log(formObj);

                        // language=JQuery-CSS
                        $("#btn_modify").on("click", function () {
                            formObj.attr("action", "/board/modifyPage");
                            formObj.attr("method", "get");
                            formObj.submit();
                        });

                        $("#btn_remove").on("click", function () {
                            formObj.attr("action", "/board/removePage");
                            formObj.submit();
                        });

                        $("#btn_list").on("click", function () {
                            self.location = "/board/listPage";
                        });

                    });
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

<%@include file="../include/footer.jsp" %>
