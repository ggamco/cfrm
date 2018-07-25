/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmbdesign.cfrm.servlets;

import com.gmbdesign.cfrm.bussiness.ICredentialCard;
import com.gmbdesign.cfrm.database.DataBase;
import com.gmbdesign.cfrm.database.Manager;
import com.gmbdesign.cfrm.dto.AlumnoAlterDTO;
import com.gmbdesign.cfrm.dto.SocioDTO;
import com.gmbdesign.cfrm.dto.SocioDataDTO;
import com.gmbdesign.cfrm.impl.SocioDAOImpl;
import com.gmbdesign.cfrm.pdfcreator.CarnetFactory;
import com.itextpdf.text.DocumentException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ggamboa
 */
@WebServlet(name = "AgregadorAlumnos", urlPatterns = {"/AgregadorAlumnos"})
public class AgregadorAlumnos extends HttpServlet {

    private final Manager manager = Manager.getInstance();
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        List<AlumnoAlterDTO> resultado = null;
        ResultSet busqueda = manager.prepareSelect(DataBase.SQL_ALTER_ALUMNO);
        if (busqueda != null) {
            try{
                resultado = new ArrayList<>();
                while(busqueda.next()) {
                    AlumnoAlterDTO alumno = new AlumnoAlterDTO();
                    alumno.setCategoria(busqueda.getString("categoria"));
                    alumno.setCodigo(busqueda.getString("codigo"));
                    alumno.setCredencial(busqueda.getString("credencial"));
                    alumno.setNombre(busqueda.getString("nombre"));
                    alumno.setNumero(busqueda.getString("socio"));
                    alumno.setYear(busqueda.getString("year"));
                    resultado.add(alumno);
                }
            } catch(SQLException e) {
                Logger.getLogger(SocioDAOImpl.class.getCanonicalName()).log(Level.SEVERE, "Error en el proceso de busqueda de socios por numero. {0}", e.getLocalizedMessage());
            }
        }
        
        List<ICredentialCard> carnets = new ArrayList<>();
        
        carnets.addAll(resultado);
        
        if (carnets != null) {
            ByteArrayOutputStream baos = null;

            try {
                baos = CarnetFactory.createCarnetPdf(carnets);
            } catch (DocumentException ex) {
                Logger.getLogger(GestorImpresionCarnets.class.getCanonicalName()).log(Level.SEVERE, "Error a la hora de imprimir un listado de carnets. {0}", ex.getLocalizedMessage());
            }

            resp.setHeader("Expires", "0");
            resp.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            resp.setHeader("Pragma", "public");
            resp.addHeader("Content-Disposition", "inline; filename=RMCarnets.pdf");

            resp.setContentType("application/pdf");

            resp.setContentLength(baos.size());

            ServletOutputStream out = resp.getOutputStream();
            baos.writeTo(out);
            out.flush();
        }
        
    }

    

}
