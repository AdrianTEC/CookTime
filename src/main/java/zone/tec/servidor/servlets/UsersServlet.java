package zone.tec.servidor.servlets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import zone.tec.servidor.clases.AlmacenDeEstructuras;
import zone.tec.servidor.clases.JSONManager;
import zone.tec.servidor.clases.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/users")

@SuppressWarnings("unchecked")
public class UsersServlet extends HttpServlet {


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

           if(req.getParameter("Nombre")!=null)
            {
                resp.getWriter().write(AlmacenDeEstructuras.getUsers().lookForSome(req.getParameter("Nombre"), 15).toJSONString());
            }
           else
                {
                    GeneralServlet x= new GeneralServlet();
                    x.getting(getServletContext(),req,resp,"Users");
                }


        }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
        {
            //String que se va a armar
            StringBuilder agregado= new StringBuilder();

            //El contexto es requerido para ubicar los archivos
            JSONManager manager = new JSONManager(getServletContext());

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

                //verifico que usuario tiene lo minimo para ingresar

                if(newJson.get("nombre") != null && newJson.get("contrasena")!=null &&newJson.get("edad")!=null && newJson.get("correo")!=null&& newJson.get("apellido1")!=null&&newJson.get("apellido2")!=null)
                    {   //Creo un nuevo usuario

                        Usuario nuevoUsuario= new Usuario(newJson);
                        AlmacenDeEstructuras.getUsers().insert( nuevoUsuario,false);

                        if(!AlmacenDeEstructuras.have_this(nuevoUsuario.getCorreoElectronico())){
                            manager.addToArray("Users",newJson);
                            manager.saveJSONfile();
                            AlmacenDeEstructuras.addMail(nuevoUsuario.getCorreoElectronico());
                            resp.setContentType("application/json");
                            resp.getWriter().write("Se ha inscrito correctamente");
                        }
                        else {
                            resp.getWriter().write("El correo ingresado es utilizado actualmente por otro usuario");
                        }

                        //Agrego esos JSON al JSONFILE


                        //Escribo lo que agregué en la página
                        /*

                        resp.getWriter().write(manager.giveMeJson("Profiles").toString());
                        */
                    }
                else { resp.getWriter().write("Este Usuario No es Agregable"); }
            } catch (ParseException e) { e.printStackTrace(); }
        }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
            //para cambiar una característica de un usuario ocupo identificarlo
            //la manera easy pisy lemon squicy sería identificar en usuario con la ID
            //por lo tanto es recomendable tener un ABB que funcione con IDs
            //hecho lo anterior lo primero que ocupo hacer es recoger el parámetro ID y buscarlo en el árbol correspondiente
             JSONObject user=AlmacenDeEstructuras.getUsersPorID().lookForOneForID(Integer.parseInt(req.getParameter("Id")));
             //cambio el dato
             user.put(req.getParameter("Target"),req.getParameter("Value"));
            //Ahora, si cambié algo en el usuario así debe ser en el perfil
             JSONObject profile= (JSONObject) user.get("perfil");
             try {
                 profile.put(req.getParameter("Target"),req.getParameter("Value"));
             }
             catch (Exception ignored){}
            //posteriormente ocupo editarlo en el JSON para esto lo debo de encontrar
            JSONManager x= new JSONManager(getServletContext());
            JSONObject userJSON=x.giveMeObjetWithdId("Users",req.getParameter("Id"));
            //remplazo el valor en el archivo de texto
            userJSON.put(req.getParameter("Target"),req.getParameter("Value"));
            JSONObject perfil= (JSONObject) userJSON.get("perfil");
            try {
                perfil.put(req.getParameter("Target"),req.getParameter("Value"));
            }
            catch (Exception ignored){}

            x.saveJSONfile();
            AlmacenDeEstructuras.getPeticionesChef().add(userJSON.get("id"));
            resp.getWriter().write("se ha recibido una petición");

        }
}
