var aboutControllers = angular.module('aboutControllers', []);

aboutControllers.controller('aboutMeCtrl', [function () {
    var toggle = angular.element('.navbar-toggle');
    angular.element('#navbar-about').addClass('active');
    if (!toggle.hasClass('collapsed'))
        toggle.click();
}]);

aboutControllers.controller('aboutSiteCtrl', [function () {
    var toggle = angular.element('.navbar-toggle');
    angular.element('#navbar-about').addClass('active');
    if (!toggle.hasClass('collapsed'))
        toggle.click();
}]);