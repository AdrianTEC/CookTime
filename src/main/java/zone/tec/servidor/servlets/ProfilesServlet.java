package zone.tec.servidor.servlets;
import zone.tec.servidor.clases.JSONManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profiles")
public class ProfilesServlet extends HttpServlet {

    private JSONManager manager;


    /* This returns a Requested user or users list in JSON format
       @Author: Adrian Gonzalez
       @Version: 5/07/20
       @Params: HttpServlet Request and HttpServletResponse
       @Exeption: IOExeption
       @returns: nothing, but it will be able later
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        GeneralServlet getter= new GeneralServlet();
        getter.getting(getServletContext(),req,resp,"Profiles");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //aca tengo que agregar recetas



    }
}





