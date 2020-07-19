package zone.tec.servidor.clases.Estructuras;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import zone.tec.servidor.clases.AlmacenDeEstructuras;
import zone.tec.servidor.clases.Empresa;
import zone.tec.servidor.clases.JSONManager;
import zone.tec.servidor.clases.Usuario;

/**
 * Árbol binario de búsqueda en el que el último nodo accesado pasa a ser la raíz. Utiliza el Mismo nodo que el árbol
 * binario de búsqueda.
 * @param <T> Tipo de dato que almacena el árbol
 */
public class ArbolSplay<T extends Comparable<? super T>> {

    private NodoArbolBusqueda<T> raiz;
    private final NodoArbolBusqueda<T> aux;

    /**
     * Constructor del árbol splay. Similar al del binario de búsqueda
     */
    public ArbolSplay() {
        raiz = null;
        aux = new NodoArbolBusqueda<T>(null);
    }

    /**
     * Agrega un elemento al árbol que pasa a ser la raíz
     * @param element Elemento a insertar
     * @return Falso si el elemento ya está presente, si no es verdadero
     */
    public boolean insert(T element) {
        if (raiz == null) {
            raiz = new NodoArbolBusqueda<T>(element);
            return true;
        }
        splay(element);

        final int comparacion = element.compareTo(raiz.getElemento());
        if (comparacion == 0) {
            return false;
        }

        NodoArbolBusqueda<T> nodoNuevo = new NodoArbolBusqueda<T>(element);
        if (comparacion < 0) {
            nodoNuevo.setNodoIzquierdo(raiz.getNodoIzquierdo());
            nodoNuevo.setNodoDerecho(raiz);
            raiz.setNodoIzquierdo(null);
        } else {
            nodoNuevo.setNodoDerecho(raiz.getNodoDerecho());
            nodoNuevo.setNodoIzquierdo(raiz);
            raiz.setNodoDerecho(null);
        }
        raiz = nodoNuevo;
        return true;
    }

    /**
     * Elimina un elemento del árbol y pone como raíz al nodo que accedió antes de remover el indicado.
     * @param element Elemento a eliminar
     * @return Falso si el elemento ya está presente, si no es verdadero
     */
    public boolean remove(T element) {
        splay(element);

        if (element.compareTo(raiz.getElemento()) != 0) {
            return false;
        }

        // Se elimina la raíz
        if (raiz.getNodoIzquierdo() == null) {
            raiz = raiz.getNodoDerecho();
        } else {
            NodoArbolBusqueda<T> nuevaRaiz = raiz.getNodoDerecho();
            raiz = raiz.getNodoIzquierdo();
            splay(element);
            raiz.setNodoDerecho(nuevaRaiz);
        }
        return true;
    }

    /**
     * Busca el elemento más pequeño del árbol y pasa a ser la raíz
     * @return Elemento más pequeño del árbol
     */
    public T findMin() {
        NodoArbolBusqueda<T> nodoMin = raiz;
        if(raiz == null) return null;
        while(nodoMin.getNodoIzquierdo() != null){
            nodoMin = nodoMin.getNodoIzquierdo();
        }
        splay(nodoMin.getElemento());
        return nodoMin.getElemento();
    }



    /**
     * Busca un elemento en el árbol
     * @param element El elemento que se busca
     * @return El elemento (si está en el árbol)
     */
    public T find(T element) {
        if (raiz == null) {
            return null;
        }
        splay(element);
        if(raiz.getElemento().compareTo(element) != 0) {
            return null;
        }
        return raiz.getElemento();
    }

    /**
     * Revisa si el árbol contiene el elemento que se da
     * @param element El elemento que se busca
     * @return Verdadero si está presente, falso si no
     */
    public boolean contains(T element) {
        return find(element) != null;
    }



    /**
     * Método para que el nodo suba a ser la raíz
     * @param element Busca el nodo con el elemento ingrasado para que llegue a ser raíz del árbol.
     */
    private void splay(T element) {
        NodoArbolBusqueda<T> hijoIzquierdo, hijoDerecho, nuevaRaiz, temp;
        hijoIzquierdo = aux;
        hijoDerecho = aux;
        nuevaRaiz = raiz;
        aux.setNodoIzquierdo(null);
        aux.setNodoDerecho(null);
        while(true) {
            final int comp = element.compareTo(nuevaRaiz.getElemento());
            if (comp < 0) {
                if (nuevaRaiz.getNodoIzquierdo() == null) {
                    break;
                }
                // Rotación a la derecha
                if (element.compareTo(nuevaRaiz.getNodoIzquierdo().getElemento()) < 0) {
                    temp = nuevaRaiz.getNodoIzquierdo();
                    nuevaRaiz.setNodoIzquierdo(temp.getNodoDerecho());
                    temp.setNodoDerecho(nuevaRaiz);
                    nuevaRaiz = temp;
                    if (nuevaRaiz.getNodoIzquierdo() == null) {
                        break;
                    }
                }
                // Conexión previa del hijo derecho
                hijoDerecho.setNodoIzquierdo(nuevaRaiz);
                hijoDerecho = nuevaRaiz;
                nuevaRaiz = nuevaRaiz.getNodoIzquierdo();
            } else if (comp > 0) {
                if (nuevaRaiz.getNodoDerecho() == null) {
                    break;
                }
                // Rotación a la izquierda
                if (element.compareTo(nuevaRaiz.getNodoDerecho().getElemento()) > 0) {
                    temp = nuevaRaiz.getNodoDerecho();
                    nuevaRaiz.setNodoDerecho(temp.getNodoIzquierdo());
                    temp.setNodoIzquierdo(nuevaRaiz);
                    nuevaRaiz = temp;
                    if (nuevaRaiz.getNodoDerecho() == null) {
                        break;
                    }
                }
                // Conexión previa del hijo izquierdo
                hijoIzquierdo.setNodoDerecho(nuevaRaiz);
                hijoIzquierdo = nuevaRaiz;
                nuevaRaiz = nuevaRaiz.getNodoDerecho();
            } else {
                break;
            }
        }
        // Conexión final
        hijoIzquierdo.setNodoDerecho(nuevaRaiz.getNodoIzquierdo());
        hijoDerecho.setNodoIzquierdo(nuevaRaiz.getNodoDerecho());
        nuevaRaiz.setNodoIzquierdo(aux.getNodoDerecho());
        nuevaRaiz.setNodoDerecho(aux.getNodoIzquierdo());
        raiz = nuevaRaiz;
    }

    public void JSONinsert(JSONArray array)
        {
            for (Object i : array) {
                Empresa x=new Empresa((JSONObject) i) ;
                if(raiz!=null){
                    insert((T) x);}
                else
                {raiz= (NodoArbolBusqueda<T>) new NodoArbolBusqueda<Empresa>( x);}
            }
        }


    public JSONArray LastConsulted()
        {
            return LastConsulted(2, new JSONArray(), (NodoArbolBusqueda<Empresa>) raiz);


        }
    private JSONArray LastConsulted(int i,JSONArray response,NodoArbolBusqueda<Empresa> nodo)
    {   JSONManager js= new JSONManager(AlmacenDeEstructuras.getContexto());

            response.add( js.convertToJSON(nodo.getElemento()));
            i--;
            if(i>0) {
                if (raiz.getNodoIzquierdo() != null) {
                    response = LastConsulted(i, response, nodo.getNodoIzquierdo());
                }
                if (raiz.getNodoDerecho() != null) {
                    response = LastConsulted(i, response, nodo.getNodoDerecho());
                }
            }

            return response;
        }
}

