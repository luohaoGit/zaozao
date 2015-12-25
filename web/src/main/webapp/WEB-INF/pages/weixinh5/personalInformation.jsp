<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="ionicApp">
<head>
	<title>个人信息</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<link rel="stylesheet" href="/weixinh5/css/ionic.min.css">
	<link rel="stylesheet" href="/weixinh5/css/ionicons.min.css">
	<link rel="stylesheet" href="/weixinh5/css/personalInformation.css" />
	<link rel="stylesheet" href="/weixinh5/css/bindingPhone.css" />
	<link rel="stylesheet" href="/weixinh5/css/informationPlate.css" />
	<script type="text/javascript" src="/weixinh5/js/ionic.bundle.min.js"></script>
</head>
<body>

<ion-nav-view></ion-nav-view>

<script id="app.html" type="text/ng-template">
	<ion-side-menus>
		<ion-pane ion-side-menu-content>
			<ion-nav-view name="appContent" animation="slide-left-right"></ion-nav-view>
		</ion-pane>
	</ion-side-menus>
</script>

<script id="personalInformation.html" type="text/ng-template">
	<ion-view title="">
		<ion-content>
			<div class="list card border-radius">
				<div class="item item-icon-right list-container" ng-repeat="x in lists" ng-click="skip(x)">
					<div ng-if="x.hasIcon"><img class="head" ng-src="{{x.content}}" /></div>
					<div ng-if="x.hasIcon" class="name">{{x.name}}</div>
					<div ng-if="!x.hasIcon" class="noname">{{x.name}}</div>
					<div ng-if="!x.hasIcon" class="content">{{x.content}}</div>
					<i class="icon ion-chevron-right icon-color" ng-if="x.flag"></i>
				</div>
			</div>

			<div class="list card border-radius">
				<div class="item item-icon-right list-container" ng-click="bindcar()">
					<div class="noname">{{carText}}</div>
					<div class="content">{{data.cars[0].carNumber}}</div>
					<i class="icon ion-chevron-right icon-color"></i>
				</div>
				<div class="item item-icon-right list-container" ng-click="bindphone()">
					<div class="noname">{{phoneText}}</div>
					<div class="content">{{telephone}}</div>
					<i class="icon ion-chevron-right icon-color"></i>
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

		</ion-content>
	</ion-view>
</script>


<script id="bindcar.html" type="text/ng-template">
	<ion-view title="绑定车牌">
		<ion-content class="container">
			<div class="hint padding-top padding-bottom1">请填写车牌号</div>
			<div class="plate-container">
				<select class="item-input item-select fl" ng-model="data.selectArea" ng-options="value.id as value.label for value in myAreas"></select>
				<select class="item-input item-select fr" ng-model="data.selectLetter" ng-options="value.id as value.label for value in myLetters"></select>
				<input class="plate-input" type="text" maxlength="5" ng-model="data.plateNumber" placeholder="请输入车牌号！" />
			</div>
			<div class="button button-calm" ng-disabled="!data.valid" ng-click="updateCar()">确定</div>
		</ion-content>
	</ion-view>
</script>

<script id="bindphone.html" type="text/ng-template">
	<ion-view title="绑定手机">
		<ion-content class="container">
			<input type="text" ng-show="false" ng-model="message" />
			<div class="message" ng-bind="message"></div>
			<input class="width-full margin-position" type="text" maxlength="11" ng-model="data.phone" placeholder="手机号" />
			<div class="width-full margin-position position-relative">
				<input class="width-full margin-position" type="text" maxlength="6" ng-model="data.code" placeholder="验证码" />
				<div class="button button-clear security" ng-hide="sendFlag" ng-click="sendSecurityCode()">发送验证码</div>
				<div class="security text-color" ng-hide="timeFlag">{{time}}</div>
			</div>
			<div class="button button-calm width-full" ng-disabled="!(data.valid && data.codevalid)" ng-click="next()">确定</div>
		</ion-content>
	</ion-view>
</script>


