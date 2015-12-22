<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>个人中心</title>
    <link rel="stylesheet" href="/weixinh5/css/weui.min.css"/>
    <link rel="stylesheet" href="/weixinh5/css/zaozaowx.css"/>
</head>
<body ontouchstart>

    <div class="container js_container">
        <div class="page">
            <div class="bd">
                <div class="weui_cells">
                    <div class="weui_cell">
                        <div class="weui_cell_hd"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAC4AAAAuCAMAAABgZ9sFAAAAVFBMVEXx8fHMzMzr6+vn5+fv7+/t7e3d3d2+vr7W1tbHx8eysrKdnZ3p6enk5OTR0dG7u7u3t7ejo6PY2Njh4eHf39/T09PExMSvr6+goKCqqqqnp6e4uLgcLY/OAAAAnklEQVRIx+3RSRLDIAxE0QYhAbGZPNu5/z0zrXHiqiz5W72FqhqtVuuXAl3iOV7iPV/iSsAqZa9BS7YOmMXnNNX4TWGxRMn3R6SxRNgy0bzXOW8EBO8SAClsPdB3psqlvG+Lw7ONXg/pTld52BjgSSkA3PV2OOemjIDcZQWgVvONw60q7sIpR38EnHPSMDQ4MjDjLPozhAkGrVbr/z0ANjAF4AcbXmYAAAAASUVORK5CYII=" alt="" style="width:20px;margin-right:5px;display:block"></div>
                    </div>
                    <div class="weui_cell">
                        <div class="weui_cell_bd weui_cell_primary">
                            <p>早早ID</p>
                        </div>
                        <div class="weui_cell_ft">10000</div>
                    </div>
                    <div class="weui_cell">
                        <div class="weui_cell_bd weui_cell_primary">
                            <p>我的昵称</p>
                        </div>
                        <div class="weui_cell_ft">10000</div>
                    </div>
                </div>

                <div class="weui_cells weui_cells_access">
                    <a class="weui_cell js_cell" href="javascript:;" data-id="carnumber">
                        <div class="weui_cell_bd weui_cell_primary">
                            <p>我的车牌</p>
                        </div>
                        <div class="weui_cell_ft">说明文字</div>
                    </a>
                    <a class="weui_cell js_cell" href="javascript:;">
                        <div class="weui_cell_bd weui_cell_primary">
                            <p>我的手机</p>
                        </div>
                        <div class="weui_cell_ft">说明文字</div>
                    </a>
                </div>

                <div class="weui_cells weui_cells_access">
                    <a class="weui_cell js_cell" href="javascript:;">
                        <div class="weui_cell_bd weui_cell_primary">
                            <p>我的移车名片</p>
                        </div>
                        <div class="weui_cell_ft"></div>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <script type="text/html" id="tpl_carnumber">
        <div class="page">
            <div class="bd">
                <div class="weui_cells_title">输入您的车牌号</div>
                <div class="weui_cells">
                    <div class="weui_cell weui_cell_select weui_select_before">
                        <div class="weui_cell_hd">
                            <select class="weui_select" name="area">
                                <option value="0">京</option>
                                <option value="1">津</option>
                                <option value="2">沪</option>
                                <option value="3">渝</option>
                                <option value="4">冀</option>
                                <option value="5">豫</option>
                                <option value="6">云</option>
                                <option value="7">辽</option>
                                <option value="8">黑</option>
                                <option value="9">湘</option>
                                <option value="10">皖</option>
                                <option value="11">鲁</option>
                                <option value="12">新</option>
                                <option value="13">苏</option>
                                <option value="14">浙</option>
                                <option value="15">赣</option>
                                <option value="16">鄂</option>
                                <option value="17">桂</option>
                                <option value="18">甘</option>
                                <option value="19">晋</option>
                                <option value="20">蒙</option>
                                <option value="21">陕</option>
                                <option value="22">吉</option>
                                <option value="23">闽</option>
                                <option value="24">贵</option>
                                <option value="25">粤</option>
                                <option value="26">青</option>
                                <option value="27">藏</option>
                                <option value="28">川</option>
                                <option value="29">宁</option>
                                <option value="30">琼</option>
                            </select>
                        </div>
                        <div class="weui_cell_hd">
                            <select class="weui_select" name="letter">
                                <option value="0">A</option>
                                <option value="1">B</option>
                                <option value="2">C</option>
                                <option value="3">D</option>
                                <option value="4">E</option>
                                <option value="5">F</option>
                                <option value="6">G</option>
                                <option value="7">H</option>
                                <option value="8">I</option>
                                <option value="9">J</option>
                                <option value="10">K</option>
                                <option value="11">L</option>
                                <option value="12">M</option>
                                <option value="13">N</option>
                                <option value="14">O</option>
                                <option value="15">P</option>
                                <option value="16">Q</option>
                                <option value="17">R</option>
                                <option value="18">S</option>
                                <option value="19">T</option>
                                <option value="20">U</option>
                                <option value="21">V</option>
                                <option value="22">W</option>
                                <option value="23">X</option>
                                <option value="24">Y</option>
                                <option value="25"</option>
                            </select>
                        </div>
                        <div class="weui_cell_bd weui_cell_primary">
                            <input class="weui_input" type="tel" placeholder="请输入车牌号"/>
                        </div>
                    </div>
                </div>
                <div class="weui_btn_area">
                    <a class="weui_btn weui_btn_primary" href="javascript:">确定</a>
                </div>
            </div>
        </div>
    </script>

    <script src="/weixinh5/js/zepto.min.js"></script>
    <script type="text/javascript">
        $(function () {
            // page stack
            var stack = [];
            var $container = $('.js_container');
            $container.on('click', '.js_cell[data-id]', function () {
                var id = $(this).data('id');
                go(id);
            });

            // location.hash = '#hash1' 和点击后退都会触发`hashchange`，这个demo页面只关心后退
            $(window).on('hashchange', function (e) {
                if (/#.+/gi.test(e.newURL)) {
                    return;
                }
                var $top = stack.pop();
                if (!$top) {
                    return;
                }
                $top.addClass('slideOut').on('animationend', function () {
                    $top.remove();
                }).on('webkitAnimationEnd', function () {
                    $top.remove();
                });
            });

            function go(id){
                var $tpl = $($('#tpl_' + id).html()).addClass('slideIn').addClass(id);
                $container.append($tpl);
                stack.push($tpl);
                // why not use `history.pushState`, https://github.com/weui/weui/issues/26
                //history.pushState({id: id}, '', '#' + id);
                location.hash = '#' + id;

                $($tpl).on('webkitAnimationEnd', function (){
                    $(this).removeClass('slideIn');
                }).on('animationend', function (){
                    $(this).removeClass('slideIn');
                });
            }

            if (/#.*/gi.test(location.href)) {
                go(location.hash.slice(1));
            }
        });
    </script>
</body>
</html>