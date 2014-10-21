var app = angular.module('nateApp', [
    'ngRoute',
    'aboutControllers'
]);

app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/about/me', {
                templateUrl: '/assets/part/about-me.html',
                controller: 'aboutMeCtrl'

            }).
            when('/about/site', {
                templateUrl: '/assets/part/about-site.html',
                controller: 'aboutSiteCtrl'
            }).
            otherwise({
                redirectTo: '/about/me'
            });
    }
]);