/**
 * Created by Demils on 24/04/2016.
 */

var appBookOver = angular.module('BookOver');

appBookOver.controller('CtrlLogin', ['$scope', 'WebService', function ($scope, WebService) {
    var self = this;

    self.login = function (user, password) {
        WebService.login(user, password)
            .then(function (jsonObject) {
                console.log("Login success");
            }, function errorCallBack(response){
                console.log("Login failed");
            });
    };
}]);

appBookOver.controller('CtrlRegister', ['$scope', 'WebService', function ($scope, WebService) {
    var self = this;

    self.register = function (user, password, password_check, email, localization) {
        if (password != password_check) console.log("Error contraseñas");
        else WebService.register(user, password, email, localization)
            .then(function successCallback(response) {
                console.log("Registrado");
            });
    };
}]);