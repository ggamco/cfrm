package com.gmbdesign.cfrm.bussiness;

public interface ITicket {

    /**
     * Metodo que devuelve el tipo de credencial del socio/abonado/escuela/prensa
     * @return
     */
    String getCredentialType();

    /**
     * Metodo que devuelve el codigo para generar el barcode.
     * @return
     */
    String getBarcode();

    /**
     * Metodo que devuelve el alias o nombre completo para imprimir en el carnet
     * @return
     */
    String getTarifa();

    /**
     * Metodo que devuelve el número de carnet
     * @return
     */
    String getNumberCode();

    /**
     * Metodo que devuelve la puerta de acceso asignada para el carnet
     * @return
     */
    default String getAccessDoor() {
        return "Puerta 1";
    }

    String getTipo();
}
