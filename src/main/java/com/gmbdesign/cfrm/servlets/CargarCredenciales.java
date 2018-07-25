package com.gmbdesign.cfrm.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmbdesign.cfrm.dao.CredencialDAO;
import com.gmbdesign.cfrm.dto.CredencialDTO;
import com.gmbdesign.cfrm.impl.CredencialDAOImpl;


/**
 * Servlet implementation class CargarCredenciales
 */
@WebServlet("/CargarCredenciales")
public class CargarCredenciales extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG = CargarCredenciales.class.getCanonicalName();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CargarCredenciales() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Logger.getLogger(TAG).log(Level.INFO, "Se ha solicitado la carga de credenciales");
		resp.addHeader("Access-Control-Allow-Origin", "http://gmbdesign.es");
		resp.addHeader("Access-Control-Allow-Origin", "http://www.gmbdesign.es");
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
		resp.addHeader("Access-Control-Allow-Headers", "Accept, Origin, Content-Type");
		resp.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTION");

		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		boolean reload = Boolean.valueOf(req.getParameter("reload"));
		
		if (reload) {
			System.out.println("estamos recargando");
		}
		
		CredencialDAO credencialDAO = new CredencialDAOImpl();
		List<CredencialDTO> credenciales = credencialDAO.cargarCredenciales();

		if (!reload) {
			out.println("<div class='modal fade in' id='credentials' tabindex='-1' role='dialog' aria-labelledby='myModalLabel'>");
				out.println("<div class='modal-dialog' role='document'>");
					out.println("<div class='modal-content'>");
	
						out.println("<div class='modal-header'>");
							out.println("<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>");
							out.println("<h4 class='modal-title' id='myModalLabel'>CREDENCIALES</h4>");
						out.println("</div>");
	
						out.println("<div class='modal-body'>");
							out.println("<div class='alert-container-modal'></div>");
							out.println("<form style='padding-bottom: 15px'>");
								out.println("<div class='input-group'>");
									out.println("<input id='idCredencial' type='hidden' value=''>");
									out.println("<input id='credencial' type='text' class='form-control' placeholder='Nueva credencial...' style='text-transform:uppercase'>");
									out.println("<input id='codigo' type='hidden' value=''>");
									out.println("<span class='input-group-btn'>");
										out.println("<button class='btn btn-default' type='button' onclick='insertUpdateCredential()'>Guardar</button>");
									out.println("</span>");
								out.println("</div>");
							out.println("</form>");
							out.println("<table class='table table-hover' id='data'>");
								out.println("<thead>");
									out.println("<tr>");
										out.println("<th class='col-md-2'>id</th>");
										out.println("<th class='col-md-6'>Credencial</th>");
										out.println("<th class='col-md-2'>Codigo</th>");
										out.println("<th class='col-md-2' style='text-align: center'>Borrar</th>");
									out.println("</tr>");
								out.println("</thead>");
								out.println("<tbody>");
								for (CredencialDTO c : credenciales) {
									out.println("<tr onclick='selectCredential("+c.getIdCredencial()+")'>");
										out.println("<td class='col-md-2'>"+c.getIdCredencial()+"</td>");
										out.println("<td class='col-md-4'>"+c.getCredencial()+"</td>");
										out.println("<td class='col-md-4'><input type='hidden' id='cd_"+c.getIdCredencial()+"'value='"+c.getCodigo()+"'/>"+c.getCodigo()+"</td>");
										out.println("<td class='col-md-2' style='text-align: center'>");
											out.println("<a class='icon-cross' style='color: red' onclick='deleteCredential("+c.getIdCredencial()+")'></a>");
										out.println("</td>");
									out.println("</tr>");
								}	
								out.println("</tbody>");
							out.println("</table>");
						out.println("</div>");
	
					out.println("</div>");
				out.println("</div>");
			out.println("</div>");
		} else {
			out.println("<thead>");
				out.println("<tr>");
					out.println("<th class='col-md-2'>id</th>");
					out.println("<th class='col-md-6'>Credencial</th>");
					out.println("<th class='col-md-2'>Codigo</th>");
					out.println("<th class='col-md-2' style='text-align: center'>Borrar</th>");
				out.println("</tr>");
			out.println("</thead>");
			out.println("<tbody>");
			for (CredencialDTO c : credenciales) {
				out.println("<tr>");
					out.println("<td class='col-md-2'><input type='hidden' id='id_"+c.getIdCredencial()+"' value=''/>"+c.getIdCredencial()+"</td>");
					out.println("<td class='col-md-4'><input type='hidden' id='cr_"+c.getCredencial()+"' value=''/>"+c.getCredencial()+"</td>");
					out.println("<td class='col-md-4'><input type='hidden' value=''/>"+c.getCodigo()+"</td>");
					out.println("<td class='col-md-2' style='text-align: center'>");
						out.println("<a class='icon-bin2' style='color: red' onclick='deleteCredential("+c.getIdCredencial()+")'></a>");
					out.println("</td>");
				out.println("</tr>");
			}	
			out.println("</tbody>");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Logger.getLogger(TAG).log(Level.INFO, "Llamada procedente del servicio {0}", req.getHeader("Referer"));
		doGet(req, resp);
	}

}
