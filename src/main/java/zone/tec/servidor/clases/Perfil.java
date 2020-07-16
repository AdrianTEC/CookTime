package zone.tec.servidor.clases;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Perfil {

          private String iD;
          private JSONArray myMenu;
          private JSONArray following;
          private JSONArray followers;
          private  JSONArray calificaciones;
    /*This funtion is the constructor of the class
     *@author Andrés Quirós Guzmán
     *@Version 21/06/2020
     * @param nothing
     */
    public Perfil()
        {
            myMenu=new JSONArray();
            followers=new JSONArray();
            following=new JSONArray();
            calificaciones=new JSONArray();




        }
    public Perfil(JSONObject x)
    {
        myMenu= (JSONArray) x.get("MyMenu");
        followers= (JSONArray) x.get("Followers");
        following= (JSONArray) x.get("Following");
        calificaciones= (JSONArray) x.get("calificaciones");




    }


}
