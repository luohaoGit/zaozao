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
                  <h3 class="box-title">修改菜单</h3>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form role="form">
                  <div class="box-body">

                    <div class="box-body">
                      <div class="row">
                        <div class="col-xs-2">
                        <input type="text" class="form-control" id="menu0" placeholder="第一个菜单名称">
                        </div>
                      </div>
                    </div>
                    <div class="box-body">
                    <div class="row">
                      <div class="col-xs-2">
                        <input type="text" class="form-control" id="menu0-button0" placeholder="第一个菜单名称">
                      </div>
                      <div class="col-xs-2">
                        <input type="text" class="form-control" id="menu0-button1" placeholder="第二个菜单名称">
                      </div>
                      <div class="col-xs-2">
                        <input type="text" class="form-control" id="menu0-button2" placeholder="第三个菜单名称">
                      </div>
                      <div class="col-xs-2">
                        <input type="text" class="form-control" id="menu0-button3" placeholder="第四个菜单名称">
                      </div>
                      <div class="col-xs-2">
                        <input type="text" class="form-control" id="menu0-button4" placeholder="第五个菜单名称">
                      </div>
                    </div>
                  </div>
                    <div class="box-body">
                      <div class="row">
                        <div class="col-xs-2">
                          <select class="form-control button-type"></select>
                        </div>
                        <div class="col-xs-2">
                          <select class="form-control button-type"></select>
                        </div>
                        <div class="col-xs-2">
                          <select class="form-control button-type"></select>
                        </div>
                        <div class="col-xs-2">
                          <select class="form-control button-type"></select>
                        </div>
                        <div class="col-xs-2">
                          <select class="form-control button-type"></select>
                        </div>
                      </div>
                    </div>

                    <div class="box-body">
                      <div class="row">
                        <div class="col-xs-2">
                          <input type="text" class="form-control" id="menu1" placeholder="第二个菜单名称">
                        </div>
                      </div>
                    </div>
                    <div class="box-body">
                      <div class="row">
                        <div class="col-xs-2">
                          <input type="text" class="form-control" id="menu1-button0" placeholder="第一个菜单名称">
                        </div>
                        <div class="col-xs-2">
                          <input type="text" class="form-control" id="menu1-button1" placeholder="第二个菜单名称">
                        </div>
                        <div class="col-xs-2">
                          <input type="text" class="form-control" id="menu1-button2" placeholder="第三个菜单名称">
                        </div>
                        <div class="col-xs-2">
                          <input type="text" class="form-control" id="menu1-button3" placeholder="第四个菜单名称">
                        </div>
                        <div class="col-xs-2">
                          <input type="text" class="form-control" id="menu1-button4" placeholder="第五个菜单名称">
                        </div>
                      </div>
                    </div>

                    <div class="box-body">
                      <div class="row">
                        <div class="col-xs-2">
                          <input type="text" class="form-control" id="menu2" placeholder="第三个菜单名称">
                        </div>
                      </div>
                    </div>
                    <div class="box-body">
                      <div class="row">
                        <div class="col-xs-2">
                          <input type="text" class="form-control" id="menu2-button0" placeholder="第一个菜单名称">
                        </div>
                        <div class="col-xs-2">
                          <input type="text" class="form-control" id="menu2-button1" placeholder="第二个菜单名称">
                        </div>
                        <div class="col-xs-2">
                          <input type="text" class="form-control" id="menu2-button2" placeholder="第三个菜单名称">
                        </div>
                        <div class="col-xs-2">
                          <input type="text" class="form-control" id="menu2-button3" placeholder="第四个菜单名称">
                        </div>
                        <div class="col-xs-2">
                          <input type="text" class="form-control" id="menu2-button4" placeholder="第五个菜单名称">
                        </div>
                      </div>
                    </div>

                  </div><!-- /.box-body -->

                  <div class="box-footer">
                    <button type="submit" class="btn btn-primary">提交</button>
                  </div>
                </form>
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
        var menu = ${menu};
        console.log(menu);

        var options = "<option value='click'>点击推事件</option>" +
                      "<option value='view'>跳转URL</option>" +
                      "<option value='scancode_push'>扫码推事件</option>";
        $(".button-type").append(options);

        var buttons = menu.button;
        for(var i=0; i<buttons.length; i++){
          var button = buttons[i];
          $("#menu" + i).val(button.name);

          var subbuttons = button.sub_button;
          for(var j=0; j<subbuttons.length; j++){
            var subbutton = subbuttons[j];
            $("#menu" + i + "-button" + j).val(subbutton.name);
          }
        }

        $('#wx-li').addClass("active");
        $('#wx-li-menu').addClass("active");
      });
    </script>
  </body>
</html>
