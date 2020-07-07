package zone.tec.servidor.servlets;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import zone.tec.servidor.clases.JSONManager;
import zone.tec.servidor.clases.Usuario;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
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
        resp.setContentType("application/json");
        manager= new JSONManager(getServletContext());   //El contexto es requerido para ubicar los archivos

        if(req.getParameter("ID")!=null)
            { // /usersID=????
                JSONObject reponseUser= manager.giveMeObjetWithdId(req.getParameter("ID"));

                if(req.getParameter("DATA")!=null)  //si hay una petición en especifico
                    {// /users?ID=????&DATA=????
                        String peticion= req.getParameter("DATA");
                        if(reponseUser.get(req.getParameter("DATA"))!=null)
                        {
                            resp.getWriter().write((reponseUser.get(peticion)).toString());
                        }
                        else {
                            resp.getWriter().write("El parámetro ingresado es inválido");
                        }
                    }


                else
                    {
                        resp.getWriter().write(String.valueOf(reponseUser));
                    }


            //resp.getWriter().write("la id solicitada fue :"+req.getParameter("ID"));

         }
        else
            { //users?ID=?
                resp.getWriter().write(String.valueOf(manager.giveMeJson()));

            }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder agregado= new StringBuilder();        //String que se va a armar
        manager= new JSONManager(getServletContext());   //El contexto es requerido para ubicar los archivos
        BufferedReader x=req.getReader();// esto es para leer el JSON
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
            //verifico que usuario tiene lo minimo para ingresar
            if(newJson.get("nombre") != null && newJson.get("contrasena")!=null &&newJson.get("edad")!=null && newJson.get("correo")!=null&& newJson.get("apellido1")!=null&&newJson.get("apellido2")!=null)
                {
                    //Agrego el usuario a el txt
                    Usuario nuevoUsuario= new Usuario(newJson);
                    newJson = nuevoUsuario.convertToJSON();
                    manager.addToArray(newJson);
                    manager.saveJSONfile();

                    //Escribo lo que agregué en la página
                    resp.setContentType("application/json");
                    resp.getWriter().write(manager.giveMeJson().toString());

                }
            else{
                resp.getWriter().write("Este Usuario No es Agregable");
               // resp.getWriter().write(String.valueOf(newJson.get("nombre")+String.valueOf(newJson.get("contrasena"))));
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
