package com.gmbdesign.cfrm.dto;

import com.gmbdesign.cfrm.bussiness.ECredentialType;
import com.gmbdesign.cfrm.bussiness.ICredentialCard;
import com.gmbdesign.cfrm.dao.CredencialDAO;
import com.gmbdesign.cfrm.impl.CredencialDAOImpl;

import java.io.Serializable;

/**
 *
 * @author ggamboa
 */
public class AbonadoDTO implements ICredentialCard, Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8000930720620182578L;

    private Long idAbonado;
    private String numeroAbonado;
    private String codigoSistema;
    private String createDate;
    private String credencial;
    private String grada;
    private String sector;
    private String fila;
    private String asiento;
    private boolean isActive;
    private boolean isPrinted;
    private AbonadoDataDTO datos;

    public String[][] toArray() {
        String[][] resultado = {{numeroAbonado}, {codigoSistema}, {credencial}, datos.toArray()};
        return resultado;
    }

    public AbonadoDTO(Long idAbonado, String numeroSocio, String codigoSistema, String credencial, String createDate, AbonadoDataDTO abonadoData) {
        this.idAbonado = idAbonado;
        this.numeroAbonado = numeroSocio;
        this.codigoSistema = codigoSistema;
        this.createDate = createDate;
        this.credencial = credencial;
        this.datos = abonadoData;
        this.isActive = true;
        this.isPrinted = false;
    }

    public Long getIdAbonado() {
        return idAbonado;
    }

    public void setIdAbonado(Long idAbonado) {
        this.idAbonado = idAbonado;
    }

    public String getNumero() {
        return numeroAbonado;
    }

    public void setNumero(String numeroAbonado) {
        this.numeroAbonado = numeroAbonado;
    }

    public String getCodigoSistema() {
        return codigoSistema;
    }

    public void setCodigoSistema(String codigoSistema) {
        this.codigoSistema = codigoSistema;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsPrinted() {
        return isPrinted;
    }

    public void setIsPrinted(boolean isPrinted) {
        this.isPrinted = isPrinted;
    }

    public AbonadoDataDTO getSocioData() {
        return datos;
    }

    public void setSocioData(AbonadoDataDTO datos) {
        this.datos = datos;
    }

    public String getCredencial() {
        return credencial;
    }

    public void setCredencial(String credencial) {
        this.credencial = credencial;
    }

    public String getGrada() {
        return grada;
    }

    public void setGrada(String grada) {
        this.grada = grada;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    @Override
    public String getCredentialType() {
        return ECredentialType.ABONADO.tipo();
    }

    @Override
    public String getBarcode() {
        StringBuilder barcode = new StringBuilder();
        String formattedAbonado = String.format("%05d", Integer.parseInt(numeroAbonado));
        barcode.append(getCredencial()).append(formattedAbonado);
        return barcode.toString();
    }

    @Override
    public String getAlias() {
        StringBuilder sb = new StringBuilder();

        sb.append(datos.getPrimerApellido())
                .append(" ")
                .append(datos.getSegundoApellido())
                .append(", ")
                .append(datos.getNombre());

        return sb.toString();
    }

    @Override
    public String getNumberCode() {
        return getNumero();
    }

    @Override
    public String getTipo() {
        CredencialDAO credencialDAO = new CredencialDAOImpl();
        return credencialDAO.buscarCredencial(credencial);
    }

    @Override
    public String getAccessDoor() {
        return null;
    }
}
