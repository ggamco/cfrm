<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="libs/security.jsp" %>
<%
    String vista = request.getParameter("view") != null ? request.getParameter("view") : "";
%>

<html>
    <head>
        <title>Panel de Administración</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <!--GOOGLE FONTS-->
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>
        <!--CSS-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.7.5/css/bootstrap-select.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<!--         <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.5/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" /> -->
        <link href="../css/style.css" rel="stylesheet" type="text/css"/>
        <link href="../css/fonts.css" rel="stylesheet" type="text/css"/>
        <link href="../css/pruebas.css" rel="stylesheet" type="text/css"/>

        <link rel="shortcut icon" href="../img/favicon.ico">
        <!--JS-->
        <script src="//code.jquery.com/jquery-latest.js"></script>

        <script src="../js/libs/bootstrap.min.js" type="text/javascript"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.5/js/bootstrap-dialog.min.js"></script>
        <script src="../js/libs/modernizr.js" type="text/javascript"></script>
        <script src="../js/libs/jquery.scrollTo.min.js" type="text/javascript"></script>
        <script src="../js/libs/jquery.localScroll.min.js" type="text/javascript"></script>
        <script src="../js/main.js" type="text/javascript"></script>
        <script src="../js/functions.js" type="text/javascript"></script>

    </head>
    <body>
        <!-- MODALES -->
        <div id="modal-card"></div>
        <div id="modal-info"></div>
        <!--FIN MODALES-->
        
        <div class="barra-superior">
            <div></div>

            <div class="avatar"><span class="glyphicon-plus"></span></div>
            <div class="usuario-nombre"><h5><%=nombre.toUpperCase()%> <%=apellido.toUpperCase()%></h5></div>
        </div>

        <div class="menu_bar tablet-menu">
            <a class="bt-menu"><span class="icon-menu"></span></a>
        </div>

        <div class="menu_bar">
            <a href="#" class="bt-menu"><span class="icon-menu"></span>LIGA F7 Rayo Majadahonda</a>
        </div>
        <!--AQUI COMIENZA EL MENU LATERAL-->

        <%@include file="libs/sideMenu.jsp" %><!--AQUI INCLUIMOS EL MENU LATERAL-->

        <div class="main-cont">
            <div class="main">
                <div class="gradient-container"></div>
                <div class="section-cont">
                    <%if (vista.equals("usuarios")) {%>
                        <%@include file="views/usuarios.jsp" %> <!-- Cargar otra vista segun caso-->
                    <%} else if (vista.equals("socio")) {%>
                        <%@include file="views/socios.jsp" %>
                    <%} else if (vista.equals("abonado")) {%>
                        <%@include file="views/abonados.jsp" %>
                    <%} else if (vista.equals("escuela")) {%>
                        <%@include file="views/escuela.jsp" %>
                    <%} else if (vista.equals("evento")) {%>
                        <%@include file="views/evento.jsp" %>
                    <%}%>
                </div>
            </div>
            <footer>
                <div class="footer-cont">
                    <div class="copyright">
                        <p>Copyright 2017. Todos los derechos reservados. | Powered by <a href="https://www.gmbdesign.es">gmbDESIGN</a></p>
                    </div>
                    <div class="contact-mail">
                        <p>Contacta con nosotros en: <a href="mailto:gustavo@gmbdesign.es">gustavo@gmbdesign.es</a></p>
                    </div>
                </div>                
            </footer>
        </div>
        <script src="../js/libs/ali-md5.js" type="text/javascript"></script>
        <script src="../js/libs/ali-sec.js" type="text/javascript"></script>
        <script src="../js/libs/jquery.serialize-object.js" type="text/javascript"></script>
        <script src="../js/functions.js" type="text/javascript"></script>
        <script src="../js/libs/modal.js" type="text/javascript"></script>
        
    </body>
</html>