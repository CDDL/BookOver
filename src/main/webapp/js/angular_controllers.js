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


                //console.log(jsonObject);

                $window.sessionStorage.setItem('conversacion', 'null');
                $window.sessionStorage.setItem('libroActual', 'null');
                $window.sessionStorage.setItem('busqueda', 'null');
                $window.sessionStorage.setItem('usuarioActual', 'null');

                token = jsonObject.data.token;
                //console.log(token);
                //$window.sessionStorage.setItem('token', JSON.stringify(token))
                $window.sessionStorage.setItem('token', token.token)
                //console.log($window.sessionStorage.getItem('token'));
                location = "profilepage.html";
                //console.log('Authentication: ' + jsonObject.headers('Authentication'))
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
                location = "loginPage.html";
            });
    };
}]);

appBookOver.controller('CtrlProfile', ['$scope', '$window', 'WebService', function ($scope, $window, WebService) {
    var self = this;
    //console.log("prefil");
    //console.log($window.sessionStorage.getItem('token'));
                    // console.log(JSON.parse($window.sessionStorage.getItem('token')));
    WebService.getMiPerfil().then(function successCallback(response) {
        //console.log('response: '); //borrar
        //console.log(response);
        //console.log('response.data: '); //borrar
        //console.log(response.data);

        $scope.miPerfil=response.data.dataProfileUser;
    }, function errorCallBack(response){
        console.log("get profile failed");
    });

    self.editar = function (password, password_check, email, localization) {
        var dato={'usuario': { 'password': password,'email': email, 'ubicacion':localization}};
        if (password != password_check) console.log("Error contraseñas");
        else WebService.editarPerfil(dato)
            .then(function successCallback(response) {
                console.log("Editado");
                location="profilepage.html";
            });
    };
}]);




