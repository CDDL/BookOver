/**
 * Created by Demils on 28/04/2016.
 */

var appBookOver = angular.module('BookOver');

appBookOver.directive('errorAlert', function () {
    return {
        restrict: 'E',
        scope: {
            mensaje: '@'
        },
        templateUrl: '../html/angular_directives/errorAlert.html'
    }
});