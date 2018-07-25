package com.gmbdesign.cfrm.dto;

import java.io.Serializable;
import java.util.List;

public class EventoDTO implements Serializable {

    private static final long serialVersionUID = 2469403877852973957L;

    private int idEvento;
    private String fecha;
    private String hora;
    private String jornada;
    private String torneo;
    private String visitante;
    private boolean esActivo;
    private List<TarifaEventoDTO> listaTarifas;

    public EventoDTO() {
        super();
    }

    public EventoDTO(String fecha, String hora, String jornada, String torneo, String visitante,
            List<TarifaEventoDTO> listaTarifas) {
        super();
        this.fecha = fecha;
        this.hora = hora;
        this.jornada = jornada;
        this.torneo = torneo;
        this.visitante = visitante;
        this.listaTarifas = listaTarifas;
    }

    public EventoDTO(int idEvento, String fecha, String hora, String jornada, String torneo, String visitante, boolean esActivo) {
        super();
        this.idEvento = idEvento;
        this.fecha = fecha;
        this.hora = hora;
        this.jornada = jornada;
        this.torneo = torneo;
        this.visitante = visitante;
        this.esActivo = esActivo;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getTorneo() {
        return torneo;
    }

    public void setTorneo(String torneo) {
        this.torneo = torneo;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public boolean isEsActivo() {
        return esActivo;
    }

    public void setEsActivo(boolean esActivo) {
        this.esActivo = esActivo;
    }

    public List<TarifaEventoDTO> getListaTarifas() {
        return listaTarifas;
    }

    public void setListaTarifas(List<TarifaEventoDTO> listaTarifas) {
        this.listaTarifas = listaTarifas;
    }
}
