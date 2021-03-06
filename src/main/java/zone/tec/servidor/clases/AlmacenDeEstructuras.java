package zone.tec.servidor.clases;

/*  Hola , soy el AlmacénDeEstructuras, verá, yo me encargaré de recordar sus preciadas listas
 * de usuarios, recetas y demás caprichos
 *
 * Soy una clase super especial, por no decir ,la mejor de todas, no tengo recelo con compartir
 * información con las demás clases, me crearon con base al patrón de diseño SINGLETON
 * Entonces no intente instanciarme, no podrá, y fallará pateticamente, buen día
 */

import org.json.simple.JSONArray;
import zone.tec.servidor.clases.Estructuras.ArbolAVL;
import zone.tec.servidor.clases.Estructuras.ArbolBinarioBusqueda;
import zone.tec.servidor.clases.Estructuras.ArbolSplay;

import javax.servlet.ServletContext;
@SuppressWarnings("unchecked")

public class AlmacenDeEstructuras
{
    private static AlmacenDeEstructuras yo;
    private static ArbolBinarioBusqueda users;
    private static ArbolBinarioBusqueda usersPorID;
    private static ArbolSplay<Empresa> restaurantes;
    private static ArbolAVL<Receta> recipes;
    private static ServletContext contexto;
    private static final JSONArray peticionesChef=new JSONArray();
    private static JSONArray mails= new JSONArray();
    public  static boolean Starting= true;
    private  AlmacenDeEstructuras() //Porqué privado? porque solo yo voy a existir y yo controlo mi propia existencia
        { }
    public  synchronized  static void renovarArboles( ServletContext context)
        {
            //ocupo meterle un JSON a un árbol
                JSONManager jsonManager= new JSONManager(context);
                users=new ArbolBinarioBusqueda();
                usersPorID=new ArbolBinarioBusqueda();
                users.JSONinsert(jsonManager.giveMeJson("Users"),false);
                usersPorID.JSONinsert(jsonManager.giveMeJson("Users"),true);
                contexto=context;

                recipes= new ArbolAVL<Receta>();
                recipes.JSONinsert(jsonManager.giveMeJson("Recipes"));

                restaurantes= new ArbolSplay<Empresa>();
                restaurantes.JSONinsert(jsonManager.giveMeJson("Companies"));



        }

    private synchronized static AlmacenDeEstructuras getAlmacen()
        {
            if(yo == null) { yo= new AlmacenDeEstructuras(); }
            return yo;
        }

    public static ArbolBinarioBusqueda getUsers() {
        return users;
    }

    public static ArbolSplay<Empresa> getEmpresas(){return restaurantes;}
    public static ArbolAVL<Receta> getRecipes() {
        return recipes;
    }

    public static ArbolBinarioBusqueda getUsersPorID() {
        return usersPorID;
    }

    public static ServletContext getContexto() {
        return contexto;
    }

    public static JSONArray getPeticionesChef()
        { return peticionesChef; }

    public static boolean have_this( String thing)
        {
            return mails.contains(thing);
        }
    public static void addMail(String thing)
        {
            mails.add(thing);
        }


}
