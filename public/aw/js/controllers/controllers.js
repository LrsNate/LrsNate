var controllers = angular.module('controllers', ['directives']);

controllers.controller('GameController', [
    'restService',
    '$scope',
    function (restService, $scope) {
        'use strict';

        $scope.gameId = '1';
        $scope.status = {classes: ['text-warning'], message: 'Loading map...'};
        $scope.selectedUnit = null;

        $scope.loadMap = function () {
            restService.getGameState('1', function (data) {
                $scope.status = {classes: ['text-warning'], message: 'Loading map...'};
                $scope.grid = data.grid.grid;
                angular.forEach(data.grid['units'], function (u) {
                    $scope.grid[u.y][u.x].unit = {kind: u.kind, x: u.x, y: u.y};
                    $scope.status = {
                        classes: ['text-success'],
                        message: 'Ready!'
                    };
                });
            });
        };
        $scope.loadMap();

        function clearMap() {
            for (var y = 0; y < $scope.grid.length; y++) {
                for (var x = 0; x < $scope.grid[y].length; x++) {
                    $scope.grid[y][x].selected = false;
                }
            }
        }

        function moveUnit(from, to) {
            $scope.status = {
                classes: ['text-warning'],
                message: 'Applying unit move...'
            };
            var move = {
                game_id: $scope.gameId,
                from: from,
                to: to
            };
            console.log(move);
            restService.moveUnit(move, function (data, status) {
                if (status < 300) {
                    $scope.status = {
                        classes: ['text-success'],
                        message: 'Unit moved successfully!'
                    };
                }
                else if (status < 500) {
                    $scope.status = {
                        classes: ['text-danger'],
                        message: 'Your map is currently outdated. Please reload the map.'
                    };
                }
                else {
                    $scope.status = {
                        classes: ['text-danger'],
                        message: 'Something has gone terribly wrong. Please reload the page.'
                    };
                }
            });
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
                    $scope.selectedUnit.x = x;
                    $scope.selectedUnit.y = y;
                    moveUnit(
                        {kind: $scope.selectedUnit.kind, x: oldX, y: oldY},
                        $scope.selectedUnit
                    );
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
    }
]);