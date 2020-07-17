package zone.tec.servidor.servlets;

import org.json.simple.JSONObject;
import zone.tec.servidor.clases.AlmacenDeEstructuras;

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

    protected  void  clickEvent(){

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Admin.jsp");
        //dispatcher.forward(req,resp);

        resp.setContentType("text/html;charset=UTF-8");
        for (Object i:AlmacenDeEstructuras.getPeticionesChef()){
            resp.getWriter().print(i);
            resp.getWriter().print("<button name=\"a\"  onclick=\"\n" +
                    "\n" +
                    "        <%AlmacenDeEstructuras.getUsers().lookForOneForID(Integer.parseInt("+i+")).put('chef', '2');%> \n" +
                    "      \"> HOLA </button>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
