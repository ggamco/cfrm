<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,100' rel='stylesheet' type='text/css'>
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <link href="css/fonts.css" rel="stylesheet" type="text/css"/>
        <link href="css/animate.css" rel="stylesheet" type="text/css"/>
        <title>Inicio de Sesión</title>
    </head>
    <body>
        <div class="main">
            <div class="table">
                <div class="table-cell">
                    <img src="img/EscudoNeW_LINE.png" alt=""/>
                    <br>
                    <label class="label-login">Introduce tus datos de registro:</label>
                    <form action="../SendMailPass" method="POST">

                        <div class="login-box">
                            <input type="text" name="usuario" placeholder="Usuario" required/>

                            <input id="correo" type="text" placeholder="Correo electrónico" name="email" required/>
                        </div>
                        <button type="submit" class="btn">
                            <i><span class="icon-circle-right"></span></i>
                        </button>
                    </form>
                    <label class="label-login pass">¿Ya estas registrado? <a href="./">Introduce tus datos.</a></label>
                    <% if (request.getParameter("error") != null) { %>
                    <% if(request.getParameter("error").equals("error")){%>
                    <p class="animated shake error">Usuario y Correo no coinciden.</p>
                    <%}else if(request.getParameter("error").equals("ok")){%>
                    <p class="not-error">Recibirás instrucciones en tu Correo.</p>
                    <%}else if(request.getParameter("error").equals("exist")){%>
                    <p class="animated shake error">Solo una petición cada 24 horas.</p>
                    <%}}%>
                </div>
            </div>
        </div>
        <footer>
            <div class="separator">
                <div class="copyright">
                    <p>Copyright 2017. Todos los derechos reservados. | Powered by <a href="https://www.gmbdesign.es">gmbDESIGN</a></p>
                </div>
                <div class="contact-mail">
                    <p>Contacta con nosotros en: <a href="mailto:gustavo@gmbdesign.es">gustavo@gmbdesign.es</a></p>
                </div>
                <div class="copy-media">
                    <p></p>
                </div>
            </div>                

        </footer>
    </body>

    <script type="text/javascript">
        
        window.addEventListener("load", function () {
            // Set a timeout...
            setTimeout(function () {
                // Hide the address bar!
                window.scrollTo(0, 1);
            }, 0);
        });

    </script>


</html>
