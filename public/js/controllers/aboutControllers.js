var aboutControllers = angular.module('aboutControllers', [
    'navbarServices'
]);

aboutControllers.controller('aboutMeCtrl', [
    'navbarService',
    function (navbarService) {
        navbarService.setPage('about')
    }
]);

aboutControllers.controller('aboutSiteCtrl', [
    'navbarService',
    function (navbarService) {
        navbarService.setPage('about')
    }
]);