<%@ page import="java.util.logging.ConsoleHandler" %>
<%@ page import="zone.tec.servidor.clases.AlmacenDeEstructuras" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.Console" %>
<%@ page import="java.util.concurrent.TimeUnit" %><%--
  Created by IntelliJ IDEA.
  User: Adrian
  Date: 16/07/2020
  Time: 08:58 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>ControlDeUsuarios</title>
</head>
<body>

<%!int actualID;%>
<%!int peticiones;%>
<%!String usuario;%>
<%!HttpSession sesion;%>

<%


     sesion = request.getSession();

    try {
        response.setIntHeader("Refresh", 3);
        peticiones =AlmacenDeEstructuras.getPeticionesChef().size();
        usuario= (String) AlmacenDeEstructuras.getPeticionesChef().get(0);
    }catch (Exception e)
        {usuario= "No hay Peticiones";}

%>


Peticiones:<%= peticiones%>
Usuario: <%= usuario%>
abc: <%=sesion.getAttribute("abc") %>

<form action="${pageContext.request.contextPath}/admin" method="post">
    <input type="submit" name="button1" value="Button 1" />
    <input type="submit" name="button2" value="Button 2" />
    <input type="submit" name="button3" value="Button 3" />
</form>


</body>
</html>
