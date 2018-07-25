package com.gmbdesign.cfrm.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gmbdesign.cfrm.dao.CredencialDAO;
import com.gmbdesign.cfrm.database.DataBase;
import com.gmbdesign.cfrm.database.Manager;
import com.gmbdesign.cfrm.dto.CredencialDTO;

public class CredencialDAOImpl implements CredencialDAO {

    private static final String TAG = CredencialDAOImpl.class.getCanonicalName();
    private Manager manager = Manager.getInstance();

    @Override
    public List<CredencialDTO> cargarCredenciales() {
        List<CredencialDTO> listaCredenciales = null;
        ResultSet busqueda = manager.prepareSelect(DataBase.SQL_SELECT_ALL_CREDENCIAL);
        if (busqueda != null) {
            listaCredenciales = new ArrayList<>();
            try {
                while (busqueda.next()) {
                    CredencialDTO credencial = new CredencialDTO(
                            busqueda.getInt("idCredencial"),
                            busqueda.getString("credencial"),
                            busqueda.getString("codigo"));
                    listaCredenciales.add(credencial);
                }
            } catch (Exception e) {
                Logger.getLogger(TAG).log(Level.SEVERE, "Se ha producido un error al procesar el resultado de la busqueda", e);
            }
        }
        return listaCredenciales;
    }

    @Override
    public boolean insertCredencial(CredencialDTO credencial) {
        boolean resultado = false;
        int rows = manager.prepareUpdate(DataBase.SQL_INSERT_CREDENCIAL, credencial.toArray());
        if (rows != 0) {
            resultado = true;
        }
        return resultado;
    }

    @Override
    public boolean updateCredencial(CredencialDTO credencial) {
        boolean resultado = false;
        String[] datos = {
            credencial.getCredencial(),
            credencial.getCodigo(),
            String.valueOf(credencial.getIdCredencial())
        };
        int rows = manager.prepareUpdate(DataBase.SQL_UPDATE_CREDENCIAL, datos);
        if (rows != 0) {
            resultado = true;
        }
        return resultado;
    }

    @Override
    public boolean borrarCredencial(CredencialDTO credencial) {
        boolean resultado = false;
        int rows = manager.prepareUpdate(DataBase.SQL_DELETE_CREDENCIAL, new String[]{String.valueOf(credencial.getIdCredencial())});
        if (rows != 0) {
            resultado = true;
        }
        return resultado;
    }

    @Override
    public String buscarCredencial(String codigo) {
        String resultado = null;
        ResultSet busqueda = manager.prepareSelect(DataBase.SQL_SELECT_CREDENTIAL_BY_CODIGO, new String[] {codigo});
        if (busqueda != null) {
            try {
                while(busqueda.next()){
                    resultado = busqueda.getString("credencial");
                }
            } catch (Exception e) {
                Logger.getLogger(TAG).log(Level.SEVERE, "Se ha producido un error recuperando la credencial");
            }
        }
        return resultado;
    }
}
