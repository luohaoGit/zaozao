<%@ include file="../common/common.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <%@ include file="../common/css.jsp"%>
    <!-- DataTables -->
    <link rel="stylesheet" href="/js/plugins/datatables/dataTables.bootstrap.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
     folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="/css/skins/_all-skins.min.css">
  </head>

  <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">

      <%@ include file="../common/header.jsp"%>
      <%@ include file="../common/sidebar.jsp"%>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">

        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            用户转换
            <small></small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active"><a href="/admin/users/transformation">用户转换</a></li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-xs-12">
              <div class="box">
                <div class="box-header">
                  <h3 class="box-title">列表</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                    <table id="table" class="table table-bordered table-striped">
                      <thead>
                      <tr>
                        <th>OPENID</th>
                        <th>电话</th>
                        <th>车牌</th>
                        <th>绑定时间</th>
                      </tr>
                      </thead>
                      <tbody>
                      <c:forEach var="item" items="${page.data}">
                        <tr>
                          <td>${item.openid}</td>
                          <td>${item.phone}</td>
                          <td>${item.carNumber}</td>
                          <td><fmt:formatDate value="${item.born}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
                        </tr>
                      </c:forEach>
                      </tbody>
                      <tfoot></tfoot>
                    </table>
                    <div class="row">
                    <div class="col-sm-5">
                      <div class="dataTables_info">总共<b>${page.rowCount}</b>条记录</div>
                    </div>
                    <div class="col-sm-7">
                      <div class="dataTables_paginate">
                        <ul class="pagination">
                          <li class="paginate_button first" onclick="toPage(0)">
                            <a href="#">第一页</a>
                          </li>
                          <li class="paginate_button previous
                            <c:if test="${page.first}">disabled</c:if>"
                            <c:if test="${not page.first}">onclick="toPage(${page.pageNumber-1})"</c:if>>
                            <a href="#">上一页</a>
                          </li>
                          <c:forEach var="i" begin="1" end="${page.showCount}" step="1">
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
                            <a href="#">最后一页</a>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
            </div><!-- /.col -->
          </div><!-- /.row -->
        </section><!-- /.content -->

      </div><!-- /.content-wrapper -->

      <%@ include file="../common/footer.jsp"%>

    </div>

    <%@ include file="../common/js.jsp"%>

    <!-- page script -->
    <script>
      $(function () {
        $('#user-trans-li').addClass("active");
      });
      function toPage(i){
        window.location = "/admin/users/transformation?pageNumber=" + i;
      }
    </script>
  </body>
</html>
