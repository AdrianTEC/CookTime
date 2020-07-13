package zone.tec.servidor.clases.Estructuras.Arboles;

public class NodoArbolBusqueda<T extends Comparable<? super T>> {

    private T elemento;
    private NodoArbolBusqueda<T> nodoIzquierdo;
    private NodoArbolBusqueda<T> nodoDerecho;

    public NodoArbolBusqueda(T elemento) {
        this.elemento = elemento;
    }

    public NodoArbolBusqueda(T elemento, NodoArbolBusqueda<T> nodoIzquierdo, NodoArbolBusqueda<T> nodoDerecho) {
        this.elemento = elemento;
        this.nodoIzquierdo = nodoIzquierdo;
        this.nodoDerecho = nodoDerecho;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public NodoArbolBusqueda<T> getNodoIzquierdo() {
        return nodoIzquierdo;
    }

    public void setNodoIzquierdo(NodoArbolBusqueda<T> nodoIzquierdo) {
        this.nodoIzquierdo = nodoIzquierdo;
    }

    public NodoArbolBusqueda<T> getNodoDerecho() {
        return nodoDerecho;
    }

    public void setNodoDerecho(NodoArbolBusqueda<T> nodoDerecho) {
        this.nodoDerecho = nodoDerecho;
    }
}
