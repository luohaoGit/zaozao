<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<title>个人中心</title>
	<link rel="stylesheet" href="/weixinh5/css/weui.min.css"/>
	<link rel="stylesheet" href="/weixinh5/css/zaozaowx.css"/>
	<link rel="stylesheet" href="/weixinh5/css/businessCard.css"/>
	<style>
		.security{
			position: absolute;
			top: 0;
			right: 0;
			text-align: center;
			line-height: 2rem;
			height: 2rem;
			min-height: 2rem;
			min-width: 4rem;
			margin: 0.5rem 0;
			padding: 0 1rem;
			border-left: solid 1px #B2B2B2!important;
		}
	</style>
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
				<a class="weui_cell js_cell" href="javascript:;" data-id="phonenumber">
					<div class="weui_cell_bd weui_cell_primary">
						<p>我的手机</p>
					</div>
					<div class="weui_cell_ft" id="telephone">${user.telephone}</div>
				</a>
			</div>

			<div class="weui_cells weui_cells_access">
				<a class="weui_cell js_cell" href="javascript:;" data-id="">
					<div class="weui_cell_bd weui_cell_primary">
						<p>我的移车名片</p>
					</div>
					<div class="weui_cell_ft" id="card"></div>
				</a>
			</div>
		</div>
	</div>
</div>

<div class="weui_toptips weui_warn js_tooltips">格式不对</div>
<div class="weui_toptips weui_warn sec_tooltips">手机号和验证码是必须的</div>
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

<script type="text/html" id="tpl_carnumber">
	<div class="page">
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
	</div>
</script>

<script type="text/html" id="tpl_phonenumber">
	<div class="page">
		<div class="bd">
			<div class="weui_cells weui_cells_form">
				<div class="weui_cell">
					<div class="weui_cell_hd"><label class="weui_label">手机号</label></div>
					<div class="weui_cell_bd weui_cell_primary">
						<input id="phone" class="weui_input" maxlength="11" type="tel" placeholder="请输入手机号"/>
					</div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_hd"><label class="weui_label">验证码</label></div>
					<div class="weui_cell_bd weui_cell_primary">
						<input id="sec" class="weui_input" maxlength="6" type="tel" placeholder="请输入验证码"/>
						<div id="secBtn" class="security" style="display:none"></div>
					</div>
				</div>
			</div>
			<div class="weui_btn_area">
				<a id="phoneBtn" class="weui_btn weui_btn_primary" href="javascript:">确定</a>
			</div>
		</div>
	</div>
</script>

<script type="text/html" id="tpl_card">
	<div class="page">
		<div class="bd">
			<div class="weui_cells weui_cells_form">
				<div class="business-card-container">
					<div class="card-title">
						<img class="logo" src="/weixinh5/img/logo.jpg" />
						<div class="ad">您的挪车好帮手</div>
					</div>
					<div class="code-right">
						<div><img class="code" src="/img/qrcode_for_gh_f6aa1edbfa91_258.jpg"/></div>
					</div>
					<div class="code-container">
						<div class="code-left">
							<div class="">早早服务号：zaozaoyiche</div>
						</div>
					</div>
					<div class="card-connection">
						<div class="connection-left">车主早早号：11111</div>
						<div class="connection-right">车主手机号：1111</div>
					</div>
				</div>
				<div class="item item-checkbox checkbox-container">
					<label class="checkbox">
						<input type="checkbox">
					</label>
					不显示手机号
				</div>
			</div>
		</div>
	</div>
</script>

