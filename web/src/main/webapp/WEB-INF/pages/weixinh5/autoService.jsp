<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<title>汽车服务</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<link rel="stylesheet" href="/weixinh5/css/ionic.min.css"> 
		<link rel="stylesheet" href="/weixinh5/css/ionicons.min.css">
		<link rel="stylesheet" href="/weixinh5/css/autoService.css" />
		<script type="text/javascript" src="/weixinh5/js/jquery-1.11.1.js"></script>
		<script type="text/javascript" src="/weixinh5/js/ionic.bundle.min.js"></script>
		<script type="text/javascript" src="/weixinh5/js/angular-resource.min.js"></script>
	</head>
	<body>
		<div ng-app="autoService" ng-controller="autoService">
			<ion-content class="container">
				<ul class="clearfix margin-top">
					<li class="clearfix item-container" ng-repeat="x in lists" ng-click="skip(this)">
						<div class="item-img"><img ng-src="{{x.src}}"/></div>
						<div class="item-name">{{x.name}}</div>
					</li>
				</ul>
				<div class="periphery">周边</div>
				<ul class="clearfix">
					<li class="clearfix item-container" ng-repeat="x in perLists" ng-click="skip(this)">
						<div class="item-img"><img ng-src="{{x.src}}"/></div>
						<div class="item-name">{{x.name}}</div>
					</li>
				</ul>
			</ion-content>
		</div>
	</body>
	<script>
		var autoService = angular.module('autoService',['ionic']);
		autoService.controller('autoService', ['$scope', function($scope){
			$scope.lists = [
				{name:'配饰车件',src:'/weixinh5/img/test.png'},
				{name:'洗车',src:'/weixinh5/img/test.png'},
				{name:'保养',src:'/weixinh5/img/test.png'},
				{name:'维修',src:'/weixinh5/img/test.png'},
				{name:'车险',src:'/weixinh5/img/test.png'},
				{name:'代驾',src:'/weixinh5/img/test.png'}
			];
			$scope.perLists = [
				{name:'4S店',src:'/weixinh5/img/test.png'},
				{name:'加油站',src:'/weixinh5/img/test.png'},
				{name:'停车场',src:'/weixinh5/img/test.png'}
			];
			$scope.skip = function(e){
				console.log(e);
			};
		}]);
	</script>
</html>