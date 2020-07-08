package zone.tec.servidor.clases;

import Estructuras.ListaLineal;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.image.BufferedImage;

public class Perfil {

          private String iD;
          private JsonArray myMenu;
          private JsonArray following;
          private JsonArray followers;
          private  String calificaciones;
    /*This funtion is the constructor of the class
     *@author Andrés Quirós Guzmán
     *@Version 21/06/2020
     * @param nothing
     */
    public Perfil(String ID)
        {
            iD= ID;
            myMenu=new JsonArray();
            followers=new JsonArray();
            following=new JsonArray();
            calificaciones="[]";




        }



}
