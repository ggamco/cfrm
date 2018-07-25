package com.gmbdesign.cfrm.dao;

import java.util.List;

import com.gmbdesign.cfrm.dto.EventoDTO;

public interface EventoDAO {

	/**
	 * Metodo que lista todos los eventos.jsp disponibles en la base de datos.
	 * @return
	 */
	List<EventoDTO> listarEventos();
	
	/**
	 * Metodo que actualiza los datos de un evento dado.
	 * @param evento
	 * @return
	 */
	boolean updateEvento(EventoDTO evento);
	
	/**
	 * Metodo que inserta un nuevo evento en la base de datos.
	 * @param evento
	 * @return
	 */
	boolean insertEvento(EventoDTO evento);
	
	/**
	 * Metodo que elimina un evento concreto de la base de datos.
	 * @param evento
	 * @return
	 */
	boolean deleteEvento(EventoDTO evento);
}
