package zone.tec.servidor.clases;

import org.json.simple.JSONObject;

import java.awt.image.BufferedImage;

public class Empresa implements Comparable<Empresa> {
    private String nombre;
    private String contacto;
    private String horario;
    private String id;
    private String direccion;
    private BufferedImage logotipo;
    /**This funtion is the constructor of the class
     *@author Andrés Quirós Guzmán
     *@Version 21/06/2020
     * @param x JSONObject
     **/
    public  Empresa(JSONObject x) {


        nombre = (String) x.get("nombre");
        contacto = (String) x.get("contacto");
        horario = (String) x.get("horario");
        direccion=(String) x.get("direccion");


    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public BufferedImage getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(BufferedImage logotipo) {
        this.logotipo = logotipo;
    }

    @Override
    public int compareTo(Empresa o) {
        return nombre.compareTo(o.getNombre());
    }
}
