package zone.tec.servidor.clases;

import org.json.simple.JSONObject;

public class Usuario implements Comparable<Usuario> {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String contrasena;
    private String correoElectronico;
    private String edad;
    private Perfil perfil;
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
        if(x.get("id")!= null){
            id=(String) x.get("id");}
        else {
            id=String.valueOf(AlmacenDeEstructuras.getUsers().getUltimaID());
        }

        if(x.get("perfil")!=null)
            {
                perfil=new Perfil((JSONObject) x.get("perfil"));
            }
        else {
            perfil=new Perfil();

        }
        //calificaciones = new Lista();

    }

    public String getNombreCompleto()
        {
            return  nombre+ apellido1+apellido2;
        }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(Usuario o) {
        return nombre.compareTo(o.getNombre());
    }
}
