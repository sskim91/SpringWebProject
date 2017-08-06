<%--
  Created by IntelliJ IDEA.
  User: kss
  Date: 2017-07-28
  Time: 오후 2:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>

<%@include file="../include/header.jsp"%>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-md-12">
            <!-- general form elements -->

            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">LIST ALL PAGE</h3>
                </div>
                <div class="box-body">

                    <select name="searchType" id="">
                        <option value="n" <c:out value="${cri.searchType == null ? 'selected' : ''}"/>>----</option> <!-- null 검색 조건 없음 -->
                        <option value="t" <c:out value="${cri.searchType eq 't' ? 'selected' : ''}"/>>Title</option> <!-- t 제목 -->
                        <option value="c" <c:out value="${cri.searchType eq 'c' ? 'selected' : ''}"/>>Content</option> <!-- c 내용-->
                        <option value="w" <c:out value="${cri.searchType eq 'w' ? 'selected' : ''}"/>>Writer</option> <!-- w 작성자 -->
                        <option value="tc" <c:out value="${cri.searchType eq 'tc' ? 'selected' : ''}"/>>Title OR Content</option>   <!-- tc 제목, 내용 -->
                        <option value="cw" <c:out value="${cri.searchType eq 'cw' ? 'selected' : ''}"/>>Content OR Writer</option>   <!-- cw 내용, 작성자 -->
                        <option value="tcw" <c:out value="${cri.searchType eq 'tcw' ? 'selected' : ''}"/>>ALL</option> <!-- tcw 전체 -->
                    </select>

                    <input type="text" name="keyword" id="keywordInput" value="${cri.keyword}">
                    <button id="searchBtn">Search</button>
                    <button id="newBtn">New Board</button>

                    <table class="table table-bordered">
                        <tr>
                            <th style="width: 10px">BNO</th>
                            <th>TITLE</th>
                            <th>WRITER</th>
                            <th>REGDATE</th>
                            <th style="width: 40px">VIEWCNT</th>
                        </tr>

                        <c:forEach items="${list}" var="boardVO">
                            <tr>
                                <td>${boardVO.bno}</td>
                                <td><a href='/sboard/readPage${pageMaker.makeSearch(pageMaker.cri.page)}&bno=${boardVO.bno}'>
                                        ${boardVO.title}<strong> [${boardVO.replycnt}]</strong></a>
                                </td>
                                <td>${boardVO.writer}</td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}" /></td>
                                <td><span class="badge bg-red">${boardVO.viewcnt }</span></td>
                            </tr>
                        </c:forEach>
                    </table>

                </div>
                <!-- /.box-body -->
                <div class="box-footer">
                    <div class="text-center">
                        <ul class="pagination">

                            <c:if test="${pageMaker.prev}">
                                <li><a href="list${pageMaker.makeSearch(pageMaker.startPage -1)}">&laquo;</a></li>
                            </c:if>

                            <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                                <li
                                        <c:out value="${pageMaker.cri.page == idx ? 'class=active' : ''}"/>>
                                    <a href="list${pageMaker.makeSearch(idx)}">${idx}</a>
                                </li>
                            </c:forEach>

                            <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                                <li><a href="list${pageMaker.makeSearch(pageMaker.endPage + 1)}">&raquo;</a></li>
                            </c:if>

                        </ul>
                    </div>
                </div>
                <!-- /.box-footer-->
            </div>
        </div>
    </div>
    <!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<script>

    var result = '${msg}';

    if(result == 'SUCCESS'){
        alert('처리가 완료 되었습니다.');
    }

</script>

<script>
    $(document).ready(function () {

        $("#searchBtn").on("click", function (event) {

            self.location = "list"
                + '${pageMaker.makeQuery(1)}'
                + "&searchType="
                + $("select option:selected").val()
                + "&keyword=" + encodeURIComponent($("#keywordInput").val());
        });

        $("#newBtn").on("click", function () {
            self.location = "/sboard/register";
        })
    });
</script>

<%@include file="../include/footer.jsp"%>
