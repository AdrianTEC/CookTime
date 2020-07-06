package zone.tec.servidor.clases;

import Estructuras.ListaLineal;

public class Usuario {
    private String nombre;
    private String correoElectronico;
    private int edad;
   // private Perfil perfil;
    private ListaLineal calificaciones; //recibe strings



    public Usuario() {
        /*This funtion is the constructor of the class
         *@author Andrés Quirós Guzmán
         *@Version 21/06/2020
         * @param nothing
         */

        nombre = "";
        correoElectronico = "";
        edad = 0;
     //   perfil = new Perfil();

        //calificaciones = new Lista();

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ListaLineal getCalificaciones() {
        return calificaciones;
    }




    public void crearReceta(){

    }

    public void crearEmpresa(){

    }

    public void borrar(){

    }

    public void cambiarContraseña(){

    }


    public void seguir (Usuario usuario){

    }



}
