package zone.tec.servidor.clases;

import org.json.simple.JSONObject;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario implements Comparable<Usuario> {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String contrasena;
    private String correo;
    private String edad;
    private JSONObject perfil;
    private String id;
    private String chef;
   // private Perfil perfil;



    /**This funtion is the constructor of the class
     *@author Andrés Quirós Guzmán
     *@Version 21/06/2020
     * @param x JSONObject
     **/
    public  Usuario(JSONObject x) {


        nombre = (String) x.get("nombre");
        apellido1 = (String) x.get("apellido1");
        apellido2 = (String) x.get("apellido2");
        chef = "0";
        correo = (String) x.get("correo");
        edad = (String) x.get("edad");
        contrasena = encriptar((String) x.get("contrasena"));
        if(x.get("id")!= null){
            id=(String) x.get("id");}
        else {
            id=String.valueOf(((int)Math.random())*100+AlmacenDeEstructuras.getUsers().getUltimaID());
        }
        JSONManager manager= new JSONManager(AlmacenDeEstructuras.getContexto());
        if(x.get("perfil")!=null)
            {
                Perfil Auxperfil=new Perfil(x);
                perfil=manager.convertToJSON(Auxperfil);
            }
        else {
            Perfil Auxperfil=new Perfil();
            Auxperfil.setNombre(nombre);
            Auxperfil.setApellido1(apellido1);
            Auxperfil.setApellido2(apellido2);
            Auxperfil.setCorreoElectronico(correo);
            Auxperfil.setEdad(edad);
            perfil=manager.convertToJSON(Auxperfil);

        }
        //calificaciones = new Lista();
    }

    private String encriptar(String clave){

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(clave.getBytes());
            byte[] convertidor = md.digest();
            return DatatypeConverter.printHexBinary(convertidor).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "Error";
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

    public int compareTo(Usuario o,Boolean porID) {
        if(porID){
            return id.compareTo(o.getId());
        }
        else {
        return nombre.compareTo(o.getNombre());}


    }

    @Override
    public int compareTo(Usuario o) {
        return 0;
    }


    public String getCorreoElectronico() {
        return correo;
    }
}
