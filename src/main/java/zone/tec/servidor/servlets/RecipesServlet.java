package zone.tec.servidor.servlets;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import zone.tec.servidor.clases.*;
import zone.tec.servidor.clases.Estructuras.ArbolAVL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/recipes")
@SuppressWarnings("unchecked")
public class RecipesServlet extends HttpServlet {


    /** This returns a Requested recipe or recipe list in JSON format
       @Author: Adrian Gonzalez
       @Version: 5/07/20
       @Params: HttpServlet Request and HttpServletResponse
       @Exeption: IOExeption
       @returns: nothing, but it will be able later
     **/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
        { JSONManager xe= new JSONManager(getServletContext());
            if(req.getParameter("Nombre")!=null) {
                resp.getWriter().write(AlmacenDeEstructuras.getRecipes().lookForSome(req.getParameter("Nombre"), 15).toJSONString());
            }
            else if(req.getParameter("Id")!=null)
                {
                    resp.getWriter().write((xe.convertToJSON(AlmacenDeEstructuras.getRecipes().giveMebyID(req.getParameter("Id")).getElemento()).toString()));
                }
            else
            {
                GeneralServlet x= new GeneralServlet();
               // resp.getWriter().write("No se indicó ninguna especificación, se retorna todas las recetas");
                x.getting(getServletContext(),req,resp,"Recipes");
            }




        }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {

            JSONManager x= new JSONManager(getServletContext());
            x.giveMeJson("Users");
            x.saveJSONfile();

            AlmacenDeEstructuras.renovarArboles(AlmacenDeEstructuras.getContexto());
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

            //verifico que la receta tiene lo minimo para ingresar

            if(newJson.get("nombre") != null && newJson.get("autor")!=null &&newJson.get("instrucciones")!=null) {

                //Crea una nueva receta
                Receta nuevaReceta= new Receta(newJson);
                //Convierto esa receta en un JSON
                newJson = manager.convertToJSON(nuevaReceta);
                //agrego la nueva receta al arbol de recetas
                AlmacenDeEstructuras.getRecipes().insert(nuevaReceta);

                //referencio la id de la receta en MyMenu del usuario que realizó la peticion
                JSONObject usuario = AlmacenDeEstructuras.getUsers().findUserBynameAndID(req.getParameter("Nombre"),req.getParameter("Id"));
                JSONObject perfil= (JSONObject) usuario.get("perfil");
                JSONArray mymenu= (JSONArray) perfil.get("MyMenu");
                mymenu.add(nuevaReceta.getId());

                JSONObject user = manager.giveMeObjetWithdId("Users", req.getParameter("Id"));
                JSONObject current = (JSONObject) user.get("perfil");
                JSONArray myMenu=(JSONArray) current.get("MyMenu");
                myMenu.add(newJson.get("id"));

                //Agrego esos JSON a el array de recetas
                manager.addToArray("Recipes",newJson);
                manager.saveJSONfile();

                //Escribo lo que agregué en la página
                /*
                resp.setContentType("application/json");
                resp.getWriter().write(manager.giveMeJson("Recipes").toString());
                */
            }else { resp.getWriter().write("Esta Receta No es Agregable"); }
        } catch (ParseException e) { e.printStackTrace(); }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONManager manager= new JSONManager(AlmacenDeEstructuras.getContexto());
        ArbolAVL arbolAVL= AlmacenDeEstructuras.getRecipes();

        //para los likes y dislikes
        if(req.getParameter("Target").equals("opinion"))
            {
                Receta receta= (Receta) arbolAVL.giveMebyID(req.getParameter("Id")).getElemento();

                if(req.getParameter("DATA").equals("1"))
                    {
                            //modifico la receta en el árbol
                            receta.setLikes(String.valueOf(Integer.parseInt(receta.getLikes())+1));

                            JSONObject object= manager.giveMeObjetWithdId("Recipes",req.getParameter("Id"));

                            object.put("likes",String.valueOf(Integer.parseInt((String) object.get("likes"))+1));

                    }
                else
                    {
                        receta.setDislikes(String.valueOf(Integer.parseInt(receta.getDislikes())+1));
                        JSONObject object= manager.giveMeObjetWithdId("Recipes",req.getParameter("Id"));
                        object.put("dislikes",String.valueOf(Integer.parseInt((String) object.get("dislikes"))+1));
                    }
                manager.saveJSONfile();



            }
        //para agregar comentarios
        if(req.getParameter("Target").equals("comentarios"))
            {
                Receta receta= (Receta) arbolAVL.giveMebyID(req.getParameter("Id")).getElemento();
                Comentario nuevocomentario= new Comentario(req.getParameter("DATA"));
                JSONObject comment= new JSONManager(getServletContext()).convertToJSON(nuevocomentario);
                receta.getComentarios().add(comment);



            }
        resp.getWriter().write("se ha enviado la peticion");







    }
}
