<%@page import="com.gmbdesign.cfrm.bussiness.ECredentialType"%>
<jsp:include page="/AuthService" />
<%@include file="../libs/dataFromDB.jsp" %>

<%String authCode = (String) session.getAttribute("systemCode");%>
<div class="container-header">
    <div class="container container-title">
        <div class="tittle-sect-cont">
            <div class="big-text">
                <h2>Gestion Socios</h2>
            </div>
        </div>
        <div class="tab-nav">
            <ul class="nav nav-tabs nav-justified" role="tablist">
                <li role="presentation" class="active">
                    <a href="#registro" aria-controls="registro" role="presentation" data-toggle="tab" aria-expanded="true">Nuevo Socio</a>
                </li>
                <li role="presentation">
                    <a href="#list" aria-controls="list" role="presentation" data-toggle="tab" aria-expanded='false'>Listar Socios</a>
                </li>
            </ul>
        </div>
    </div>
</div><!--CABECERA CON NAVEGADOR TABS-->
<div class="container">
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="registro">
            <%@include file="../libs/crearSocioTab.jsp" %>
        </div>
        <div role="tabpanel" class="tab-pane" id="list">
            <%@include file="../libs/listarSocioTab.jsp" %>
        </div>
    </div>
</div>
