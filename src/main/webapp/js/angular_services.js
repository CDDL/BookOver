/**
 * Created by Demils on 24/04/2016.
 */


var appBookOver = angular.module('BookOver');

appBookOver.baseURI = 'http://localhost:8080/BookOver/';

appBookOver.loginURI = appBookOver.baseURI + "login";
appBookOver.registerURI = appBookOver.baseURI + "register";
appBookOver.registrarLibroURI = appBookOver.baseURI + "registrarLibro";
appBookOver.editarLibroURI = appBookOver.baseURI + "editarLibro";


// appBookOver.service('IdentificationService', [function () {
//
//     function Usuario (usuario, contraseña) {
//         this.usuario = usuario;
//         this.contraseña = contraseña;
//
//         this.getUsuario = function () {return this.usuario }
//         this.getContraseña = function () { return this.contraseña }
//     }
//
//     //var user= Usuario (usuario, contraseña);
//
//
//     this.saveLogin = function (usuario, contraseña) {
//
//     };
//
//     this.injectDataLogin = function (data) {
//
//     };
//
//     this.logOut = function () {
//
//     };
// }]);

appBookOver.service('WebService', ['$http', function ($http) {
    // this.create = function(nombre, apellidos, nif) {
    //     dato = {'persona': {'nombre': nombre, 'apellidos': apellidos, 'nif': nif}};
    //     var url = appBookOver.baseURI + nif;
    //     return $http.put(url, dato);
    // }

    var token = '';

    this.login = function (user, password) {
        return $http.post(appBookOver.loginURI,
            {'loginData':{'username': user, 'password': password}}, {headers: {'Authorization': token}});
    };

    this.register = function (user, password, email, localization) {
        return $http.post(appBookOver.registerURI,
            {'user': user, 'password': password, 'email': email, 'localization':localization});
    };


    this.registrarLibro = function (dato) {
        //dataFinal = injectDataUsuario({'nombreLibro': nombreLibro});


        return $http.post(appBookOver.registrarLibroURI, dato,  {headers: {'Authorization': 'Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ=='}});
    };

    this.editarLibro = function (dato) {

        return $http.post(appBookOver.editarLibroURI, dato, {headers: {'Authorization': 'Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ=='}});
    };
    //cmprbr lo que hace el update del controler de agenda -> actualiza variable del scope


    this.recuperaTodosLibros = function(idUsuario) {
        var url = appBookOver.baseURI + 'usuario/' + idUsuario; // + ??? cmprbr url
        return $http.get(appBookOver.baseURI);
    }

    this.recuperaLibro = function(idLibro) {
        var url = appBookOver.baseURI + 'libro/' + idLibro; //+ ?? cmprbr url
        return $http.get(url);
    }
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
