var controllers = angular.module('controllers', []);

controllers.controller('GameController', [
    'restService',
    '$scope',
    function (restService, $scope) {
        'use strict';
        restService.getDefaultMap(function (data) {
            $scope.grid = data.grid;
            angular.forEach(data.units, function (u) {
                $scope.grid[u.y][u.x].unit = {type: u.type};
            });
        });

        $scope.cellClick = function (cell, x, y) {
            angular.element('.cell').removeClass('selected');
            angular.element('.x' + x + '.y' + y).addClass('selected');
        };

        $scope.cellClasses = function (cell, x, y) {
            var base = [cell.type, 'x' + x, 'y' + y];
            if (cell.unit) {
                base.push('unit');
                base.push('unit-' + cell.unit.type);
            }
            return base;
        };
    }
]);