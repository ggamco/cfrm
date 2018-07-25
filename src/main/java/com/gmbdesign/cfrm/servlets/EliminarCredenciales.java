package com.gmbdesign.cfrm.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmbdesign.cfrm.dao.CredencialDAO;
import com.gmbdesign.cfrm.database.DataBase;
import com.gmbdesign.cfrm.dto.CredencialDTO;
import com.gmbdesign.cfrm.impl.CredencialDAOImpl;

/**
 * Servlet implementation class EliminarCredenciales
 */
@WebServlet("/EliminarCredenciales")
public class EliminarCredenciales extends HttpServlet {

	private static final long serialVersionUID = -1415738658558645165L;
	private static final String TAG = EliminarCredenciales.class.getCanonicalName();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarCredenciales() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p_idCredencial = req.getParameter("idCredencial");
		CredencialDAO credencialDAO = new CredencialDAOImpl();
		CredencialDTO credencial;
		boolean resultado = false;

		if (!p_idCredencial.equals("")) {
			Logger.getLogger(TAG).log(Level.INFO, "INSERT de credencial");
			credencial = new CredencialDTO(Integer.parseInt(p_idCredencial), null);
			if (credencialDAO.borrarCredencial(credencial)) {
				resultado = true;
			}
		}

		if (resultado) {
			resp.setStatus(DataBase.SQL_OK);
			RequestDispatcher rd = req.getRequestDispatcher("CargarCredenciales?reload=true");
			rd.include(req, resp);
		} else {
			resp.setStatus(DataBase.SQL_UNKNOWN_ERROR);
		}
	}

}
