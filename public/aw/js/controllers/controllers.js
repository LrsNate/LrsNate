var controllers = angular.module('controllers', []);

controllers.controller('GameController', [
    'restService',
    '$scope',
    function (restService, $scope) {
        'use strict';

        $scope.loadMap = function () {
            restService.getDefaultGameState(function (data) {
                $scope.grid = data.grid.grid;
                angular.forEach(data.grid.units, function (u) {
                    $scope.grid[u.y][u.x].unit = {kind: u.kind, x: u.x, y: u.y};
                });
            });
        };
        $scope.loadMap();

        $scope.selectedUnit = null;

        function clearMap() {
            for (var y = 0; y < $scope.grid.length; y++) {
                for (var x = 0; x < $scope.grid[y].length; x++) {
                    $scope.grid[y][x].selected = false;
                }
            }
        }

        $scope.cellClick = function (x, y) {
            var cell = $scope.grid[y][x],
                wasSelected = cell.selected;
            clearMap();
            if (!wasSelected) {
                cell.selected = true;
                if (cell.unit) {
                    $scope.selectedUnit = {
                        kind: $scope.grid[y][x].unit.kind,
                        x: x,
                        y: y
                    };
                }
                else if ($scope.selectedUnit) {
                    var oldX = $scope.selectedUnit.x,
                        oldY = $scope.selectedUnit.y;
                    cell.unit = $scope.selectedUnit;
                    $scope.grid[oldY][oldX].unit = null;
                    $scope.selectedUnit = null;
                    cell.selected = false;
                }
            }
            else {
                $scope.selectedUnit = null;
            }
        };

        $scope.cellMouseEnter = function (x, y) {
            $scope.grid[y][x].hover = true;
        };

        $scope.cellMouseOut = function (x, y) {
            $scope.grid[y][x].hover = false;
        };

        $scope.cellClasses = function (x, y) {
            var cell = $scope.grid[y][x],
                base = ['field', 'field-' + cell.kind, 'x' + x, 'y' + y];
            if (cell.unit) {
                base.push('unit');
                base.push('unit-' + cell.unit.kind);
            }
            if (cell.hover) {
                base.push('cell-hover');
            }
            if (cell.selected) {
                base.push('cell-selected');
            }
            return base;
        };
    }
]);