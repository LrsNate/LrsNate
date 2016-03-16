var app = angular.module('nateApp', [
    'ngRoute',
    'navbarServices',
    'aboutControllers',
    'animeControllers',
    'homeControllers'
]);

app.config(['$routeProvider', '$locationProvider',
    function ($routeProvider, $locationProvider) {
        'use strict';
        $routeProvider.when('/about/me', {
            templateUrl: '/assets/part/about-me.html',
            controller: 'aboutMeCtrl'
        }).when('/about/site', {
            templateUrl: '/assets/part/about-site.html',
            controller: 'aboutSiteCtrl'
        }).when('/anime', {
            templateUrl: '/assets/part/anime-watching.html',
            controller: 'animeWatchingCtrl'
        }).when('/', {
            templateUrl: '/assets/part/home.html',
            controller: 'homeCtrl'
        }).otherwise({
            redirectTo: '/'
        });
        return $locationProvider.html5Mode(true).hashPrefix("!");
    }]);

app.run([
    'navbarService',
    function (navbarService) {
        'use strict';
        navbarService.init();
        FastClick.attach(document.body, {});
    }
]);