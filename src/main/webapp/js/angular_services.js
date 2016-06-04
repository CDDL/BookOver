/**
 * Created by Demils on 24/04/2016.
 */


var appBookOver = angular.module('BookOver');

appBookOver.baseURI = 'http://localhost:8080/BookOver/';

appBookOver.loginURI = appBookOver.baseURI + "usuarios/login";
appBookOver.registerURI = appBookOver.baseURI + "usuarios";
appBookOver.editarPerfilURI = appBookOver.baseURI + "usuarios/editar";
appBookOver.registrarLibroURI = appBookOver.baseURI + "libros";
appBookOver.editarLibroURI = appBookOver.baseURI + "libros/";
appBookOver.retirarLibroURI = appBookOver.baseURI + "libros/";

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

appBookOver.service('WebService', ['$http', '$rootScope', function ($http, $rootScope) {
    // this.create = function(nombre, apellidos, nif) {
    //     dato = {'persona': {'nombre': nombre, 'apellidos': apellidos, 'nif': nif}};
    //     var url = appBookOver.baseURI + nif;
    //     return $http.put(url, dato);
    // }
    
    this.setToken=function (tkn){
        $rootScope.token=tkn;
    };
    
    this.getToken=function () {
        return($rootScope);
    };
    
    this.login = function (user, password) {
        var dato={'usuario':{'username': user, 'password': password}};
        return $http.post(appBookOver.loginURI,dato);
    };

    this.register = function (user, password, email, localization) {
        var dato={'usuario': {'username': user, 'email': email, 'password': password, 'ubicacion':localization}};
        //console.log(dato);
        return $http.post(appBookOver.registerURI,dato);
    };

    this.editarPerfil = function (dato) {

        //codigo para probar
        console.log(dato); //borrar
        $rootScope.token="x";   //borrar
        return $http.post(appBookOver.editarPerfilURI,dato, {'headers': {'Authorization': $rootScope.token}} );

        //codigo final
        //return $http.post(appBookOver.editarPerfilURI,dato, {'headers': {'Authorization': $rootScope.token.token}} );

    };

    this.registrarLibro = function (dato) {
        //dataFinal = injectDataUsuario({'nombreLibro': nombreLibro});

        return $http.post(appBookOver.registrarLibroURI, dato,  {'headers': {'Authorization': $rootScope.token}});
    };

    this.editarLibro = function (dato) {
        var idlibro = dato.libro.id;
        return $http.post(appBookOver.editarLibroURI + idlibro, dato, {'headers': {'Authorization': $rootScope.token}});
    };
    //cmprbr lo que hace el update del controler de agenda -> actualiza variable del scope
    
    this.recuperaTodosLibros = function(idUsuario) {
        var url = appBookOver.baseURI + 'usuarios/' + idUsuario; // + ??? cmprbr url
        return $http.get(url);
    };

    this.recuperaLibro = function(idLibro) {
        var url = appBookOver.baseURI + 'libros/' + idLibro; //+ ?? cmprbr url
        return $http.get(url);
    };

    this.retirarLibro = function(idLibro) {
        var url = appBookOver.retirarLibroURI + idLibro; //+ ?? cmprbr url
        return $http.delete(url, idLibro, {headers: {'Authorization': token}});
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
