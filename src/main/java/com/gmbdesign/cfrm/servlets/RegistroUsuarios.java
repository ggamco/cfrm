package com.gmbdesign.cfrm.servlets;

import static com.gmbdesign.cfrm.utils.DecodeJSON.*;
import com.gmbdesign.cfrm.dao.UsuarioDAO;
import com.gmbdesign.cfrm.database.DataBase;
import com.gmbdesign.cfrm.database.Manager;
import com.gmbdesign.cfrm.dto.UsuarioDTO;
import com.gmbdesign.cfrm.impl.UsuarioDAOImpl;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author gustavogamboa
 */
public class RegistroUsuarios extends HttpServlet {

    private static final long serialVersionUID = -956600406183126881L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String localName = req.getLocalAddr();
        Logger.getLogger(Manager.class.getName()).log(Level.WARNING, "La IP {0} ha intentado acceder utilizando un metodo no permitido", localName);

        resp.sendRedirect("./");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UsuarioDTO usuarioRegistrado = descodificaJSON(req.getParameter("json"), UsuarioDTO.class);

        //ENCRIPTAMOS LA CONTRASEÑA ANTES DE ALMACENARLA
        usuarioRegistrado.setPass(DigestUtils.sha256Hex(usuarioRegistrado.getPass()));

        //Preparamos la solicitud de login a la DataBase
        UsuarioDAO userDAO = new UsuarioDAOImpl();
        int[] tx = userDAO.agregarUsuario(usuarioRegistrado);

        switch (tx[1]) {
            case DataBase.SQL_OK:
                //El usuario se ha insertado correctamente
                Logger.getLogger(Login.class.getName()).log(Level.INFO, "El usuario se ha registrado correctamente en la DataBase");
                resp.setStatus(HttpServletResponse.SC_OK); //Respuesta Http 200
                break;
            case DataBase.SQL_DUPLICATE_ENTRY:
                //No hubo coincidencia con la base de datos. Redirección a login.
                Logger.getLogger(Login.class.getName()).log(Level.INFO, "Ya existe el usuario. Se reenvia al formulario de registro.");
                resp.sendError(1020);
                break;
            default:
                Logger.getLogger(Login.class.getName()).log(Level.INFO, "Error desconocido. Se reenvia al formulario de registro.");
                resp.sendError(1050);
                break;
        }

    }

}
