<%@ page import="zone.tec.servidor.clases.AlmacenDeEstructuras" %><%--
  Created by IntelliJ IDEA.
  User: Adrian
  Date: 29/06/2020
  Time: 08:02 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Servidor CookTime</title>
  </head>
  <body>
   Bienvenido al servidor de CookTime


   <button class="favorite styled"
           type="button"
           onclick = "function clickEvent() {
               <%


               %>
           }
        clickEvent()";
           name = "i">
       Hola
   </button>




  </body>


  <%
    AlmacenDeEstructuras.renovarArboles(getServletConfig().getServletContext());
  %>
</html>
