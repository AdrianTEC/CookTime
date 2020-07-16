package zone.tec.servidor.clases.Estructuras.Arboles;

/**
 * Árbol binario de búsqueda que siempre está balanceado, lo que significa que la profundidad de sus nodos hoja solo
 * tendrán 1 de diferencia entre ellos.
 * @param <T>
 */
public class ArbolAVL<T extends Comparable<? super T>> {
    NodoAVL<T> root;

    /**
     * Constructor del árbol con raíz vacía
     */
    public ArbolAVL() {
        root = null;
    }

    /**
     * Revisa si un elemento está contenido en el árbol
     * @param elemento Elemento a buscar en el árbol
     * @return Booleano que dice si el elemento está o no en el árbol
     */
    public boolean contains(T elemento) {
        NodoAVL<T> local = root;
        while (local != null) {
            if (local.getElemento().compareTo(elemento) == 0)
                return true;
            else if (local.getElemento().compareTo(elemento) > 0)
                local = local.getNodoIzquierdo();
            else
                local = local.getNodoDerecho();
        }
        return false;
    }

    /**
     * Busca el elemento más pequeño del árbol
     * @return elemento más pequeño
     */
    public T findMin() {
        NodoAVL<T> local = root;
        if (local == null)
            return null;
        while (local.getNodoIzquierdo() != null) {
            local = local.getNodoIzquierdo();
        }
        return local.getElemento();
    }

    /**
     * Busca el elemento más grande del árbol
     * @return elemento más grande
     */
    public T findMax() {
        NodoAVL<T> local = root;
        if (local == null)
            return null;
        while (local.getNodoDerecho() != null)
            local = local.getNodoDerecho();
        return local.getElemento();
    }

    /**
     * Retorna la profundidad de un nodo determinado
     * @param nodo Nodo con la profundidad a determinar
     * @return Profundidad del nodo
     */
    private int getProfundidad(NodoAVL<T> nodo) {
        if (nodo == null)
            return 0;
        return nodo.getProfundidad();
        // 1 + Math.max(depth(node.getLeft()), depth(node.getRight()));
    }

    /**
     * Inserta un elemento y aplica una rotación de acuerdo a su factor de balanceo
     * @param elemento Elemento a insertar
     * @return nodo a insertar
     */
    public NodoAVL<T> insert(T elemento) {
        root = insert(elemento, root);
        switch (factorBalance(root)) {
            case 1:
                root = rotacionIzquierda(root);
                break;
            case -1:
                root = rotacionDerecha(root);
                break;
            default:
                break;
        }
        return root;
    }

    /**
     * Llamado úicamente en recursión. Recorre el árbol realizando las rotaciones necesarias para insertar un nodo.
     * @param elemento Elemento a insertar
     * @param nodo Nodo que se recorre actualmente
     * @return Nodo a insertar
     */
    public NodoAVL<T> insert(T elemento, NodoAVL<T> nodo) {
        if (nodo == null)
            return new NodoAVL<T>(elemento);
        if (nodo.getElemento().compareTo(elemento) > 0) {
            nodo = new NodoAVL<T>(nodo.getElemento(), insert(elemento, nodo.getNodoIzquierdo()), nodo.getNodoDerecho());
            // node.setLeft(insert(elemento, node.getLeft()));
        } else if (nodo.getElemento().compareTo(elemento) < 0) {
            // node.setRight(insert(elemento, node.getRight()));
            nodo = new NodoAVL<T>(nodo.getElemento(), nodo.getNodoIzquierdo(), insert(elemento, nodo.getNodoDerecho()));
        }
        // Después de insertar el nodo, verifica su factor de balance para revisar si es necesario realizar una
        // rotación
        switch (factorBalance(nodo)) {
            case 1:
                nodo = rotacionIzquierda(nodo);
                break;
            case -1:
                nodo = rotacionDerecha(nodo);
                break;
            default:
                return nodo;
        }
        return nodo;
    }

    /**
     * Factor de balance que define si hay que rotar un subárbol o no
     * @param nodo Nodo con subárbol a rotar
     * @return Resultado que depende entre la diferencia entre la profundidad del subárbol izquierdo menos el
     * subárbol derecho
     */
    private int factorBalance(NodoAVL<T> nodo) {
        int L = getProfundidad(nodo.getNodoIzquierdo());
        int R = getProfundidad(nodo.getNodoDerecho());
        if (L - R >= 2)
            return -1;
        else if (L - R <= -2)
            return 1;
        return 0;
    }

    /**
     * Rota el subárbol del nodo a la izquierda
     * @param nodo raíz del subárbol a rotar
     * @return raíz del subárbol después de la rotación
     */
    private NodoAVL<T> rotacionIzquierda(NodoAVL<T> nodo) {
        NodoAVL<T> nodoPadre = nodo;
        NodoAVL<T> nodoDerecho = nodoPadre.getNodoDerecho();
        NodoAVL<T> nodoIzquierdo = nodoPadre.getNodoIzquierdo();
        NodoAVL<T> hijoIzquierdoNodoDerecho = nodoDerecho.getNodoIzquierdo();
        NodoAVL<T> hijoDerechoNodoDerecho = nodoDerecho.getNodoDerecho();
        nodoPadre = new NodoAVL<T>(nodoPadre.getElemento(), nodoIzquierdo, hijoIzquierdoNodoDerecho);
        nodoDerecho = new NodoAVL<T>(nodoDerecho.getElemento(), nodoPadre, hijoDerechoNodoDerecho);
        return nodoDerecho;
    }

    /**
     * Rota el subárbol del nodo a la derecha
     * @param nodo raíz del subárbol a rotar
     * @return raíz del subárbol después de la rotación
     */
    private NodoAVL<T> rotacionDerecha(NodoAVL<T> nodo) {
        NodoAVL<T> nodoPadre = nodo;
        NodoAVL<T> nodoIzquierdo = nodoPadre.getNodoIzquierdo();
        NodoAVL<T> nodoDerecho = nodoPadre.getNodoDerecho();
        NodoAVL<T> hijoIzquierdoNodoIzquierdo = nodoIzquierdo.getNodoIzquierdo();
        NodoAVL<T> hijoDerechoNodoIzquierdo = nodoIzquierdo.getNodoDerecho();
        nodoPadre = new NodoAVL<T>(nodoPadre.getElemento(), hijoDerechoNodoIzquierdo, nodoDerecho);
        nodoIzquierdo = new NodoAVL<T>(nodoIzquierdo.getElemento(), hijoIzquierdoNodoIzquierdo, nodoPadre);
        return nodoIzquierdo;
    }

}
