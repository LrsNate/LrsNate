var directives = angular.module('directives', []);

directives.directive('awGrid', function () {
    return {
        restrict: 'E',
        templateUrl: '/assets/aw/tpl/aw_grid.html',
        link: function (scope) {
            scope.cellMouseEnter = function (x, y) {
                scope.grid[y][x].hover = true;
            };

            scope.cellMouseOut = function (x, y) {
                scope.grid[y][x].hover = false;
            };

            scope.cellClasses = function (x, y) {
                var cell = scope.grid[y][x],
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
    };
});