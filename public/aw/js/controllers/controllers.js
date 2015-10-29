var controllers = angular.module('controllers', []);

controllers.controller('GameController', [
    'restService',
    '$scope',
    function (restService, $scope) {
        'use strict';
        restService.getDefaultMap(function (data) {
            $scope.grid = data.grid;
            angular.forEach(data.units, function (u) {
                $scope.grid[u.y][u.x].unit = {kind: u.kind, x: u.x, y: u.y};
            });
        });

        console.log($scope.grid);

        $scope.selectedUnit = {};

        $scope.cellClick = function (cell, x, y) {
            var element = angular.element('.x' + x + '.y' + y),
                wasSelected = element.hasClass('cell-selected');
            angular.element('.cell-selected').removeClass('cell-selected');
            if (!wasSelected) {
                element.addClass('cell-selected');
                if (element.hasClass('unit')) {
                    $scope.selectedUnit = {
                        kind: $scope.grid[y][x].unit.kind,
                        x: x,
                        y: y
                    };
                }
                else if (!angular.equals({}, $scope.selectedUnit)) {
                    var oldX = $scope.selectedUnit.x,
                        oldY = $scope.selectedUnit.y;
                    $scope.grid[y][x].unit = $scope.selectedUnit;
                    $scope.grid[oldY][oldX].unit = {};
                    angular.element('.x' + oldX + '.y' + oldY)
                        .removeClass('unit')
                        .removeClass('unit-' + $scope.selectedUnit.kind);
                    element.addClass('unit')
                        .addClass('unit-' + $scope.selectedUnit.kind);
                    $scope.selectedUnit = {};
                    element.removeClass('cell-selected');
                }
            }
            else {
                $scope.selectedUnit = {};
            }
            console.log($scope.grid);
        };

        $scope.cellMouseEnter = function (cell, x, y) {
            angular.element('.x' + x + '.y' + y).addClass('cell-hover');
        };

        $scope.cellMouseOut = function (cell, x, y) {
            angular.element('.x' + x + '.y' + y).removeClass('cell-hover');
        };

        $scope.cellClasses = function (cell, x, y) {
            var base = [cell.kind, 'x' + x, 'y' + y];
            if (cell.unit) {
                base.push('unit');
                base.push('unit-' + cell.unit.kind);
            }
            return base;
        };
    }
]);