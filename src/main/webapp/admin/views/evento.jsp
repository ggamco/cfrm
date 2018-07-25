<%@page import="com.gmbdesign.cfrm.bussiness.ECredentialType"%>
<jsp:include page="/AuthService" />
<%@include file="../libs/dataFromDB.jsp" %>

<%String authCode = (String) session.getAttribute("systemCode");%>
<div class="container-header">
    <div class="container container-title">
        <div class="tittle-sect-cont">
            <div class="big-text">
                <h2>Gestion Eventos</h2>
            </div>

        </div>

        <div class="tab-nav">
            <ul class="nav nav-tabs nav-justified" role="tablist">
                <li role="presentation" class="active">
                    <a href="#registro" aria-controls="registro" role="tab" data-toggle="tab" aria-expanded="true">Nuevo Evento</a>
                </li>
                <li role="presentation">
                    <a href="#listado" aria-controls="listado" role="tab" data-toggle="tab" aria-expanded='false'>Listar Eventos</a>
                </li>
            </ul>
        </div>
    </div>
</div><!--CABECERA CON NAVEGADOR TABS-->
<div class="container">

    <div class="tab-content">

        <div role="tabpanel" class="tab-pane active" id="registro">
            <div class="alert-container"></div>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form class="form-horizontal" id="evento">
                        <div class="form-group container-fluid" id="form-user">
                            <label for="jornada" class="col-sm-2 col-md-2 col-lg-2 control-label">Jornada:</label>
                            <div class="col-sm-2 col-md-2 col-lg-">
                                <input type="number" name="numeroJornada" value="" class="form-control" id="jornada" aria-describedby="inputSuccess2Status">
                            </div>
                            <label for="torneo" class="col-sm-2 col-md-2 col-lg-2 control-label">Codigo sistema:</label>
                            <div class="col-sm-6 col-md-4 col-lg-6">
                                <input type="text" name="torneo" value="" class="form-control" id="torneo" aria-describedby="inputSuccess2Status" style="text-transform:uppercase">
                            </div>
                        </div>
                        <div class="form-group container-fluid" id="form-name">
                            <label for="fecha" class="col-sm-2 col-md-2 col-lg-2 control-label">Fecha:</label>
                            <div class="col-sm-4 col-md-4 col-lg-4">
                                <input type="date" name="fecha" value="" class="form-control" id="fecha" aria-describedby="inputSuccess2Status">
                            </div>
                            <label for="hora" class="col-sm-2 col-md-2 col-lg-2 control-label">Hora:</label>
                            <div class="col-sm-4 col-md-4 col-lg-4">
                                <input type="text" name="hora" value="" class="form-control" id="hora" aria-describedby="inputSuccess2Status" placeholder="12:00">
                            </div>
                        </div>
                        <div class="form-group container-fluid">
                            <label for="visitante" class="col-sm-2 col-md-2 col-lg-2 control-label">Visitante:</label>
                            <div class="col-sm-10 col-md-10 col-lg-10">
                                <input type="text" name="visitante" class="form-control" id="visitante" aria-describedby="inputSuccess2Status" placeholder="">
                            </div>
						</div>
						<div class="form-group container-fluid">
                            <label for="tarifa" class="col-sm-2 col-md-2 col-lg-2 control-label">Tarifa:</label>
                            <div class="col-sm-4 col-md-4 col-lg-4">
                                <input type="text" name="listaTarifas[1][]" class="form-control" id="tarifa">
                            </div>
                            <div class="col-sm-2 col-md-2 col-lg-2">
                                <input type="text" name="listaTarifas[1][]" class="form-control" id="letra" style="text-transform:uppercase">
                            </div>
							<div class="col-sm-2 col-md-2 col-lg-2">
                                <input type="text" name="listaTarifas[1][]" class="form-control" id="letra" style="text-transform:uppercase">
                            </div>
							<div class="col-sm-2 col-md-2 col-lg-2">
                                <button onclick="" type="button" class="btn btn-danger btn-block" id="form-sub" >Agregar otra</button>
                            </div>
                        </div>

                        <div class="form-group container-fluid">
                            <label for="activo" class="col-sm-8 col-md-8 col-lg-8 control-label"></label>
                            <div class="col-sm-4 col-md-4 col-lg-4">
                                <button onclick="callServerWithValidation('RegistroEvento', 'evento')" type="button" class="btn btn-primary btn-lg btn-block" id="form-sub" >Registrar Evento en el sistema</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>

        </div>
        <div role="tabpanel" class="tab-pane" id="list">
            <%@include file="../libs/listarSocioTab.jsp" %>
        </div>
    </div>
</div>