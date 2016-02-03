<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<title>车牌号咨询</title>
	<link rel="stylesheet" href="/weixinh5/css/weui.min.css"/>
	<link rel="stylesheet" href="/weixinh5/css/zaozaowx.css"/>
</head>
<body ontouchstart>

<div class="container js_container">
	<div class="page">
		<div class="bd">
			<div class="weui_toptips weui_warn js_tooltips">格式不对</div>
			<div class="weui_cells_title">请填写车牌号，我们帮您联系车主！</div>
			<div class="weui_cells">
				<div class="weui_cell weui_cell_select weui_select_before">
					<div class="weui_cell_hd">
						<select class="weui_select" id="area"></select>
					</div>
					<div class="weui_cell_hd">
						<select class="weui_select" id="letter"></select>
					</div>
					<div class="weui_cell_bd weui_cell_primary">
						<input id="carnum" class="weui_input" maxlength="5" type="text" placeholder="请输入车牌号"/>
					</div>
				</div>
			</div>
			<div class="weui_cells">
				<div class="weui_cells_title">您也可以输入车主留下的其他信息</div>
				<div class="weui_cell">
					<div class="weui_cell_bd weui_cell_primary">
						<input id="telzz" class="weui_input" type="tel" placeholder="请输入早早号或手机号"/>
					</div>
				</div>
			</div>
			<div class="weui_btn_area">
				<a id="wxbtn" class="weui_btn weui_btn_primary" href="javascript:">启动微信聊天</a>
				<a id="telbtn" class="weui_btn weui_btn_primary" href="javascript:">拨打车主电话</a>
			</div>
			<div id="toast" style="display: none;">
				<div class="weui_mask_transparent"></div>
				<div class="weui_toast">
					<i class="weui_icon_warn" style="font-size: 35px;"></i>
					<p class="weui_toast_content" id="toasttxt"></p>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="/weixinh5/js/zepto.min.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	$(function () {
		var authinfo=JSON.parse('${authinfo}');
		wx.config({
			debug:false,
			appId:authinfo.wxJsapiSignature.appid,
			timestamp: authinfo.wxJsapiSignature.timestamp,
			nonceStr:authinfo.wxJsapiSignature.noncestr,
			signature:authinfo.wxJsapiSignature.signature,
			jsApiList: []
		});

		var areas = ["京", "津", "沪", "渝", "冀", "豫", "云", "辽", "黑", "湘", "皖", "鲁", "新", "苏", "浙", "赣", "鄂", "桂", "甘", "晋",
			"蒙", "陕", "吉", "闽", "贵", "粤", "青", "藏", "川", "宁", "琼"];
		var letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
			"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];

		var myCarNum = '${myCarNum}';
		var shortName = '${shortName}';

		var selecetedArea = localStorage.area ? localStorage.area : '京';
		var selecetedLetter = localStorage.letter ? localStorage.letter : 'A';
		var carnum = localStorage.plateNumber ? localStorage.plateNumber : '';

		if(myCarNum != ''){
			selecetedArea = myCarNum[0];
			selecetedLetter = shortName[1];
		}

		if(shortName != ''){
			selecetedArea = shortName[0];
			selecetedLetter = myCarNum[1];
		}

		$("#carnum").val(carnum);
		$.each(areas, function(index, item){
			var selected = '';
			if(item == selecetedArea){
				selected = 'selected';
			}
			$("#area").append('<option ' + selected + ' value="' + index + '">' + item + '</option>')
		});

		$.each(letters, function(index, item){
			var selected = '';
			if(item == selecetedLetter){
				selected = 'selected';
			}
			$("#letter").append('<option ' + selected + ' value="' + index + '">' + item + '</option>')
		});

		$("#wxbtn").on("click", function() {
			query("wx");
		});

		$("#telbtn").on("click", function() {
			query("phone");
		});

		function query(type){
			var carnum = $("#carnum").val();
			var telzz = $("#telzz").val();
			if(telzz == ''){
				if(!validate()){
					//$("#wxbtn").addClass("weui_btn_disabled");
					$('.js_tooltips').show();
					setTimeout(function () {
						$('.js_tooltips').hide();
					}, 3000);
					return;
				}
			}

			localStorage.area = areas[$("#area").val()];
			localStorage.letter = letters[$("#letter").val()];
			localStorage.plateNumber = carnum;
			localStorage.openid = authinfo.openid;
			var newNumber = areas[$("#area").val()] + letters[$("#letter").val()] + carnum;

			if(!validate()){
				newNumber = '';
			}

			var postdata = {
				openid : authinfo.openid ? authinfo.openid : localStorage.openid,
				type:type,
				carNumber: newNumber,
				telOrZzid: telzz
			};

			$.ajax({
				type: 'post', url: '/weixin/h5/car/plate.json',
				data: JSON.stringify(postdata),
				contentType: 'application/json',
				success: function (data) {
					if(data.success){
						if(type == "phone"){
							window.location.href='tel:051280895104';
						}else{
							wx.closeWindow();
						}
					}else{
						dialog(data.msg);
					}
				},
				error: function(){
					dialog("没有找到车主");
				}
			});
		}

		validate();
		$('#carnum').on('input propertychange', function() {
			validate()
		});

		$("#area").on("change", function(){
			validate()
		});

		$("#letter").on("change", function(){
			validate()
		});

		function validate(){
			var res = false;
			var newValue = $("#carnum").val();

			if(newValue.length > 5){
				newValue = newValue.substr(0, 5);
				$("#carnum").val(newValue);
			}

			$("#carnum").val(newValue.replace(/[^A-Za-z0-9]*/g,"").toUpperCase());
			//$("#wxbtn").addClass("weui_btn_disabled");
			if(newValue.length == 5){
				if(/^[A-Za-z0-9]+$/.test(newValue)) {
					//$("#wxbtn").removeClass("weui_btn_disabled");
					res = true;
				}
			}
			return res;
		}

		function dialog(msg){
			$("#toasttxt").html(msg);
			var $toast = $('#toast');
			if ($toast.css('display') != 'none') {
				return;
			}

			$toast.show();
			setTimeout(function () {
				$toast.hide();
			}, 2000);
		}
	});
</script>
</body>
</html>