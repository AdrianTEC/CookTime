package Sample;

import Estructuras.ListaLineal;

import java.awt.image.BufferedImage;

public class Receta {
    private String nombre;
    private String autor;
    private String tipoPlato;
    private String tiempo;
    private String dieta;
    private String instrucciones;
    private String precio;

    private int porciones;
    private int dificultad;

    private ListaLineal ingredientes; //recibe strings

    private BufferedImage receta;


    public Receta(){

        /*This funtion is the constructor of the class
         *@author Andrés Quirós Guzmán
         *@Version 21/06/2020
         * @param nothing
         */

        nombre = "";
        autor = "";
        tipoPlato = "";
        tiempo = "";
        dieta = "";
        instrucciones = "";
        precio = "";

        porciones = 0;
        dificultad = 0;

        ingredientes = new ListaLineal();

        //receta = BufferedImage;
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

    public int getPorciones() {
        return porciones;
    }

    public void setPorciones(int porciones) {
        this.porciones = porciones;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public ListaLineal getIngredientes() {
        return ingredientes;
    }

    public BufferedImage getReceta() {
        return receta;
    }

    public void setReceta(BufferedImage receta) {
        this.receta = receta;
    }
}
