<%@ page import="java.util.logging.ConsoleHandler" %>
<%@ page import="zone.tec.servidor.clases.AlmacenDeEstructuras" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.Console" %>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="zone.tec.servidor.clases.Usuario" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.JSONArray" %><%--
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

<%!String puntero;%>
<%!String peticiones;%>
<%!String username;%>

<%!HttpSession sesion;%>
<%!JSONObject usuario;%>
<%!String  correo;%>
<%!String  nombre;%>


<%


     sesion = request.getSession();
    if(sesion.getAttribute("ActualID")==null)
        {
            sesion.setAttribute("ActualID",0);
            username= "";
            nombre="";
            correo="";
            peticiones="-";
            usuario=new JSONObject();
            puntero="-";
        }
    try {
        response.setIntHeader("Refresh", 3);//refresco página

        peticiones =String.valueOf(AlmacenDeEstructuras.getPeticionesChef().size());//tomo la cantidad de peticiones

        JSONArray peticionesArray=AlmacenDeEstructuras.getPeticionesChef(); //tomo la lista de posiciones

        String userID=  peticionesArray.get((Integer) sesion.getAttribute("ActualID")).toString(); //tomo la id

        usuario= AlmacenDeEstructuras.getUsers().lookForOneForID(userID);//busco el usuario con esa id
        username= userID;

        correo= (String) usuario.get("correo");//tomo el correo del usuario
        nombre= (String) usuario.get("nombre");//tomo el nombre del usuario

        puntero= String.valueOf( (Integer) sesion.getAttribute("ActualID") +1  );
    }catch (Exception ignored)
        {
        }

%>


Peticiones:<%= peticiones%> / noPetición: <%=puntero%>
<h1></h1>
<h1></h1>
<h1></h1>
<h1> Usuario: <%= username%> </h1>
<h1></h1>
     Correo :<%=correo %>
<h1></h1>
     Nombre :<%=nombre  %>

<h1></h1>
<%--- Si algo se le visualiza mal use esto
        Json:  <%= usuario.toJSONString()%>>
        ---%>



<form action="${pageContext.request.contextPath}/admin" method="post">
    <input type="submit" name="Anterior" value="Anterior" />
    <input type="submit" name="Siguiente" value="Siguiente" />
    <input type="submit" name="Aprobar" value="Aprobar" />
    <input type="submit" name="Rechazar" value="Rechazar" />

</form>


</body>
</html>
