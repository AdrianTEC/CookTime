package zone.tec.servidor.servlets;

import org.json.simple.JSONObject;
import zone.tec.servidor.clases.AlmacenDeEstructuras;
import zone.tec.servidor.clases.JSONManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")

public class AdminServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Admin.jsp");
        //dispatcher.forward(req,resp);
        req.getRequestDispatcher("/WEB-INF/Admin.jsp").forward(req, resp);

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
