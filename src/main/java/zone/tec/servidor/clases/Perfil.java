package Sample;

import Estructuras.ListaLineal;

import java.awt.image.BufferedImage;

public class Perfil {

    private BufferedImage fotoPerfil;

    private ListaLineal myMenu; //recibe Recetas
    private ListaLineal seguidores; //recibe Usuarios
    private ListaLineal seguidos; //recibe Usuarios

    public Perfil(){

        /*This funtion is the constructor of the class
         *@author Andrés Quirós Guzmán
         *@Version 21/06/2020
         * @param nothing
         */

        //fotoPerfil = BufferedImage;

        myMenu = new ListaLineal();
        seguidores = new ListaLineal();
        seguidos = new ListaLineal();
    }


    public BufferedImage getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(BufferedImage fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public ListaLineal getMyMenu() {
        return myMenu;
    }

    public ListaLineal getSeguidores() {
        return seguidores;
    }

    public ListaLineal getSeguidos() {
        return seguidos;
    }


    public void cambiarFoto(){

    }

    public void añadirReceta(){

    }
}
