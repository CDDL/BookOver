/**
 * Created by Demils on 24/04/2016.
 */


var appBookOver = angular.module('BookOver');

appBookOver.baseURI = 'http://localhost:8080/BookOver/';

appBookOver.loginURI = appBookOver.baseURI + "login";
appBookOver.registerURI = appBookOver.baseURI + "register";

appBookOver.service('WebService', ['$http', function ($http) {
    // this.create = function(nombre, apellidos, nif) {
    //     dato = {'persona': {'nombre': nombre, 'apellidos': apellidos, 'nif': nif}};
    //     var url = appBookOver.baseURI + nif;
    //     return $http.put(url, dato);
    // }

    this.login = function (user, password) {
        return $http.post(appBookOver.loginURI,
            {'loginData':{'username': user, 'password': password}});
    };

    this.register = function (user, password, email, localization) {
        return $http.post(appBookOver.registerURI,
            {'user': user, 'password': password, 'email': email, 'localization':localization});
    };

    // this.retrieveContact = function(nif) {
    //     var url = appBookOver.baseURI + nif;
    //     return $http.get(url);
    // }
    //
    // this.delete = function(nif) {
    //     var url = appBookOver.baseURI + nif;
    //     var dato = {'nif': nif}
    //     return $http.delete(url, dato);
    // }
    //
    // this.update = function (persona) {
    //     var url = appBookOver.baseURI + persona.persona.nif;
    //     return $http.put(url, persona);
    // }
}]);