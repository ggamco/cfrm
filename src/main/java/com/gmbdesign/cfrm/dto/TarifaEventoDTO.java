package com.gmbdesign.cfrm.dto;

public class TarifaEventoDTO extends TarifaDTO{

	private static final long serialVersionUID = 7315244348301160876L;
	private int idTarifaEvento;
	private int idEvento;
	
	public TarifaEventoDTO() {
		super();
	}
	
	public TarifaEventoDTO(int idEvento, String nombre, String code, double precio) {
		super(nombre, code, precio);
		this.idEvento = idEvento;
	}
	
	public TarifaEventoDTO(int idTarifaEvento, int idEvento, String nombre, String code, double precio) {
		super(nombre, code, precio);
		this.idTarifaEvento = idTarifaEvento;
		this.idEvento = idEvento;
	}

	public int getIdTarifa() {
		return idTarifaEvento;
	}

	public void setIdTarifa(int idTarifa) {
		this.idTarifaEvento = idTarifa;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
}