package com.gmbdesign.cfrm.dto;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TarifaDTO implements Serializable {

	private static final long serialVersionUID = 4932884613620655658L;
	
	private int idTarifa;
	private String nombre;
	private String code;
	private double precio;
	
	public TarifaDTO() {
		super();
	}
	
	public TarifaDTO(String nombre, double precio) {
		super();
		this.nombre = nombre;
		setPrecio(precio);
		this.code = codeGenerator(nombre);
	}

	public TarifaDTO(String nombre, String code, double precio) {
		super();
		this.nombre = nombre;
		this.code = code;
		setPrecio(precio);
	}

	public int getIdTarifa() {
		return idTarifa;
	}

	public void setIdTarifa(int idTarifa) {
		this.idTarifa = idTarifa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String nombreTarifa) {
		this.code = codeGenerator(nombreTarifa);
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		NumberFormat formatter = new DecimalFormat("#0.00"); 
		this.precio = Double.valueOf(formatter.format(precio));
	}

	private String codeGenerator(String nombre){
		StringBuilder code = null;
		if (nombre != null) {
			String[] nombreSplited = nombre.split(" ");
			code = new StringBuilder();
			if (nombreSplited.length > 1) {
				code.append(nombreSplited[0].charAt(1)).append(nombreSplited[1].charAt(1));
			} else {
				code.append(nombre.substring(0, 2));
			}
		}
		return code.toString().toUpperCase();
	}
}
