<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">

  <!-- sidebar: style can be found in sidebar.less -->
  <section class="sidebar">
    <!-- Sidebar Menu -->
    <ul class="sidebar-menu">
      <li class="header">菜单</li>
      <!-- Optionally, you can add icons to the links -->
      <li id="user-li"><a href="/admin/users"><i class="fa fa-link"></i> <span>用户管理</span></a></li>
      <li><a href="#"><i class="fa fa-link"></i> <span>移车记录</span></a></li>
      <li id="zzid-li"><a href="/zzid"><i class="fa fa-link"></i> <span>早早号</span></a></li>
      <li><a href="#"><i class="fa fa-link"></i> <span>模版管理</span></a></li>
      <li><a href="#"><i class="fa fa-link"></i> <span>系统设置</span></a></li>
      <li class="treeview" id="wx-li">
        <a href="#"><i class="fa fa-link"></i> <span>微信平台设置</span> <i class="fa fa-angle-left pull-right"></i></a>
        <ul class="treeview-menu">
          <li id="wx-li-menu"><a href="/admin/settings/wx/menu">菜单设置</a></li>
          <li><a href="#">设置2</a></li>
        </ul>
      </li>
    </ul><!-- /.sidebar-menu -->
  </section>
  <!-- /.sidebar -->
</aside>