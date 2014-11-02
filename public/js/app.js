var app = angular.module('nateApp', [
    'ui.router',
    'navbarServices',
    'aboutControllers',
    'animeControllers',
    'homeControllers'
]);

app.config(['$stateProvider',
            '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {
        $stateProvider.
            state('aboutMe', {
                url: '/about/me',
                templateUrl: '/assets/part/about-me.html',
                controller: 'aboutMeCtrl'
            }).
            state('aboutSite', {
                url: '/about/site',
                templateUrl: '/assets/part/about-site.html',
                controller: 'aboutSiteCtrl'
            }).
            state('animeWatching', {
                url: '/anime',
                templateUrl: '/assets/part/anime-watching.html',
                controller: 'animeWatchingCtrl'
            }).
            state('home', {
                url: '',
                templateUrl: '/assets/part/home.html',
                controller: 'homeCtrl'
            });
        $urlRouterProvider.otherwise('/about/me');
    }
]);

app.run([
    'navbarService',
    function (navbarService) {
        navbarService.init();
        Origami.fastclick(document.body);
    }
]);