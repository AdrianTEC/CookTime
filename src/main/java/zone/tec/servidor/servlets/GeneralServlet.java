package zone.tec.servidor.servlets;

import org.json.simple.JSONObject;
import zone.tec.servidor.clases.JSONManager;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GeneralServlet {


    public void getting(ServletContext context, HttpServletRequest req, HttpServletResponse resp, String arrayName) throws IOException {


        //Establezco el tipo de respuesta que voy a dar
        resp.setContentType("application/json");
        //El contexto es requerido para ubicar los archivos
        JSONManager manager = new JSONManager(context);

        if(req.getParameter("ID")!=null)
        { // /usersID=????
            JSONObject reponseUser= manager.giveMeObjetWithdId(arrayName,req.getParameter("ID"));

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
            resp.getWriter().write(String.valueOf(manager.giveMeJson(arrayName)));

        }



    }







}
