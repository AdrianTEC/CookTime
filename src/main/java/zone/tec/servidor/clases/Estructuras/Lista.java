package zone.tec.servidor.clases.Estructuras;

import zone.tec.servidor.clases.Receta;
import zone.tec.servidor.clases.Usuario;

public class Lista {

    public Nodo ultimo;
    public Nodo primero;
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
            ultimo.setINDEX(tamano);
            tamano+=1;
            primero= ultimo;
        }

        else
        {
            ultimo.setSiguiente(casillita);
            ultimo =casillita;
            ultimo.setINDEX(tamano);
            tamano+=1;

        }
    }



    }

