package zone.tec.servidor.servlets;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import zone.tec.servidor.clases.Empresa;
import zone.tec.servidor.clases.JSONManager;
import zone.tec.servidor.clases.Receta;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/recipes")
public class RecipesServlet extends HttpServlet {
    private JSONManager manager;


    /* This returns a Requested user or users list in JSON format
       @Author: Adrian Gonzalez
       @Version: 5/07/20
       @Params: HttpServlet Request and HttpServletResponse
       @Exeption: IOExeption
       @returns: nothing, but it will be able later
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        GeneralServlet getter= new GeneralServlet();
        getter.getting(getServletContext(),req,resp,"Recipes");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        //String que se va a armar
        StringBuilder agregado= new StringBuilder();

        //El contexto es requerido para ubicar los archivos
        manager= new JSONManager(getServletContext());

        //Lector del mensaje HTTP, lee la primera linea
        BufferedReader x=req.getReader();// esto es para leer el JSON
        String line= x.readLine();

        while (line!=null)
        {
            agregado.append(line);
            line=x.readLine();
        }
        try {
            JSONParser parser = new JSONParser();
            JSONObject newJson= (JSONObject) parser.parse(String.valueOf(agregado));

            //verifico que la receta tiene lo minimo para ingresar

            if(newJson.get("nombre") != null && newJson.get("autor")!=null &&newJson.get("instrucciones")!=null) {
                //Crea una nueva receta
                Receta nuevaReceta= new Receta(newJson);
                //Convierto esa receta en un JSON
                newJson = manager.convertToJSON(nuevaReceta);


                //Agrego esos JSON a el array de recetas
                manager.addToArray("Recipes",newJson);
                manager.saveJSONfile();

                //Escribo lo que agregué en la página
                resp.setContentType("application/json");
                resp.getWriter().write(manager.giveMeJson("Recipes").toString());

            }else { resp.getWriter().write("Esta Receta No es Agregable"); }
        } catch (ParseException e) { e.printStackTrace(); }
    }
}
