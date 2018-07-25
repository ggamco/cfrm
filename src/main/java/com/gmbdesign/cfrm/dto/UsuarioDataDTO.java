package com.gmbdesign.cfrm.dto;

import java.io.Serializable;

import com.gmbdesign.cfrm.bussiness.IAbstractDTO;

/**
 * Clase que almacena los datos personales de un usuario
 * @author ggamboa
 */
public class UsuarioDataDTO implements IAbstractDTO, Serializable{
    
    private static final long serialVersionUID = -6420645716157012353L;
	private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String dni;
    private String fechaNacimiento;
    private String email;

    public UsuarioDataDTO() {
    }

    public UsuarioDataDTO(String nombre, String primerApellido, String segundoApellido, String dni, String fechaNacimiento, String email) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String[] toArray() {
        String[] data = new String[6];
        
        data[0] = nombre;
        data[1] = primerApellido;
        data[2] = segundoApellido;
        data[3] = dni;
        data[4] = fechaNacimiento;
        data[5] = email;
        
        return data;
    }

}
