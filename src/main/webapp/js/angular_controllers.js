/**
* Created by Demils on 24/04/2016.
*/


var appBookOver = angular.module('BookOver');

appBookOver.controller('CtrlLogin', ['$scope', '$window', 'WebService', function ($scope, $window, WebService) {
    var self = this;


    self.login = function (user, password) {
        WebService.login(user, password)
            .then(function (jsonObject) {
                console.log("Login success");

                //location = "profilepage.html";
                //console.log(jsonObject);

                $window.sessionStorage.setItem('conversacion', 'null');
                $window.sessionStorage.setItem('libroActual', 'null');

                token = jsonObject.data.token;
                console.log(token);
                //$window.sessionStorage.setItem('token', JSON.stringify(token))
                $window.sessionStorage.setItem('token', token.token)
                console.log($window.sessionStorage.getItem('token'));

                //console.log('Authorization: ' + jsonObject.headers('Authorization'))
            }, function errorCallBack(response){
                console.log("Login failed");
            });
    };
}]);

appBookOver.controller('CtrlRegister', ['$scope', '$window', 'WebService', function ($scope, $window, WebService) {
    var self = this;

    self.register = function (user, password, password_check, email, localization) {
        if (password != password_check) console.log("Error contraseñas");
        else WebService.register(user, password, email, localization)
            .then(function successCallback(response) {
                console.log("Registrado");
                //location = "loginPage.html";
            });
    };
}]);

appBookOver.controller('CtrlProfile', ['$scope', '$window', 'WebService', function ($scope, $window, WebService) {
    var self = this;
    console.log("prefil");
    console.log($window.sessionStorage.getItem('token'));
   // console.log(JSON.parse($window.sessionStorage.getItem('token')));
    WebService.getPerfil("").then(function successCallback(response) {
        console.log(response); //borrar
        $scope.miPerfil=response.data;
    }, function errorCallBack(response){
        console.log("get profile failed");
    });

    self.editar = function (password, password_check, email, localization) {
        var dato={'usuario': { 'password': password,'email': email, 'ubicacion':localization}};
        if (password != password_check) console.log("Error contraseñas");
        else WebService.editarPerfil(dato)
            .then(function successCallback(response) {
                console.log("Editado");
            });
    };
}]);



appBookOver.controller('CtrlChat', ['$scope', '$window', 'WebService', function ($scope, $window, WebService) {
    var self = this;

    var conversacionActual= $window.sessionStorage.getItem('conversacion');

    WebService.listarConversaciones().then(function successCallback(response) { //carga tus conversaciones en misConversaciones
        console.log(response); //borrar
        $scope.misConversaciones = response.data;
    }, function errorCallBack(response) {
        console.log("get conversations failed");
    });

    if (conversacionActual != 'null') {
        WebService.mostrarConversacion(parseInt(conversacionActual)).then(function successCallback(response) {
            console.log("entra");
            console.log(response); //borrar
            $scope.conversacion = response.data;
        }, function errorCallBack(response) {
            console.log("get conversation failed");
        });
    }
   self.setConversacion = function(conversacion, conQuien){
       $window.sessionStorage.setItem('conversacion', conversacion.toString());
       $window.sessionStorage.setItem('conversacionConQuien', conQuien);
    };
    self.getConversacionConQuien=function(){
        return $window.sessionStorage.getItem('conversacionConQuien');
    };

    self.enviarMensaje = function(txt, para){
        var dato={'DataMensaje': {'mensaje': txt, 'para': para}};
        
       WebService.enviarMensaje(dato).then(function successCallback(response) {
            console.log(response); //borrar
        }, function errorCallBack(response){
            console.log("enviar mensaje failed");
        });
    };

}]);

appBookOver.controller('CtrlLibro', ['$scope', 'WebService', function ($scope, WebService) {
    var self = this;

    self.recuperaTodosLibros = function (idUsuario){ //recupera los libros de un usuario
        WebService.recuperaTodosLibros(idUsuario)
            .success(function(data) {
                $scope.libros = data.libro;
            });
    };

 
    WebService.recuperaTodosLibros("")      //guarda tus propios libros en misLibros
            .then(function successCallback(response) {
                console.log(response); //borrar
                $scope.misLibros=response.data;
            }, function errorCallBack(response){
                console.log("get profile failed");
            });
 
    
    self.recuperaLibro = function(idLibro) { //recupera un libro a partir de su id
        WebService.recuperaLibro(idLibro)
            .success(function(data) {
                console.log(data);
                $scope.libroActual = data;
            });
    };

    self.registrarLibro = function (titulo, autor, editorial, isbn, estado, infoAdicional, esPrestable, esVendible, esIntercambiable, precio, /* 'usuario': usuario,*/ fotos) {

        if (typeof esVendible === 'undefined') {
            esVendible=false;
        }
        if (typeof esIntercambiable === 'undefined') {
            esIntercambiable=false;
        }
        if (typeof esPrestable === 'undefined') {
            esPrestable=false;
        }
        if (typeof precio === 'undefined') {
            precio=0.0;
        }
        var dato = { 'libro': {'titulo': titulo , 'autor': autor, 'editorial': editorial , 'isbn': isbn, 'estado': estado, 'infoAdicional': infoAdicional, 'esPrestable': esPrestable, 'esVendible': esVendible, 'esIntercambiable': esIntercambiable, 'precio': precio, /* 'usuario': usuario,*/ 'fotos': fotos}};
        
        console.log(dato); //borrar
        WebService.registrarLibro(dato)
            .then(function successCallback(response) {
                console.log("Libro registrado");
            });
    };


    self.editarLibro = function (titulo, autor, editorial, isbn, estado, infoAdicional, esPrestable, esVendible, esIntercambiable, precio, /* 'usuario': usuario,*/ fotos) {
        var dato = { 'libro': {'titulo': titulo , 'autor': autor, 'editorial': editorial , 'isbn': isbn, 'estado': estado, 'infoAdicional': infoAdicional, 'esPrestable': esPrestable, 'esVendible': esVendible, 'esIntercambiable': esIntercambiable, 'precio': precio, /* 'usuario': usuario,*/ 'fotos': fotos}};
        //CC
        WebService.editarLibro(dato)
            .then(function successCallback(response) {
                console.log("Libro editado correctamente");
            });
    };

    self.retirarLibro = function(idLibro) {
        WebService.retirarLibro(idLibro).then(function successCallback(response){
            console.log("Libro retirado");
        });
    };

}]);