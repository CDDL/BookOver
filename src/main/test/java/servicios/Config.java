package servicios;

/**
 * Created by Demils on 23/05/2016.
 */
public interface Config {
    String URI_APP_BASE = "http://127.0.0.1:8080/BookOver/";
    String URI_APP_BORRAR_LIBRO = URI_APP_BASE + "libros/";                               //DELETE
    String URI_APP_LOGOUT = URI_APP_BASE + "usuarios/login";                              //DELETE
    String URI_APP_LOGIN = URI_APP_BASE + "usuarios/login";                               //POST
    String URI_APP_REGISTER = URI_APP_BASE + "usuarios";                                  //POST
    String URI_APP_EDITAR_PERFIL = URI_APP_BASE + "usuarios/editar";                      //POST
    String URI_APP_NUEVO_LIBRO = URI_APP_BASE + "libros";                                 //POST
    String URI_APP_BUSCAR_LIBRO = URI_APP_BASE + "libros/buscar/";                        //POST
    String URI_APP_PETICION_INTERCAMBIO = URI_APP_BASE + "transacciones/intercambio";     //POST
    String URI_APP_PETICION_PRESTAMO = URI_APP_BASE + "transacciones/prestamo";           //POST
    String URI_APP_PETICION_COMPRA = URI_APP_BASE + "transacciones/compra";               //POST
    String URI_APP_ENVIAR_MENSAJE = URI_APP_BASE + "conversaciones/mensaje";              //POST
    String URI_APP_EDITAR_LIBRO = URI_APP_BASE + "libros/";                               //PUT
    String URI_APP_CONFIRMACION_PRESTAMO = URI_APP_BASE + "transacciones/";               //PUT
    String URI_APP_CONFIRMACION_RECEPCION = URI_APP_BASE + "transacciones/";              //PUT
    String URI_APP_PETICION_PRESTAMO_ACEPTAR = URI_APP_BASE + "transacciones/";           //PUT
    String URI_APP_VISUALIZAR_MIS_LIBROS = URI_APP_BASE + "libros/mislibros";             //GET
    String URI_APP_LISTAR_USUARIOS = URI_APP_BASE + "usuarios";                           //GET
    String URI_APP_LISTAR_CONVERSACIONES = URI_APP_BASE + "conversaciones/listaConversaciones"; //GET
}
