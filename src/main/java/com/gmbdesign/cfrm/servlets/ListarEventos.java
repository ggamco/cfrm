package com.gmbdesign.cfrm.servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmbdesign.cfrm.dao.EventoDAO;
import com.gmbdesign.cfrm.dto.EventoDTO;
import com.gmbdesign.cfrm.impl.EventoDAOImpl;
import com.google.gson.GsonBuilder;

/**
 * Servicio que responde con el listado de eventos.jsp disponibles en base de datos.
 * @author ggamboa
 */
public class ListarEventos extends HttpServlet {

    private static final long serialVersionUID = -521300885340397052L;
    private static final String TAG = ListarEventos.class.getCanonicalName();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger.getLogger(TAG).log(Level.INFO, "Recibida petición de listar eventos.jsp desde la IP: {0}", req.getRemoteAddr());
        List<EventoDTO> listaEventos;
        EventoDAO eventoDAO = new EventoDAOImpl();
        listaEventos = eventoDAO.listarEventos();
        if (listaEventos != null) {
            String eventosJSON = new GsonBuilder().create().toJson(listaEventos);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            resp.getWriter().write(eventosJSON);
        } else {
            resp.sendError(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
