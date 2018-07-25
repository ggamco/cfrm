package com.gmbdesign.cfrm.dto;

/**
 *
 * @author ggamboa
 */
public class AbonadoDataDTO {

    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String[] dni;
    private String nacimiento;
    private String alta;
    private String correo;
    private String telefono;
    private String[] direccion;
    private String codigoPostal;
    private String ciudad;

    public AbonadoDataDTO() {}

    public AbonadoDataDTO(String nombre, String primerApellido, String segundoApellido, String dni, String nacimiento, String alta, String correo, String telefono, String direccion, String codigoPostal, String ciudad) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.nacimiento = nacimiento;
        this.alta = alta;
        this.correo = correo;
        this.telefono = telefono;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        setDni(dni);
        setDireccion(direccion);
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
        return arrayToString(this.dni);
    }

    public void setDni(String dni) {
        this.dni = dni.split("/");
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getAlta() {
        return alta;
    }

    public void setAlta(String alta) {
        this.alta = alta;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return arrayToString(this.direccion);
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion.split("/");
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Metodo para convertir en String aquellos arrays de String incluyendo como separador el
     * caracter '/'
     * 
     * @param data
     * @return 
     */
    private String arrayToString(String[] data){
        StringBuilder resultado = new StringBuilder();
        for (String part : data) {
            resultado.append(part).append("/");
        }
        if(resultado.lastIndexOf("/") == resultado.length() - 1 && resultado.length() > 0){
            resultado.deleteCharAt(resultado.length() - 1);
        }
        return resultado.toString();
    }
    
    public String[] toArray() {
        String[] resultado = {
            nombre, primerApellido, 
            segundoApellido, getDni(), 
            nacimiento, alta, 
            correo, telefono, 
            getDireccion(), codigoPostal,
            ciudad
        };
        return resultado;
    }
}
