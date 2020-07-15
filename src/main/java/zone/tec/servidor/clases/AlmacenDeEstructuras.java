package zone.tec.servidor.clases;

/*  Hola , soy el AlmacénDeEstructuras, verá, yo me encargaré de recordar sus preciadas listas
 * de usuarios, recetas y demás caprichos
 *
 * Soy una clase super especial, por no decir ,la mejor de todas, no tengo recelo con compartir
 * información con las demás clases, me crearon con base al patrón de diseño SINGLETON
 * Entonces no intente instanciarme, no podrá, y fallará pateticamente, buen día
 */

import zone.tec.servidor.clases.Estructuras.Arboles.ArbolBinarioBusqueda;
import javax.servlet.ServletContext;

public class AlmacenDeEstructuras
{
    private static AlmacenDeEstructuras yo;
    private static ArbolBinarioBusqueda<Usuario> users;
    private static ServletContext contexto;
    /*
    private ArbolBinarioBusqueda usuarios;
    private ArbolSP empresas;
    private ArbolAVL recetas;
   */
    private  AlmacenDeEstructuras() //Porqué privado? porque solo yo voy a existir y yo controlo mi propia existencia
        {
        }

    public  synchronized  static void renovarArboles( ServletContext context)
        {
            //ocupo meterle un JSON a un árbol
                JSONManager jsonManager= new JSONManager(context);
                users=new ArbolBinarioBusqueda<Usuario>();
                users.JSONinsert(jsonManager.giveMeJson("Users"));
                contexto=context;
        }

    private synchronized static AlmacenDeEstructuras getAlmacen()
        {
            if(yo == null) { yo= new AlmacenDeEstructuras(); }
            return yo;
        }

    public static ArbolBinarioBusqueda<Usuario> getUsers() {
        return users;
    }

    public static AlmacenDeEstructuras getYo() {
        return yo;
    }

    public static ServletContext getContexto() {
        return contexto;
    }
/*
    public ArbolBB getUsuarios(){}
    public ArbolSP getEmpresas(){}
    public ArbolAVL getUsuarios(){}
    */

}
