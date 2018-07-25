package com.gmbdesign.cfrm.dto;

import java.io.Serializable;

import com.gmbdesign.cfrm.bussiness.IAbstractDTO;

public class CredencialDTO implements IAbstractDTO, Serializable {

	private static final long serialVersionUID = 5882890854610969756L;

	private int idCredencial;
	private String credencial;
	private String codigo;

	public CredencialDTO(String credencial) {
		super();
		this.credencial = credencial;
		this.codigo = generaCodigo(credencial);
	}

	public CredencialDTO(int idCredencial, String credencial) {
		super();
		this.idCredencial = idCredencial;
		this.credencial = credencial;
		this.codigo = generaCodigo(credencial);
	}

	public CredencialDTO(int idCredencial, String credencial, String codigo) {
		super();
		this.idCredencial = idCredencial;
		this.credencial = credencial;
		this.codigo = codigo;
	}

	public int getIdCredencial() {
		return idCredencial;
	}

	public void setIdCredencial(int idCredencial) {
		this.idCredencial = idCredencial;
	}

	public String getCredencial() {
		return credencial;
	}

	public void setCredencial(String credencial) {
		this.credencial = credencial;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		if (codigo.length() == 2) {
			this.codigo = codigo;
		} else {
			// TODO: lanzar excepción propia
		}
	}

	private String generaCodigo(String credencial) {
		if (credencial != null) {
			String[] splitedCredential = credencial.split(" ");
			StringBuilder sb = new StringBuilder();
			if (splitedCredential.length > 1) {
				sb.append("RM").append(splitedCredential[0].charAt(0)).append(splitedCredential[1].charAt(0));
			} else {
				sb.append("RM").append(credencial.charAt(0)).append(credencial.charAt(2));
			}
			return sb.toString().toUpperCase();
		}

		return null;
	}

	@Override
	public String[] toArray() {
		return new String[] { credencial, codigo };
	}
}
