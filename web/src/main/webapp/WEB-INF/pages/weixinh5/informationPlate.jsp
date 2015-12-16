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
	</head>
	<body>
	<div ng-app="queryPlate" ng-controller="queryPlateController">
		<ion-content class="container">
			<div class="hint padding-top padding-bottom1">请填写车牌号，我们帮您联系车主！</div>
			<div class="plate-container">
				<select id="selectedArea" class="item-input item-select fl" ng-change="selectAreaAction()" ng-model="selectArea" ng-options="value.id as value.label for value in myAreas"></select>
				<select id="selectedLetter" class="item-input item-select fr" ng-change="selectLetterAction()" ng-model="selectLetter" ng-options="value.id as value.label for value in myLetters"></select>
				<input class="plate-input" type="text" maxlength="5" ng-model="data.plateNumber" placeholder="请输入车牌号！" />
			</div>
			<div class="more padding-bottom1">您也可输入车主留下的其他信息</div>
			<div class="padding-bottom1">
				<input class="width-full" type="text" ng-blur="query()" ng-model="moreInput" placeholder="请输入车主ID或手机号！" />
			</div>
			<div class="button button-calm" ng-click="weChat()">启动微信聊天</div>
			<div class="button button-balanced" ng-click="phone()">拨打车主电话</div>
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
		 'openCard'
		 ]
		 });
	</script>

	<script>
		var queryPlate = angular.module('queryPlate',['ionic']);
		queryPlate.controller('queryPlateController', ['$scope', function($scope){
			$scope.data = {};

			var areas = ["京", "津", "沪", "渝", "冀", "豫", "云", "辽", "黑", "湘", "皖", "鲁", "新", "苏", "浙", "赣", "鄂", "桂", "甘", "晋",
				"蒙", "陕", "吉", "闽", "贵", "粤", "青", "藏", "川", "宁", "琼"];
			var letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
			$scope.myAreas = [];
			$scope.myLetters = [];

			angular.forEach(areas, function(data,index,array){
				$scope.myAreas.push({"id": index,"label": data});
			});

			$scope.selectArea = $scope.myAreas[0].id;
			$scope.selectAreaAction = function(){
				console.log($('#selectedArea').val());
			}

			angular.forEach(letters, function(data,index,array){
				$scope.myLetters.push({"id": index,"label": data});
			});

			$scope.selectLetter = $scope.myLetters[0].id;
			$scope.selectLetterAction = function(){
				console.log($('#selectedLetter').val());
			}
			$scope.query = function(){

			}

			$scope.data.plateNumber = "";
			$scope.$watch('data.plateNumber', function(newValue, oldValue, scope){
				if(/^[A-Za-z0-9]+$/.test(newValue)){
					scope.data.plateNumber = newValue.toUpperCase();
				}else{
					scope.data.plateNumber = newValue.replace(/[^A-Za-z0-9]*/g,"").toUpperCase();
				}
			})

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
