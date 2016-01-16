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
    <c:set var="pageTitle" value="取消关注日志"/>
    <c:set var="pageUrl" value="/admin/users/unsub"/>
    <c:set var="activeId" value="user-unsub-li"/>
    <%@ include file="../common/sectionHeader.jsp"%>

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
                  <th>用户动作</th>
                  <th>操作时间</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${page.data}">
                  <tr>
                    <td>${item.openid}</td>
                    <td>${item.type eq 'sub' ? "取消关注" : "关注"}</td>
                    <td><fmt:formatDate value="${item.born}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
                  </tr>
                </c:forEach>
                </tbody>
                <tfoot></tfoot>
              </table>
              <%@ include file="../common/page.jsp"%>
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

  });
</script>
</body>
</html>
