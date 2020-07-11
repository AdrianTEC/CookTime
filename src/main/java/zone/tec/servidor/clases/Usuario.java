package zone.tec.servidor.clases;

import org.json.simple.JSONObject;

public class Usuario {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String contrasena;
    private String correoElectronico;
    private String edad;
    private String perfil;
    private String id;
   // private Perfil perfil;




    public  Usuario(JSONObject x) {
        /*This funtion is the constructor of the class
         *@author Andrés Quirós Guzmán
         *@Version 21/06/2020
         * @param nothing
         */

        nombre = (String) x.get("nombre");
        apellido1 = (String) x.get("apellido1");
        apellido2 = (String) x.get("apellido2");
        correoElectronico = (String) x.get("correo");
        edad = (String) x.get("edad");
        contrasena= (String) x.get("contrasena");
        id=String.valueOf((int) (Math.random() * 100) +1);
        perfil=nombre+"ID"+id;
        //calificaciones = new Lista();

    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }







    public void crearReceta(){

    }

    public void crearEmpresa(){

    }

    public void borrar(){

    }


    public void seguir (Usuario usuario){

    }



}
