package com.gmbdesign.cfrm.database;

/**
 * Clase que contiene los datos de acceso a la Base de Datos.
 * 
 * Si es necesario modificar cualquier valor de los definidos por defecto, 
 * debe hacer uso del constructor especifico que inicializa con valores dados por el programador.
 * 
 * @author ggamboa
 */
public final class DataBase {
    
//    public static final String HOST = "localhost";
//    public static final String PORT = "3306";
//    public static final String USER = "cfrm";
//    public static final String PASS = "x0Jia81&";
//    public static final String DATABASE = "cfrm";

    public static final String HOST = "localhost";
    public static final String PORT = "3306";
    public static final String USER = "root";
    public static final String PASS = "root";
    public static final String DATABASE = "cfrm";
    
    //SQL User
    public static final String SQL_LOGIN = "SELECT * FROM usuario JOIN usuario_data ON usuario.id_usuario = usuario_data.id_usuario WHERE usuario.user = ? AND usuario.pass = ?";
    public static final String SQL_PROCEDURE_REGISTRO_USER = "CALL crearUsuario(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_PROCEDURE_UPDATE_USER = "CALL crearUsuario(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    //SQL Socio
    public static final String SQL_SELECT_ALL_SOCIO = "SELECT * FROM socio JOIN socio_data ON socio.codigoSistema = socio_data.codigoSistema ORDER BY socio.numero ASC";
    public static final String SQL_SELECT_SOCIO_BY_CODE = "SELECT * FROM socio JOIN socio_data ON socio.codigoSistema = socio_data.codigoSistema WHERE socio.codigoSistema = ?";
    public static final String SQL_PROCEDURE_REGISTRO_SOCIO = "CALL crearSocio(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_PROCEDURE_UPDATE_SOCIO = "CALL actualizarSocio(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_SELECT_ALL_SOCIO_NOT_PRINTED = "SELECT * FROM socio JOIN socio_data ON socio.codigoSistema = socio_data.codigoSistema WHERE socio.printed = 0";
    public static final String SQL_SELECT_SOCIO_BY_NUMERO = "SELECT * FROM socio INNER JOIN socio_data ON socio.codigoSistema = socio_data.codigoSistema WHERE socio.numero = ?";
    public static final String SQL_UPDATE_PRINTED_STATUS = "UPDATE socio SET printed = 1 WHERE codigoSistema = ?";
    public static final String SQL_ALTER_ALUMNO = "SELECT * FROM alumnoAlter";
    //SQL Credencial
    public static final String SQL_SELECT_ALL_CREDENCIAL = "SELECT * FROM credencial";
    public static final String SQL_INSERT_CREDENCIAL = "INSERT INTO credencial (credencial, codigo) VALUES (?, ?)";
    public static final String SQL_UPDATE_CREDENCIAL = "UPDATE credencial SET credencial = ?, codigo = ? WHERE idCredencial = ?";
    public static final String SQL_DELETE_CREDENCIAL = "DELETE FROM credencial WHERE idCredencial = ?";
    public static final String SQL_SELECT_CREDENTIAL_BY_CODIGO = "SELECT * FROM credencial WHERE codigo = ?";
    //SQL Eventos
    public static final String SQL_SELECT_ALL_EVENTOS = "SELECT * FROM evento ORDER BY idEvento ASC";
    public static final String SQL_INSERT_EVENTO = "INSERT INTO evento (fecha, hora, jornada, torneo, visitante, esActivo) VALUES (?, ?, ?, ?, ?, ?,)";
    public static final String SQL_UPDATE_EVENTO = "UPDATE evento SET fecha = ?, hora = ?, jornada = ?, torneo = ?, visitante = ?, esActivo = ? WHERE idEvento = ?";
    public static final String SQL_DELETE_EVENTO = "DELETE FROM evento WHERE idEvento = ?";
    //SQL Tarifas
    public static final String SQL_SELECT_ALL_TARIFAS = "SELECT * FROM tarifa ORDER BY idTarifa ASC";
    public static final String SQL_SELECT_TARIFAS_BY_EVENTO = "SELECT * FROM tarifa_evento WHERE idEvento = ? ORDER BY precio DESC";
    public static final String SQL_INSERT_TARIFA = "INSERT INTO tarifa (nombre, code) VALUES (?, ?)";
    public static final String SQL_INSERT_TARIFA_BY_EVENTO = "INSERT INTO tarifa_evento (idEvento, nombre, code, precio) VALUES (?, ?, ?, ?)";
    public static final String SQL_UPDATE_TARIFA = "UPDATE tarifa SET nombre = ?, code = ? WHERE idTarifa = ?";
    public static final String SQL_UPDATE_TARIFA_BY_EVENTO = "UPDATE tarifa_evento SET precio = ? WHERE idEvento = ? AND idTarifaEvento = ?";
    public static final String SQL_DELETE_TARIFA = "DELETE FROM tarifa WHERE idTarifa = ?";
    public static final String SQL_DELETE_TARIFA_BY_EVENTO = "DELETE FROM tarifa_evento WHERE idEvento = ? AND idTarifaEvento = ?";
    //SQL CODES
    public static final int SQL_OK = 200;
    public static final int SQL_DUPLICATE_ENTRY = 501;
    public static final int SQL_DUPLICATE_ENTRY_SOCIO = 502;
    public static final int SQL_DUPLICATE_ENTRY_SYSTEM_CODE = 503;
    public static final int SQL_DUPLICATE_ENTRY_DNI = 504;
    public static final int SQL_UNKNOWN_ERROR = 510;

    public DataBase() {
        throw new Error("Imposible instanciar esta clase");
    }
}
