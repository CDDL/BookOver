/**
* Created by Demils on 24/04/2016.
*/

var appBookOver = angular.module('BookOver');

appBookOver.controller('CtrlLogin', ['$scope', 'WebService', function ($scope, WebService) {
    var self = this;

    console.log('Content-Range: ' + response.headers('Content-Range'))

    self.login = function (user, password) {
        WebService.login(user, password)
            .then(function (jsonObject) {
                console.log("Login success");
                token = response.headers('Authorization');
                console.log('Authorization: ' + response.headers('Authorization'))
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

appBookOver.controller('CtrlLibro', ['$scope', 'WebService', function ($scope, WebService) {
    var self = this;

    // $scope.usuario ?? cmprbr
    

    self.recuperaTodosLibros = function (idUsuario){
        WebService.recuperaTodosLibros(idUsuario)
            .success(function(data) {
                $scope.libros = data.libro;
            });
    }

    self.recuperaLibro = function(idLibro) {
        WebService.recuperaLibro(idLibro)
            .success(function(data) {
                console.log(data);
                $scope.libroActual = data;
            });
    }

    self.registrarLibro = function (titulo, autor, editorial, isbn, estado, infoAdicional, esPrestable, esVendible, esIntercambiable, precio, usuario, fotos) {
        var dato = { libro: {'titulo': titulo , 'autor': autor, 'editorial': editorial , 'isbn': isbn, 'estado': estado, 'infoAdicional': infoAdicional, 'esPrestable': esPrestable, 'esVendible': esVendible, 'esIntercambiable': esIntercambiable, 'precio': precio, 'usuario': usuario, 'fotos': fotos}}

        WebService.registrarLibro(dato)
            .then(function successCallback(response) {
                console.log("Libro registrado");
            });
    };

    //cmprbr - esta funcion incluye el id
    self.editarLibro = function (id, titulo, autor, editorial, isbn, estado, infoAdicional, esPrestable, esVendible, esIntercambiable, precio, usuario, fotos) {
        var dato = { libro: {'id': id, 'titulo': titulo , 'autor': autor, 'editorial': editorial , 'isbn': isbn, 'estado': estado, 'infoAdicional': infoAdicional, 'esPrestable': esPrestable, 'esVendible': esVendible, 'esIntercambiable': esIntercambiable, 'precio': precio, 'usuario': usuario, 'fotos': fotos}}
        //CC
        WebService.editarLibro(dato)
            .then(function successCallback(response) {
                console.log("Libro editado correctamente");
            });
    };

    self.cargaFotos = function (event) {
        document.getElementById("fotos").innerHTML = '';
        document.getElementById("fotos").appendChild(document.createElement("br"));
        for (i = 0; i < event.target.files.length; i++) {
            var elem = document.createElement("img");
            elem.setAttribute("src", URL.createObjectURL(event.target.files[i]));
            elem.setAttribute("height", "100");
            elem.setAttribute("width", "100");
            document.getElementById("fotos").appendChild(elem);
        }
        ;
    }

}]);