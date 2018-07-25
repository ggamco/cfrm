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
public class SocioDTO extends AbstractEntidadDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8000930720620182578L;

    private Long idSocio;
    private String numeroSocio;
    private String codigoSistema;
    private String createDate;
    private String credencial;
    private boolean isActive;
    private boolean isPrinted;
    private SocioDataDTO datos;

    public String[][] toArray() {
        String[][] resultado = {{numeroSocio}, {codigoSistema}, {credencial}, datos.toArray()};
        return resultado;
    }

    public SocioDTO(Long idSocio, String numeroSocio, String codigoSistema, String credencial, String createDate, SocioDataDTO socioData) {
        super(ECredentialType.SOCIO.toString());
        this.idSocio = idSocio;
        this.numeroSocio = numeroSocio;
        this.codigoSistema = codigoSistema;
        this.createDate = createDate;
        this.credencial = credencial;
        this.datos = socioData;
        this.isActive = true;
        this.isPrinted = false;
    }

    public Long getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(Long idSocio) {
        this.idSocio = idSocio;
    }

    public String getNumero() {
        return numeroSocio;
    }

    public void setNumero(String numeroSocio) {
        this.numeroSocio = numeroSocio;
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

    public SocioDataDTO getSocioData() {
        return datos;
    }

    public void setSocioData(SocioDataDTO datos) {
        this.datos = datos;
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
        String formattedSocio = String.format("%05d", Integer.parseInt(numeroSocio));
        barcode.append(getCredencial()).append(formattedSocio);
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

//    @Override
//    public String getAccessDoor() {
//        return null;
//    }
}
