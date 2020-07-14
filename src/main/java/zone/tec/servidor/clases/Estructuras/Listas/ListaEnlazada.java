package zone.tec.servidor.clases.Estructuras.Listas;

/**
 * Superclass of linear data structures where each element can be accessed and manipulated freely.
 * @param <T> Data type on Nodes
 */
public abstract class ListaEnlazada<T> extends EstructuraLineal {

    protected NodoLista<T> head;

    /**
     * Clears contents on list and leaves it empty
     */
    public abstract void clear();

    /**
     * returns first node of list
     * @return first node
     */
    public abstract NodoLista<T> getHead();

    /**
     * Returns last Node of List
     * @return last Node
     */
    public abstract NodoLista<T> getLast();

    /**
     * Returns Node on given position at index
     * @param index where Node is located
     * @return Node indexed
     */
    public abstract NodoLista<T> get(int index);

    /**
     * Adds a Node to the List
     * @param data info to be added on List
     */
    public abstract void add(T data);

    /**
     * Adds a Node to list on given index
     * @param data info to be added on List
     * @param index where the Node will be added
     */
    public abstract void add(T data, int index);

    /**
     * Remove a Node on the List on given index
     * @param index where Node to be removed is located
     */
    public abstract void remove(int index);
}
