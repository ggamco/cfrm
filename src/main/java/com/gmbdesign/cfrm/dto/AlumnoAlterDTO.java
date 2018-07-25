/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmbdesign.cfrm.dto;

/**
 *
 * @author ggamboa
 */
public class AlumnoAlterDTO extends AbstractEntidadDTO{

    private String numero;
    private String year;
    private String codigo;
    private String nombre;
    private String categoria;
    private String credencial;

    public AlumnoAlterDTO() {
        super("SOCIO");
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCredencial() {
        return credencial;
    }

    public void setCredencial(String credencial) {
        this.credencial = credencial;
    }
    
    @Override
    public String getBarcode() {
        StringBuilder barcode = new StringBuilder();
        String formattedSocio = String.format("%05d", Integer.parseInt(numero));
        barcode.append(getCredencial()).append(formattedSocio);
        return credencial;
    }

    @Override
    public String getAlias() {
        return nombre;
    }

    @Override
    public String getNumberCode() {
        return numero;
    }

    @Override
    public String getTipo() {
        return categoria;
    }
    
}
