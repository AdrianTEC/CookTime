package zone.tec.servidor.clases;

import java.awt.image.BufferedImage;

public class Empresa {
    private String nombre;
    private String contacto;
    private String horario;

    private BufferedImage logotipo;

    //private GOOGLEMAPSAPPI dirección;

    public Empresa (){
        nombre = "";
        contacto = "";
        horario = "";

        //logotipo = BufferedImage;
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
}
