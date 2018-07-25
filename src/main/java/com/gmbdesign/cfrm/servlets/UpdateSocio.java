package com.gmbdesign.cfrm.servlets;

import com.gmbdesign.cfrm.dao.SocioDAO;
import com.gmbdesign.cfrm.database.DataBase;
import com.gmbdesign.cfrm.database.Manager;
import com.gmbdesign.cfrm.dto.SocioDTO;
import com.gmbdesign.cfrm.impl.SocioDAOImpl;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateSocio extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String localName = req.getLocalAddr();
        Logger.getLogger(UpdateSocio.class.getName()).log(Level.WARNING, "La IP {0} ha intentado acceder utilizando un metodo no permitido", localName);

        resp.sendRedirect("./");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = new String(Base64.getDecoder().decode(req.getParameter("json").getBytes("utf-8")), "utf-8");
        System.out.println(json);
        SocioDTO nuevoSocio = new GsonBuilder().create().fromJson(json, SocioDTO.class);
        Logger.getLogger(UpdateSocio.class.getCanonicalName()).log(
                Level.INFO,
                "Socio numero {0} registrado",
                new String[]{
                        String.valueOf(nuevoSocio.getNumero()),
                }
        );
        SocioDAO socioDAO = new SocioDAOImpl();
        int[] tx = socioDAO.editarDatosSocio(nuevoSocio);

        switch (tx[1]) {
            case DataBase.SQL_OK:
                //El socio se ha insertado correctamente
                Logger.getLogger(UpdateSocio.class.getName()).log(Level.INFO, "El socio se ha registrado correctamente en la DataBase");
                resp.setStatus(HttpServletResponse.SC_OK); //Respuesta Http 200
                break;
            case DataBase.SQL_DUPLICATE_ENTRY:
                //No hubo coincidencia con la base de datos. Redirección a login.
                Logger.getLogger(UpdateSocio.class.getName()).log(Level.INFO, "Ya existe el socio. Se reenvia al formulario de registro.");
                resp.sendError(1020);
                break;
            case DataBase.SQL_DUPLICATE_ENTRY_SOCIO:
                //No hubo coincidencia con la base de datos. Redirección a login.
                Logger.getLogger(UpdateSocio.class.getName()).log(Level.INFO, "Ya existe el numero de socio. Se reenvia al formulario de registro.");
                resp.sendError(1021);
                break;
            case DataBase.SQL_DUPLICATE_ENTRY_SYSTEM_CODE:
                //No hubo coincidencia con la base de datos. Redirección a login.
                Logger.getLogger(UpdateSocio.class.getName()).log(Level.INFO, "Ya existe el codigoSistema. Se reenvia al formulario de registro.");
                resp.sendError(1022);
                break;
            case DataBase.SQL_DUPLICATE_ENTRY_DNI:
                //No hubo coincidencia con la base de datos. Redirección a login.
                Logger.getLogger(UpdateSocio.class.getName()).log(Level.INFO, "Ya existe un socio con el mismo DNI. Se reenvia al formulario de registro.");
                resp.sendError(1023);
                break;
            default:
                Logger.getLogger(UpdateSocio.class.getName()).log(Level.INFO, "Error desconocido. Se reenvia al formulario de registro.");
                resp.sendError(1050);
                break;
        }
    }
}
