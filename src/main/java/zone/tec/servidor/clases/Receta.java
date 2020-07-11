package zone.tec.servidor.clases;

import org.json.simple.JSONObject;

import java.awt.image.BufferedImage;

public class Receta {
    private String nombre;
    private String autor;
    private String tipoPlato;
    private String tiempo;
    private String dieta;
    private String instrucciones;
    private String precio;
    private String id;
    private String porciones;
    private String dificultad;


    private BufferedImage receta;


    public  Receta(JSONObject x) {
        /*This funtion is the constructor of the class
         *@author Andrés Quirós Guzmán
         *@Version 21/06/2020
         * @param nothing
         */

        nombre = (String) x.get("nombre");
        autor = (String) x.get("autor");
        tipoPlato = (String) x.get("tipo de plato");
        tiempo = (String) x.get("tiempo");
        dieta = (String) x.get("dieta");
        instrucciones = (String) x.get("instrucciones");
        precio = (String) x.get("precio");
        porciones = (String) x.get("porciones");
        dificultad = (String) x.get("dificultad");


        //ingredientes
        //imagen



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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTipoPlato() {
        return tipoPlato;
    }

    public void setTipoPlato(String tipoPlato) {
        this.tipoPlato = tipoPlato;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getDieta() {
        return dieta;
    }

    public void setDieta(String dieta) {
        this.dieta = dieta;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getPorciones() {
        return porciones;
    }

    public void setPorciones(String porciones) {
        this.porciones = porciones;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }


    public BufferedImage getReceta() {
        return receta;
    }

    public void setReceta(BufferedImage receta) {
        this.receta = receta;
    }
}
