<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.1.4 -->
<script src="/js/jQuery-2.1.4.min.js"></script>
<!-- Bootstrap 3.3.5 -->
<script src="/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="/js/app.min.js"></script>

<script>
    $(function () {
        $('.exit').click(function(){
            location.href = "/admin/logout";
        });
        $('#${activeId}').addClass("active");
    });
</script>