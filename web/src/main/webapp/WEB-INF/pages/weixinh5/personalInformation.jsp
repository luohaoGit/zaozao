<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="ionicApp">
<head>
	<title>个人信息</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<link href="http://cdn.bootcss.com/ionic/1.2.1/css/ionic.min.css" rel="stylesheet">
	<link href="http://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet">
	<link href="/weixinh5/css/style.css" rel="stylesheet"/>
	<script type="text/javascript" src="http://cdn.bootcss.com/ionic/1.2.1/js/ionic.bundle.min.js"></script>
</head>
<body>

<ion-nav-view></ion-nav-view>

<script type="text/javascript">
	angular.module('ionicApp', ['ionic'])

			.config(function($stateProvider, $urlRouterProvider) {
				$stateProvider

						.state('app', {
							url: "/app",
							abstract: true,
							templateUrl: "/weixinh5/html/app.html",
							controller: 'AppCtrl'
						})

						.state('app.personalInformation', {
							url: "/personalInformation",
							views: {
								'appContent' :{
									templateUrl: "/weixinh5/html/personalInformation.html",
									controller: 'personalInformation'
								}
							}
						})

						.state('app.bindcar', {
							url: "/bindcar",
							views: {
								'appContent' :{
									templateUrl: "/weixinh5/html/perfectInformation.html",
									controller: 'bindcar'
								}
							}
						})

						.state('app.bindphone', {
							url: "/bindphone",
							views: {
								'appContent' :{
									templateUrl: "/weixinh5/html/bindingPhone.html",
									controller: 'bindphone'
								}
							}
						})

				// if none of the above states are matched, use this as the fallback
				$urlRouterProvider.otherwise('/app/personalInformation');
			})

			.controller('AppCtrl', function($scope, $rootScope) {
				$rootScope.pdata = JSON.parse('${user}')
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
				$scope.phoneText = "绑定手机";

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
