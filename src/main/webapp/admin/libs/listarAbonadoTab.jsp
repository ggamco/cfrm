<%@page import="com.gmbdesign.cfrm.bussiness.ICredentialCard"%>
<%@page import="com.gmbdesign.cfrm.impl.SocioDAOImpl"%>
<%@page import="com.gmbdesign.cfrm.dao.SocioDAO"%>
<%@page import="com.gmbdesign.cfrm.dto.SocioDTO"%>
<%@page import="java.util.List"%>
<%
    SocioDAO socioDAO = new SocioDAOImpl();
    List<SocioDTO> listaSociosNoImpresos = socioDAO.buscarSociosNotPrinted();
%>
<div class="alert-container">
    <%if(listaSociosNoImpresos.size() > 0){%>
    <div class="alert fade in out alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">�</span>
        </button>
        <strong>�ATENCI�N!.</strong> En la Base de Datos se han encontrado carnets sin imprimir. Puedes <a class="alert-link" href="#" onclick="callServer('ListarAbonados', 'aviso')">'LISTAR'</a> o <a target="_blank" class="alert-link" href="../GestorImpresionCarnets?imprimir=all">'IMPRIMIR'</a>
        <form id="aviso"><input type="hidden" name="busqueda" value="notPrinted"/></form>
    </div>
    <%}%>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Busqueda de Abonados</h3>
    </div>
    <div class="panel-body">
        <form class="form-horizontal" style="margin-top: 12px;" id="busqueda">
            <div class="form-group container-fluid">
                <div class="col-sm-3 col-md-3 col-lg-3">
                    <select class="form-control" name="busqueda" onchange="activaForm();" id="selector-busqueda">
                        <option value="nombre">Nombre o apellidos</option>
                        <option value="numero">N�mero de abonado</option>
                        <option value="rango">Rango de abonados</option>
                        <option value="notPrinted">No Impresos</option>
                    </select>
                </div>
                <div class="col-sm-4 col-md-4 col-lg-4">
                    <input class="form-control" type="text" name="param1" id="param1" placeholder="Nombre o apellido"/>
                </div>
                <div class="col-sm-3 col-md-3 col-lg-3">
                    <div class="col-sm-5 col-md-5 col-lg-5">
                        <input disabled class="form-control" type="text" name="param2" id="param2" placeholder="N�"/>
                    </div>
                    <div class="col-sm-2 col-md-2 col-lg-2" style="text-align: center;">
                        <label class="control-label">AL</label>
                    </div>
                    <div class="col-sm-5 col-md-5 col-lg-5">
                        <input disabled class="form-control" type="text" name="param3" id="param3" placeholder="N�"/>
                    </div>
                </div>
                <div class="col-sm-2 col-md-2 col-lg-2">
                    <button class="btn btn-primary btn-block" type="button" onclick="callServer('ListarAbonados', 'busqueda')">Buscar</button>
                </div>
            </div>
        </form>
    </div>
</div>
<table class="table table-hover listaAbonados">
    
</table>