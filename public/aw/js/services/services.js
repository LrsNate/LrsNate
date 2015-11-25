var services = angular.module('services', []);

services.factory('restService', [
    '$http',
    function ($http) {
        'use strict';
        var service = {};

        service.getGameState = function (gameId, onComplete) {
            $http.get('/aw/game/' + gameId)
                .success(onComplete)
                .error(onComplete);
        };

        service.moveUnit = function (move, onComplete) {
            $http.post('/aw/move/unit', move)
                .success(onComplete)
                .error(onComplete);
        };

        return service;
    }
]);