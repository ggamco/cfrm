package com.gmbdesign.cfrm.servlets;

import com.gmbdesign.cfrm.bussiness.ICredentialCard;
import com.gmbdesign.cfrm.dao.SocioDAO;
import com.gmbdesign.cfrm.impl.SocioDAOImpl;
import com.gmbdesign.cfrm.pdfcreator.CarnetFactory;
import com.itextpdf.text.DocumentException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ggamboa
 */
public class GestorImpresionCarnets extends HttpServlet {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5940238139136152177L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Estamos llamando al servicio de Impresion");

        resp.addHeader("Access-Control-Allow-Origin", "http://gmbdesign.es");
        resp.addHeader("Access-Control-Allow-Origin", "http://www.gmbdesign.es");
        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        resp.addHeader("Access-Control-Allow-Headers", "Accept, Origin, Content-Type");
        resp.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTION");
        resp.setCharacterEncoding("utf-8");

        String accion = req.getParameter("imprimir");
        String tipo = req.getParameter("tipoCarnet");
        String codigoSistema = req.getParameter("codigoSistema");
        SocioDAO socioDAO = new SocioDAOImpl();
        List<ICredentialCard> carnets = null;

        if (accion != null) {
            switch (accion) {
                case "all":
                    carnets = new ArrayList<>();
                    carnets.addAll(socioDAO.buscarSociosNotPrinted());
                    break;
                case "rango":
                    break;
                case "single":
                    carnets = new ArrayList<>();
                    carnets.add(socioDAO.buscarSocioByCodigo(codigoSistema));
                    break;
                case "alumno":
                    carnets = new ArrayList<>();
            }
        }

        if (carnets != null) {
            ByteArrayOutputStream baos = null;

            try {
                baos = CarnetFactory.createCarnetPdf(carnets);
                socioDAO.updateToPrinted(carnets);
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
