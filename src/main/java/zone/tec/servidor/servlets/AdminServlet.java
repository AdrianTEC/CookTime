package zone.tec.servidor.servlets;

import org.json.simple.JSONObject;
import zone.tec.servidor.clases.AlmacenDeEstructuras;
import zone.tec.servidor.clases.JSONManager;

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
    public void Hola(int i)
        {
            AlmacenDeEstructuras.getUsers().lookForOneForID(i).put("chef","322222222222222222222222");
            JSONManager x= new JSONManager(getServletContext());
            JSONObject userJSON=x.giveMeObjetWithdId("Users",String.valueOf(i));
            //remplazo el valor en el archivo de texto
            userJSON.put("chef","3222222222222222222222222");
            x.saveJSONfile();
        }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion =request.getSession();
        if (request.getParameter("button1") != null) {
            sesion.setAttribute("abc","1111");

        } else if (request.getParameter("button2") != null) {
            sesion.setAttribute("abc","2222");

        } else if (request.getParameter("button3") != null) {

        }
        response.sendRedirect("/CookTime_war_exploded/Admin.jsp?");
    }
}
