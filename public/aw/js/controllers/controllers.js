var controllers = angular.module('controllers', []);

controllers.controller('GameController', [
    '$scope',
    function ($scope) {
        'use strict';
        $scope.grid = [
            [
                {type: "plain"},
                {type: "plain"},
                {type: "plain"},
                {type: "plain"},
                {type: "plain"}
            ],
            [
                {type: "plain"},
                {type: "plain"},
                {type: "plain"},
                {type: "plain"},
                {type: "plain"}
            ],
            [
                {type: "plain"},
                {type: "plain"},
                {type: "plain"},
                {type: "plain"},
                {type: "plain"}
            ],
            [
                {type: "plain"},
                {type: "plain"},
                {type: "plain"},
                {type: "plain"},
                {type: "plain"}
            ]
        ];

        $scope.cellClick = function (cell, x, y) {
            angular.element('.cell').removeClass('selected');
            angular.element('.x' + x + '.y' + y).addClass('selected');
        };

        $scope.cellClasses = function (cell, x, y) {
            return [cell.type, 'x' + x, 'y' + y];
        };
    }
]);