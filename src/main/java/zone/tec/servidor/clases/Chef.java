package Sample;

import Estructuras.ListaLineal;

public class Chef extends Sample.Usuario {
    private ListaLineal reseñas;  //recibe strings



    public Chef(){
        /*This funtion is the constructor of the class
         *@author Andrés Quirós Guzmán
         *@Version 21/06/2020
         * @param nothing
         */

        reseñas = new ListaLineal();
    }


    public ListaLineal getReseñas() {
        return reseñas;
    }
}
