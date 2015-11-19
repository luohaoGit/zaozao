<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<title>个人信息</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<link rel="stylesheet" href="/weixinh5/css/ionic.min.css"> 
		<link rel="stylesheet" href="/weixinh5/css/ionicons.min.css">
		<link rel="stylesheet" href="/weixinh5/css/personalInformation.css" />
		<script type="text/javascript" src="/weixinh5/js/jquery-1.11.1.js"></script>
		<script type="text/javascript" src="/weixinh5/js/ionic.bundle.min.js"></script>
		<script type="text/javascript" src="/weixinh5/js/angular-resource.min.js"></script>
	</head>
	<body>
		<div ng-app="personalInformation" ng-controller="personalInformation">
			<ion-content>
				<div class="list card border-radius">
					<div class="item item-icon-right list-container" ng-repeat="x in lists" ng-click="skip(x)">
						<div ng-if="x.hasIcon"><img class="head" ng-src="/weixinh5/img/test.png" /></div>
						<div ng-if="x.hasIcon" class="name">{{x.name}}</div>
						<div ng-if="!x.hasIcon" class="noname">{{x.name}}</div>
						<div ng-if="!x.hasIcon" class="content">{{x.content}}</div>
						<i class="icon ion-chevron-right icon-color" ng-if="x.flag"></i>
					</div>
				</div>
				<div class="list card border-radius">
					<div class="item item-icon-right list-container" ng-repeat="x in lists_1" ng-click="skip(x)">
						<div ng-if="x.hasIcon"><img class="head" ng-src="/weixinh5/img/test.png" /></div>
						<div ng-if="x.hasIcon" class="name">{{x.name}}</div>
						<div ng-if="!x.hasIcon" class="noname">{{x.name}}</div>
						<div ng-if="!x.hasIcon" class="content">{{x.content}}</div>
						<i class="icon ion-chevron-right icon-color" ng-if="x.flag"></i>
					</div>
				</div>
				<div class="list card border-radius">
					<div class="item item-icon-right list-container" ng-repeat="x in lists_2" ng-click="skip(x)">
						<div ng-if="x.hasIcon"><img class="head" ng-src="/weixinh5/img/test.png" /></div>
						<div ng-if="x.hasIcon" class="name">{{x.name}}</div>
						<div ng-if="!x.hasIcon" class="noname">{{x.name}}</div>
						<div ng-if="!x.hasIcon" class="content">{{x.content}}</div>
						<i class="icon ion-chevron-right icon-color" ng-if="x.flag"></i>
					</div>
				</div>
			</ion-content>
		</div>
	</body>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript">
		var json=JSON.parse('${json}');
		wx.config({
			debug:true,
			appId:json.appId,
			timestamp: json.timestamp,
			nonceStr:json.nonceStr,
			signature:json.signature,
			jsApiList: [
				'checkJsApi',
				'onMenuShareTimeline',
				'onMenuShareAppMessage',
				'onMenuShareQQ',
				'onMenuShareWeibo',
				'hideMenuItems',
				'showMenuItems',
				'hideAllNonBaseMenuItem',
				'showAllNonBaseMenuItem',
				'translateVoice',
				'startRecord',
				'stopRecord',
				'onRecordEnd',
				'playVoice',
				'pauseVoice',
				'stopVoice',
				'uploadVoice',
				'downloadVoice',
				'chooseImage',
				'previewImage',
				'uploadImage',
				'downloadImage',
				'getNetworkType',
				'openLocation',
				'getLocation',
				'hideOptionMenu',
				'showOptionMenu',
				'closeWindow',
				'scanQRCode',
				'chooseWXPay',
				'openProductSpecificView',
				'addCard',
				'chooseCard',
				'openCard'
			]
		});
	</script>
	<script>
		var personalInformation = angular.module('personalInformation',['ionic']);
		personalInformation.controller('personalInformation',['$scope','$ionicActionSheet',function($scope,$ionicActionSheet){
			$scope.lists = [
				{href:'#',name:'没头像，没礼貌，请上传头像！',hasIcon:true,content:'',flag:false},
				{href:'#',name:'早早ID',hasIcon:false,content:'23232424223',flag:true},
				{href:'#',name:'我的车牌',hasIcon:false,content:'苏A L634P',flag:true},
				{href:'#',name:'我的昵称',hasIcon:false,content:'王宇',flag:true}
			];
			$scope.lists_1 = [
				{href:'#',name:'我的移车名片',hasIcon:false,content:'',flag:true}
			];
			$scope.lists_2 = [
				{href:'#',name:'修改密码',hasIcon:false,content:'',flag:true},
				{href:'#',name:'绑定手机',hasIcon:false,content:'15850761726',flag:true}
			];
			$scope.skip = function(item){
				console.log(item);
				if(item.hasIcon){
					$ionicActionSheet.show({
			            titleText: null,
			            buttons: [{
			                text: '拍照'
			            }, {
			                text: '从相册中选择'
			            }, ],
			            cancelText: '取消',
			            cancel: function() {
			                console.log('取消');
			            },
			            buttonClicked: function(index) {
			                console.log('BUTTON CLICKED', index);
			                return true;
			            }
			        });
				} else {
					console.log(item.href);
					//self.location = item.href;
				}
			};
		}]);
	</script>
</html>