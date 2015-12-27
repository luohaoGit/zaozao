

app.controller('controller.perfectInformation', ['$scope', function($scope) {
	var areas = ["京", "津", "沪", "渝", "冀", "豫", "云", "辽", "黑", "湘", "皖", "鲁", "新", "苏", "浙", "赣", "鄂", "桂", "甘", "晋",
		"蒙", "陕", "吉", "闽", "贵", "粤", "青", "藏", "川", "宁", "琼"
	];
	var letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
		"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
	];
	$scope.myAreas = [];
	$scope.myLetters = [];

	angular.forEach(areas, function(data, index, array) {
		$scope.myAreas.push({
			"id": index,
			"label": data
		});
	});

	$scope.selectArea = $scope.myAreas[0].id;
	$scope.selectAreaAction = function() {
		console.log($('#selectedArea').val());
	}

	angular.forEach(letters, function(data, index, array) {
		$scope.myLetters.push({
			"id": index,
			"label": data
		});
	});

	$scope.selectLetter = $scope.myLetters[0].id;
	$scope.selectLetterAction = function() {
		console.log($('#selectedLetter').val());
	}
	$scope.submit = function() {

	}
}]);

app.controller('controller.personalInformation', ['$scope', '$ionicActionSheet', function($scope, $ionicActionSheet) {
	$scope.lists = [{
		href: '#',
		name: '没头像，没礼貌，请上传头像！',
		hasIcon: true,
		src: 'img/test.png',
		content: '',
		flag: false
	}, {
		href: '#',
		name: '早早ID',
		hasIcon: false,
		src: '',
		content: '23232424223',
		flag: true
	}, {
		href: '#perfectInformation',
		name: '我的车牌',
		hasIcon: false,
		src: '',
		content: '苏A L634P',
		flag: true
	}, {
		href: '#perfectInformation',
		name: '我的昵称',
		hasIcon: false,
		src: '',
		content: '王宇',
		flag: true
	}];
	$scope.lists_1 = [{
		href: '#',
		name: '我的移车名片',
		hasIcon: false,
		src: '',
		content: '',
		flag: true
	}];
	$scope.lists_2 = [{
		href: '#',
		name: '修改密码',
		hasIcon: false,
		src: '',
		content: '',
		flag: true
	}, {
		href: '#bindingPhone',
		name: '绑定手机',
		hasIcon: false,
		src: '',
		content: '15850761726',
		flag: true
	}];
	$scope.skip = function(item) {
		if (item.hasIcon) {
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
			self.location = item.href;
		}
	};
}]);

app.controller('controller.bindingPhone', ['$scope', function($scope) {
	$scope.sendFlag = false;
	$scope.timeFlag = true;
	$scope.sendSecurityCode = function() {
		//60s 倒计时 start
		$scope.time = 60;
		$scope.sendFlag = true;
		$scope.timeFlag = false;
		var myTime = setInterval(function() {
			if (--$scope.time == 0) {
				clearInterval(myTime);
				$scope.sendFlag = false;
				$scope.timeFlag = true;
			}
			$scope.$digest();
		}, 1000);
		//60s 倒计时  end
	};
	$scope.submit = function() {
		console.log($scope.phone);
		$scope.message = '手机号是必须的。';
	};
}]);
