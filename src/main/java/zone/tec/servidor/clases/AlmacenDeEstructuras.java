package zone.tec.servidor.clases;

/*  Hola , soy el AlmacénDeEstructuras, verá, yo me encargaré de recordar sus preciadas listas
 * de usuarios, recetas y demás caprichos
 *
 * Soy una clase super especial, por no decir ,la mejor de todas, no tengo recelo con compartir
 * información con las demás clases, me crearon con base al patrón de diseño SINGLETON
 * Entonces no intente instanciarme, no podrá, y fallará pateticamente, buen día
 */

public class AlmacenDeEstructuras
{
    private static AlmacenDeEstructuras yo;
    /*
    private ArbolBB usuarios;
    private ArbolSP empresas;
    private ArbolAVL recetas;
   */
    private  AlmacenDeEstructuras() //Porqué privado? porque solo yo voy a existir y yo controlo mi propia existencia
        {

        }
    private synchronized static AlmacenDeEstructuras getAlmacen()
        {
            if(yo == null) { yo= new AlmacenDeEstructuras(); }
            return yo;
        }




    /*
    public ArbolBB getUsuarios(){}
    public ArbolSP getEmpresas(){}
    public ArbolAVL getUsuarios(){}
    */

}
