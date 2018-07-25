/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmbdesign.cfrm.bussiness;

/**
 *
 * @author ggamboa
 */
public enum EBusquedaType {
    NOMBRE("nombre"),
    NUMERO("numero"),
    RANGO("rango");

    private String tipo;

    private EBusquedaType(String tipo) {
        this.tipo = tipo;
    }

    public String tipo() {
        return tipo;
    }
}
