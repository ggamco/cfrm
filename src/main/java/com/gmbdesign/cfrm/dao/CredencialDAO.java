package com.gmbdesign.cfrm.dao;

import java.util.List;

import com.gmbdesign.cfrm.dto.CredencialDTO;

public interface CredencialDAO {

	/**
	 * Metodo que carga todas las credenciales almacenadas en el sistema.
	 * @return
	 */
	List<CredencialDTO> cargarCredenciales();
	
	/**
	 * Metodo que almacena una nueva credencial en el sistema.
	 * @param credencial
	 * @return
	 */
	boolean insertCredencial(CredencialDTO credencial);
	
	/**
	 * Metodo que edita una credencial almacenada en el sistema.
	 * @param credencial
	 * @return
	 */
	boolean updateCredencial(CredencialDTO credencial);
	
	/**
	 * Metodo que elimina una credencial almacenada en el sistema.
	 * @param credencial
	 * @return
	 */
	boolean borrarCredencial(CredencialDTO credencial);
        
        /**
         * Metodo que busca una credencial dado un codigo.
         * @param codigo
         * @return 
         */
        String buscarCredencial(String codigo);
}
