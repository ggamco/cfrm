package com.gmbdesign.cfrm.servlets;

import com.gmbdesign.cfrm.dao.UsuarioDAO;
import com.gmbdesign.cfrm.dto.UsuarioDTO;
import com.gmbdesign.cfrm.impl.UsuarioDAOImpl;
import com.gmbdesign.cfrm.utils.AuthTokenGenerator;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author ggamboa
 */
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1776975497604733757L;
    private static String remoteName;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getINFO(req); //Grabamos en el LOG del server Datos de la conexión realizada.
        remoteName = req.getRemoteAddr();
        Logger.getLogger(Login.class.getName()).log(Level.WARNING, "La IP: {0} ha solocitado acceso a un metodo no permitido", remoteName);
        resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); //status 405
        resp.getWriter().print("<h1>Acceso no permitido</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getINFO(req); //Grabamos en el LOG del server Datos de la conexión realizada.
        remoteName = req.getRemoteAddr();
        UsuarioDTO usuarioLogueado;
        UsuarioDAO usuarioDAO;
        UsuarioDTO data;
        if (req.getContentType() != null) {
            if (req.getContentType().equals("application/json")) {
                Logger.getLogger(Login.class.getName()).log(Level.INFO, "Recuperamos el usuario en formato JSON");
                String json = req.getReader().readLine();
                usuarioLogueado = new GsonBuilder().create().fromJson(json, UsuarioDTO.class);
                usuarioDAO = new UsuarioDAOImpl();
                data = usuarioDAO.login(usuarioLogueado);
                if (data != null) {
                    Logger.getLogger(Login.class.getName()).log(Level.INFO, "El usuario está registrado en el sistema");
                    RequestDispatcher rd = req.getRequestDispatcher("ListarEventos");
                    rd.forward(req, resp);
                }
            } else {
                Logger.getLogger(Login.class.getName()).log(Level.INFO, "Recuperamo el usuario del formulario WEB");
                String user = req.getParameter("plainUser");
                String pass = DigestUtils.sha256Hex(req.getParameter("cryptoPass"));
                usuarioLogueado = new UsuarioDTO(user, pass);
                usuarioDAO = new UsuarioDAOImpl();
                data = usuarioDAO.login(usuarioLogueado);
                if (data != null) {
                    Logger.getLogger(Login.class.getName()).log(Level.INFO, "El usuario está registrado y se le concede el acceso al servicio.");
                    req.getSession().setAttribute("usuarioLogueado", data);
                    req.getSession().setAttribute("token", AuthTokenGenerator.create(usuarioLogueado));
                    resp.setStatus(HttpServletResponse.SC_OK); //Respuesta Http 200
                    resp.sendRedirect("./admin/");
                } else {
                    Logger.getLogger(Login.class.getName()).log(Level.INFO, "El usuario no está registrado. Se redirige al usuario a la página de login.");
                    resp.sendRedirect("./index.jsp?error=code01");
                }
            }
        } else {
            Logger.getLogger(Login.class.getName()).log(Level.WARNING, "La IP: {0} ha solocitado acceso con un contenido incorrecto", remoteName);
            resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); //status 405
            resp.getWriter().print("<h1>Acceso no permitido</h1>");
        }
    }

    private void getINFO(HttpServletRequest req) {
        String[] data = {
            req.getRequestedSessionId(),
            req.getRemoteAddr(),
            req.getRemoteHost(),
            req.getHeader("Referer")
        };
        Logger.getLogger(Login.class.getCanonicalName()).log(Level.INFO, "Conexión realizada por: [{0}][{1}][{2}][{3}]", data);
    }
}
