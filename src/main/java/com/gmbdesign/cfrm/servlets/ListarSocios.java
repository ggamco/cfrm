package com.gmbdesign.cfrm.servlets;

import com.gmbdesign.cfrm.dao.SocioDAO;
import com.gmbdesign.cfrm.dto.BusquedaDTO;
import com.gmbdesign.cfrm.dto.SocioDTO;
import com.gmbdesign.cfrm.impl.SocioDAOImpl;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ggamboa
 */
@WebServlet(name = "ListarSocios", urlPatterns = {"/ListarSocios"})
public class ListarSocios extends HttpServlet {

    private PrintWriter out;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = new String(Base64.getDecoder().decode(req.getParameter("json").getBytes("utf-8")), "utf-8");
        BusquedaDTO busqueda = new GsonBuilder().create().fromJson(json, BusquedaDTO.class);
        SocioDAO socioDAO = new SocioDAOImpl();
        List<SocioDTO> listaSocios = null;
        switch (busqueda.getBusqueda()) {
            case "nombre":
                listaSocios = socioDAO.buscarPorNombre(busqueda.getParam1());
                break;
            case "numero":
                listaSocios = socioDAO.buscarPorNumeroSocio(busqueda.getParam2());
                break;
            case "rango":
                listaSocios = socioDAO.buscarPorRango(busqueda.getParam2(), busqueda.getParam3());
                break;
            case "notPrinted":
                listaSocios = socioDAO.buscarSociosNotPrinted();
                break;    
        }
        if (listaSocios != null) {
            resp.setCharacterEncoding("utf-8");
            out = resp.getWriter();
            out.println("<thead><tr><th>Socio</th><th>Nombre</th><th>Email</th><th>Fecha Alta</th><th>Estado</th><th>Impreso</th><th style='text-align: center;'>Acciones</th></tr></thead>");
            out.println("<tbody>");
            for (SocioDTO socio : listaSocios) {
                out.println("<tr>");
                out.println("<td>" + socio.getNumero() + "</td>");
                out.println("<td>" + socio.getAlias() + "</td>");
                out.println("<td>" + socio.getSocioData().getCorreo() + "</td>");
                out.println("<td>" + socio.getSocioData().getAlta() + "</td>");
                out.println("<td><select disabled class='form-control'>");
                if(socio.isIsActive()) {
                    out.println("<option>Activo</option>");
                } else {
                    out.println("<option>Bloqueado</option>");
                }
                out.println("</select></td>");
                out.println("<td><select disabled class='form-control'>");
                if(socio.isIsPrinted()) {
                    out.println("<option>Si</option>");
                } else {
                    out.println("<option>No</option>");
                }
                out.println("</select></td>");
                out.println("<td class='acciones' style='text-align: center;'>");
                out.println("<span><a class='icon-file-text2 pulsable' onclick=\"cargarModal('"+socio.getCodigoSistema()+"', 'CargarInfoSocio')\"></a></span>");
                out.println("<span><a class='icon-barcode pulsable' href='../GestorImpresionCarnets?imprimir=single&codigoSistema="+socio.getCodigoSistema()+"' target='_blank'></a></span>");
                out.println("<span class='icon-bin hide'></span>");
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</tbody>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
