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
            早早号
            <small></small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="/admin/main"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active"><a href="/zzid">早早号</a></li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-xs-12">
              <div class="box">
                <div class="box-header">
                  <h3 class="box-title">早早号</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                    <table id="table" class="table table-bordered table-striped">
                      <thead>
                      <tr>
                        <th>早早号</th>
                      </tr>
                      </thead>
                      <tbody>
                      <c:forEach var="item" items="${list}" varStatus="status">
                      <tr>
                        <td>${item}</td>
                      </tr>
                      </c:forEach>
                      </tbody>
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
    <script>
      $(function () {
        $('#user-li').addClass("active");
      });
    </script>
  </body>
</html>
