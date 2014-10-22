var app = angular.module('nateApp', [
    'ui.router',
    'aboutControllers',
    'animeControllers'
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
            });
        $urlRouterProvider.otherwise('/about/me');
    }
]);

app.run(function () {
    Origami.fastclick(document.body);
});