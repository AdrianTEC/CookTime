package zone.tec.servidor.clases.Estructuras.Arboles;

public class NodoAVL<T extends Comparable<T>> implements Comparable<NodoAVL<T>> {

    private T data;
    private NodoAVL<T> left;
    private NodoAVL<T> right;
    public int level;
    private int depth;

    public NodoAVL(T data) {
        this(data, null, null);
    }

    public NodoAVL(T data, NodoAVL<T> left, NodoAVL<T> right) {
        super();
        this.data = data;
        this.left = left;
        this.right = right;
        if (left == null && right == null)
            setDepth(1);
        else if (left == null)
            setDepth(right.getDepth() + 1);
        else if (right == null)
            setDepth(left.getDepth() + 1);
        else
            setDepth(Math.max(left.getDepth(), right.getDepth()) + 1);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodoAVL<T> getLeft() {
        return left;
    }

    public void setLeft(NodoAVL<T> left) {
        this.left = left;
    }

    public NodoAVL<T> getRight() {
        return right;
    }

    public void setRight(NodoAVL<T> right) {
        this.right = right;
    }

    /**
     * @return the depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * @param depth
     *            the depth to set
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public int compareTo(NodoAVL<T> o) {
        return this.data.compareTo(o.data);
    }

    @Override
    public String toString() {
        return "Level " + level + ": " + data;
    }

}
