package zone.tec.servidor.clases;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Receta implements Comparable<Receta> {

    private String nombre;
    private String autor;
    private String tipo;
    private String tiempo;
    private String dieta;
    private String instrucciones;
    private String precio;
    private String id;
    private String porciones;
    private String dificultad;
    private String foto;
    private JSONArray ingredientes; //recibe strings
    private JSONArray comentarios;

    public  Receta(JSONObject x) {
        /*This funtion is the constructor of the class
         *@author Andrés Quirós Guzmán
         *@Version 21/06/2020
         * @param nothing
         */
        if( x.get("id")!=null)
            {
                id = (String) x.get("id");
            }
        else {
            id= String.valueOf(AlmacenDeEstructuras.getRecipes().getUltimaID()+1);
        }
        nombre = (String) x.get("nombre");
        autor = (String) x.get("autor");
        tipo = (String) x.get("tipo");
        tiempo = (String) x.get("tiempo");
        dieta = (String) x.get("dieta");
        instrucciones = (String) x.get("instrucciones");
        precio = (String) x.get("precio");
        porciones = (String) x.get("porciones");
        dificultad = (String) x.get("dificultad");
        foto = (String) x.get("foto");
        comentarios=(JSONArray) x.get("comentarios");


        ingredientes = new JSONArray();
        //ingredientes
        //imagen



    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(Receta o) {
        return id.compareTo(o.getId());
    }
}
