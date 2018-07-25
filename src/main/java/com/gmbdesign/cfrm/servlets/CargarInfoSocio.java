/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmbdesign.cfrm.servlets;

import com.gmbdesign.cfrm.dao.CredencialDAO;
import com.gmbdesign.cfrm.dao.SocioDAO;
import com.gmbdesign.cfrm.dto.CredencialDTO;
import com.gmbdesign.cfrm.dto.SocioDTO;
import com.gmbdesign.cfrm.impl.CredencialDAOImpl;
import com.gmbdesign.cfrm.impl.SocioDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ggamboa
 */
public class CargarInfoSocio extends HttpServlet {

    private static final long serialVersionUID = -3315676784617703014L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "http://gmbdesign.es");
        resp.addHeader("Access-Control-Allow-Origin", "http://www.gmbdesign.es");
        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        resp.addHeader("Access-Control-Allow-Headers", "Accept, Origin, Content-Type");
        resp.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTION");

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String codigoSistema = req.getParameter("codigoSistema");

        SocioDAO socioDAO = new SocioDAOImpl();
        SocioDTO socioSolicitado = socioDAO.buscarSocioByCodigo(codigoSistema);

        CredencialDAO credencialDAO = new CredencialDAOImpl();
        List<CredencialDTO> listaCredenciales = credencialDAO.cargarCredenciales();
        
        String[] dni = socioSolicitado.getSocioData().getDni().split("/");
        String[] direccion = socioSolicitado.getSocioData().getDireccion().split("/");
        String[] ccc = socioSolicitado.getSocioData().getIban().split("/");

        out.println("<div class='modal fade' id='myModal' tabindex='-1' role='dialog' aria-labelledby='myModalLabel'>");
        out.println("<div class='modal-dialog modal-lg' role='document'>");
        out.println("<div class='modal-content'>");

        //out.println("<form id='listado' action='UpdateInfoSocio' method='POST'>");

        out.println("<div class='modal-header'>");
        out.println("<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>");
        out.println("<h4 class='modal-title' id='myModalLabel'>INFORMACIÓN SOCIO / ABONADO</h4>");
        out.println("</div>");

        out.println("<div class='modal-body flexy' id='emergente'>");

        out.println("<div class='alert-container-modal'></div>");

        out.println("<div class='panel panel-default'>");
        out.println("<div class='panel-body'>");
        out.println("<form class='form-horizontal' id='updateSocio'>");
        out.println("<div class='form-group container-fluid' id='form-user'>");
        out.println("<label for='socio' class='col-sm-2 col-md-2 col-lg-2 control-label'>Número de Socio:</label>");
        out.println("<div class='col-sm-2 col-md-2 col-lg-'>");
        out.println("<input type='number' name='numeroSocio' value='" + socioSolicitado.getNumero() + "' class='form-control' id='socio' aria-describedby='inputSuccess2Status'>");
        out.println("</div>");
        out.println("<div class='col-sm-2 col-md-2 col-lg-'>");
        out.println("<select name='credencial' class='form-control' id='tipoAbono' aria-describedby='inputSuccess2Status'>");
        out.println("<option disabled value='0'>Tipo abono</option>");
        for (CredencialDTO credencial : listaCredenciales) {
            if(socioSolicitado.getCredencial().equalsIgnoreCase(credencial.getCodigo())) {
                out.println("<option selected value='"+credencial.getCodigo()+"'>"+credencial.getCredencial()+"</option>");
            } else {
                out.println("<option value='"+credencial.getCodigo()+"'>"+credencial.getCredencial()+"</option>");
            }
        }
        out.println("</select>");
        out.println("</div>");
        out.println("<label for='usuario' class='col-sm-2 col-md-2 col-lg-2 control-label'>Código sistema:</label>");
        out.println("<div class='col-sm-4 col-md-4 col-lg-4'>");
        out.println("<input type='text' disabled class='form-control' value='" + codigoSistema + "' aria-describedby='inputSuccess2Status' style='text-transform:uppercase'>");
        out.println("<input type='hidden' name='codigoSistema' class='form-control' value='" + codigoSistema + "' aria-describedby='inputSuccess2Status' style='text-transform:uppercase'>");
        out.println("<input type='hidden' name='idSocio' class='form-control' value='" + socioSolicitado.getIdSocio() + "' aria-describedby='inputSuccess2Status' style='text-transform:uppercase'>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='form-group container-fluid'>");
        out.println("<label for='direccion' class='col-sm-12 col-md-12 col-lg-12 control-label'></label>");
        out.println("</div>");
        out.println("<div class='form-group container-fluid' id='form-name'>");
        out.println("<label for='nombre' class='col-sm-2 col-md-2 col-lg-2 control-label'>Nombre:</label>");
        out.println("<div class='col-sm-4 col-md-4 col-lg-4'>");
        out.println("<input type='text' name='datos[nombre]' value='" + socioSolicitado.getSocioData().getNombre() + "' class='form-control' id='nombre' aria-describedby='inputSuccess2Status' placeholder=''>");
        out.println("</div>");
        out.println("<label for='primerApellido' class='col-sm-2 col-md-2 col-lg-2 control-label'>Primer apellido:</label>");
        out.println("<div class='col-sm-4 col-md-4 col-lg-4'>");
        out.println("<input type='text' name='datos[primerApellido]' value='" + socioSolicitado.getSocioData().getPrimerApellido() + "' class='form-control' id='primerApellido' aria-describedby='inputSuccess2Status' placeholder=''>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='form-group container-fluid'>");
        out.println("<label for='segundoApellido' class='col-sm-2 col-md-2 col-lg-2 control-label'>Segundo apellido:</label>");
        out.println("<div class='col-sm-4 col-md-4 col-lg-4'>");
        out.println("<input type='text' name='datos[segundoApellido]' value='" + socioSolicitado.getSocioData().getSegundoApellido() + "' class='form-control' id='segundoApellido' aria-describedby='inputSuccess2Status' placeholder=''>");
        out.println("</div>");
        out.println("<label for='dni' class='col-sm-2 col-md-2 col-lg-2 control-label'>DNI:</label>");
        out.println("<div class='col-sm-3 col-md-3 col-lg-3'>");
        if (dni.length > 0) {
            out.println("<input maxlength='8' type='text' name='datos[dni][]' value='" + dni[0] + "' class='form-control' id='dni'>");
        } else {
            out.println("<input maxlength='8' type='text' name='datos[dni][]' value='' class='form-control' id='dni'>");
        }
        out.println("</div>");
        out.println("<div class='col-sm-1 col-md-1 col-lg-1'>");
        if (dni.length > 1) {
            out.println("<input maxlength='1' type='text' name='datos[dni][]' value='" + dni[1] + "' class='form-control' id='letra' style='text-transform:uppercase'>");
        } else {
            out.println("<input maxlength='1' type='text' name='datos[dni][]' value='' class='form-control' id='letra' style='text-transform:uppercase'>");
        }
        out.println("</div>");
        out.println("</div>");
        out.println("<div class='form-group container-fluid'>");
        out.println("<label for='nacimiento' class='col-sm-2 col-md-2 col-lg-2 control-label'>Fecha Nacimiento:</label>");
        out.println("<div class='col-sm-4 col-md-4 col-lg-4'>");
        out.println("<input type='date' name='datos[nacimiento]' value='" + socioSolicitado.getSocioData().getNacimiento() + "' class='form-control' id='nacimiento'>");
        out.println("</div>");
        out.println("<label for='alta' class='col-sm-2 col-md-2 col-lg-2 control-label'>Fecha Alta:</label>");
        out.println("<div class='col-sm-4 col-md-4 col-lg-4'>");
        out.println("<input type='date' name='datos[alta]' value='" + socioSolicitado.getSocioData().getAlta() + "' class='form-control' id='alta'>");
        out.println("</div>");
        out.println("</div>");

        out.println("<div class='form-group container-fluid'>");
        out.println("<label for='direccion' class='col-sm-12 col-md-12 col-lg-12 control-label'></label>");
        out.println("</div>");

        out.println("<div class='form-group container-fluid'>");
        out.println("<label for='email' class='col-sm-2 col-md-2 col-lg-2 control-label'>Correo electrónico:</label>");
        out.println("<div class='col-sm-4 col-md-4 col-lg-4'>");
        out.println("<input type='email' name='datos[correo]' value='" + socioSolicitado.getSocioData().getCorreo() + "' class='form-control' id='email' aria-describedby='inputSuccess2Status'>");
        out.println("</div>");
        out.println("<label for='phone' class='col-sm-2 col-md-2 col-lg-2 control-label'>Teléfono:</label>");
        out.println("<div class='col-sm-4 col-md-4 col-lg-4'>");
        out.println("<input type='tel' name='datos[telefono]' value='" + socioSolicitado.getSocioData().getTelefono() + "' class='form-control' id='phone' aria-describedby='inputSuccess2Status'>");
        out.println("</div>");
        out.println("</div>");

        out.println("<div class='form-group container-fluid'>");
        out.println("<label for='direccion' class='col-sm-12 col-md-12 col-lg-12 control-label'></label>");
        out.println("</div>");

        out.println("<div class='form-group container-fluid'>");
        out.println("<label for='direccion' class='col-sm-2 col-md-2 col-lg-2 control-label'>Dirección:</label>");
        out.println("<div class='col-sm-6 col-md-6 col-lg-6'>");
        if (direccion.length > 0) {
            out.println("<input type='text' name='datos[direccion][]' value='" + direccion[0] + "' class='form-control' id='direccion' aria-describedby='inputSuccess2Status'>");
        } else {
            out.println("<input type='text' name='datos[direccion][]' class='form-control' id='direccion' aria-describedby='inputSuccess2Status'>");
        }
        out.println("</div>");
        out.println("<div class='col-sm-2 col-md-2 col-lg-2'>");
        if (direccion.length > 1) {
            out.println("<input type='text' name='datos[direccion][]' value='" + direccion[1] + "' placeholder='Nº' class='form-control' id='numero' aria-describedby='inputSuccess2Status'>");
        } else {
            out.println("<input type='text' name='datos[direccion][]' placeholder='Nº' class='form-control' id='numero' aria-describedby='inputSuccess2Status'>");
        }
        out.println("</div>");
        out.println("<div class='col-sm-2 col-md-2 col-lg-2'>");
        if (direccion.length > 2) {
            out.println("<input type='text' name='datos[direccion][]' value='" + direccion[2] + "' placeholder='Piso' class='form-control' id='piso' aria-describedby='inputSuccess2Status'>");
        } else {
            out.println("<input type='text' name='datos[direccion][]' placeholder='Piso' class='form-control' id='piso' aria-describedby='inputSuccess2Status'>");
        }
        out.println("</div>");
        out.println("</div>");

        out.println("<div class='form-group container-fluid'>");
        out.println("<label for='activo' class='col-sm-2 col-md-2 col-lg-2 control-label'></label>");
        out.println("<div class='col-sm-2 col-md-2 col-lg-2'>");
        out.println("<input type='number' name='datos[codigoPostal]' value='" + socioSolicitado.getSocioData().getCodigoPostal() + "' placeholder='C. Postal' class='form-control' id='postal' aria-describedby='inputSuccess2Status'>");
        out.println("</div>");
        out.println("<div class='col-sm-4 col-md-4 col-lg-4'>");
        out.println("<input type='text' name='datos[ciudad]' value='" + socioSolicitado.getSocioData().getCiudad() + "' placeholder='Ciudad' class='form-control' id='ciudad' aria-describedby='inputSuccess2Status'>");
        out.println("</div>");
        out.println("</div>");

        out.println("<div class='form-group container-fluid'>");
        out.println("<label for='activo' class='col-sm-2 col-md-2 col-lg-2 control-label'></label>");
        out.println("</div>");

        out.println("<div class='form-group container-fluid'>");
        out.println("<div class='col-sm-2 col-md-2 col-lg-2'>");
        if (ccc.length > 0) {
            out.println("<input type='text' maxlength='4' name='datos[iban][]' value='" + ccc[0] + "' placeholder='ESXX' class='input_center form-control' id='direccion' aria-describedby='inputSuccess2Status' style='text-transform:uppercase'>");
        } else {
            out.println("<input type='text' maxlength='4' name='datos[iban][]' placeholder='ESXX' class='input_center form-control' id='direccion' aria-describedby='inputSuccess2Status' style='text-transform:uppercase'>");
        }
        out.println("</div>");
        out.println("<div class='col-sm-2 col-md-2 col-lg-2'>");
        if (ccc.length > 1) {
            out.println("<input type='text' maxlength='4' name='datos[iban][]' value='" + ccc[1] + "' class='input_center form-control' aria-describedby='inputSuccess2Status'>");
        } else {
            out.println("<input type='text' maxlength='4' name='datos[iban][]' class='input_center form-control' aria-describedby='inputSuccess2Status'>");
        }
        out.println("</div>");
        out.println("<div class='col-sm-2 col-md-2 col-lg-2'>");
        if (ccc.length > 2) {
            out.println("<input type='text' maxlength='4' name='datos[iban][]' value='" + ccc[2] + "' class='input_center form-control' aria-describedby='inputSuccess2Status'>");
        } else {
            out.println("<input type='text' maxlength='4' name='datos[iban][]' class='input_center form-control' aria-describedby='inputSuccess2Status'>");
        }
        out.println("</div>");
        out.println("<div class='col-sm-2 col-md-2 col-lg-2'>");
        if (ccc.length > 3) {
            out.println("<input type='text' maxlength='4' name='datos[iban][]' value='" + ccc[3] + "' class='input_center form-control' aria-describedby='inputSuccess2Status'>");
        } else {
            out.println("<input type='text' maxlength='4' name='datos[iban][]' class='input_center form-control' aria-describedby='inputSuccess2Status'>");
        }
        out.println("</div>");
        out.println("<div class='col-sm-2 col-md-2 col-lg-2'>");
        if (ccc.length > 4) {
            out.println("<input type='text' maxlength='4' name='datos[iban][]' value='" + ccc[4] + "' class='input_center form-control' aria-describedby='inputSuccess2Status'>");
        } else {
            out.println("<input type='text' maxlength='4' name='datos[iban][]' class='input_center form-control' aria-describedby='inputSuccess2Status'>");
        }
        out.println("</div>");
        out.println("<div class='col-sm-2 col-md-2 col-lg-2'>");
        if (ccc.length > 5) {
            out.println("<input type='text' maxlength='4' name='datos[iban][]' value='" + ccc[5] + "' class='input_center form-control' aria-describedby='inputSuccess2Status'>");
        } else {
            out.println("<input type='text' maxlength='4' name='datos[iban][]' class='input_center form-control' aria-describedby='inputSuccess2Status'>");
        }
        out.println("</div>");
        out.println("</div>");
        out.println("</form>");
        out.println("</div>");
        out.println("</div>");

        out.println("</div>");

        out.println("<div class='modal-footer'>");
        out.println("<button type='button' data-dismiss='modal' class='btn btn-primary btn-lg btn-block' id='form-sub' onclick='callServer(\"UpdateSocio\", \"updateSocio\")'>Actualizar información</button>");
        out.println("</div>");

        //out.println("</form>");

        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String localName = req.getLocalAddr();
        Logger.getLogger(CargarInfoSocio.class.getName()).log(Level.WARNING, "La IP: {0} ha intentado acceder utilizando un metodo no permitido", localName);

        resp.sendRedirect("./");
    }

}
