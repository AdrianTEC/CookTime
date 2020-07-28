package zone.tec.servidor.servlets;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import zone.tec.servidor.clases.JSONManager;
import zone.tec.servidor.clases.Perfil;
import zone.tec.servidor.clases.Receta;
import zone.tec.servidor.clases.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/profiles")
public class ProfilesServlet extends HttpServlet {

    private JSONManager manager;


    /** This returns a Requested user or users list in JSON format
       @Author: Adrian Gonzalez
       @Version: 5/07/20
       @Params: HttpServlet Request and HttpServletResponse
       @Exeption: IOExeption
       @returns: nothing, but it will be able later
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
        {
            //me tiene que llegar el perfil de usuario que me consultan
            GeneralServlet getter= new GeneralServlet();
            getter.getting(getServletContext(),req,resp,"Profiles");

        }
        /* Post va a servir para publicar recetas o a añadir seguidores o seguidos
            la request va a recibir un JSON que corresponde a una receta


            Usuario que pide el post será un parámetro
            Atributo que se busca cambiar

            TODOS LOS CAMBIOS AGREGACION O BORRADO DE CHUNCHES SON TRABAJADOS
            INDEPENDIENTEMENTE AL ALMACENDEESTRUCTURAS


            RECORDAR EN CASO DE HACER UN BORRADO DECIRLE AL ALMACENDEESTRUCTURAS QUE ESE OBJETO YA NO EXISTE
            PORQUE PUEDE DAR PROBLEMAS DE OBJETOS NULOS , REFERENCIAS PERDIDAS O NO SE PUEDE VISUALIZAR


            SEGURAMENTE  SERA CON ALMACEN.UPDATE()


         */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
        {
            /*Para hacer esta operación necesito hacer lo siguiente
             *
             * 1-saber quien hizo el post
             * 2-saber que quiere agregar el usuario
             * 3-agarrar el campo que pidio
             * 4-agregar el post al campo especificado
             */

            //1-saber quien fue, la request la realiza el cliente y este envía su ID
                    //confiar en que el usuario va a ser un buen cliente y va a mandar su ID
            //2-Saber que quiere
            String campo= req.getParameter("campo");



            //3- agarro el campo
            if(campo!=null) {
                JSONArray current= new JSONArray();
                JSONManager manager = new JSONManager(getServletContext());


                if (campo.equals("followers")) {
                    JSONObject usuario = manager.giveMeObjetWithdId("Profiles", req.getParameter("ID"));
                    current = (JSONArray) usuario.get("Followers");
                    current.add(req.getParameter("SECOND_ID"));

                }
                if (campo.equals("following")) {
                    JSONObject usuario = manager.giveMeObjetWithdId("Profiles", req.getParameter("ID"));
                    current = (JSONArray) usuario.get("Following");
                    current.add(req.getParameter("SECOND_ID"));
                    JSONObject followed=manager.giveMeObjetWithdId("Profiles", req.getParameter("SECOND_ID"));
                    ((JSONArray )followed.get("Followers")).add(req.getParameter("ID"));

                }





                manager.saveJSONfile();

            }



        }



}





