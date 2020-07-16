package zone.tec.servidor.clases.Estructuras.Arboles;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import zone.tec.servidor.clases.AlmacenDeEstructuras;
import zone.tec.servidor.clases.JSONManager;
import zone.tec.servidor.clases.Usuario;

/**
 * Árbol binario con el hijo menor a la izquierda y el mayor a la derecha.
 * @param <T> Tipo de dato que almacena el árbol
 */
public class ArbolBinarioBusqueda<T extends Comparable<? super T>> {

    private NodoArbolBusqueda<T> raiz;
    private int ultimaID;

    public ArbolBinarioBusqueda() {
            ultimaID=0;
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

    /**
     * Llamado úicamente en recursión. Revisa los hijos izquierdos buscando el elemento más pequeño del árbol
     * @param nodo Nodo en el que se encuentra buscando al más pequeño
     * @return elemento más pequeño
     */
    private NodoArbolBusqueda<T> findMin(NodoArbolBusqueda<T> nodo) {
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
    private NodoArbolBusqueda<T> findMax(NodoArbolBusqueda<T> nodo) {
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

        if(Integer.parseInt(((Usuario) elemento).getId())>ultimaID)
            {
                ultimaID=Integer.parseInt(((Usuario) elemento).getId());
            }
        raiz = insert(elemento, raiz);
    }

    /**
     * Llamado úicamente en recursión. Busca la hoja apropiada donde se agrega el nuevo elemento, revisando si son
     * mayores o menores que él y recorriendolo a la izquierda o derecha, según corresponda
     * @param elemento Elemento a insertar
     * @param nodo Ubicación actual, siguiendo el camino del nodo a insertar
     * @return Nodo que sigue el camino a recorrer
     */
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

    /**
     * Llamado úicamente en recursión. Busca unicamente un elemento
     * @param thing Elemento a buscar
     * @return Nodo que sigue el camino a recorrer
     */

    public JSONObject lookForOne( String thing)
        {
            return lookForOne(thing,raiz);
        }
    private JSONObject lookForOne(String thing, NodoArbolBusqueda puntero)
        {
            if(((Usuario)puntero.getElemento()).getNombreCompleto().equals(thing))
                {
                    JSONObject  response= new JSONManager(AlmacenDeEstructuras.getContexto()).convertToJSON(puntero.getElemento());
                    return response;
                }
            else {
                int comparacion=thing.compareTo(((Usuario) puntero.getElemento()).getNombreCompleto());
                if(comparacion>1)
                    {
                        puntero=puntero.getNodoDerecho();
                    }
                else
                    {
                        puntero=puntero.getNodoIzquierdo();
                    }
                if(puntero!=null){
                return lookForOne(thing,puntero);}
                else {return null;}

            }
        }

    /**
     * Llamado úicamente en recursión. Busca varios elementos con similitud
     * @param thing Elemento a buscar
     * @param cantidad cantidad maxima de resultados
     * @Author Adrián González
     * @return Array de usuarios que comparten la indicación
     */
    public  JSONArray lookForSome(String  thing, int cantidad)
        {
            return lookForSome(thing,raiz,cantidad,new JSONArray());
        }
    private JSONArray lookForSome(String thing, NodoArbolBusqueda puntero,int cantidad,JSONArray respuesta)
        {
            if(cantidad>0)
                {
                    //si contiene lo que busco, o es igual, o tiene la primera letra....
                    if (((Usuario) puntero.getElemento()).getNombre().equals(thing) || ((Usuario) puntero.getElemento()).getNombreCompleto().contains(thing) )
                        {   //lo añado a mi respuesta
                            cantidad-=1;
                            respuesta.add(new JSONManager(AlmacenDeEstructuras.getContexto()).convertToJSON(puntero.getElemento()));
                        }
                    //si estoy en un nodo que no contiene lo que busco
                    else
                        {int comparacion= thing.compareTo(((Usuario) puntero.getElemento()).getNombreCompleto());
                            NodoArbolBusqueda newPuntero=puntero;
                            if(comparacion>1)
                                {   if(puntero.getNodoDerecho()!=null){
                                    newPuntero= puntero.getNodoDerecho();}
                                }
                            else
                                {
                                    if(puntero.getNodoIzquierdo()!=null){
                                        newPuntero=puntero.getNodoIzquierdo();}
                                }
                            if(puntero!=null&&puntero!=newPuntero){
                            return lookForSome(thing,newPuntero,cantidad,respuesta);}
                            else {return respuesta;}


                        }
                    //si resulta y acontece que su nodo derecho posee igualmente la cosa, analicemoslo a él
                    if(puntero.getNodoDerecho()!=null)
                        {
                            if (((Usuario) puntero.getNodoDerecho().getElemento()).getNombreCompleto().contains(thing)) {
                                //añado si tengo que añadir
                                //respuesta = lookForSome(thing, puntero.getNodoDerecho(), cantidad, respuesta);


                                respuesta=  lookForSome(thing,puntero.getNodoDerecho(),cantidad,respuesta);

                            }
                        }
                    //si resulta y acontece que su nodo izquierdo posee igualmente la cosa, analicemoslo a él
                     if(puntero.getNodoIzquierdo()!=null)
                        {
                            if (((Usuario) puntero.getNodoIzquierdo().getElemento()).getNombreCompleto().contains(thing)) {   //añado si tengo que añadir
                                //respuesta = lookForSome(thing, puntero.getNodoDerecho(), cantidad, respuesta);

                                respuesta=  lookForSome(thing,puntero.getNodoIzquierdo(),cantidad,respuesta);


                            }
                        }
                }
            else {return respuesta;}
         return respuesta;
        }


    public JSONObject findUserBynameAndID(String name, String id)
        {
            for(Object i:lookForSome(name,15))
                {
                    if(((JSONObject) i).get("id").equals(id))
                        {
                            return (JSONObject) i;
                        }
                }
            return null;
        }
    /**
     * Agrega objetos desde un JSONArray
     * @param array arreglo de objetos JSON que componen al árbol
     * @Author Adrián González
     * @return nada
     */
    public void JSONinsert(JSONArray array)
    {
        for (Object i : array) {
            Usuario x=new Usuario((JSONObject) i) ;
            if(raiz!=null){
                insert((T) x);}
            else
                {raiz= new NodoArbolBusqueda<T>((T) x);}
        }
    }

    public int getUltimaID() {
        return ultimaID;
    }
}
