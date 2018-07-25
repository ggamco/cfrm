package com.gmbdesign.cfrm.dao;

import java.util.List;

import com.gmbdesign.cfrm.dto.TarifaEventoDTO;

public interface TarifaEventoDAO {
	
	/**
	 * Metodo que lista todas las tarifas que pertenecen a un evento concreto
	 * @return
	 */
	List<TarifaEventoDTO> listarTarifasByEvento(int idEvento);
	
	/**
	 * Metodo que almacena en base de datos una lista de tarifas por evento
	 * @param listaTarifas
	 * @param idEvento
	 * @return
	 */
	boolean insertTarifasByEvento(List<TarifaEventoDTO> listaTarifas, int idEvento);
	
	/**
	 * Metodo que actualiza en base de datos una lista de tarifas por evento
	 * @param listaTarifas
	 * @param idEvento
	 * @return
	 */
	boolean updateTarifasByEvento(List<TarifaEventoDTO> listaTarifas, int idEvento);
	
	/**
	 * Metodo que elimina de la base de datos una lista de tarifas por evento
	 * @param listaTarifas
	 * @param idEvento
	 * @return
	 */
	boolean deleteTarifasByEvento(List<TarifaEventoDAO> listaTarifas, int idEvento);
}
