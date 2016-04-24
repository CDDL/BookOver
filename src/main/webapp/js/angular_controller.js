/**
 * Created by Demils on 24/04/2016.
 */
var appBookOver = angular.module('appBookOver');

appBookOver.controller('CtrlLogin', ['$scope', 'WebService', function ($scope, WebService) {
    var self = this;

    self.login = function (email, password) {
        AgendaService.login(email, password)
            .success(function (data) {
                console.log(data);
            });
    }
}]);