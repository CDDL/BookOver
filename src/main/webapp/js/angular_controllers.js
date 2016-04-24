/**
 * Created by Demils on 24/04/2016.
 */
var appBookOver = angular.module('BookOver');

appBookOver.controller('CtrlLogin', ['$scope', 'WebService', function ($scope, WebService) {
    var self = this;

    self.login = function (user, password) {
        WebService.login(user, password)
            .success(function (data) {
                console.log(data.msg);
            });
    }
}]);

appBookOver.controller('CtrlRegister', ['$scope', 'WebService', function ($scope, WebService) {
    var self = this;

    self.register = function (user, password, password_check, email, localization) {
        if(password != password_check) console.log("error contraseñas");
        else WebService.register(user, password, email, localization)
            .success(function (data) {
                console.log(data.msg);
            });
    }
}]);