package zone.tec.servidor.clases;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Perfil {

          private String iD;
          private JSONArray MyMenu;
          private JSONArray Following;
          private JSONArray Followers;
          private  JSONArray calificaciones;
    /*This funtion is the constructor of the class
     *@author Andrés Quirós Guzmán
     *@Version 21/06/2020
     * @param nothing
     */
    public Perfil()
        {
            MyMenu=new JSONArray();
            Followers=new JSONArray();
            Following=new JSONArray();
            calificaciones=new JSONArray();




        }
    public Perfil(JSONObject x)
    {
        MyMenu= (JSONArray) x.get("MyMenu");
        Followers= (JSONArray) x.get("Followers");
        Following= (JSONArray) x.get("Following");
        calificaciones= (JSONArray) x.get("calificaciones");




    }


}
