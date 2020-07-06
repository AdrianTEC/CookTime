package zone.tec.servidor.clases;

import Estructuras.ListaLineal;

public class Publicación {

    //private Json contenido;
    private ListaLineal comentarios; //recibe strings


    public Publicación() {
        /*This funtion is the constructor of the class
         *@author Andrés Quirós Guzmán
         *@Version 21/06/2020
         * @param nothing
         */
        comentarios = new ListaLineal();

    }

    public ListaLineal getComentarios() {
        return comentarios;
    }

    public void comentar(Usuario ussuario) {
    } //,comentarios)


    public void borrarse(){

    }

    public void compartirse(){

    }

    public void calificar(){

    }

    public void desplegarse(){

    }

}
