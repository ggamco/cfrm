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
                    <label class="label-login">Introduce tus datos de acceso:</label>
                    <form action="Login" method="POST" id="login">

                        <div class="login-box">
                            <input type="text" placeholder="Usuario" name="plainUser" required/>
                            <input type="password" placeholder="Contraseña" id="password" required/>

                            <input type="hidden" name="cryptoPass" id="cryptoPass" />
                        </div>
                        <button type="submit" class="btn">
                            <i><span class="icon-circle-right"></span></i>
                        </button>
                    </form>
                    <label class="label-login pass">Olvidaste tus datos de acceso. <a href="recupera.jsp">Recuperar Datos</a></label>
                    <% if (request.getParameter("error") != null) { %>
                    <%if (request.getParameter("error").equals("code01")) {%>
                    <p class="animated shake error">Usuario o contraseña incorrectos</p>
                    <%} else if (request.getParameter("error").equals("code02")) {%>
                    <p class="animated shake error">Se produjo un error grave. Contacte con soporte.</p>
                    <%} else if (request.getParameter("error").equals("")) {%>
                    <p class="animated shake error">Hubo un error desconocido.</p>
                    <%} else {%>
                    <p class="not-error">Cambio de contraseña correcto.</p>
                    <%}
                        }%>
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
        <script src="//code.jquery.com/jquery-latest.js"></script>
        <script src="js/libs/ali-sha256.js" type="text/javascript"></script>
        <script src="js/libs/ali-md5.js" type="text/javascript"></script>
        <script src="js/libs/ali-sec.js" type="text/javascript"></script>
        <script type="text/javascript">

            window.addEventListener("load", function () {
                // Set a timeout...
                setTimeout(function () {
                    // Hide the address bar!
                    window.scrollTo(0, 1);
                }, 0);
            });

        </script>

    </body>

</html>
