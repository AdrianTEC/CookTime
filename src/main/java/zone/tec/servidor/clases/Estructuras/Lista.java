
package Estructuras;

public class Lista {

    public Object ultimo;
    public Object primero;
    public int tamano;
//////////////////////////////////////////////////////////////////////////
////AQUI HAY UN EJEMPLO DE OVERLOAD ///////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////


    //ABSTRACCIÓN PARA NOSOTROS IMPLEMENTAR EL CÓDIGO DE LISTAS LO VEMOS COMO UN CONCEPTO ABSTRACTO
    //EN EL CUAL SE SIMPLIFICÓ A NIVEL DE CÓDIGO LA FORMA EN LA QUE SE DESARROLLAN Y RELACIONAN SUS COMPONENTES



    public int Size()
    {
        return tamano;
    }

    public void ingresarNodo(Nodo casillita)
    {/*This adds a new Casilla to the  list
     *@author  Adrián González
     *@Version 17/05/20
     * @param CasillaSimple casillita
     *
     */
        if(ultimo ==null)

        {
            ultimo = casillita;
            ((Nodo) ultimo).setINDEX(tamano);
            tamano+=1;
            primero= ultimo;
        }

        else
        {
            ((Nodo) ultimo).setSiguiente(casillita);
            ultimo =casillita;
            ((Nodo) ultimo).setINDEX(tamano);
            tamano+=1;

        }
    }


    public Object giveMe(int ind)
    {

        /*This funtion returns the Casilla with the inserted index
         *@author Adrián González Jiménez
         *@Version 02/05/2020
         * @param Casilla
         */
        Object puntero = primero;
        int i =0;
        while (i!=ind)
        {
            if(((Nodo)puntero).getINDEX()==ind)
            {
                return puntero;
            }
            puntero= ((Nodo) puntero).getSiguiente();

            i++;

        }
        return puntero;
    }


    }

