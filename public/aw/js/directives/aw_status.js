var directives = angular.module('directives');

directives.directive('awStatus', function () {
    return {
        restrict: 'E',
        templateUrl: '/assets/aw/tpl/aw_status.html',
        link: function (scope) {
            scope.statesEnum = {
                init: {
                    classes: ['text-warning'],
                    message: 'Loading map...'
                },
                loading: {
                    classes: ['text-warning'],
                    message: 'Applying move...'
                },
                ok: {
                    classes: ['text-success'],
                    message: 'Ready!'
                },
                outdated: {
                    classes: ['text-danger'],
                    message: 'Your map is currently outdated. Please reload the map.'
                },
                error: {
                    classes: ['text-danger'],
                    message: 'Something has gone terribly wrong. Please reload the page.'
                }
            };

            scope.updateStatus = function (httpData, httpStatus) {
                if (httpStatus < 300) {
                    scope.status = scope.statesEnum.ok;
                }
                else if (httpStatus < 500) {
                    scope.status = scope.statesEnum.outdated;
                }
                else {
                    scope.status = scope.statesEnum.error;
                }
            };
        }
    };
});