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
                location = "profilepage.html";
                //console.log(jsonObject);
                token = jsonObject.data.token;
                WebService.setToken(token);
                //console.log('Authorization: ' + jsonObject.headers('Authorization'))
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
                //location = "loginPage.html";
            });
    };
}]);

appBookOver.controller('CtrlProfile', ['$scope', 'WebService', function ($scope, WebService) {
    var self = this;
    
    self.editar = function (password, password_check, email, localization) {
        var dato={'usuario': { 'password': password,'email': email, 'ubicacion':localization}};
        //console.log(WebService.getToken());//borrar
        if (password != password_check) console.log("Error contraseñas");
        else WebService.editarPerfil(dato)
            .then(function successCallback(response) {
                console.log("Editado");
            });
    };
}]);

appBookOver.controller('CtrlLibro', ['$scope', 'WebService', function ($scope, WebService) {
    var self = this;

    // $scope.usuario ?? cmprbr

    self.recuperaTodosLibros = function (idUsuario){
        WebService.recuperaTodosLibros(idUsuario)
            .success(function(data) {
                $scope.libros = data.libro;
            });
    };

    self.recuperaLibro = function(idLibro) {
        WebService.recuperaLibro(idLibro)
            .success(function(data) {
                console.log(data);
                $scope.libroActual = data;
            });
    };

    self.registrarLibro = function (titulo, autor, editorial, isbn, estado, infoAdicional, esPrestable, esVendible, esIntercambiable, precio, /* 'usuario': usuario,*/ fotos) {
        var dato = { 'libro': {'titulo': titulo , 'autor': autor, 'editorial': editorial , 'isbn': isbn, 'estado': estado, 'infoAdicional': infoAdicional, 'esPrestable': esPrestable, 'esVendible': esVendible, 'esIntercambiable': esIntercambiable, 'precio': precio, /* 'usuario': usuario,*/ 'fotos': fotos}};
        console.log(dato);
/*        WebService.registrarLibro(dato)
            .then(function successCallback(response) {
                console.log("Libro registrado");
            });*/
    };

    //cmprbr - esta funcion incluye el id
    self.editarLibro = function (id, titulo, autor, editorial, isbn, estado, infoAdicional, esPrestable, esVendible, esIntercambiable, precio, /* 'usuario': usuario,*/ fotos) {
        var dato = { 'libro': {'id': id, 'titulo': titulo , 'autor': autor, 'editorial': editorial , 'isbn': isbn, 'estado': estado, 'infoAdicional': infoAdicional, 'esPrestable': esPrestable, 'esVendible': esVendible, 'esIntercambiable': esIntercambiable, 'precio': precio, /* 'usuario': usuario,*/ 'fotos': fotos}};
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