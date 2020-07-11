package zone.tec.servidor.clases.Estructuras;

public class Nodo {

    private int INDEX;         //AGREGADO QUE ME PERMITE SABER QUÉ CASILLA ES
    private Object contenido;
    private Nodo siguiente;  //Indica cual es la casilla siguiente

    public Nodo()
    {
        INDEX=0;
        contenido= null;
        siguiente= null;

    }

    public Object getContenido() {
        return contenido;
    }

    public void setContenido(Object contenido) {
        this.contenido = contenido;
    }

    public int getINDEX() {
        return INDEX;
    }

    public void setINDEX(int INDEX) {
        this.INDEX = INDEX;
    }


    public Object getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}

