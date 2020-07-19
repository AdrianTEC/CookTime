package zone.tec.servidor.clases.Estructuras;

/**
 * Nodo que almacena los datos en el arbol AVL. La diferencia más notable es el constructor adicional utlilizado
 * en rotaciones.
 * @param <T> Tipo de dato contenido en el nodo
 */
public class NodoAVL<T extends Comparable<? super T>> implements Comparable<NodoAVL<T>> {

    private T elemento;
    private NodoAVL<T> nodoIzquierdo;
    private NodoAVL<T> nodoDerecho;
    public int nivel;
    private int profundidad;

    /**
     * Constructor que guarda un elemento en el nodo
     * @param elemento Elemento que se guarda dentro del nodo
     */
    public NodoAVL(T elemento) {
        this.elemento = elemento;
    }

    /**
     * Constructor utilizado en inserciones y rotaciones para manipular el nodo izquierdo y el derecho, además de
     * definir la profundidad del nodo.
     * @param elemento Elemento contenido en el nodo
     * @param nodoIzquierdo Hijo izquierdo del nodo
     * @param nodoDerecho Hijo derecho del nodo
     */
    public NodoAVL(T elemento, NodoAVL<T> nodoIzquierdo, NodoAVL<T> nodoDerecho) {
        this.elemento = elemento;
        this.nodoIzquierdo = nodoIzquierdo;
        this.nodoDerecho = nodoDerecho;
        if (nodoIzquierdo == null && nodoDerecho == null)
            setProfundidad(1);
        else if (nodoIzquierdo == null)
            setProfundidad(nodoDerecho.getProfundidad() + 1);
        else if (nodoDerecho == null)
            setProfundidad(nodoIzquierdo.getProfundidad() + 1);
        else
            setProfundidad(Math.max(nodoIzquierdo.getProfundidad(), nodoDerecho.getProfundidad()) + 1);
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
    public NodoAVL<T> getNodoIzquierdo() {
        return nodoIzquierdo;
    }

    /**
     * Le asigna un elemento al nodo izquierdo
     * @param nodoIzquierdo Elemento a asignar en el nodo izquierdo
     */
    public void setNodoIzquierdo(NodoAVL<T> nodoIzquierdo) {
        this.nodoIzquierdo = nodoIzquierdo;
    }

    /**
     * Retorna el elemento del nodo derecho
     * @return elemento contenido en el nodo derecho
     */
    public NodoAVL<T> getNodoDerecho() {
        return nodoDerecho;
    }

    /**
     * Le asigna un elemento al nodo derecho
     * @param nodoDerecho Elemento a asignar en el nodo derecho
     */
    public void setNodoDerecho(NodoAVL<T> nodoDerecho) {
        this.nodoDerecho = nodoDerecho;
    }

    /**
     * Retorna la cantidad de aristas que hay desde la raíz hasta el nodo
     * @return Profundidad del nodo en el árbol
     */
    public int getProfundidad() {

        return profundidad;
    }

    /**
     * Determina la profundidad del nodo al ser insertado en un árbol
     * @param profundidad Profundidad del nodo en el árbol
     */
    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    /**
     * Compara el elemento que está dentro del nodo con el de otro nodo
     * @param o Nodo a comparar
     * @return Comparación entre el contenido de ambos nodos
     */
    @Override
    public int compareTo(NodoAVL<T> o) {
        return this.elemento.compareTo(o.elemento);
    }

}
