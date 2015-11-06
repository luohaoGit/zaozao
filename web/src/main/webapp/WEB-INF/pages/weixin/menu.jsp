<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <%@ include file="../common/css.jsp"%>
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
            微信平台设置
            <small>菜单设置</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="/admin/main"><i class="fa fa-dashboard"></i> Home</a></li>
            <li>微信平台设置</li>
            <li class="active"><a href="/admin/settings/wx/menu">菜单设置</a></li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-xs-12">
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title">修改菜单(当前只支持json修改)</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <div class="box-body">
                  <div class="form-group">
                    <label>菜单JSON</label>
                    <textarea class="form-control" rows="20" id="menu"></textarea>
                  </div>
                  <div class="box-footer">
                    <button type="button" class="btn btn-primary" id="update">修改菜单</button>
                    <%--<button type="button" class="btn btn-primary" id="delete">清空菜单</button>--%>
                  </div>
                </div>
              </div><!-- /.box -->
            </div>
          </div>
        </section><!-- /.content -->

      </div><!-- /.content-wrapper -->

      <%@ include file="../common/footer.jsp"%>

    </div>

    <%@ include file="../common/js.jsp"%>

    <script>
      $(function () {
        var menu = '${menu}';

        if(menu != ''){
          menu = JSON.parse('${menu}');
          $("#menu").val(JSON.stringify(menu, null, "\t"));
        }

        $("#update").click(function(){
          var data = '{"menu":' + $("#menu").val() + '}';
          $.ajax({
            url: '/admin/settings/wx/menu',
            type: 'POST',
            contentType: 'application/json',
            data: data,
            success: function(response) {
              alert("成功");
            },
            error: function(response){
              console.log(response)
              alert("失败");
            }
          });
        });

        $("#delete").click(function(){
          $.ajax({
            url: '/admin/settings/wx/menu',
            type: 'DELETE',
            success: function(response) {
              alert("成功");
            },
            error: function(response){
              console.log(response)
              alert("失败");
            }
          });
        });

        $('#wx-li').addClass("active");
        $('#wx-li-menu').addClass("active");
      });
    </script>
  </body>
</html>
