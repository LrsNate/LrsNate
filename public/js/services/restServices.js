var restServices = angular.module('restServices', []);

restServices.factory('restService', [
    '$http',
    function ($http) {
        return {
            getTwitter: function (onComplete) {
                $http.get('/twitter/mention').
                    success(onComplete).
                    error(onComplete);
            },
            getAnimelists: function (onComplete) {
                $http.get('/anime/lists').
                    success(onComplete).
                    error(onComplete);
            }
        };
    }
]);