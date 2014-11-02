var homeControllers = angular.module('homeControllers', [
    'navbarServices',
    'restServices'
]);

homeControllers.controller('homeCtrl', [
    '$scope',
    '$sce',
    'navbarService',
    'restService',
    function ($scope, $sce, navbarService, restService) {
        navbarService.setPage('home');

        $scope.loadPage = function () {
            $scope.status = "loading";
            restService.getTwitter(function (data, status) {
                if (status / 100 == 2) {
                    $scope.data = data;
                    $scope.data.html = $sce.trustAsHtml(data.html);
                    $scope.status = "ok";
                }
                else {
                    $scope.status = "error";
                }
            });
        };

        $scope.loadPage();
    }
]);