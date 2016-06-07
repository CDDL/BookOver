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
       //console.log($http.post(appBookOver.editarPerfilURI,dato, {'headers': {'Authentication': $window.sessionStorage.getItem('token')}}) );
        //

        //codigo final
        return $http.post(appBookOver.editarPerfilURI,dato, {'headers': {'Authentication': $window.sessionStorage.getItem('token')}} );

    };

    this.getMiPerfil=function () {
        //codigo final
        url=appBookOver.baseURI+"usuarios/profile";
        //
        ///////
        //codigo para probar
        //url="perfil.json";
        //
        console.log('url: ' + url);
        console.log('token: ' + $window.sessionStorage.getItem('token'));

        return $http.get(url, {'headers': {'Authentication': $window.sessionStorage.getItem('token')}});

    };

    this.getPerfil = function (dato) {
        //codigo final
            url=appBookOver.baseURI+"usuarios/profile/"+dato;
            //
        ///////
        //codigo para probar
            //url="perfil.json";
        //
        console.log('url: ' + url);
        console.log('token: ' + $window.sessionStorage.getItem('token'));

        return $http.get(url, {'headers': {'Authentication': $window.sessionStorage.getItem('token')}});

    };


    this.registrarLibro = function (dato) { //ok
        //codigo final
        console.log(dato); //borrar
        return $http.post(appBookOver.registrarLibroURI,dato,  {'headers': {'Authentication': $window.sessionStorage.getItem('token')}});
    };

    this.editarLibro = function (dato, idLibro) {  //ok
        url=appBookOver.baseURI + "libros/" + idLibro;
        return $http.put(url, dato, {'headers': {'Authentication': $window.sessionStorage.getItem('token')}});
    };

    this.recuperaTodosMisLibros = function() {

        //codigo final
        var url = appBookOver.baseURI + 'libros/lista'; //
        return $http.get(url, {'headers': {'Authentication': $window.sessionStorage.getItem('token')}});
        //
        //codigo de prueba
        //url="libros.json";
        //return $http.get(url);
        //
    };

    this.recuperaTodosLibros = function(idUsuario) {

       //codigo final
        var url = appBookOver.baseURI + 'libros/lista/' + idUsuario; //
        return $http.get(url, {'headers': {'Authentication': $window.sessionStorage.getItem('token')}});
        //
        //codigo de prueba
        //url="libros.json";
        //return $http.get(url);
        //
    };

    this.getLibro = function(idLibro) {
        //codigo final
        var url = appBookOver.baseURI + 'libros/' + idLibro; //+ ?? cmprbr url
        return $http.get(url, {'headers': {'Authentication': $window.sessionStorage.getItem('token')}});

        //codigo de prueba
        //url="libro.json";
        //return $http.get(url);
    };

    this.getPropietario = function(idLibro) {
        //codigo final
        var url = appBookOver.baseURI + '/libros/user/' + idLibro
        return $http.get(url, {'headers': {'Authentication': $window.sessionStorage.getItem('token')}});

        //codigo de prueba
        //url="perfil.json";
        //return $http.get(url);
    };

    this.buscaLibros=function(txt){
        //codigo final
        var url = appBookOver.baseURI + 'libros/buscar/' + txt; //
        return $http.get(url, {'headers': {'Authentication': $window.sessionStorage.getItem('token')}});
        //
        //codigo de prueba
        //url="libros.json";
        //return $http.get(url);
        //
    };

    this.retirarLibro = function(idLibro) {
        var url = appBookOver.baseURI + "libros/" + idLibro;
        console.log(url);
        return $http.delete(url, {'headers': {'Authentication': $window.sessionStorage.getItem('token')}});
    };

    
    //----------------------------------------------conversaciones
    this.listarConversaciones = function() {
        //codigo de prueba
        //var url="conversaciones.json";
        //
        //codigo final
        var url = appBookOver.listarConversacionesURI;
        //
        return $http.get(url, {'headers': {'Authentication': $window.sessionStorage.getItem('token')}});
    };
    
    this.mostrarConversacion = function(idConversacion) {
        //codigo de prueba
        //var url = "conversacion.json";
        //
        //codigo final
        var url = appBookOver.baseURI + "conversaciones/" + idConversacion;
        //
        return $http.get(url, {'headers': {'Authentication': $window.sessionStorage.getItem('token')}});
    };

    this.enviarMensaje = function(dato){
        console.log(dato);
        
        var url = appBookOver.enviarMensajeURI;
        return $http.post(url, dato,  {'headers': {'Authentication': $window.sessionStorage.getItem('token')}});
    };

}]);
