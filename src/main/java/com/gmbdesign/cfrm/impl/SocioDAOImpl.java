package com.gmbdesign.cfrm.impl;

import com.gmbdesign.cfrm.bussiness.ICredentialCard;
import com.gmbdesign.cfrm.dao.SocioDAO;
import com.gmbdesign.cfrm.database.DataBase;
import com.gmbdesign.cfrm.database.Manager;
import com.gmbdesign.cfrm.dto.SocioDTO;
import com.gmbdesign.cfrm.dto.SocioDataDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ggamboa
 */
public class SocioDAOImpl implements SocioDAO {

    private final Manager manager = Manager.getInstance();

    @Override
    public List<SocioDTO> buscarTodos() {
        List<SocioDTO> lista = new ArrayList<>();

        String sql = DataBase.SQL_SELECT_ALL_SOCIO;
        ResultSet busqueda = manager.prepareSelect(sql);

        try {
            if (busqueda != null) {
                while (busqueda.next()) {
                    SocioDTO socio = new SocioDTO(
                            busqueda.getLong("idSocio"),
                            busqueda.getString("numero"),
                            busqueda.getString("codigoSistema"),
                            busqueda.getString("createDate"),
                            busqueda.getString("credencial"),
                            new SocioDataDTO(
                                    busqueda.getString("nombre"),
                                    busqueda.getString("apellido1"),
                                    busqueda.getString("apellido2"),
                                    busqueda.getString("dni"),
                                    busqueda.getString("nacimiento"),
                                    busqueda.getString("alta"),
                                    busqueda.getString("correo"),
                                    busqueda.getString("telefono"),
                                    busqueda.getString("direccion"),
                                    busqueda.getString("codigoPostal"),
                                    busqueda.getString("ciudad"),
                                    busqueda.getString("ccc")
                            )
                    );

                    if (busqueda.getInt("active") != 0) {
                        socio.setIsActive(true);
                    } else {
                        socio.setIsActive(false);
                    }

                    if (busqueda.getInt("printed") != 0) {
                        socio.setIsPrinted(true);
                    } else {
                        socio.setIsPrinted(false);
                    }

                    lista.add(socio);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(SocioDAOImpl.class.getCanonicalName()).log(Level.SEVERE, "Error en el proceso de mappeo de socios. {0}", ex.getLocalizedMessage());
        }

        return lista;
    }

    @Override
    public List<SocioDTO> buscarPorId(int[] ids) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SocioDTO> buscarPorNombre(String nombre) {
        List<SocioDTO> resultado = null;
        String sql = "SELECT * FROM socio "
                + "INNER JOIN socio_data ON socio.codigoSistema = socio_data.codigoSistema "
                + "WHERE LOWER(socio_data.nombre) LIKE _utf8'%"+nombre.toLowerCase()+"%' COLLATE utf8_general_ci "
                + "OR LOWER(socio_data.apellido1) LIKE _utf8'%"+nombre.toLowerCase()+"%' COLLATE utf8_general_ci "
                + "OR LOWER(socio_data.apellido2) LIKE _utf8'%"+nombre.toLowerCase()+"%' COLLATE utf8_general_ci "
                + "ORDER BY socio.numero ASC";
        ResultSet busqueda = manager.prepareSelect(sql);
        if (busqueda != null) {
            try{
                resultado = new ArrayList<>();
                while(busqueda.next()) {
                    SocioDTO socio = new SocioDTO(
                            busqueda.getLong("idSocio"),
                            busqueda.getString("numero"),
                            busqueda.getString("codigoSistema"),
                            busqueda.getString("createDate"),
                            busqueda.getString("credencial"),
                            new SocioDataDTO(
                                    busqueda.getString("nombre"),
                                    busqueda.getString("apellido1"),
                                    busqueda.getString("apellido2"),
                                    busqueda.getString("dni"),
                                    busqueda.getString("nacimiento"),
                                    busqueda.getString("alta"),
                                    busqueda.getString("correo"),
                                    busqueda.getString("telefono"),
                                    busqueda.getString("direccion"),
                                    busqueda.getString("codigoPostal"),
                                    busqueda.getString("ciudad"),
                                    busqueda.getString("ccc")
                            )
                    );
                    socio.setIsActive(busqueda.getBoolean("active"));
                    socio.setIsPrinted(busqueda.getBoolean("printed"));
                    resultado.add(socio);
                }
            } catch(SQLException e) {
                Logger.getLogger(SocioDAOImpl.class.getCanonicalName()).log(Level.SEVERE, "Error en el proceso de busqueda de socios por nombre. {0}", e.getLocalizedMessage());
            }
        }
        return resultado;
    }

    @Override
    public List<SocioDTO> buscarPorNumeroSocio(int numeroSocio) {
        List<SocioDTO> resultado = null;
        ResultSet busqueda = manager.prepareSelect(DataBase.SQL_SELECT_SOCIO_BY_NUMERO, new String[] {String.valueOf(numeroSocio)});
        if (busqueda != null) {
            try{
                resultado = new ArrayList<>();
                while(busqueda.next()) {
                    SocioDTO socio = new SocioDTO(
                            busqueda.getLong("idSocio"),
                            busqueda.getString("numero"),
                            busqueda.getString("codigoSistema"),
                            busqueda.getString("createDate"),
                            busqueda.getString("credencial"),
                            new SocioDataDTO(
                                    busqueda.getString("nombre"),
                                    busqueda.getString("apellido1"),
                                    busqueda.getString("apellido2"),
                                    busqueda.getString("dni"),
                                    busqueda.getString("nacimiento"),
                                    busqueda.getString("alta"),
                                    busqueda.getString("correo"),
                                    busqueda.getString("telefono"),
                                    busqueda.getString("direccion"),
                                    busqueda.getString("codigoPostal"),
                                    busqueda.getString("ciudad"),
                                    busqueda.getString("ccc")
                            )
                    );
                    resultado.add(socio);
                }
            } catch(SQLException e) {
                Logger.getLogger(SocioDAOImpl.class.getCanonicalName()).log(Level.SEVERE, "Error en el proceso de busqueda de socios por numero. {0}", e.getLocalizedMessage());
            }
        }
        return resultado;
    }

    @Override
    public List<SocioDTO> buscarActivos(boolean esActivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[] agregarSocio(SocioDTO socio) {
        //TODO - Preparar la insercción de un socio en su base de datos correspondiente.
        int[] respuesta;

        //TODO - Crear rutina SQL para enviar los datos a las dos tablas
        String sql = DataBase.SQL_PROCEDURE_REGISTRO_SOCIO;

        respuesta = manager.prepareCallable(sql, socio.toArray());

        return respuesta;
    }

    @Override
    public int[] editarDatosSocio(SocioDTO socio) {
        String sql = DataBase.SQL_PROCEDURE_UPDATE_SOCIO;
        int[] tx = manager.prepareUpdateCallable(sql, socio.getIdSocio(), socio.toArray());
        return tx;
    }

    @Override
    public boolean eliminarSocio(SocioDTO socio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SocioDTO buscarSocioByCodigo(String codigo) {

        SocioDTO socio = null;

        String sql = DataBase.SQL_SELECT_SOCIO_BY_CODE;
        ResultSet busqueda = manager.prepareSelect(sql, new String[]{codigo});

        try {

            if (busqueda != null) {
                while (busqueda.next()) {
                    socio = new SocioDTO(
                            busqueda.getLong("idSocio"),
                            busqueda.getString("numero"),
                            busqueda.getString("codigoSistema"),
                            busqueda.getString("credencial"),
                            busqueda.getString("createDate"),
                            new SocioDataDTO(
                                    busqueda.getString("nombre"),
                                    busqueda.getString("apellido1"),
                                    busqueda.getString("apellido2"),
                                    busqueda.getString("dni"),
                                    busqueda.getString("nacimiento"),
                                    busqueda.getString("alta"),
                                    busqueda.getString("correo"),
                                    busqueda.getString("telefono"),
                                    busqueda.getString("direccion"),
                                    busqueda.getString("codigoPostal"),
                                    busqueda.getString("ciudad"),
                                    busqueda.getString("ccc")
                            )
                    );

                    if (busqueda.getInt("active") != 0) {
                        socio.setIsActive(true);
                    } else {
                        socio.setIsActive(false);
                    }

                    if (busqueda.getInt("printed") != 0) {
                        socio.setIsPrinted(true);
                    } else {
                        socio.setIsPrinted(false);
                    }

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(SocioDAOImpl.class.getCanonicalName()).log(Level.SEVERE, "Error en el proceso de busqueda de socios. {0}", ex.getLocalizedMessage());
        }

        return socio;
    }

    @Override
    public List<SocioDTO> buscarPorRango(int menor, int mayor) {
        List<SocioDTO> resultado = null;
        String sql = "SELECT * FROM socio INNER JOIN socio_data ON socio.codigoSistema = socio_data.codigoSistema WHERE socio.numero BETWEEN "+menor+" and "+mayor+"";
        ResultSet busqueda = manager.prepareSelect(sql);
        if (busqueda != null) {
            try{
                resultado = new ArrayList<>();
                while(busqueda.next()) {
                    SocioDTO socio = new SocioDTO(
                            busqueda.getLong("idSocio"),
                            busqueda.getString("numero"),
                            busqueda.getString("codigoSistema"),
                            busqueda.getString("credencial"),
                            busqueda.getString("createDate"),
                            new SocioDataDTO(
                                    busqueda.getString("nombre"),
                                    busqueda.getString("apellido1"),
                                    busqueda.getString("apellido2"),
                                    busqueda.getString("dni"),
                                    busqueda.getString("nacimiento"),
                                    busqueda.getString("alta"),
                                    busqueda.getString("correo"),
                                    busqueda.getString("telefono"),
                                    busqueda.getString("direccion"),
                                    busqueda.getString("codigoPostal"),
                                    busqueda.getString("ciudad"),
                                    busqueda.getString("ccc")
                            )
                    );
                    resultado.add(socio);
                }
            } catch(SQLException e) {
                Logger.getLogger(SocioDAOImpl.class.getCanonicalName()).log(Level.SEVERE, "Error en el proceso de busqueda de socios por rango. {0}", e.getLocalizedMessage());
            }
        }
        return resultado;
    }

    @Override
    public boolean updateActivado(SocioDTO socio, boolean nuevoEstado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateToPrinted(List<ICredentialCard> listaSocios) {
        boolean resultado = false;
        int rowsUpdated = 0;
        rowsUpdated = listaSocios.stream().map((socio) -> manager.prepareUpdate(DataBase.SQL_UPDATE_PRINTED_STATUS, new String[] {((SocioDTO)socio).getCodigoSistema()})).reduce(rowsUpdated, Integer::sum);
        if(rowsUpdated != 0) {
            resultado = true;
        }
        return resultado;
    }

    @Override
    public List<SocioDTO> buscarSociosNotPrinted() {
        List<SocioDTO> resultado = null;
        ResultSet busqueda = manager.prepareSelect(DataBase.SQL_SELECT_ALL_SOCIO_NOT_PRINTED);
        if (busqueda != null) {
            try{
                resultado = new ArrayList<>();
                while(busqueda.next()) {
                    SocioDTO socio = new SocioDTO(
                            busqueda.getLong("idSocio"),
                            busqueda.getString("numero"),
                            busqueda.getString("codigoSistema"),
                            busqueda.getString("credencial"),
                            busqueda.getString("createDate"),
                            new SocioDataDTO(
                                    busqueda.getString("nombre"),
                                    busqueda.getString("apellido1"),
                                    busqueda.getString("apellido2"),
                                    busqueda.getString("dni"),
                                    busqueda.getString("nacimiento"),
                                    busqueda.getString("alta"),
                                    busqueda.getString("correo"),
                                    busqueda.getString("telefono"),
                                    busqueda.getString("direccion"),
                                    busqueda.getString("codigoPostal"),
                                    busqueda.getString("ciudad"),
                                    busqueda.getString("ccc")
                            )
                    );
                    resultado.add(socio);
                }
            } catch(SQLException e) {
                Logger.getLogger(SocioDAOImpl.class.getCanonicalName()).log(Level.SEVERE, "Error en el proceso de busqueda de socios por numero. {0}", e.getLocalizedMessage());
            }
        }
        return resultado;
    }
}
