package com.gmbdesign.cfrm.impl;

import com.gmbdesign.cfrm.dao.UsuarioDAO;
import com.gmbdesign.cfrm.database.DataBase;
import com.gmbdesign.cfrm.database.Manager;
import com.gmbdesign.cfrm.dto.UsuarioDTO;
import com.gmbdesign.cfrm.dto.UsuarioDataDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ggamboa
 */
public class UsuarioDAOImpl implements UsuarioDAO{

	private Manager manager = Manager.getInstance();
	
    @Override
    public List<UsuarioDTO> buscarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuarioDTO> buscarPorId(int[] ids) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuarioDTO> buscarPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuarioDTO> buscarPorRole(String role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UsuarioDTO> buscarActivos(boolean esActivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * {@inheritDoc}
     * @param usuario
     * @return 
     */
    @Override
    public UsuarioDTO login(UsuarioDTO usuario) {
        UsuarioDTO user = null;
        String sql = DataBase.SQL_LOGIN;
        ResultSet data = manager.prepareSelect(sql, usuario.toArray2D());
        try {
            if (data.next()){ 
                user = new UsuarioDTO(null, null, data.getString("role"), new UsuarioDataDTO(
                        data.getString("nombre"), 
                        data.getString("apellido1"), 
                        data.getString("apellido2"), 
                        data.getString("dni"), 
                        data.getString("nacimiento"), 
                        data.getString("email")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOImpl.class.getName()).log(Level.SEVERE, "Se ha producido un error procesando el resultado", ex);
        }
        return user;
    }

    @Override
    public int[] agregarUsuario(UsuarioDTO usuario) {
        int[] respuesta;
        String sql = DataBase.SQL_PROCEDURE_REGISTRO_USER;
        respuesta = manager.prepareCallable(sql, usuario.toArray2D());
        return respuesta;
    }

    @Override
    public boolean editarDatosUsuario(UsuarioDTO usuarioData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarUsuario(UsuarioDTO usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
