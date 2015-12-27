var app = angular.module('myApp', ['ionic']);
app.config(function ($stateProvider, $urlRouterProvider) {
    $stateProvider.state('perfectInformation', {
		url: '/perfectInformation',
		templateUrl: '/weixinh5/html/perfectInformation.html'
	}).state('personalInformation', {
		url: '/personalInformation',
		templateUrl: '/weixinh5/html/personalInformation.html'
	}).state('bindingPhone', {
		url: '/bindingPhone',
		templateUrl: '/weixinh5/html/bindingPhone.html'
	})
    $urlRouterProvider.otherwise('/personalInformation');
});
