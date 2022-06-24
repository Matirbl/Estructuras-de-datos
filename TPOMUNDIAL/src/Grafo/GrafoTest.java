package Grafo;

import lineales.dinamicas.*;

/**
 *
 * @author JMJ - FAI-1629, FAI-1648, FAI-974
 */
public class GrafoTest {

    private static Grafo grafote = new Grafo();

    public static void main(String[] args) {

        Object arr = new Object[2];
        System.out.println("Inserción de A: " + grafote.insertarVertice('A'));
        System.out.println("Inserción de B: " + grafote.insertarVertice('B'));
        System.out.println("Inserción de C: " + grafote.insertarVertice('C'));
        System.out.println("Inserción de C: " + grafote.insertarVertice('D'));
        System.out.println("Inserción de C: " + grafote.insertarVertice('E'));

        System.out.println("Inserción del arco A->C: " + grafote.insertarArco('A', 'C', 30));
        System.out.println("Inserción del arco C->B: " + grafote.insertarArco('A', 'B', 50));
        System.out.println("Inserción del arco C->B: " + grafote.insertarArco('C', 'B', 230));
        System.out.println("Inserción del arco C->B: " + grafote.insertarArco('A', 'D', 70));
        System.out.println("Inserción del arco C->B: " + grafote.insertarArco('D', 'E', 40));
        System.out.println("Inserción del arco C->B: " + grafote.insertarArco('E', 'B', 80));

        System.out.println("Grafote: " + grafote.toString());

        System.out.println("Lista del camino'A', 'C' más corto: " + grafote.caminoMasCorto('A', 'C')); //ok!!!
        System.out.println("Lista del camino más corto que pasa por C:" + grafote.caminoMasCortoQuePasaPorC('A', 'B', 'C'));
        System.out.println("Lista de caminos posibles de A a B:" + grafote.caminosPosibles('A', 'B'));
        System.out.println("Lista del camino 'A', 'B' mas corto en km:" + ((Lista) grafote.caminoMenosKm('A', 'E')[0]).toString() + " con longitud: " + (grafote.caminoMenosKm('A', 'E')[1]).toString() + " KM ");

    }
}
