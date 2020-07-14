package zone.tec.servidor.clases;

import zone.tec.servidor.clases.Estructuras.Listas.ListaSimple;

public class Publicación {

    //private Json contenido;
    private ListaSimple<String> comentarios; //recibe strings


    public Publicación() {
        /*This funtion is the constructor of the class
         *@author Andrés Quirós Guzmán
         *@Version 21/06/2020
         * @param nothing
         */
        comentarios = new ListaSimple<String>();

    }

    public ListaSimple<String> getComentarios() {
        return comentarios;
    }

    public void comentar(Usuario ussuario) {
    } //,comentarios)


    public void borrar(){

    }

    public void compartir(){

    }

    public void calificar(){

    }

    public void desplegar(){

    }

}
