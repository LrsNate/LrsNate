var restServices = angular.module('restServices', []);

restServices.factory('restService', [
    '$http',
    function ($http) {
        return {
            getAnimeWatching: function (onComplete) {
                $http.get('/anime/watching').
                    success(onComplete).
                    error(onComplete);
            }
        };
    }
]);