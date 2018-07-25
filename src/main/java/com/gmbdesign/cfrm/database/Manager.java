package com.gmbdesign.cfrm.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class Manager {

    private final String host, port, user, pass, database, url;
    private Statement st;
    private PreparedStatement pst;
    private CallableStatement cst;
    private Connection connection;
    
    private static Manager manager;

    /**
     * Constructor que inicializa el objeto con valores por defecto
     */
    private Manager() {
        this.host = DataBase.HOST;
        this.port = DataBase.PORT;
        this.user = DataBase.USER;
        this.pass = DataBase.PASS;
        this.database = DataBase.DATABASE;
        this.url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database;
    }
    
    public static Manager getInstance() {
    	if (manager != null) {
    		return manager;
    	} else {
    		return new Manager();
    	}
    }

    /**
     * Metodo privado para realizar la conexión a la base de datos usando los
     * valores asignados por defecto o mediante el constructor inicializador de
     * parametros.
     */
    private void connectToDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, "No se ha encontrado el Driver", ex);
        } catch (SQLException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, "Se ha producido un error conectando a la DB", ex);
        }

    }

    /**
     * Metodo privado para realizar una desconexión controlada de la base de
     * datos.
     */
    private void disconnectToDB() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, "Se ha producido un error desconectando de la DB", ex);
        }
    }

    /**
     * @deprecated Metodo para ejecutar sentencias SQL de tipo INSERT, UPDATE o
     * DELETE, recibiendo un sentencia ya configurada.
     *
     * Este metodo es deprecado debido a que no controla la inyección de SQL
     * dentro de las busquedas o sentencias.
     *
     * Se recomienda el uso de
     * {@link #prepareUpdate(java.lang.String, java.lang.String[])}
     *
     * @param sql
     * @return
     */
    public int executeUpdate(String sql) {
        int rows = 0;

        //1º Conectar a la DB
        this.connectToDB();

        //2º Ejecución de la sentencia
        try {
            st = connection.createStatement();

            rows = st.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, "Se ha producido un error en la solicitud.", ex);
        } finally {
            //3º desconecto de la DB
            this.disconnectToDB();
        }

        //4º Devolución del dato obtenido
        return rows;
    }

    /**
     * @deprecated Metodo para ejecutar sentencias SQL de tipo SELECT,
     * recibiendo un sentencia. ya configurada.
     *
     * Este metodo es deprecado debido a que no controla la inyección de SQL
     * dentro de las busquedas o sentencias.
     *
     * Se recomienda el uso de
     * {@link #prepareSelect(java.lang.String, java.lang.String[])}
     *
     * @param sql
     * @return
     */
    public CachedRowSet executeSelect(String sql) {
        ResultSet result;
        CachedRowSet info = null;
        //1º conectar a la DB
        this.connectToDB();

        //2º solicitud de Select
        try {
            st = connection.createStatement();

            result = st.executeQuery(sql);
            info = RowSetProvider.newFactory().createCachedRowSet();
            info.populate(result);

        } catch (SQLException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, "Se ha producido un error en la solicitud.", ex);
        } finally {
            //3º desconecto de la DB
            this.disconnectToDB();
        }

        //4º Devolución del resultado       
        return info;
    }
    
    /**
     * Requiere la entrada de una sentencia precompilada SQL. Utilizada para las sentencias de tipo
     * SELECT. Previene la inyección de SQL en las busquedas.
     *
     * @param sql
     * @return
     */
    public CachedRowSet prepareSelect(String sql) {
        CachedRowSet resultado = null;

        this.connectToDB();

        try {
            pst = connection.prepareStatement(sql);

            ResultSet resultSet = pst.executeQuery();
            resultado = RowSetProvider.newFactory().createCachedRowSet();
            resultado.populate(resultSet);

        } catch (SQLException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, "Se ha producido un error en la solicitud {0}.", pst.toString());
        } finally {
            //3º desconecto de la DB
            this.disconnectToDB();
        }

        //4º Devolución del resultado  
        return resultado;
    }

    /**
     * Requiere la entrada de una sentencia precompilada SQL así como un array
     * con los argumentos de su busqueda. Utilizada para las sentencias de tipo
     * SELECT. Previene la inyección de SQL en las busquedas.
     *
     * Si se requiere una sentencia de tipo UPDATE, INSERT o DELETE usar el
     * metodo {@link #prepareUpdate(java.lang.String, java.lang.String[]) }
     *
     * @param sql
     * @param args
     * @return
     */
    public CachedRowSet prepareSelect(String sql, String[] args) {
        CachedRowSet resultado = null;

        this.connectToDB();

        try {
            pst = connection.prepareStatement(sql);

            int contador = 1;

            for (String data : args) {
                if (data != null) {
                    pst.setString(contador++, data);
                }
            }

            ResultSet resultSet = pst.executeQuery();
            resultado = RowSetProvider.newFactory().createCachedRowSet();
            resultado.populate(resultSet);

        } catch (SQLException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, "Se ha producido un error en la solicitud {0}.", pst.toString());
        } finally {
            //3º desconecto de la DB
            this.disconnectToDB();
        }

        //4º Devolución del resultado  
        return resultado;
    }
    
    public CachedRowSet prepareSelect(String sql, String[][] args) {
        CachedRowSet resultado = null;

        this.connectToDB();

        try {
            pst = connection.prepareStatement(sql);

            int contador = 1;

            for (String[] array : args) {
                if (array != null) {
                    for (String data : array) {
                        if (data != null) {
                            pst.setString((contador++), data);
                        }
                    }
                }
            }
            
            ResultSet resultSet = pst.executeQuery();
            resultado = RowSetProvider.newFactory().createCachedRowSet();
            resultado.populate(resultSet);

        } catch (SQLException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, "Se ha producido un error en la solicitud.", ex);
        } finally {
            //3º desconecto de la DB
            this.disconnectToDB();
        }

        //4º Devolución del resultado  
        return resultado;
    }

    /**
     * Requiere la entrada de una sentencia precompilada SQL así como un array
     * con los argumentos de su busqueda. Utilizada para las sentencias de
     * UPDATE, INSERT y DELETE. Previene la inyección de SQL en las busquedas.
     *
     * Si se requiere una sentencia de tipo SELECT usar el metodo {@link #prepareSelect(java.lang.String, java.lang.String[])
     * }
     *
     * @param sql
     * @param args
     * @return
     */
    public int prepareUpdate(String sql, String[] args) {

        int rows = 0;

        //1º Conectar a la DB
        this.connectToDB();

        //2º Ejecución de la sentencia
        try {
            pst = connection.prepareStatement(sql);

            int contador = 1;

            for (String data : args) {
                if (data != null) {
                    pst.setString(contador++, data);
                }
            }

            rows = pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, "Se ha producido un error en la solicitud.", ex);
        } finally {
            //3º Desconectar
            this.disconnectToDB();
        }

        //4º Devolución del dato obtenido
        return rows;

    }

    /**
     * Requiere la entrada de una sentencia precompilada SQL así como un array
     * bidimensional con los argumentos de su busqueda. Utilizada para las
     * sentencias de UPDATE, INSERT y DELETE. Previene la inyección de SQL en
     * las busquedas.
     *
     * @param sql
     * @param args
     * @return
     */
    public int prepareUpdate(String sql, String[][] args) {

        int rows = 0;

        //1º Conectar a la DB
        this.connectToDB();

        //2º Ejecución de la sentencia
        try {
            pst = connection.prepareStatement(sql);

            int contador = 1;

            for (String[] array : args) {
                if (array != null) {
                    for (String data : array) {
                        if (data != null) {
                            pst.setString((contador++), data);
                        }
                    }
                }
            }

            rows = pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, "Se ha producido un error en la solicitud.", ex);
        } finally {
            //3º Desconectar
            this.disconnectToDB();
        }

        //4º Devolución del dato obtenido
        return rows;

    }

    public int[] prepareCallable(String call, String[][] args) {
        int resultado[] = new int[2];

        //1º Conectar a la DB
        this.connectToDB();

        //2º Ejecución de la llamada
        try {
            cst = connection.prepareCall(call);

            int contador = 1;

            for (String[] array : args) {
                if (array != null) {
                    for (String data : array) {
                        if (data != null) {
                            cst.setString((contador++), data);
                        }
                    }
                }
            }
            
            Logger.getLogger(Manager.class.getCanonicalName()).log(Level.INFO, "Call: {0}",cst.toString());
            
            resultado[0] = cst.executeUpdate();
            resultado[1] = DataBase.SQL_OK;

        } catch (SQLException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, "Se ha producido un error en la ejecución del PROCESO ALMACENADO.", ex);

            resultado[0] = 0;

            if (ex.getLocalizedMessage().contains("Duplicate")) {
                if (ex.getLocalizedMessage().contains("numeroSocio_Unico")) {
                    resultado[1] = DataBase.SQL_DUPLICATE_ENTRY_SOCIO;
                } else if (ex.getLocalizedMessage().contains("codigoSistema_Unico")) {
                    resultado[1] = DataBase.SQL_DUPLICATE_ENTRY_SYSTEM_CODE;
                } else if (ex.getLocalizedMessage().contains("dni")) {
                    resultado[1] = DataBase.SQL_DUPLICATE_ENTRY_DNI;
                } else {
                    resultado[1] = DataBase.SQL_DUPLICATE_ENTRY;
                }
                
            } else {
                resultado[1] = 510;
            }

        } finally {
            //3º Desconectar
            this.disconnectToDB();
        }

        //4º Devolución del dato obtenido
        return resultado;
    }

    public int[] prepareUpdateCallable(String call, Long id, String[][] args) {
        int resultado[] = new int[2];

        //1º Conectar a la DB
        this.connectToDB();

        //2º Ejecución de la llamada
        try {
            cst = connection.prepareCall(call);

            int contador = 1;
            cst.setLong(contador, id);

            for (String[] array : args) {
                if (array != null) {
                    for (String data : array) {
                        if (data != null) {
                            cst.setString((++contador), data);
                        }
                    }
                }
            }

            Logger.getLogger(Manager.class.getCanonicalName()).log(Level.INFO, "Call: {0}",cst.toString());

            resultado[0] = cst.executeUpdate();
            resultado[1] = DataBase.SQL_OK;

        } catch (SQLException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, "Se ha producido un error en la ejecución del PROCESO ALMACENADO.", ex);

            resultado[0] = 0;

            if (ex.getLocalizedMessage().contains("Duplicate")) {
                if (ex.getLocalizedMessage().contains("numeroSocio_Unico")) {
                    resultado[1] = DataBase.SQL_DUPLICATE_ENTRY_SOCIO;
                } else if (ex.getLocalizedMessage().contains("codigoSistema_Unico")) {
                    resultado[1] = DataBase.SQL_DUPLICATE_ENTRY_SYSTEM_CODE;
                } else if (ex.getLocalizedMessage().contains("dni")) {
                    resultado[1] = DataBase.SQL_DUPLICATE_ENTRY_DNI;
                } else {
                    resultado[1] = DataBase.SQL_DUPLICATE_ENTRY;
                }

            } else {
                resultado[1] = 510;
            }

        } finally {
            //3º Desconectar
            this.disconnectToDB();
        }

        //4º Devolución del dato obtenido
        return resultado;
    }

}
