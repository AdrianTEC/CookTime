package zone.tec.servidor.servlets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import zone.tec.servidor.clases.RecetaManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/recetas")
public class RecetasServlet extends HttpServlet {
    private RecetaManager manager;

    /* This returns a Requested company or companies list in JSON format
       @Author: Andrés Quirós
       @Version: 7/7/20
       @Params: HttpServlet Request and HttpServletResponse
       @Exeption: IOExeption
       @returns: nothing
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        manager= new RecetaManager(getServletContext());   //El contexto es requerido para ubicar los archivos

        if(req.getParameter("ID")!=null)
        {

            resp.getWriter().write(String.valueOf(manager.giveMeReceta(req.getParameter("ID"))));
            //resp.getWriter().write("la id solicitada fue :"+req.getParameter("ID"));

        }
        else
        {
            resp.getWriter().write(String.valueOf(manager.giveMeJson()));

        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder agregado= new StringBuilder();
        manager= new RecetaManager(getServletContext());   //El contexto es requerido para ubicar los archivos
        BufferedReader x=req.getReader();
        String line= x.readLine();
        while (line!=null)
        {
            //resp.getWriter().write(line);
            agregado.append(line);
            line=x.readLine();
        }
        JSONParser parser = new JSONParser();
        try {
            JSONObject newJson= (JSONObject) parser.parse(String.valueOf(agregado));
            manager.addToArray(newJson);
            // manager.refreshArray();
            //   manager.saveJSONfile();
            resp.setContentType("application/json");
            resp.getWriter().write(manager.giveMeJson().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}