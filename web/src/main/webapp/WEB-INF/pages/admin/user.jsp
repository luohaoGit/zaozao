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
            用户管理
            <small></small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="/admin/main"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active"><a href="/admin/users">用户管理</a></li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-xs-12">
              <div class="box">
                <div class="box-header">
                  <h3 class="box-title">用户列表</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                    <table id="table" class="table table-bordered table-striped">
                      <thead>
                      <tr>
                        <th>ID</th>
                        <th>OPENID</th>
                        <th>用户名</th>
                        <th>电话</th>
                        <th>注册时间</th>
                        <th>是否关注</th>
                        <th>性别</th>
                        <th>微信号</th>
                        <th>操作</th>
                      </tr>
                      </thead>
                      <tbody>
                      <c:forEach var="item" items="${page.data}" varStatus="status">
                      <tr>
                        <td>${item.id}</td>
                        <td>${item.openId}</td>
                        <td>${item.username}</td>
                        <td>${item.telephone}</td>
                        <td><fmt:formatDate value="${item.registerTime}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
                        <td>${item.subcribe ? "是" : "否"}</td>
                        <td>${item.gender}</td>
                        <td>${item.wxNumber}</td>
                        <td></td>
                      </tr>
                      </c:forEach>
                      </tbody>
                      <tfoot>
                      <tr>
                        <th>ID</th>
                        <th>OPENID</th>
                        <th>用户名</th>
                        <th>电话</th>
                        <th>注册时间</th>
                        <th>是否关注</th>
                        <th>性别</th>
                        <th>微信号</th>
                        <th>操作</th>
                      </tr>
                      </tfoot>
                    </table>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
            </div><!-- /.col -->
          </div><!-- /.row -->
        </section><!-- /.content -->

      </div><!-- /.content-wrapper -->

      <%@ include file="../common/footer.jsp"%>

    </div>

    <%@ include file="../common/js.jsp"%>
    <!-- DataTables -->
    <script src="/js/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="/js/plugins/datatables/dataTables.bootstrap.min.js"></script>
    <!-- SlimScroll -->
    <script src="/js/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="/js/plugins/fastclick/fastclick.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="/js/demo.js"></script>
    <!-- page script -->
    <script>
      $(function () {
        $('#user-li').addClass("active");
        $('#table').DataTable({
          "searching": false,
          "lengthChange": false
        });
      });
    </script>
  </body>
</html>
