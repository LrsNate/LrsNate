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

        $scope.loadPage = function () {
            $scope.pageStatus = "loading";
            $scope.data = {};
            restService.getAnimelists(function (data, status) {
                if (status / 100 == 2) {
                    $scope.data = data;
                    $scope.pageStatus = "ok";
                }
                else {
                    $scope.pageStatus = "error";
                }
            });
        };
        $scope.loadPage();
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