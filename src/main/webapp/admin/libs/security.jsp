<%@page import="com.gmbdesign.cfrm.dto.UsuarioDTO"%>

<%
    //FICHERO PARA COMPROBAR SI TENEMOS UNA SESSION ACTIVA, 
    //SINO TENEMOS SESSION ACTIVA, PROBLEMA DE SEGURIDAD, REDIRECCIONAMOS A INDEX
    //SI TENEMOS SESSION ACTIVA, NO HACEMOS NADA, TODO OK.

    String nombre = "", apellido = "", role = "";

    UsuarioDTO user = (UsuarioDTO) session.getAttribute("usuarioLogueado");
    if (user != null) {
        if (user.getDatos() != null) {
            nombre = user.getDatos().getNombre();
            apellido = user.getDatos().getPrimerApellido();
            role = user.getRole();
        }
    } else {
        response.sendRedirect("../");
    }
%>