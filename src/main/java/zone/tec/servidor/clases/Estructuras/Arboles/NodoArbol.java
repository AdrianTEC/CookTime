package zone.tec.servidor.clases.Estructuras.Arboles;

public class NodoArbol<T extends Comparable<? super T>> {

    private T elemento;
    private NodoArbol<T> nodoIzquierdo;
    private NodoArbol<T> nodoDerecho;

    public NodoArbol(T elemento) {
        this.elemento = elemento;
    }

    public NodoArbol(T elemento, NodoArbol<T> nodoIzquierdo, NodoArbol<T> nodoDerecho) {
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

    public NodoArbol<T> getNodoIzquierdo() {
        return nodoIzquierdo;
    }

    public void setNodoIzquierdo(NodoArbol<T> nodoIzquierdo) {
        this.nodoIzquierdo = nodoIzquierdo;
    }

    public NodoArbol<T> getNodoDerecho() {
        return nodoDerecho;
    }

    public void setNodoDerecho(NodoArbol<T> nodoDerecho) {
        this.nodoDerecho = nodoDerecho;
    }
}
