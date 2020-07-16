package zone.tec.servidor.clases.Estructuras.Arboles;

/**
 * Árbol binario con el hijo menor a la izquierda y el mayor a la derecha.
 * @param <T> Tipo de dato que almacena el árbol
 */
public class ArbolBinarioBusqueda<T extends Comparable<? super T>> {

    private NodoArbolBinario<T> raiz;

    /**
     * Constructor que define la raíz del árbol
     * @param raiz Raíz del árbol
     */
    public ArbolBinarioBusqueda(NodoArbolBinario<T> raiz) {
        this.raiz = raiz;
    }

    /**
     * Dice si el árbol está vacío o no
     * @return Booleano con valor "true" si el árbol está vacío
     */
    public boolean isEmpty() {
        return this.raiz == null;
    }

    /**
     * Revisa si un elemento está contenido en el árbol
     * @param elemento Elemento a buscar en el árbol
     * @return Booleano que dice si el elemento está o no en el árbol
     */
    public boolean contains(T elemento) {
        return contains(elemento, raiz);
    }

    /**
     * Se utiliza en recursión del método anterior. Compara los elementos del árbol hasta encontrar el que se busca
     * @param elemento Elemento a buscar en el árbol
     * @param nodoComparado Nodo en el que se busca el elemento actualmente
     * @return Booleano que dice si el elemento está o no en el árbol
     */
    private boolean contains(T elemento, NodoArbolBinario<T> nodoComparado) {
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

    /**
     * Busca el elemento más pequeño del árbol
     * @return elemento más pequeño
     */
    public T findMin() {
        if (isEmpty()) {
            return null;
        } else {
            return findMin(raiz).getElemento();
        }
    }

    /**
     * Llamado úicamente en recursión. Revisa los hijos izquierdos buscando el elemento más pequeño del árbol
     * @param nodo Nodo en el que se encuentra buscando al más pequeño
     * @return elemento más pequeño
     */
    private NodoArbolBinario<T> findMin(NodoArbolBinario<T> nodo) {
        if (nodo == null) {
            return null;
        } else if (nodo.getNodoIzquierdo() == null) {
            return nodo;
        } else {
            return findMin(nodo.getNodoIzquierdo());
        }
    }

    /**
     * Busca el elemento más grande del árbol
     * @return elemento más grande
     */
    public T findMax() {
        if (isEmpty()) {
            return null;
        } else {
            return findMax(raiz).getElemento();
        }
    }

    /**
     * Llamado úicamente en recursión. Revisa los hijos derechos buscando el elemento más grande del árbol
     * @param nodo Nodo en el que se encuentra buscando al más grande
     * @return elemento más grande
     */
    private NodoArbolBinario<T> findMax(NodoArbolBinario<T> nodo) {
        if (nodo == null) {
            return null;
        } else if (nodo.getNodoDerecho() == null) {
            return nodo;
        } else {
            return findMax(nodo.getNodoDerecho());
        }
    }

    /**
     * Inserta un elemento en la árbol en la hoja que corresponda
     * @param elemento Elemento a insertar
     */
    public void insert(T elemento) {
        raiz = insert(elemento, raiz);
    }

    /**
     * Llamado úicamente en recursión. Busca la hoja apropiada donde se agrega el nuevo elemento, revisando si son
     * mayores o menores que él y recorriendolo a la izquierda o derecha, según corresponda
     * @param elemento Elemento a insertar
     * @param nodo Ubicación actual, siguiendo el camino del nodo a insertar
     * @return Nodo que sigue el camino a recorrer
     */
    private NodoArbolBinario<T> insert(T elemento, NodoArbolBinario<T> nodo) {
        if (nodo == null) {
            return new NodoArbolBinario<T>(elemento);
        }
        int comparacion = elemento.compareTo(nodo.getElemento());
        if (comparacion < 0) {
            nodo.setNodoIzquierdo(insert(elemento, nodo.getNodoIzquierdo()));
        } else if (comparacion > 0) {
            nodo.setNodoDerecho(insert(elemento, nodo.getNodoDerecho()));
        }
        return nodo;
    }

    /**
     * Borra a un nodo con el elemento ingresado
     * @param elemento Elemento a borrar del árbol
     */
    public void remove(T elemento) {
        raiz = remove(elemento, raiz);
    }

    /**
     * Llamado úicamente en recursión. Busca el elemento a borrar. Si lo encuentra, lo elimina
     * @param elemento Elemento a eliminar
     * @param nodo Nodo que se recorre para encontrar el elemento a eliminar
     * @return Nodo que sigue el camino a recorrer
     */
    private NodoArbolBinario<T> remove(T elemento, NodoArbolBinario<T> nodo) {
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
