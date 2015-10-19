/**
 * Created by nate on 10/19/15.
 */

var services = angular.module('services', []);

services.factory('restService', [
    '$http',
    function ($http) {
        'use strict';
        var service = {};

        service.getDefaultMap = function (onComplete) {
            $http.get('/aw/map/default')
                .success(onComplete)
                .error(onComplete);
        };

        return service;
    }
]);