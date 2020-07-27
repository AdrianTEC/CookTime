package zone.tec.servidor.clases;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Receta implements Comparable<Receta> {

    private String nombre;
    private String autor;
    private String tipo;
    private String tiempo;



    private String likes;
    private String dislikes;
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

        if( x.get("comentarios")!=null){
        comentarios=(JSONArray) x.get("comentarios");
        }
        else {
            comentarios=new JSONArray();
        }
        if(x.get("likes")!=null){
        likes=(String) x.get("likes");}
        else {
            likes="0";
        }
        if(x.get("dislikes")!=null){
        dislikes=(String) x.get("dislikes");}
        else {dislikes="0";}


        ingredientes = new JSONArray();
        //ingredientes
        //imagen



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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getDislikes() {
        return dislikes;
    }

    public void setDislikes(String dislikes) {
        this.dislikes = dislikes;
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

    public void setId(String id) {
        this.id = id;
    }

    public String getPorciones() {
        return porciones;
    }

    public void setPorciones(String porciones) {
        this.porciones = porciones;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public JSONArray getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(JSONArray ingredientes) {
        this.ingredientes = ingredientes;
    }

    public JSONArray getComentarios() {
        return comentarios;
    }

    public void setComentarios(JSONArray comentarios) {
        this.comentarios = comentarios;
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
