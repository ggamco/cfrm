package com.gmbdesign.cfrm.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gmbdesign.cfrm.dao.TarifaEventoDAO;
import com.gmbdesign.cfrm.database.DataBase;
import com.gmbdesign.cfrm.database.Manager;
import com.gmbdesign.cfrm.dto.TarifaEventoDTO;

public class TarifaEventoDAOImpl implements TarifaEventoDAO {

	private static final String TAG = TarifaEventoDAOImpl.class.getCanonicalName();
	private Manager manager = Manager.getInstance();
	
	@Override
	public List<TarifaEventoDTO> listarTarifasByEvento(int idEvento) {
		Logger.getLogger(TAG).log(Level.INFO, "Iniciando el listado de tarifas por evento.");
		List<TarifaEventoDTO> listaTarifasByEvento = null;
		final String SQL = DataBase.SQL_SELECT_TARIFAS_BY_EVENTO;
		final String[] PARAMETROS = {String.valueOf(idEvento)};
		ResultSet busquedaTarifas = manager.prepareSelect(SQL, PARAMETROS);
		if (busquedaTarifas != null) {
			listaTarifasByEvento = new ArrayList<>();
			try{
				while(busquedaTarifas.next()){
					TarifaEventoDTO tarifaByEvento = new TarifaEventoDTO(
							busquedaTarifas.getInt("idTarifaEvento"),
							busquedaTarifas.getInt("idEvento"),
							busquedaTarifas.getString("nombre"),
							busquedaTarifas.getString("code"),
							busquedaTarifas.getDouble("precio")
							);
					listaTarifasByEvento.add(tarifaByEvento);
				}
			} catch(Exception e) {
				Logger.getLogger(TAG).log(Level.SEVERE, "Se produjo un error al procesar las tarifas por evento");
			}
		}
		
		return listaTarifasByEvento;
	}

	@Override
	public boolean insertTarifasByEvento(List<TarifaEventoDTO> listaTarifas, int idEvento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTarifasByEvento(List<TarifaEventoDTO> listaTarifas, int idEvento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTarifasByEvento(List<TarifaEventoDAO> listaTarifas, int idEvento) {
		// TODO Auto-generated method stub
		return false;
	}

}
