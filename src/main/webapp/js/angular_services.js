/**
 * Created by Demils on 24/04/2016.
 */


var appBookOver = angular.module('BookOver');

appBookOver.baseURI = 'http://localhost:8080/BookOver/';

appBookOver.loginURI = appBookOver.baseURI + "usuarios/login";
appBookOver.registerURI = appBookOver.baseURI + "usuarios";
appBookOver.editarPerfilURI = appBookOver.baseURI + "usuarios/editar";
appBookOver.registrarLibroURI = appBookOver.baseURI + "libros";

appBookOver.listarConversacionesURI = appBookOver.baseURI + "conversaciones/listaConversaciones";
appBookOver.enviarMensajeURI = appBookOver.baseURI + "conversaciones/mensaje";

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

appBookOver.service('WebService', ['$http', '$window', function ($http, $window) {
    // this.create = function(nombre, apellidos, nif) {
    //     dato = {'persona': {'nombre': nombre, 'apellidos': apellidos, 'nif': nif}};
    //     var url = appBookOver.baseURI + nif;
    //     return $http.put(url, dato);
    // }

    
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
       //console.log($http.post(appBookOver.editarPerfilURI,dato, {'headers': {'Authorization': $window.sessionStorage.getItem('token')}}) );
        //

        //codigo final
        return $http.post(appBookOver.editarPerfilURI,dato, {'headers': {'Authorization': $window.sessionStorage.getItem('token')}} );

    };

    this.getPerfil = function (dato) {
        //codigo final
            //url=BaseURL+"usuarios/dato";
            //
        ///////
        //codigo para probar
            url="pefil.json";
        //
        return $http.get(url);

    };


    this.registrarLibro = function (dato) {
        //codigo final
        console.log(dato); //borrar
        return $http.post(appBookOver.registrarLibroURI,dato,  {'headers': {'Authorization': $window.sessionStorage.getItem('token')}});
    };

    this.editarLibro = function (dato, idLibro) {
        url=appBookOver.editarLibroURI + idLibro;
        return $http.put(url, dato, {'headers': {'Authorization': $rootScope.token}});
    };

    this.recuperaTodosLibros = function(idUsuario) {

       //codigo final
        //var url = appBookOver.baseURI + 'libros/lista/' + idUsuario; //
        //return $http.get(url, {'headers': {'Authorization': $window.sessionStorage.getItem('token')}});
        //
        //codigo de prueba
        url="libros.json";
        return $http.get(url);
        //
    };

    this.recuperaLibro = function(idLibro) {
        var url = appBookOver.baseURI + 'libros/' + idLibro; //+ ?? cmprbr url
        return $http.get(url);
    };

    this.retirarLibro = function(idLibro) {
        var url = appBookOver.retirarLibroURI + idLibro; //+ ?? cmprbr url
        return $http.delete(url, idLibro, {headers: {'Authorization': token}});
    };

    
    //conversaciones
    this.listarConversaciones = function() {
        //codigo de prueba
        var url="conversaciones.json";
        //
        //codigo final
        //var url = appBookOver.listarConversacionesURI;
        //
        return $http.get(url);
    };
    
    this.mostrarConversacion = function(idConversacion) {
        //codigo de prueba
        var url = "conversacion.json";
        //
        //codigo final
        //var url = appBookOver.baseURI + "conversaciones/" + idConversacion;
        //
        return $http.get(url);
    };

    this.enviarMensaje = function(dato){
        console.log(dato);
        
        var url = appBookOver.enviarMensajeURI;
        return $http.post(url, dato,  {'headers': {'Authorization': $window.sessionStorage.getItem('token')}});
    };

}]);
