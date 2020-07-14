package zone.tec.servidor.clases.Estructuras.Listas;

/**
 * Superclass made to add length to Lists and Stack
 */
public abstract class EstructuraLineal {

    protected int length;

    /**
     * Returns the length of the structure. Used on every structure.
     * @return
     */
    public int getLength() {
        return length;
    }
}
