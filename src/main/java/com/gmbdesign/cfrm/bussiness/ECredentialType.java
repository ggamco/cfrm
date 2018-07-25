package com.gmbdesign.cfrm.bussiness;

public enum ECredentialType {

    SOCIO("RMSC"),
    ESCUELA("RMEC"),
    GRADAFAN("RMGF"),
    VIP("RMVP"),
    PRENSA("RMPE"),
    ABONADO("RMAO"),
    JUBILADO("RMJB");

    private String tipo;

    private ECredentialType(String tipo) {
        this.tipo = tipo;
    }

    public String tipo() {
        return tipo;
    }
}
