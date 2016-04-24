/**
 * Created by Demils on 24/04/2016.
 */
var appBookOver = angular.module('appBookOver');

appBookOver.baseURI = 'http://localhost:8080/services/';

appBookOver.module('appBookOver')
    .service('WebService', ['$http', function($http) {
    this.create = function(nombre, apellidos, nif) {
        dato = {'persona': {'nombre': nombre, 'apellidos': apellidos, 'nif': nif}};
        var url = appBookOver.baseURI + nif;
        return $http.put(url, dato);
    }

    this.retrieveAll = function() {
        return $http.get(appBookOver.baseURI);
    }

    this.retrieveContact = function(nif) {
        var url = appBookOver.baseURI + nif;
        return $http.get(url);
    }

    this.delete = function(nif) {
        var url = appBookOver.baseURI + nif;
        var dato = {'nif': nif}
        return $http.delete(url, dato);
    }

    this.update = function (persona) {
        var url = appBookOver.baseURI + persona.persona.nif;
        return $http.put(url, persona);
    }
}]);