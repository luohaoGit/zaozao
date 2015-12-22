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
					<div class="weui_cell_hd"><img src="${user.headImgUrl}" alt="" style="height:40px;width:40px;margin-right:5px;display:block"></div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_bd weui_cell_primary">
						<p>早早ID</p>
					</div>
					<div class="weui_cell_ft">${user.zzid}</div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_bd weui_cell_primary">
						<p>我的昵称</p>
					</div>
					<div class="weui_cell_ft">${user.wxnickname}</div>
				</div>
			</div>

			<div class="weui_cells weui_cells_access">
				<a class="weui_cell js_cell" href="javascript:;" data-id="carnumber">
					<div class="weui_cell_bd weui_cell_primary">
						<p>我的车牌</p>
					</div>
					<div class="weui_cell_ft" id="carNumber">${user.cars[0].carNumber}</div>
				</a>
				<a class="weui_cell js_cell" href="javascript:;">
					<div class="weui_cell_bd weui_cell_primary">
						<p>我的手机</p>
					</div>
					<div class="weui_cell_ft">${user.telephone}</div>
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
		<div class="weui_toptips weui_warn js_tooltips">格式不对</div>
		<div class="bd">
			<div class="weui_cells_title">输入您的车牌号</div>
			<div class="weui_cells">
				<div class="weui_cell weui_cell_select weui_select_before">
					<div class="weui_cell_hd">
						<select class="weui_select" id="area"></select>
					</div>
					<div class="weui_cell_hd">
						<select class="weui_select" id="letter"></select>
					</div>
					<div class="weui_cell_bd weui_cell_primary">
						<input id="carnum" maxlength="5" class="weui_input" type="text" placeholder="请输入车牌号"/>
					</div>
				</div>
			</div>
			<div class="weui_btn_area">
				<a id="carBtn" class="weui_btn weui_btn_disabled weui_btn_primary" href="javascript:">确定</a>
			</div>
		</div>
		<div class="weui_dialog_alert" id="dialog" style="display: none;">
			<div class="weui_mask"></div>
			<div class="weui_dialog">
				<div class="weui_dialog_hd"><strong class="weui_dialog_title">早早移车</strong></div>
				<div class="weui_dialog_bd" id="dialog_msg"></div>
				<div class="weui_dialog_ft">
					<a href="javascript:;" class="weui_btn_dialog primary">确定</a>
				</div>
			</div>
		</div>
	</div>
</script>

<script src="/weixinh5/js/zepto.min.js"></script>
<script type="text/javascript">
	$(function () {
		//var pageData = JSON.parse('${user}');
		var areas = ["京", "津", "沪", "渝", "冀", "豫", "云", "辽", "黑", "湘", "皖", "鲁", "新", "苏", "浙", "赣", "鄂", "桂", "甘", "晋",
			"蒙", "陕", "吉", "闽", "贵", "粤", "青", "藏", "川", "宁", "琼"];
		var letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
			"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
		var carNumber = '${user.cars[0].carNumber}';

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

		function dialog(msg){
			$("#dialog_msg").html(msg);
			$('#dialog').show();
			$('#dialog').find('.weui_btn_dialog').on('click', function () {
				$('#dialog').hide();
			});
		}

		function validate(){
			var newValue = $("#carnum").val();
			$("#carnum").val(newValue.replace(/[^A-Za-z0-9]*/g,"").toUpperCase());
			$("#carBtn").addClass("weui_btn_disabled");
			if(newValue.length == 5){
				if(/^[A-Za-z0-9]+$/.test(newValue)) {
					$("#carBtn").removeClass("weui_btn_disabled");
				}
			}
		}

		function go(id){
			var $tpl = $($('#tpl_' + id).html()).addClass('slideIn').addClass(id);
			$container.append($tpl);
			stack.push($tpl);
			// why not use `history.pushState`, https://github.com/weui/weui/issues/26
			//history.pushState({id: id}, '', '#' + id);
			location.hash = '#' + id;

			if(id == "carnumber"){
				var carArea = carNumber.substr(0, 1);
				var carLetter = carNumber.substr(1, 1);
				var carNum = carNumber.substr(2, 5);

				$("#carnum").val(carNum);

				$('#carnum').on('input propertychange', function() {
					validate();
				});

				$("#area").on("change", function(){
					validate()
				});

				$("#letter").on("change", function(){
					validate()
				});

				$("#carBtn").on("click", function(){
					var carnum = $("#carnum").val();

					if(!/^[A-Za-z0-9]+$/.test(carnum)){
						$("#carBtn").addClass("weui_btn_disabled");
						$('.js_tooltips').show();
						setTimeout(function (){
							$('.js_tooltips').hide();
						}, 3000);
						return;
					}

					var newNumber = areas[$("#area").val()] + letters[$("#letter").val()] + carnum;
					$.ajax({
						type: 'get', url: '/weixin/h5/carnumber.json?carNumber=' + newNumber,
						success: function (data) {
							if (data.count == 1) {
								dialog("车牌已被绑定");
							} else {
								$.ajax({
									type: 'post', url: '/weixin/h5/carnumber.json',
									data: JSON.stringify({
										carNumber : newNumber,
										openid: '${user.id}'
									}),
									contentType: 'application/json',
									success: function () {
										$("#carNumber").html(newNumber);
										carNumber = newNumber;
										location.hash = "";
									},
									error: function(){
										dialog("保存失败");
									}
								});
							}
						},
						error: function(){
							dialog("网络错误")
						}
					});
				})

				$.each(areas, function(index, item){
					var selected = '';
					if(item == carArea){
						selected = 'selected';
					}
					$("#area").append('<option ' + selected + ' value="' + index + '">' + item + '</option>')
				});

				$.each(letters, function(index, item){
					var selected = '';
					if(item == carLetter){
						selected = 'selected';
					}
					$("#letter").append('<option ' + selected + ' value="' + index + '">' + item + '</option>')
				});
			}

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