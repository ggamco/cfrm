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
public class BusquedaDTO {
    
    private String busqueda;
    private String param1;
    private int param2;
    private int param3;

    public BusquedaDTO() {
    }

    public BusquedaDTO(String busqueda, String param1, int param2, int param3) {
        this.busqueda = busqueda;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public int getParam2() {
        return param2;
    }

    public void setParam2(int param2) {
        this.param2 = param2;
    }

    public int getParam3() {
        return param3;
    }

    public void setParam3(int param3) {
        this.param3 = param3;
    }
    
    
}
