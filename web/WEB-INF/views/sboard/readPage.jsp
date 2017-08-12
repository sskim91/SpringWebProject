<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kss
  Date: 2017-07-26
  Time: 오후 5:12
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../include/header.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/upload.js"></script>
<style>
    li{
        list-style: none;
    }
    .popup {
        position: absolute;
    }
    .back {
        background-color: grey;
        opacity: 0.5;
        width: 100%;
        height: 300%;
        overflow: hidden;
        z-index: 1101;
    }
    .front {
        z-index: 1110;
        opacity: 1;
        border: 1px;
        margin: auto;
    }
    .show {
        position: relative;
        max-width: 1200px;
        max-height: 800px;
        overflow: auto;
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

                <form role="form" method="post">

                    <input type='hidden' name='bno' value="${boardVO.bno}">
                    <!-- page 목록 정보 유지시에 필요 -->
                    <input type="hidden" name="page" value="${cri.page}">
                    <input type="hidden" name="perPageNum" value="${cri.perPageNum}">
                    <input type="hidden" name="searchType" value="${cri.searchType}">
                    <input type="hidden" name="keyword" value="${cri.keyword}">

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

                <!-- 이미지 보여주기 위한 숨김 div -->
                <div class='popup back' style="display: none"></div>
                <div id="popup_front" class='popup front' style="display: none;">
                    <img id="popup_img">

                </div>

                <div class="box-footer">
                    <div>
                        <hr>
                    </div>
                    <ul class="mailbox-attachments clearfix uploadedList">
                        <!-- 업로드 된 파일 표시 -->
                    </ul>

                    <!-- 조회 페이지 조건 추가 로그인한 사용자는 수정 삭제 보일수 있게 로그인 안한 사용자는 리스트만 -->
                    <c:if test="${login.uid == boardVO.writer}">
                        <button type="submit" class="btn btn-warning" id="btn_modify">Modify</button>
                        <button type="submit" class="btn btn-danger" id="btn_remove">REMOVE</button>
                    </c:if>
                        <button type="submit" class="btn btn-primary" id="btn_list">LIST ALL</button>
                </div>


                <div class="row">
                    <div class="col-md-12">

                        <div class="box box-success">
                            <div class="box-header">
                                <h3 class="box-title">ADD NEW REPLY</h3>
                            </div>
                            <!-- 댓글의 추가 : 로그인한 사용자의 경우 댓글의 작성자는 로그인한 사용자의 아이디로 구성되어야 한다
                                 댓글의 수정이나 삭제 : 댓글의 목록을 보는 것은 자유롭지만, 자신이 작성한 댓글에 대해서만 수정이나 삭제 작업이 가능하도록 수정해야한다.
                            -->
                            <c:if test="${not empty login}">
                                <div class="box-body">
                                    <label for="newReplyWriter">Writer</label>
                                    <input type="text" class="form-control" id="newReplyWriter" value="${login.uid}" readonly>
                                    <label for="newReplyText">ReplyText</label>
                                    <input type="text" class="form-control" id="newReplyText">
                                </div>
                                <!-- /.box-body -->
                                <div class="box-footer">
                                    <button type="submit" class="btn btn-primary" id="replyAddBtn">ADD REPLY</button>
                                </div>
                            </c:if>

                            <c:if test="${empty login}">
                                <div class="box-body">
                                    <div><a href="javascript:goLogin();">Login Please</a></div>
                                </div>
                            </c:if>
                        </div>

                        <!-- The time line -->
                        <ul class="timeline">
                            <!-- timeline time label  댓글 수 볼 수 있게 처리-->
                            <li class="time-label" id="repliesDiv">
                                <span class="bg-green">Replies List <small id="replycntSmall"> [ ${boardVO.replycnt} ] </small></span>
                            </li>
                        </ul>

                        <div class="text-center">
                            <ul id="pagination" class="pagination pagination-sm no-margin">
                                <!-- 댓글 페이지 -->
                            </ul>
                        </div>

                    </div>
                </div>

                <!-- Modal -->
                <div id="modifyModal" class="modal modal-primary fade" role="dialog">
                    <div class="modal-dialog">
                        <!-- Modal Content -->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title"></h4>
                            </div>
                            <div class="modal-body" data-rno>
                                <p><input type="text" id="replytext" class="form-control"></p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-info" id="replyModBtn">Modify</button>
                                <button type="button" class="btn btn-danger" id="replyDelBtn">DELETE</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>

                <script id="template" type="text/x-handlebars-template">
                    {{#each .}}
                    <li class="replyLi" data-rno="{{rno}}">
                        <i class="fa fa-comments bg-blue"></i>
                        <div class="timeline-item">
                            <span class="time">
                                <i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
                            </span>
                            <h3 class="timeline-header"><strong>{{rno}}</strong> -{{replyer}}</h3>
                            <div class="timeline-body">{{replytext}}</div>
                                <div class="timeline-footer">
                                    {{#eqReplyer replyer }}
                                    <a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modifyModal">Modify</a>
                                    {{/eqReplyer}}
                                </div>
                        </div>
                    </li>
                    {{/each}}
                </script>

                <!-- 댓글 -->
                <script>
                    <%----%>
                    Handlebars.registerHelper("eqReplyer", function (replyer, block) {
                        var accum = '';
                        if(replyer == '${login.uid}') {
                            accum += block.fn();
                        }
                        return accum;
                    });

                    Handlebars.registerHelper("prettifyDate", function (timeValue) {
                        var dateObj = new Date(timeValue);
                        var year = dateObj.getFullYear();
                        var month = dateObj.getMonth() + 1;
                        var date = dateObj.getDate();
                        return year + "/" + month + "/" + date;
                    });

                    var printData = function (replyArr, target, templateObject) {
                        var template = Handlebars.compile(templateObject.html());

                        var html = template(replyArr);
                        $(".replyLi").remove();
                        target.after(html);
                    }

                    var bno = ${boardVO.bno};      //게시글 번호 가져옴
                    var replyPage = 1;

                    function getPage(pageInfo) {
                        $.getJSON(pageInfo, function (data) {
                            printData(data.list, $("#repliesDiv"), $("#template"));
                            printPaging(data.pageMaker, $(".pagination"));

                            $("#modifyModal").modal('hide');
                            $("#replycntSmall").html("[ " + data.pageMaker.totalCount + " ]");
                        })
                    }

                    var printPaging = function (pageMaker, target) {

                        var str = "";

                        if(pageMaker.prev) {
                            str += "<li><a href='"+(pageMaker.startPage-1)+"'><<</a></li>";
                        }

                        for(var i=pageMaker.startPage, len = pageMaker.endPage; i<=len; i++) {
                            var strClass = pageMaker.cri.page == i?'class=active':'';
                            str += "<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";
                        }

                        if(pageMaker.next) {
                            str += "<li><a href='"+(pageMaker.endPage +1)+"'> >> </a></li>";
                        }

                        target.html(str);
                    }

                    $("#repliesDiv").on("click", function () {

                        if($(".timeline li").size() > 1) {
                            return;
                        }
                        getPage("/replies/" + bno + "/1");
                    });

                    $(".pagination").on("click", "li a", function (event) {

                        event.preventDefault();

                        replyPage = $(this).attr("href");
                        getPage("/replies/" + bno + "/" + replyPage);
                    });

                    //댓글 추가
                    $("#replyAddBtn").on("click", function () {

                        var replyerObj = $("#newReplyWriter");
                        var replytextObj = $("#newReplyText");
                        var replyer = replyerObj.val();
                        var replytext = replytextObj.val();

                        $.ajax({
                            type: 'post',
                            url: '/replies/',
                            headers: {
                                "Content-Type": "application/json",
                                "X-HTTP-Method-Override": "POST"
                            },
                            dataType: 'text',
                            data: JSON.stringify({bno: bno, replyer: replyer, replytext: replytext}),
                            success: function (result) {
                                console.log("result : " + result);
                                if(result == 'SUCCESS') {
                                    alert("등록 되었습니다.");
                                    replyPage = 1;
                                    getPage("/replies/" + bno + "/" + replyPage);
                                    replyerObj.val("");
                                    replytextObj.val("");
                                }
                            }
                        });
                    });


                    $(".timeline").on("click", ".replyLi", function (event) {

                        var reply = $(this);

                        $("#replytext").val(reply.find('.timeline-body').text());
                        $(".modal-title").html(reply.attr("data-rno"));
                    });

                    //수정
                    $("#replyModBtn").on("click", function () {

                        var rno = $(".modal-title").html();
                        var replytext = $("#replytext").val();

                        $.ajax({
                            type: 'put',
                            url: '/replies/' + rno,
                            headers : {
                                "Content-Type": "application/json",
                                "X-HTTP-Method-Override" : "PUT"
                            },
                            data :JSON.stringify({replytext : replytext}),
                            dataType : 'text',
                            success : function (result) {
                                console.log("result : "+result);
                                if(result == 'SUCCESS') {
                                    alert("수정 되었습니다.");
                                    getPage("/replies/" + bno + "/" + replyPage);
                                }
                            }
                        });
                    });

                    //삭제
                    $("#replyDelBtn").on("click", function () {

                        var rno = $(".modal-title").html();
                        var replytext = $("#replytext").val();

                        $.ajax({
                            type : 'delete',
                            url : '/replies/'+rno,
                            headers : {
                                "Content-Type" : "application/json",
                                "X-HTTP-Method-Override" :"DELETE"
                            },
                            dataType : 'text',
                            success : function (result) {
                                console.log("result :" + result);
                                if(result == 'SUCCESS') {
                                    alert("삭제 되었습니다.");
                                    getPage("/replies/" + bno + "/" + replyPage);
                                }
                            }
                        })
                    });

                </script>

                <!-- Handlebars 템플릿 -->
                <script id="templateAttach" type="text/x-handlebars-template">
                    <li data-src="{{fullName}}">
                        <span class="mailbox-attachment-icon has-img">
                            <img src="{{imgsrc}}" alt="Attachment">
                        </span>
                        <div class="mailbox-attachment-info">
                            <a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
                            </span>
                        </div>
                    </li>
                </script>

                <script>
                    $(document).ready(function () {

                        var formObj = $("form[role='form']");

                        console.log(formObj);

                        // language=JQuery-CSS
                        $("#btn_modify").on("click", function () {
                            formObj.attr("action", "/sboard/modifyPage");
                            formObj.attr("method", "get");
                            formObj.submit();
                        });

                        $("#btn_remove").on("click", function () {

                            //댓글 개수 구하기
                            var replyCnt = $("#replycntSmall").html().replace(/[^0-9]/g, "");

                            if(replyCnt > 0) {
                                alert("댓글이 달린 게시물을 삭제할 수 없습니다.");
                                return;
                            }

                            var arr = [];
                            $(".uploadedList li").each(function (index) {
                                arr.push($(this).attr("data-src"));
                            });

                            if(arr.length > 0) {
                                $.post("/deleteAllFiles", {files: arr}, function () {

                                });
                            }

                            formObj.attr("action", "/sboard/removePage");
                            formObj.submit();
                        });

                        $("#btn_list").on("click", function () {
                            formObj.attr("method", "get");
                            formObj.attr("action", "/sboard/list");
                            formObj.submit();
                        });

                        <!-- 조회화면 파일 업로드 보여지는 부분 -->
                        var bno = ${boardVO.bno};
                        console.log("bno test :"+bno);
                        var template = Handlebars.compile($("#templateAttach").html());

                        $.getJSON("/sboard/getAttach/"+bno, function (list) {
                            $(list).each(function () {

                                var fileInfo = getFileInfo(this);

                                var html = template(fileInfo);

                                $(".uploadedList").append(html);
                            })
                        });

                        //이미지 파일인 경우
                        $(".uploadedList").on("click", ".mailbox-attachment-info a", function (event) {

                            var fileLink = $(this).attr("href");

                            if(checkImageType(fileLink)) {

                                event.preventDefault();

                                var imgTag = $("#popup_img");
                                imgTag.attr("src", fileLink);

                                console.log(imgTag.attr("src"));

                                $(".popup").show('slow');
                                imgTag.addClass("show");
                            }
                        });

                        $("#popup_img").on("click", function () {
                            $(".popup").hide('slow');
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
