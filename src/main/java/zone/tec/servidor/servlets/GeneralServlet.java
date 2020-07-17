package zone.tec.servidor.servlets;

import org.json.simple.JSONObject;
import zone.tec.servidor.clases.JSONManager;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GeneralServlet {

    private JSONObject reponseUser;

    public GeneralServlet(){

        reponseUser= new JSONObject();
    }
    public JSONObject getConsultedUser(){
        return reponseUser;
    }
    public void getting(ServletContext context, HttpServletRequest req, HttpServletResponse resp, String treeName) throws IOException {


        //Establezco el tipo de respuesta que voy a dar
        resp.setContentType("application/json");
        //El contexto es requerido para ubicar los archivos
        JSONManager manager = new JSONManager(context);

        if(req.getParameter("ID")!=null)
        {
             reponseUser= manager.giveMeObjetWithdId(treeName,req.getParameter("ID"));

            if(req.getParameter("DATA")!=null)  //si hay una petición en especifico
                {
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
        {
            resp.getWriter().write(String.valueOf(manager.giveMeJson(treeName)));

        }



    }







}
