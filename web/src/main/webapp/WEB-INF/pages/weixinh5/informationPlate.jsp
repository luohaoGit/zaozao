<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<title>车牌号咨询</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<link rel="stylesheet" href="/weixinh5/css/ionic.min.css"> 
		<link rel="stylesheet" href="/weixinh5/css/ionicons.min.css">
		<link rel="stylesheet" href="/weixinh5/css/informationPlate.css" />
		<script type="text/javascript" src="/weixinh5/js/ionic.bundle.min.js"></script>
		<script type="text/javascript" src="/weixinh5/js/angular-resource.min.js"></script>
		<script type="text/javascript" src="/weixinh5/js/jquery-1.11.1.js" ></script>
	</head>
	<body>
		<div ng-app="queryPlate" ng-controller="queryPlate">
			<ion-content class="container">
				<div class="hint padding-top padding-bottom1">您只需要将车主的车牌号告诉我们，我们将为您找到车主的联系方式！</div>
				<div class="plate-container">
					<select id="selectedArea" class="item-input item-select fl" ng-change="selectAreaAction()" ng-model="selectArea" ng-options="value.id as value.label for value in myAreas"></select>
					<select id="selectedLetter" class="item-input item-select fr" ng-change="selectLetterAction()" ng-model="selectLetter" ng-options="value.id as value.label for value in myLetters"></select>
					<input class="plate-input" type="text" ng-blur="query()" ng-model="plateNumber" placeholder="请输入车牌号！" />
				</div>
				<div class="clearfix padding-bottom1">
					<div class="more-query" ng-click="more()">更多查询</div>
				</div>
				<div ng-hide="moreFlag" class="padding-bottom1">
					<input class="width-full" type="text" ng-blur="query()" ng-model="moreInput" placeholder="请输入车主ID或手机号！" />
				</div>
				<div ng-hide="flag">
					<hr />
					<div class="padding-top1 padding-bottom1">车主信息</div>
					<div class="padding-bottom1 assist-color">微信号：{{ weChatNum }}</div>
					<div class="padding-bottom1 assist-color">手机号：{{ phoneNum }}</div>
				</div>
				<div class="button button-calm" ng-click="weChat()">启动微信聊天</div>				
				<div class="button button-balanced" ng-click="phone()">拨打车主电话</div>
			</ion-content>
		</div>
	</body>
	<script>
		var queryPlate = angular.module('queryPlate',['ionic']);
		queryPlate.controller('queryPlate', ['$scope', function($scope){
			$scope.flag = true;
			$scope.moreFlag = true;
			var areas = ["京", "津", "沪", "渝", "冀", "豫", "云", "辽", "黑", "湘", "皖", "鲁", "新", "苏", "浙", "赣", "鄂", "桂", "甘", "晋",
					"蒙", "陕", "吉", "闽", "贵", "粤", "青", "藏", "川", "宁", "琼"].reverse();
			var letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"].reverse();
			$scope.myAreas = [];
			$scope.myLetters = [];

			angular.forEach(areas, function(data,index,array){
				$scope.myAreas.push({"label": data});
			});

			angular.forEach(letters, function(data,index,array){
				$scope.myLetters.push({"label": data});
			});

			$scope.selectArea = $scope.myAreas[0].id;
			$scope.selectAreaAction = function(){
				console.log($('#selectedArea').val());
			}

			$scope.selectLetter = $scope.myLetters[0].id;
			$scope.selectLetterAction = function(){
				console.log($('#selectedLetter').val());
			}
			$scope.query = function(){
				$scope.flag = false;
			}
			//$scope.plateNumber = ;
			//$scope.moreInput = ;
			$scope.weChatNum = 'weixin9293041029'.replace('weixin9293041029'.substring(3,'weixin9293041029'.length-4), '****');
			$scope.phoneNum = '15850761726'.replace('15850761726'.substring(3,'15850761726'.length-4), '****');
			$scope.more = function(){
				$scope.moreFlag = false;
			}
			$scope.weChat = function(){
				$('.button').css('color','#F8F8F8');
				alert('weChat!');
			};
			$scope.phone = function(){
				$('.button').css('color','#F8F8F8');
				alert('phone!');
			};
		}]);
	</script>
</html>
