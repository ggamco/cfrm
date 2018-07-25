<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.gmbdesign.cfrm.database.Manager"%>
<%
    String arg = request.getParameter("view");
    String sql = "SELECT MAX(numero) AS ultimo FROM";
    Integer numero = 0;
    
    Manager manager = Manager.getInstance();
    ResultSet last = manager.prepareSelect(sql.concat(" ").concat(arg));
    
    if(last != null && last.first()){
        numero = last.getInt("ultimo");
        Logger.getLogger(Manager.class.getName()).log(Level.INFO, "El último socio inscrito es el número {0}.", numero);
    }
    
    numero = numero + 1;
%>