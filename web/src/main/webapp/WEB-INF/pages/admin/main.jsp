<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <%@ include file="../common/css.jsp"%>
    <link rel="stylesheet" href="/css/morris.css">
  </head>

  <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">

      <%@ include file="../common/header.jsp"%>
      <%@ include file="../common/sidebar.jsp"%>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">

        <%@ include file="../common/contentHeader.jsp"%>

        <!-- Main content -->
        <section class="content">

          <div class="row">
            <div class="col-lg-3 col-xs-6">
              <div class="small-box bg-yellow">
                <div class="inner">
                  <h3>${regCount}<sup style="font-size: 20px">人</sup></h3>
                  <p>7天内新增用户</p>
                </div>
                <div class="icon">
                  <i class="ion ion-person-add"></i>
                </div>
                <a href="/admin/users/register" class="small-box-footer">详细 <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div>
            <div class="col-lg-3 col-xs-6">
              <div class="small-box bg-aqua">
                <div class="inner">
                  <h3>${unsubCount}<sup style="font-size: 20px">次</sup></h3>
                  <p>7天内取消关注</p>
                </div>
                <div class="icon">
                  <i class="ion ion-sad"></i>
                </div>
                <a href="/admin/users/unsub" class="small-box-footer">详细 <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div>
            <div class="col-lg-3 col-xs-6">
              <div class="small-box bg-red">
                <div class="inner">
                  <h3>${phoneCount}<sup style="font-size: 20px">人</sup></h3>
                  <p>7天内绑定手机</p>
                </div>
                <div class="icon">
                  <i class="ion ion-android-phone-portrait"></i>
                </div>
                <a href="/admin/users/transformation" class="small-box-footer">详细 <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div>
            <div class="col-lg-3 col-xs-6">
              <div class="small-box bg-green">
                <div class="inner">
                  <h3>${carCount}<sup style="font-size: 20px">人</sup></h3>
                  <p>7天内绑定车牌</p>
                </div>
                <div class="icon">
                  <i class="ion ion-model-s"></i>
                </div>
                <a href="/admin/users/transformation" class="small-box-footer">详细 <i class="fa fa-arrow-circle-right"></i></a>
              </div>
            </div>
          </div>

          <div class="row">
            <!-- Left col -->
            <section class="col-lg-7 connectedSortable">
              <!-- Custom tabs (Charts with tabs)-->
              <div class="nav-tabs-custom">
                <!-- Tabs within a box -->
                <ul class="nav nav-tabs pull-right">
                  <li class="active"><a href="#regMon" data-toggle="tab">本月新增人数</a></li>
                  <li><a href="#regYear" data-toggle="tab">本年新增人数</a></li>
                  <li class="pull-left header"><i class="fa fa-inbox"></i>新增人数</li>
                </ul>
                <div class="tab-content no-padding">
                  <div class="chart tab-pane active" id="regMon" style="position: relative; height: 300px;"></div>
                  <div class="chart tab-pane" id="regYear" style="position: relative; height: 300px;"></div>
                </div>
              </div>

            </section>

            <section class="col-lg-5 connectedSortable">

              <!-- solid sales graph -->
              <div class="box box-solid bg-teal-gradient">
                <div class="box-header">
                  <i class="fa fa-th"></i>
                  <h3 class="box-title">用户转化</h3>
                  <div class="box-tools pull-right">
                    <button class="btn bg-teal btn-sm" data-widget="collapse"><i class="fa fa-minus"></i></button>
                    <%--<button class="btn bg-teal btn-sm" data-widget="remove"><i class="fa fa-times"></i></button>--%>
                  </div>
                </div>
                <div class="box-body border-radius-none">
                  <div class="chart" id="line-chart" style="height: 250px;"></div>
                </div><!-- /.box-body -->
                <div class="box-footer no-border">
                  <div class="row">
                    <div class="col-xs-6 text-center" style="border-right: 1px solid #f4f4f4">
                      <input type="text" class="knob" data-readonly="true" value="${phoneCount / (phoneCount+carCount) * 100}" data-width="60" data-height="60" data-fgColor="#39CCCC">
                      <div class="knob-label">绑定手机</div>
                    </div><!-- ./col -->
                    <div class="col-xs-6 text-center" style="border-right: 1px solid #f4f4f4">
                      <input type="text" class="knob" data-readonly="true" value="${carCount / (phoneCount+carCount) * 100}" data-width="60" data-height="60" data-fgColor="#39CCCC">
                      <div class="knob-label">绑定车牌</div>
                    </div><!-- ./col -->
                  </div><!-- /.row -->
                </div><!-- /.box-footer -->
              </div>

            </section>
          </div>

        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->

      <%@ include file="../common/footer.jsp"%>

    </div>

    <%@ include file="../common/js.jsp"%>
    <!-- Morris.js charts -->
    <script src="/js/raphael-min.js"></script>
    <script src="/js/jquery.sparkline.min.js"></script>
    <script src="/js/morris.min.js"></script>
    <script src="/js/jquery.knob.js"></script>
    <script src="/js/demo.js"></script>

    <script type="text/javascript">
      $(function () {

        "use strict";

        var regMon = new Morris.Line({
          element: 'regMon',
          resize: true,
          data: ${monList},
          xkey: 'day',
          ykeys: ['count'],
          parseTime: false,
          labels: ['人数'],
          lineColors: ['#a0d0e0', '#3c8dbc'],
          hideHover: 'auto'
        });

        var regYear = new Morris.Bar({
          element: 'regYear',
          resize: true,
          data: ${yearList},
          xkey: 'month',
          ykeys: ['count'],
          parseTime: false,
          labels: ['人数'],
          lineColors: ['#a0d0e0', '#3c8dbc'],
          hideHover: 'auto'
        });

        var userTransd = ${userTransd};
        userTransd.sort(function(a, b){
          return a.day > b.day;
        });

        var line = new Morris.Line({
          element: 'line-chart',
          resize: true,
          parseTime: false,
          data: userTransd,
          xkey: 'day',
          ykeys: ['car', 'tel'],
          labels: ['车牌', '手机'],
          lineColors: ['#efefef'],
          lineWidth: 2,
          hideHover: 'auto',
          gridTextColor: "#fff",
          gridStrokeWidth: 0.4,
          pointSize: 4,
          pointStrokeColors: ["#efefef"],
          gridLineColor: "#efefef",
          gridTextFamily: "Open Sans"
        });

        $('.box ul.nav a').on('shown.bs.tab', function () {
          regMon.redraw();
          regYear.redraw();
          line.redraw();
        });

        $(".knob").knob();

      });
    </script>

  </body>
</html>
