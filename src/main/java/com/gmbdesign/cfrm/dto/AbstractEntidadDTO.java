package com.gmbdesign.cfrm.dto;

import com.gmbdesign.cfrm.bussiness.ICredentialCard;

/**
 *
 * @author Formador
 */
public abstract class AbstractEntidadDTO implements ICredentialCard{
    
    protected String tipoEntidad;

	protected AbstractEntidadDTO(String clase) {
        this.tipoEntidad = clase;
    }

    @Override
    public String getCredentialType() {
        return tipoEntidad;
    }
}
