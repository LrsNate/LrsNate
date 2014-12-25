var navbarServices = angular.module('navbarServices', []);

navbarServices.factory('navbarService', function () {
   return {
       init: function () {
           angular.element('.navbar-link').click(function () {
               var toggle = angular.element('.navbar-toggle');
               if (angular.element('.navbar-collapse').hasClass('in'))
                   toggle.click();
           });
       },

       setPage: function (page) {
           angular.element('.navbar-tab').removeClass('active');
           angular.element('.navbar-' + page).addClass('active');
       }
   };
});