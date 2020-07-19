package zone.tec.servidor.servlets;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import zone.tec.servidor.clases.AlmacenDeEstructuras;

import zone.tec.servidor.clases.JSONManager;
import zone.tec.servidor.clases.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin")

public class AdminServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Admin.jsp");
        //dispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion =request.getSession();
        if (request.getParameter("Siguiente") != null) {
            if((Integer)sesion.getAttribute("ActualID")<(Integer)AlmacenDeEstructuras.getPeticionesChef().size() -1){
                sesion.setAttribute("ActualID", (Integer) sesion.getAttribute("ActualID") +1);

            }

        } else if (request.getParameter("Anterior") != null) {
            if((Integer)sesion.getAttribute("ActualID")>0) {
                sesion.setAttribute("ActualID", (Integer) sesion.getAttribute("ActualID") - 1);
            }

        } else if (request.getParameter("Aprobar") != null) {
            JSONArray peticionesArray=AlmacenDeEstructuras.getPeticionesChef();

            JSONObject user = AlmacenDeEstructuras.getUsersPorID().lookForOneForID(peticionesArray.get((Integer) sesion.getAttribute("ActualID")).toString());;
            //cambio el dato
            user.put("chef","2");

            JSONManager x = new JSONManager(getServletContext());
            JSONObject userJSON = x.giveMeObjetWithdId("Users", String.valueOf( peticionesArray.get((Integer) sesion.getAttribute("ActualID"))));
            //remplazo el valor en el archivo de texto
            userJSON.put("chef","2");


            x.saveJSONfile();

        }
        else if (request.getParameter("Rechazar") != null) {

            if (AlmacenDeEstructuras.getPeticionesChef().size() == 1){
                JSONArray peticionesArray = AlmacenDeEstructuras.getPeticionesChef();
                peticionesArray.remove(peticionesArray.get((Integer) sesion.getAttribute("ActualID")));
                sesion.setAttribute("ActualID", null);


            }
            else if (AlmacenDeEstructuras.getPeticionesChef().size() > 1){
                JSONArray peticionesArray = AlmacenDeEstructuras.getPeticionesChef();
                peticionesArray.remove(peticionesArray.get((Integer) sesion.getAttribute("ActualID")));
                sesion.setAttribute("ActualID", (Integer) sesion.getAttribute("ActualID") - 1);
            }
            else {
                sesion.setAttribute("ActualID", (Integer) sesion.getAttribute("ActualID") - 1);
                sesion.setAttribute("ActualID", null);

            }

        }


        response.sendRedirect("/CookTime_war_exploded/Admin.jsp?");
    }
}
