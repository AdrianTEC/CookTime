package zone.tec.servidor.clases.Estructuras.Arboles;

/**
 * Nodo que almacena los datos en el arbol binario de búsqueda
 * @param <T> Tipo de dato que se agrega al nodo
 */
public class NodoArbolBinario<T extends Comparable<? super T>> {

    private T elemento;
    private NodoArbolBinario<T> nodoIzquierdo;
    private NodoArbolBinario<T> nodoDerecho;

    /**
     * Constructor que guarda un elemento en el nodo
     * @param elemento elemento que se agrega al nodo
     */
    public NodoArbolBinario(T elemento) {
        this.elemento = elemento;
    }

    /**
     * Retorna el elemento del nodo
     * @return elemento contenido en el nodo
     */
    public T getElemento() {
        return elemento;
    }

    /**
     * Le asigna un elemento al nodo
     * @param elemento Elemento a asignar en el nodo
     */
    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    /**
     * Retorna el elemento del nodo izquierdo
     * @return elemento contenido en el nodo izquierdo
     */
    public NodoArbolBinario<T> getNodoIzquierdo() {
        return nodoIzquierdo;
    }

    /**
     * Le asigna un elemento al nodo izquierdo
     * @param nodoIzquierdo Elemento a asignar en el nodo izquierdo
     */
    public void setNodoIzquierdo(NodoArbolBinario<T> nodoIzquierdo) {
        this.nodoIzquierdo = nodoIzquierdo;
    }

    /**
     * Retorna el elemento del nodo derecho
     * @return elemento contenido en el nodo derecho
     */
    public NodoArbolBinario<T> getNodoDerecho() {
        return nodoDerecho;
    }

    /**
     * Le asigna un elemento al nodo derecho
     * @param nodoDerecho Elemento a asignar en el nodo derecho
     */
    public void setNodoDerecho(NodoArbolBinario<T> nodoDerecho) {
        this.nodoDerecho = nodoDerecho;
    }
}
