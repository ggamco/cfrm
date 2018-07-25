package com.gmbdesign.cfrm.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gmbdesign.cfrm.dao.EventoDAO;
import com.gmbdesign.cfrm.dao.TarifaEventoDAO;
import com.gmbdesign.cfrm.database.DataBase;
import com.gmbdesign.cfrm.database.Manager;
import com.gmbdesign.cfrm.dto.EventoDTO;
import com.gmbdesign.cfrm.dto.TarifaEventoDTO;
import java.sql.SQLException;

public class EventoDAOImpl implements EventoDAO {

	private static final String TAG = EventoDAOImpl.class.getCanonicalName();
	private Manager manager = Manager.getInstance();
	
	@Override
	public List<EventoDTO> listarEventos() {
		final String SQL = DataBase.SQL_SELECT_ALL_EVENTOS;
		ResultSet busquedaEvento = manager.prepareSelect(SQL);
		List<EventoDTO> listaEventos = null;
		List<TarifaEventoDTO> listaTarifaEvento = null;
		TarifaEventoDAO tarifaEventoDAO = new TarifaEventoDAOImpl();
		if(busquedaEvento != null){
			listaEventos = new ArrayList<>();
			try {
				while(busquedaEvento.next()) {
					listaTarifaEvento = new ArrayList<>();
					EventoDTO evento = new EventoDTO(
							busquedaEvento.getInt("idEvento"),
							busquedaEvento.getString("fecha"),
							busquedaEvento.getString("hora"),
							busquedaEvento.getString("jornada"),
							busquedaEvento.getString("torneo"),
							busquedaEvento.getString("visitante"),
							busquedaEvento.getBoolean("esActivo")
							);
					listaTarifaEvento = tarifaEventoDAO.listarTarifasByEvento(evento.getIdEvento());
					evento.setListaTarifas(listaTarifaEvento);
					listaEventos.add(evento);
				}
			} catch(SQLException e){
				Logger.getLogger(TAG).log(Level.SEVERE, "Se ha producio un error en el procesado de los eventos.jsp. ", e);
			}
		}
		return listaEventos;
	}

	@Override
	public boolean updateEvento(EventoDTO evento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertEvento(EventoDTO evento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEvento(EventoDTO evento) {
		// TODO Auto-generated method stub
		return false;
	}

}