appBookOver.controller('CtrlLibro', ['$scope', '$window', 'WebService', function ($scope, $window, WebService) {
    var self = this;

    console.log($window.sessionStorage.getItem('usuarioActual'));


    self.recuperaTodosLibros = function (idUsuario){ //recupera los libros de un usuario
        WebService.recuperaTodosLibros(idUsuario)
            .success(function(data) {
                $scope.libros = data.libro;
            });
    };

    if($window.sessionStorage.getItem('libroActual') != 'null'){
        WebService.getLibro(parseInt($window.sessionStorage.getItem('libroActual'))).then(function successCallback(response) {
            //console.log("libroActual: ");
            //console.log(response); //borrar
            $scope.libroActual = response.data.libro;
        }, function errorCallBack(response) {
            console.log("get conversation failed");
        });
    }
    self.setLibroActual = function(id, titulo){
        $window.sessionStorage.setItem('libroActual', id.toString());
    };
    if($window.sessionStorage.getItem('busqueda') != 'null'){
        WebService.buscaLibros($window.sessionStorage.getItem('busqueda')).then(function successCallback(response) {
            //console.log("libroActual: ");
            //console.log(response); //borrar
            console.log("resultados busqueda: response.data ");
            console.log(response.data);
            $scope.listaLibrosBusqueda = response.data.libro;

        }, function errorCallBack(response) {
            console.log("busqueda failed");
        });
    }

    if($window.sessionStorage.getItem('usuarioActual') != 'null' && $window.sessionStorage.getItem('usuarioActual') != null){
        console.log("pal scope");
        var item=JSON.parse(($window.sessionStorage.getItem('usuarioActual')));
        $scope.usuarioActual=item;
        console.log($scope.usuarioActual);
        $scope.idUsuarioActual=parseInt($window.sessionStorage.getItem('idUsuarioActual'));
        console.log($scope.idUsuarioActual);
        WebService.recuperaTodosLibros($scope.idUsuarioActual).then(function successCallback(response) {
            console.log(response.data.libro);
            $scope.listaLibrosUsuario = response.data.libro;
        }, function errorCallBack(response) {
            console.log("recupera los libros de este usuario failed");
        });
    }
    
    self.setUsuarioActual= function(idLibro){
                //var idUsuario = 0;
                WebService.getPropietario(idLibro).then(function successCallback(response) {
                    console.log("id usuario= ");
                    console.log(response.data);
                    $window.sessionStorage.setItem('idUsuarioActual', response.data);
                    WebService.getPerfil(response.data).then(function successCallback(response) {
                        console.log("variable usuarioActual= ");
                        console.log(JSON.stringify(response.data.dataProfileUser));
                        console.log("/variable");
                        $window.sessionStorage.setItem('usuarioActual',  JSON.stringify(response.data.dataProfileUser));
                        console.log($window.sessionStorage.getItem('usuarioActual'));
                        location="showuserprofile.html"
                    }, function errorCallBack(response) {
                        console.log("obtener propietario failed");
                    });


                }, function errorCallBack(response) {
                    console.log("obtener propietario failed");
                });


    };

    self.setBusqueda = function(txt){
        $window.sessionStorage.setItem('busqueda', txt);
        location = "listalibros.html";
    };
    
    WebService.recuperaTodosMisLibros()      //guarda tus propios libros en misLibros
            .then(function successCallback(response) {
                console.log("cargamos libros de nuevo"); //borrar
                $scope.misLibros=response.data.libro;
            }, function errorCallBack(response){
                console.log("get profile failed");
            });



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
                location="profilepage.html"
            });
    };


    self.editarLibro = function (id, titulo, autor, editorial, isbn, estado, infoAdicional, esPrestable, esVendible, esIntercambiable, precio, /* 'usuario': usuario,*/ fotos) {

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
            precio="0";
        }
        precio=parseFloat(precio);

        var dato = { 'libro': {'titulo': titulo , 'autor': autor, 'editorial': editorial , 'isbn': isbn, 'estado': estado, 'infoAdicional': infoAdicional, 'esPrestable': esPrestable, 'esVendible': esVendible, 'esIntercambiable': esIntercambiable, 'precio': precio, /* 'usuario': usuario,*/ 'fotos': fotos}};
        //CC
        WebService.editarLibro(dato, id)
            .then(function successCallback(response) {
                console.log("Libro editado correctamente");
                
                WebService.recuperaTodosMisLibros()      //guarda tus propios libros en misLibros
                    .then(function successCallback(response) {
                        $scope.misLibros=response.data.libro;
                        location="profilepage.html";
                    }, function errorCallBack(response){
                        console.log("get profile failed");
                    });
            });
    };

    self.retirarLibro = function(idLibro) {
        WebService.retirarLibro(idLibro).then(function successCallback(response){
            console.log("Libro retirado");
            WebService.recuperaTodosMisLibros()      //guarda tus propios libros en misLibros
                .then(function successCallback(response) {
                    $scope.misLibros=response.data.libro;

                }, function errorCallBack(response){
                    console.log("get profile failed");
                });
        });
        //location="profilepage.html";
    };

    var conversacionActual= parseInt($window.sessionStorage.getItem('conversacion'));

    WebService.listarConversaciones().then(function successCallback(response) { //carga tus conversaciones en misConversaciones
        //console.log(response); //borrar
        $scope.misConversaciones = response.data.dataListConversaciones;
        console.log("conversacionesssss");
        console.log(response.data);
    }, function errorCallBack(response) {
        console.log("get conversations failed");
    });

    if (conversacionActual != 'null') {
        WebService.mostrarConversacion(parseInt(conversacionActual)).then(function successCallback(response) {
            //console.log("entra");
            //console.log(response); //borrar
            $scope.conversacion = response.data.dataConversacion;
            console.log("la conversacion a mostrar:")
            console.log(response.data);
        }, function errorCallBack(response) {
            console.log("get conversation failed");
        });
    }
    self.setConversacion = function(idConversacion, conQuien, idConQuien){
        
        console.log("estamos en setConversacion, id y conquien:");
        console.log(idConversacion);
        console.log(conQuien);
        $window.sessionStorage.setItem('conversacion', idConversacion.toString());
        $window.sessionStorage.setItem('conversacionConQuien', conQuien);
        $window.sessionStorage.setItem('idConQuien', idConQuien);
        location="userschat.html";
        
    };
    self.getConversacionConQuien=function(){
        return $window.sessionStorage.getItem('conversacionConQuien');
    };

    self.iniciaConversacion = function(txt, para){
        var dato={'dataMensaje': {'mensaje': txt, 'para': para}};
        //console.log(dato);
        WebService.enviarMensaje(dato).then(function successCallback(response) {
           location="chatslist.html"
        }, function errorCallBack(response){
            console.log("enviar mensaje failed");
        });
    };

    self.enviarMensaje = function(txt){

        var dato={'dataMensaje': {'mensaje': txt, 'para': $window.sessionStorage.getItem('idConQuien')}};
        //console.log(dato);
        WebService.enviarMensaje(dato).then(function successCallback(response) {
            if (conversacionActual != 'null') {
                WebService.mostrarConversacion(parseInt(conversacionActual)).then(function successCallback(response) {
                    //console.log("entra");
                    //console.log(response); //borrar
                    $scope.conversacion = response.data.dataConversacion;
                }, function errorCallBack(response) {
                    console.log("get conversation failed");
                });
            }
        }, function errorCallBack(response){
            console.log("enviar mensaje failed");
        });
    };

    self.haySesion =  function(){
        return ($window.sessionStorage.getItem('token')!=null);
    };

    self.cerrarSesion= function(){
        $window.sessionStorage.removeItem('token');
        $window.sessionStorage.removeItem('conversacion');
        $window.sessionStorage.removeItem('libroActual');
        $window.sessionStorage.removeItem('busqueda');
        $window.sessionStorage.removeItem('usuarioActual');
        $window.sessionStorage.removeItem('conversacionConQuien');
        location="index.html";
    };
}]);