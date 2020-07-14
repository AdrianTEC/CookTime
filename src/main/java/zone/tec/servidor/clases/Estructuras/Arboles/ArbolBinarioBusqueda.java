package zone.tec.servidor.clases.Estructuras.Arboles;

/**
 * Arbol binario con el hijo menor a la izquierda y el mayor a la derecha.
 * @param <T> Tipo de dato que almacena el árbol
 */
public class ArbolBinarioBusqueda<T extends Comparable<? super T>> {

    private NodoArbolBusqueda<T> raiz;

    /**
     * Constructor que define la raíz del árbol
     * @param raiz Raíz del árbol
     */
    public ArbolBinarioBusqueda(NodoArbolBusqueda<T> raiz) {
        this.raiz = raiz;
    }

    /**
     * Dice si el árbol está vacío o no
     * @return Booleano con valor "true" si el árbol está vacío
     */
    public boolean isEmpty() {
        return this.raiz == null;
    }

    public boolean contains(T elemento) {
        return contains(elemento, raiz);
    }

    private boolean contains(T elemento, NodoArbolBusqueda<T> nodoComparado) {
        if (nodoComparado == null) {
            return false;
        }else {
            int comparacion = elemento.compareTo(nodoComparado.getElemento());
            if (comparacion < 0) {
                return contains(elemento, nodoComparado.getNodoIzquierdo());
            } else if (comparacion > 0) {
                return contains(elemento, nodoComparado.getNodoDerecho());
            } else {
                return true;
            }
        }
    }

    public T findMin() {
        if (isEmpty()) {
            return null;
        } else {
            return findMin(raiz).getElemento();
        }
    }

    private NodoArbolBusqueda<T> findMin(NodoArbolBusqueda<T> nodo) {
        if (nodo == null) {
            return null;
        } else if (nodo.getNodoIzquierdo() == null) {
            return nodo;
        } else {
            return findMin(nodo.getNodoIzquierdo());
        }
    }

    public T findMax() {
        if (isEmpty()) {
            return null;
        } else {
            return findMax(raiz).getElemento();
        }
    }

    private NodoArbolBusqueda<T> findMax(NodoArbolBusqueda<T> nodo) {
        if (nodo == null) {
            return null;
        } else if (nodo.getNodoDerecho() == null) {
            return nodo;
        } else {
            return findMax(nodo.getNodoDerecho());
        }
    }

    public void insert(T elemento) {
        raiz = insert(elemento, raiz);
    }

    private NodoArbolBusqueda<T> insert(T elemento, NodoArbolBusqueda<T> nodo) {
        if (nodo == null) {
            return new NodoArbolBusqueda<T>(elemento);
        }
        int comparacion = elemento.compareTo(nodo.getElemento());
        if (comparacion < 0) {
            nodo.setNodoIzquierdo(insert(elemento, nodo.getNodoIzquierdo()));
        } else if (comparacion > 0) {
            nodo.setNodoDerecho(insert(elemento, nodo.getNodoDerecho()));
        }
        return nodo;
    }

    public void remove(T elemento) {
        raiz = remove(elemento, raiz);
    }

    private NodoArbolBusqueda<T> remove(T elemento, NodoArbolBusqueda<T> nodo) {
        if (nodo == null) {
            return nodo;
        }
        int comparacion = elemento.compareTo(nodo.getElemento());
        if (comparacion < 0) {
            nodo.setNodoIzquierdo(remove(elemento, nodo.getNodoIzquierdo()));
        } else if (comparacion > 0) {
            nodo.setNodoDerecho(remove(elemento, nodo.getNodoDerecho()));
        } else if (nodo.getNodoIzquierdo() != null && nodo.getNodoDerecho() != null) {
            nodo.setElemento(findMin(nodo.getNodoDerecho()).getElemento());
            nodo.setNodoDerecho(remove(nodo.getElemento(), nodo.getNodoDerecho()));
        } else {
            nodo = nodo.getNodoIzquierdo() != null ? nodo.getNodoIzquierdo() : nodo.getNodoDerecho();
        }
        return nodo;
    }
}
