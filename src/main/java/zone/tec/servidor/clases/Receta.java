package zone.tec.servidor.clases;

import zone.tec.servidor.clases.Estructuras.Listas.ListaSimple;
import org.json.simple.JSONObject;

import java.awt.image.BufferedImage;

public class Receta implements Comparable<Receta> {

    private String nombre;
    private String autor;
    private String tipo;
    private String tiempo;
    private String dieta;
    private String instrucciones;
    private String precio;
    private String id;
    private String porciones;
    private String dificultad;
    private String foto;
    private ListaSimple<String> ingredientes; //recibe strings

    public  Receta(JSONObject x) {
        /*This funtion is the constructor of the class
         *@author Andrés Quirós Guzmán
         *@Version 21/06/2020
         * @param nothing
         */

        id = (String) x.get("id");
        nombre = (String) x.get("nombre");
        autor = (String) x.get("autor");
        tipo = (String) x.get("tipo");
        tiempo = (String) x.get("tiempo");
        dieta = (String) x.get("dieta");
        instrucciones = (String) x.get("instrucciones");
        precio = (String) x.get("precio");
        porciones = (String) x.get("porciones");
        dificultad = (String) x.get("dificultad");
        foto = (String) x.get("foto");


        ingredientes = new ListaSimple<String>();
        //ingredientes
        //imagen



    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public int compareTo(Receta o) {
        return nombre.compareTo(o.getNombre());
    }
}
