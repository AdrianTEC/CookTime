package zone.tec.servidor.clases;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Perfil {

            private String nombre;
            private String apellido1;
            private String apellido2;
            private String correoElectronico;
            private String edad;
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
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

}
