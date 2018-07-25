package com.gmbdesign.cfrm.dto;

import java.io.Serializable;

import com.gmbdesign.cfrm.bussiness.IAbstractDTO;

/**
 * Clase que almacena información de un usuario
 * @author ggamboa
 */
public class UsuarioDTO implements IAbstractDTO, Serializable{
    
    private static final long serialVersionUID = -2222995373119180041L;
	private int idUsuario;
    private String user;
    private String pass;
    private String role;
    private boolean esActivo;
    private String fechaAlta;
    private UsuarioDataDTO datos;

    /**
     * Constructor básico. Inicia el objeto vacio.
     */
    public UsuarioDTO() {
    }

    /**
     * Constructor que inicializa un Usuario mediante la insercción de user y pass.
     * El resto de parametros se incializan vacios.
     * 
     * @param user
     * @param pass 
     */
    public UsuarioDTO(String user, String pass) {
        this.user = user;
        this.pass = pass;
        this.datos = new UsuarioDataDTO();
    }

    public UsuarioDTO(String user, String pass, String role, UsuarioDataDTO datos) {
        this.user = user;
        this.pass = pass;
        this.role = role;
        this.datos = datos;
        esActivo = false;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public boolean isEsActivado() {
        return esActivo;
    }

    public void setEsActivado(boolean esActivo) {
        this.esActivo = esActivo;
    }

    public UsuarioDataDTO getDatos() {
        return datos;
    }

    public void setDatos(UsuarioDataDTO datos) {
        this.datos = datos;
    }
    
    @Override
    public String[][] toArray2D(){
        
        String[][] resultado = { {user}, {pass}, {role}, datos.toArray() };
        
        return resultado;
    }  
}
