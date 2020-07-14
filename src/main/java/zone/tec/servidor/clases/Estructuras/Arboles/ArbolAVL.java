package zone.tec.servidor.clases.Estructuras.Arboles;

public class ArbolAVL<T extends Comparable<T>> {
    NodoAVL<T> root;

    public ArbolAVL() {
        root = null;
    }

    public T findMax() {
        NodoAVL<T> local = root;
        if (local == null)
            return null;
        while (local.getNodoDerecho() != null)
            local = local.getNodoDerecho();
        return local.getElemento();
    }

    public T findMin() {
        NodoAVL<T> local = root;
        if (local == null)
            return null;
        while (local.getNodoIzquierdo() != null) {
            local = local.getNodoIzquierdo();
        }
        return local.getElemento();
    }

    private int depth(NodoAVL<T> nodoAVL) {
        if (nodoAVL == null)
            return 0;
        return nodoAVL.getProfundidad();
        // 1 + Math.max(depth(node.getLeft()), depth(node.getRight()));
    }

    public NodoAVL<T> insert(T data) {
        root = insert(root, data);
        switch (balanceNumber(root)) {
            case 1:
                root = rotateLeft(root);
                break;
            case -1:
                root = rotateRight(root);
                break;
            default:
                break;
        }
        return root;
    }

    public NodoAVL<T> insert(NodoAVL<T> nodoAVL, T data) {
        if (nodoAVL == null)
            return new NodoAVL<T>(data);
        if (nodoAVL.getElemento().compareTo(data) > 0) {
            nodoAVL = new NodoAVL<T>(nodoAVL.getElemento(), insert(nodoAVL.getNodoIzquierdo(), data),
                    nodoAVL.getNodoDerecho());
            // node.setLeft(insert(node.getLeft(), data));
        } else if (nodoAVL.getElemento().compareTo(data) < 0) {
            // node.setRight(insert(node.getRight(), data));
            nodoAVL = new NodoAVL<T>(nodoAVL.getElemento(), nodoAVL.getNodoIzquierdo(), insert(
                    nodoAVL.getNodoDerecho(), data));
        }
        // After insert the new node, check and rebalance the current node if
        // necessary.
        switch (balanceNumber(nodoAVL)) {
            case 1:
                nodoAVL = rotateLeft(nodoAVL);
                break;
            case -1:
                nodoAVL = rotateRight(nodoAVL);
                break;
            default:
                return nodoAVL;
        }
        return nodoAVL;
    }

    private int balanceNumber(NodoAVL<T> nodoAVL) {
        int L = depth(nodoAVL.getNodoIzquierdo());
        int R = depth(nodoAVL.getNodoDerecho());
        if (L - R >= 2)
            return -1;
        else if (L - R <= -2)
            return 1;
        return 0;
    }

    private NodoAVL<T> rotateLeft(NodoAVL<T> nodoAVL) {
        NodoAVL<T> q = nodoAVL;
        NodoAVL<T> p = q.getNodoDerecho();
        NodoAVL<T> c = q.getNodoIzquierdo();
        NodoAVL<T> a = p.getNodoIzquierdo();
        NodoAVL<T> b = p.getNodoDerecho();
        q = new NodoAVL<T>(q.getElemento(), c, a);
        p = new NodoAVL<T>(p.getElemento(), q, b);
        return p;
    }

    private NodoAVL<T> rotateRight(NodoAVL<T> nodoAVL) {
        NodoAVL<T> q = nodoAVL;
        NodoAVL<T> p = q.getNodoIzquierdo();
        NodoAVL<T> c = q.getNodoDerecho();
        NodoAVL<T> a = p.getNodoIzquierdo();
        NodoAVL<T> b = p.getNodoDerecho();
        q = new NodoAVL<T>(q.getElemento(), b, c);
        p = new NodoAVL<T>(p.getElemento(), a, q);
        return p;
    }

    public boolean search(T data) {
        NodoAVL<T> local = root;
        while (local != null) {
            if (local.getElemento().compareTo(data) == 0)
                return true;
            else if (local.getElemento().compareTo(data) > 0)
                local = local.getNodoIzquierdo();
            else
                local = local.getNodoDerecho();
        }
        return false;
    }

    public String toString() {
        return root.toString();
    }

}