<script type="text/javascript">
	angular.module('ionicApp', ['ionic'])

			.config(function($stateProvider, $urlRouterProvider) {
				$stateProvider

						.state('app', {
							url: "/app",
							abstract: true,
							templateUrl: "app.html",
							controller: 'AppCtrl'
						})

						.state('app.personalInformation', {
							url: "/personalInformation",
							views: {
								'appContent' :{
									templateUrl: "personalInformation.html",
									controller: 'personalInformation'
								}
							}
						})

						.state('app.bindcar', {
							url: "/bindcar",
							views: {
								'appContent' :{
									templateUrl: "bindcar.html",
									controller: 'bindcar'
								}
							}
						})

						.state('app.bindphone', {
							url: "/bindphone",
							views: {
								'appContent' :{
									templateUrl: "bindphone.html",
									controller: 'bindphone'
								}
							}
						})

				// if none of the above states are matched, use this as the fallback
				$urlRouterProvider.otherwise('/app/personalInformation');
			})

			.controller('AppCtrl', function($scope, $rootScope) {
				alert('${json}')
				$rootScope.pdata = JSON.parse('${json}');
			})

			.controller('personalInformation', function($scope,$ionicActionSheet,$state, $rootScope) {
				$scope.data = $rootScope.pdata;

				$scope.telephone = $scope.data.telephone;
				if($scope.telephone){
					$scope.telephone = $scope.telephone.substr(0, 3) + '****' + $scope.telephone.substr(7)
				}

				$scope.lists = [
					{href:'#',name:'',hasIcon:true,content:$scope.data.headImgUrl,flag:false},
					{href:'#',name:'早早ID',hasIcon:false,content:$scope.data.zzid,flag:false},
					{href:'#',name:'我的昵称',hasIcon:false,content:$scope.data.wxnickname,flag:false}
				];
				$scope.lists_1 = [
					{href:'#',name:'我的移车名片',hasIcon:false,content:'',flag:true}
				];
				$scope.lists_2 = [
					{name:"bindphone", href:'#',name:'绑定手机',hasIcon:false,content:'',flag:true}
				];

				$scope.carText = "绑定车牌";
				if($scope.data.cars[0].carNumber){
					$scope.carText = "修改车牌";
				}

				$scope.phoneText = "绑定手机";
				if($scope.data.telephone){
					$scope.phoneText = "修改手机";
				}


				$scope.skip = function(item){
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
						//$state.go("app.bindphone");
					}
				};


				$scope.bindphone = function(){
					$state.go("app.bindphone");
				}

				$scope.bindcar = function(){
					$state.go("app.bindcar");
				}
			})

			.controller('bindcar', function($scope, $stateParams, $http, $window, $rootScope) {
				var areas = ["京", "津", "沪", "渝", "冀", "豫", "云", "辽", "黑", "湘", "皖", "鲁", "新", "苏", "浙", "赣", "鄂", "桂", "甘", "晋",
					"蒙", "陕", "吉", "闽", "贵", "粤", "青", "藏", "川", "宁", "琼"];
				var letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
					"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
				$scope.myAreas = [];
				$scope.myLetters = [];

				angular.forEach(areas, function(data,index,array){
					$scope.myAreas.push({"id": data,"label": data});
				});

				angular.forEach(letters, function(data,index,array){
					$scope.myLetters.push({"id": data,"label": data});
				});

				$scope.data = {
					pdata: $rootScope.pdata,
					selectArea:'京',
					selectLetter:'A',
					plateNumber:$rootScope.pdata.cars[0].carNumber,
					valid:false
				}

				if($scope.data.plateNumber){
					$scope.data.selectArea = $scope.data.plateNumber[0];
					$scope.data.selectLetter = $scope.data.plateNumber[1];
					$scope.data.plateNumber = $scope.data.plateNumber.substr(2, 5);
				}

				$scope.$watch('data.plateNumber', function(newValue, oldValue, scope){
					scope.data.valid = false;
					if(/^[A-Za-z0-9]+$/.test(newValue)){
						scope.data.plateNumber = newValue.toUpperCase();
					}else{
						scope.data.plateNumber = newValue.replace(/[^A-Za-z0-9]*/g,"").toUpperCase();
					}
					if(scope.data.plateNumber.length == 5){
						scope.data.valid = true;
					}
				})

				$scope.updateCar = function(){
					var carNumber = $scope.data.selectArea + $scope.data.selectLetter + $scope.data.plateNumber;
					var cardata = {
						carNumber : carNumber,
						openid: $scope.data.pdata.id
					}

					$http({
						method:'GET',
						url:'/weixin/h5/carnumber.json?carNumber=' + carNumber
					}).success(function(data){
						if(data.count == 1){
							alert("车牌已被绑定");
						}else{
							$http.post('/weixin/h5/carnumber.json',cardata)
									.success(function(data){
										$rootScope.pdata.cars[0].carNumber = carNumber;
										$window.history.back();
									})
									.error(function(data){
										alert("更新失败");
									});
						}
					}).error(function(data){
						alert("网络错误");
					});

				}
			})

			.controller('bindphone', function($scope, $rootScope) {
				$scope.data = {
					pdata: $rootScope.pdata,
					phone: "",
					valid: false,
					codevalid: false
				}

				$scope.$watch("data.phone", function(newValue, oldValue, scope){
					scope.data.valid = false;

					if(!/^[0-9]*$/.test(newValue)){
						scope.data.phone = newValue.replace(/[^0-9]*/g,"");
					}

					if(/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test(newValue)){
						scope.data.valid = true;
					}
				});

				$scope.$watch("data.code", function(newValue, oldValue, scope){
					scope.data.codevalid = false;

					if(!/^[0-9]*$/.test(newValue)){
						scope.data.code = newValue.replace(/[^0-9]*/g,"");
					}

					if(newValue.length == 6){
						scope.data.codevalid = true;
					}
				});

				$scope.sendFlag = false;
				$scope.timeFlag = true;
				$scope.sendSecurityCode = function(){
					if(!$scope.data.valid){
						$scope.message = '手机号是必须的';
						return;
					}

					$scope.time = 60;
					$scope.sendFlag = true;
					$scope.timeFlag = false;
					var myTime = setInterval(function() {
						if(--$scope.time==0){
							clearInterval(myTime);
							$scope.sendFlag = false;
							$scope.timeFlag = true;
						}
						$scope.$digest();
					}, 1000);
				};
				$scope.next = function(){
					console.log($scope.phone);
					$scope.message = '手机号是必须的';
				};
			});

</script>

</body>
</html>
