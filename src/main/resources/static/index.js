angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/score';

    $scope.incrementScore = function () {
        console.log('increment')
        $http.post(contextPath + '/inc')
            .then(function (resp){
                $scope.getScore();
                console.log($scope.score)
            })

    };
    $scope.decrementScore = function () {
        $http.post(contextPath + '/dec')
            .then(function (resp){
                $scope.getScore();
            })

    };
    $scope.getScore = function () {
        $http.get(contextPath + '/get/current')
            .then(function (resp){
                $scope.score = resp.data();
            })

    };
    $scope.showScoreByUserId = function (userId) {
        $http.get(contextPath + '/get/'+ userId)
            .then(function (resp){
                $scope.scoreByUserId = resp.data();
            })

    };
});