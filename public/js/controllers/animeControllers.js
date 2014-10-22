var aboutControllers = angular.module('animeControllers', [
    'navbarServices',
    'restServices'
]);

aboutControllers.controller('animeWatchingCtrl', [
    '$scope',
    'navbarService',
    'restService',
    function ($scope, navbarService, restService) {
        navbarService.setPage('anime');

        $scope.animeWatchingLoad = function () {
            $scope.animewatching_status = "loading";
            $scope.animelist = [];
            restService.getAnimeWatching(function (data, status) {
                if (status / 100 == 2) {
                    $scope.animelist = data;
                    $scope.animewatching_status = "ok";
                }
                else {
                    $scope.animewatching_status = "error";
                }
            });
        };

        $scope.animeWatchingLoad();
    }
]);