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
        while (local.getRight() != null)
            local = local.getRight();
        return local.getData();
    }

    public T findMin() {
        NodoAVL<T> local = root;
        if (local == null)
            return null;
        while (local.getLeft() != null) {
            local = local.getLeft();
        }
        return local.getData();
    }

    private int depth(NodoAVL<T> nodoAVL) {
        if (nodoAVL == null)
            return 0;
        return nodoAVL.getDepth();
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
        if (nodoAVL.getData().compareTo(data) > 0) {
            nodoAVL = new NodoAVL<T>(nodoAVL.getData(), insert(nodoAVL.getLeft(), data),
                    nodoAVL.getRight());
            // node.setLeft(insert(node.getLeft(), data));
        } else if (nodoAVL.getData().compareTo(data) < 0) {
            // node.setRight(insert(node.getRight(), data));
            nodoAVL = new NodoAVL<T>(nodoAVL.getData(), nodoAVL.getLeft(), insert(
                    nodoAVL.getRight(), data));
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
        int L = depth(nodoAVL.getLeft());
        int R = depth(nodoAVL.getRight());
        if (L - R >= 2)
            return -1;
        else if (L - R <= -2)
            return 1;
        return 0;
    }

    private NodoAVL<T> rotateLeft(NodoAVL<T> nodoAVL) {
        NodoAVL<T> q = nodoAVL;
        NodoAVL<T> p = q.getRight();
        NodoAVL<T> c = q.getLeft();
        NodoAVL<T> a = p.getLeft();
        NodoAVL<T> b = p.getRight();
        q = new NodoAVL<T>(q.getData(), c, a);
        p = new NodoAVL<T>(p.getData(), q, b);
        return p;
    }

    private NodoAVL<T> rotateRight(NodoAVL<T> nodoAVL) {
        NodoAVL<T> q = nodoAVL;
        NodoAVL<T> p = q.getLeft();
        NodoAVL<T> c = q.getRight();
        NodoAVL<T> a = p.getLeft();
        NodoAVL<T> b = p.getRight();
        q = new NodoAVL<T>(q.getData(), b, c);
        p = new NodoAVL<T>(p.getData(), a, q);
        return p;
    }

    public boolean search(T data) {
        NodoAVL<T> local = root;
        while (local != null) {
            if (local.getData().compareTo(data) == 0)
                return true;
            else if (local.getData().compareTo(data) > 0)
                local = local.getLeft();
            else
                local = local.getRight();
        }
        return false;
    }

    public String toString() {
        return root.toString();
    }

}
