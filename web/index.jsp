<%@ page import="zone.tec.servidor.clases.AlmacenDeEstructuras" %><%--
  Created by IntelliJ IDEA.
  User: Adrian
  Date: 29/06/2020
  Time: 08:02 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<h2>Bienvenido a Cooktime&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<img style="font-size: 14px;" src="https://fotos.subefotos.com/a6220e18712c409b73ee7a9db391a04bo.jpg" alt="" width="111" height="111" /></h2>
<hr /><hr />
<p style="font-size: 1.0em;">Puede visualizar las peticiones chef aqui</p>
<form action="Admin.jsp">

  <input type="submit" value="Admin">

</form>
<p style="font-size: 1.5em;">&nbsp;</p>
<hr /><hr />
<p style="font-size: 1.5em;">&nbsp;</p>



  <%
    AlmacenDeEstructuras.renovarArboles(getServletConfig().getServletContext());
  %>
</html>