<script src="/weixinh5/js/zepto.min.js"></script>
<script type="text/javascript">
	$(function () {
		var areas = ["京", "津", "沪", "渝", "冀", "豫", "云", "辽", "黑", "湘", "皖", "鲁", "新", "苏", "浙", "赣", "鄂", "桂", "甘", "晋",
			"蒙", "陕", "吉", "闽", "贵", "粤", "青", "藏", "川", "宁", "琼"];
		var letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
			"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
		var carNumber = '${user.cars[0].carNumber}';
		var phoneNumber = '${user.telephone}';
		if(phoneNumber.length == 11){
			$("#telephone").html(getSecTel(phoneNumber));
		}
		var registerTime = '${user.registerTime}';
		var openid = '${user.openId}';
		var time = 120, send = false;

		// page stack
		var stack = [];
		var $container = $('.js_container');
		$container.on('click', '.js_cell[data-id]', function () {
			var id = $(this).data('id');
			go(id);
		});

		function go(id){
			var $tpl = $($('#tpl_' + id).html()).addClass('slideIn').addClass(id);
			$container.append($tpl);
			stack.push($tpl);
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

					if(!validate()){
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
										openid: openid
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
			}else if(id == "phonenumber"){
				$("#phone").val(phoneNumber);
				if(send){
					$("#secBtn").show();
					$("#secBtn").html(time);
				}else{
					$("#secBtn").html("发送验证码");
				}

				if(valiPhone()){
					$("#secBtn").show();
				}

				$('#phone').on('input propertychange', function() {
					phoneNumber = $("#phone").val();
					if(valiPhone()){
						$("#secBtn").show();
					}else{
						$("#secBtn").hide();
					}
				});

				$("#secBtn").on("click", function(){
					if(send){
						return;
					}
					send = true;
					$("#secBtn").html(time);
					var myTime = setInterval(function() {
						$("#secBtn").html(time);
						if(--time==0){
							send = false;
							time = 120;
							$("#secBtn").html("发送验证码");
							clearInterval(myTime);
						}
					}, 1000);

					$.ajax({
						type: 'post', url: '/weixin/h5/smscode.json',
						data: JSON.stringify({
							registerTime : registerTime,
							openId: openid,
							telephone: phoneNumber
						}),
						contentType: 'application/json',
						success: function (data) {
							if(data.success){
								dialog("发送成功");
							}else{
								dialog("发送失败");
							}
						},
						error: function(){
							dialog("发送失败");
						}
					});
				});

				$("#phoneBtn").on("click", function(){
					var sec = $("#sec").val();
					if(valiPhone() && sec.length == 6){
						$.ajax({
							type: 'post', url: '/weixin/h5/phone.json',
							data: JSON.stringify({
								seccode : sec,
								openId: openid,
								telephone: phoneNumber
							}),
							contentType: 'application/json',
							success: function (data) {
								if(data.success){
									location.hash = "";
									$("#telephone").html(getSecTel(phoneNumber));
								}else{
									dialog(data.msg);
								}
							},
							error: function(){
								dialog("绑定失败");
							}
						});
					}else{
						$('.sec_tooltips').show();
						setTimeout(function (){
							$('.sec_tooltips').hide();
						}, 3000);
					}
				});
			}

			$($tpl).on('webkitAnimationEnd', function (){
				$(this).removeClass('slideIn');
			}).on('animationend', function (){
				$(this).removeClass('slideIn');
			});
		}


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

		function getSecTel(tel){
			return tel.substr(0, 3) + "****" + tel.substr(7, 4);
		}

		function dialog(msg){
			$("#dialog_msg").html(msg);
			$('#dialog').show();
			$('#dialog').find('.weui_btn_dialog').on('click', function () {
				$('#dialog').hide();
			});
		}

		function validate(){
			var res = false;
			var newValue = $("#carnum").val();

			if(newValue.length > 5){
				newValue = newValue.substr(0, 5);
				$("#carnum").val(newValue);
			}

			$("#carnum").val(newValue.replace(/[^A-Za-z0-9]*/g,"").toUpperCase());
			$("#carBtn").addClass("weui_btn_disabled");

			if(newValue.length == 5){
				if(/^[A-Za-z0-9]+$/.test(newValue)) {
					$("#carBtn").removeClass("weui_btn_disabled");
					res = true;
				}
			}
			return res;
		}

		function valiPhone(){
			var res = false;
			var newValue = $("#phone").val();
			if(newValue.length == 11 && /^(13|14|15|17|18)\d{9}$/.test(newValue)){
				res = true;
			}
			return res;
		}


		if (/#.*/gi.test(location.href)) {
			go(location.hash.slice(1));
		}
	});
</script>
</body>
</html>