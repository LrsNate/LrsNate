var aboutControllers = angular.module('animeControllers', [
    'navbarServices',
    'restServices'
]);

aboutControllers.controller('animeWatchingCtrl', [
    '$scope',
    'navbarService',
    'restService',
    function ($scope, navbarService, restService) {
        $scope.animewatching_status = "loading";
        navbarService.setPage('anime');
        restService.getAnimeWatching(function (data, status) {
            $scope.animelist = data;
            $scope.animewatching_status = "ok";
        });
    }
]);