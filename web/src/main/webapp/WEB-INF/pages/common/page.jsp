<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-sm-5">
        <div class="dataTables_info">总共<b>${page.rowCount}</b>条记录</div>
    </div>
    <div class="col-sm-7">
        <div class="dataTables_paginate">
            <ul class="pagination">
                <li class="paginate_button first" onclick="toPage(0)">
                    <a href="#">首页</a>
                </li>
                <li class="paginate_button previous
                            <c:if test="${page.first}">disabled</c:if>"
                    <c:if test="${not page.first}">onclick="toPage(${page.pageNumber-1})"</c:if>>
                    <a href="#">上一页</a>
                </li>
                <c:forEach var="i" begin="${page.startIndex}" end="${page.endIndex}" step="1">
                    <li class="paginate_button <c:if test="${page.pageNumber eq i-1}">active</c:if>" onclick="toPage(${i-1})">
                        <a href="#">${i}</a>
                    </li>
                </c:forEach>
                <li class="paginate_button next
                            <c:if test="${page.last}">disabled</c:if>"
                    <c:if test="${not page.last}">onclick="toPage(${page.pageNumber+1})"</c:if>>
                    <a href="#">下一页</a>
                </li>
                <li class="paginate_button last" onclick="toPage(${page.pageCount-1})">
                    <a href="#">尾页</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<script type="text/javascript">
    function toPage(i){
        window.location = "${pageUrl}?pageNumber=" + i;
    }
</script>