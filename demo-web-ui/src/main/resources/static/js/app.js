angular.module('users', ['ngRoute']).config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'user-page.html',
        controller: 'userCtr'
    })
}).controller('userCtr', function ($scope, $http) {
    $http.get('users').success(function (data) {
    	//alert(data+"");
        $scope.userList = data;
    });
	$.get('go?a=123&b=456', function (data) {
    	//alert(data+"");
        $scope.fooGo = data;
    });
	
});