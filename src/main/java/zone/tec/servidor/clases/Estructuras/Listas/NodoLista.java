package zone.tec.servidor.clases.Estructuras.Listas;

/**
 * Superclass of all Nodes. Stores data on lists.
 * @param <T> Data type of the info stored on each Node
 */
public  abstract class NodoLista<T> {

    protected T data;

    /**
     * Constructor that sets the data to the node
     * @param data data of generic type saved on new node
     */
    public NodoLista(T data) {
        this.data = data;
    }

    /**
     * Gets the data store on the node
     * @return data stored on node
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data stored on node
     * @param data data stored on node
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Gets the previous node on doubly nodes
     * @return previous node (only on doubly nodes)
     */
    public abstract NodoLista<T> getPrevious();

    /**
     * Sets the previous reference on doubly nodes
     * @param previous previous reference
     */
    public abstract void setPrevious(NodoLista<T> previous);

    /**
     * Gets the next node on both singly and doubly node
     * @return next node
     */
    public abstract NodoLista<T> getNext();

    /**
     * Sets the next reference on both singly and doubly node
     * @param next next reference
     */
    public abstract void setNext(NodoLista<T> next);
}
