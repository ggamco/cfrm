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
 * Servlet implementation class InsertUpdateCredenciales
 */
@WebServlet("/InsertUpdateCredenciales")
public class InsertUpdateCredenciales extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG = InsertUpdateCredenciales.class.getCanonicalName();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertUpdateCredenciales() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p_credencial = req.getParameter("credencial").toUpperCase();
		CredencialDAO credencialDAO = new CredencialDAOImpl();
		CredencialDTO credencial;
		boolean resultado = false;

		Logger.getLogger(TAG).log(Level.INFO, "INSERT de credencial");
		credencial = new CredencialDTO(p_credencial);
		if (credencialDAO.insertCredencial(credencial)) {
			resultado = true;
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
