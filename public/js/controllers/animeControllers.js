var animeControllers = angular.module('animeControllers', [
    'navbarServices',
    'ui.bootstrap',
    'restServices'
]);

animeControllers.controller('animeWatchingCtrl', [
    '$scope',
    'navbarService',
    'restService',
    function ($scope, navbarService, restService) {
        navbarService.setPage('anime');

        $scope.animeWatchingLoad = function () {
            $scope.aw_status = "loading";
            $scope.animelist = {};
            restService.getAnimelists(function (data, status) {
                if (status / 100 == 2) {
                    $scope.anime = data;
                    $scope.aw_status = "ok";
                }
                else {
                    $scope.aw_status = "error";
                }
            });
        };
        $scope.animeWatchingLoad();
    }
]);

animeControllers.filter('slice', function () {
    return function (arr, start, end) {
        return arr.slice(start, end);
    };
});

animeControllers.config(function($provide) {
    $provide.decorator('accordionDirective', function($delegate) {
        $delegate[0].templateUrl = "/assets/tpl/accordion.html";
        return $delegate;
    });
    $provide.decorator('accordionGroupDirective', function($delegate) {
        $delegate[0].templateUrl = "/assets/tpl/accordion-group.html";
        return $delegate;
    });
});