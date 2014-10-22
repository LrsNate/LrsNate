var navbarServices = angular.module('navbarServices', []);

navbarServices.factory('navbarService', function () {
   return {
       setPage: function (page) {
           angular.element('.navbar-tab').removeClass('active');
           angular.element('.navbar-' + page).addClass('active');
           var toggle = angular.element('.navbar-toggle');
           if (!toggle.hasClass('collapsed'))
               toggle.click();
       }
   };
});