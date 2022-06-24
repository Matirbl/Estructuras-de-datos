package testJerarquicas;

import Utiles.TecladoIn;
import jerarquicas.*;
import lineales.dinamicas.Lista;

public class TestArbolBinInt {

    public static int menu() {
        int opcion;
        do {
            System.out.println("------------MENU-----------");
            System.out.println("1.Insertar un elemento al Arbol");
            System.out.println("2.Obtener el padre de un elem a buscar ");
            System.out.println("3.Ver si un arbol es vacio");
            System.out.println("4.Obtener la altura del arbol");
            System.out.println("5.Ver el string de un arbol");
            System.out.println("6.Clonar arbol");
            System.out.println("7.Devolver el nivel de un nodo dado");
            System.out.println("8.Llenar arbol");
            System.out.println("9.listar Preorden");
            System.out.println("10.listar posorden");
            System.out.println("11.listar inorden");
            System.out.println("12.listar por niveles");
            System.out.println("13.Ver si existe camino");
            System.out.println("14.Frontera");
            System.out.println("15.salir");
            opcion = TecladoIn.readLineInt();
        } while (opcion > 14 && opcion < 1);
        return opcion;
    }

    public static void main(String[] args) {
        int opcion;
        ArbolBinInt prueba = new ArbolBinInt();

        do {
            opcion = menu();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el elemento a agregar");
                    int agregar = TecladoIn.readLineInt();
                    System.out.println("Ingrese el padre del elemento");
                    int padre = TecladoIn.readLineInt();
                    System.out.println("Indique la posicion, izquierda(I), derecha (D)");
                    char lado = TecladoIn.readLineNonwhiteChar();
                    if (prueba.insertar(padre, agregar, lado)) {
                        System.out.println("Se pude insertar con exito");
                    } else {
                        System.out.println("No se pudo insertar ");
                    }
                    break;
                case 2:
                    System.out.println("Ingrese el elemento a buscar y devolvera el padre");
                    int n = TecladoIn.readLineInt();
                    int padre1;
                    padre1 = prueba.padre(n);
                    if (padre1 == -1) {
                        System.out.println("No se escontro el elem");
                    } else {
                        System.out.println("El padre es:" + padre1);
                    }
                    break;
                case 3:
                    if (prueba.esVacio()) {
                        System.out.println("El arbol es vacio");
                    } else {
                        System.out.println("El arbol no es vacio");
                    }
                    break;

                case 4:
                    System.out.println("La altura es:" + prueba.altura());
                    break;
                case 5:
                    System.out.println(prueba.toString());
                    break;
                case 6:
                    ArbolBinInt clonado = new ArbolBinInt();
                    clonado = prueba.clon();
                    System.out.println(clonado.toString());
                    break;
                case 7:
                    System.out.println("ingrese el entero a buscar");
                    int enteroABuscar = TecladoIn.readLineInt();
                    System.out.println(prueba.nivel(enteroABuscar));
                    break;
                case 8:
                    prueba.insertar(1, 1, 'd');
                    prueba.insertar(1, 2, 'i');
                    prueba.insertar(2, 3, 'i');
                    prueba.insertar(2, 5, 'd');
                    prueba.insertar(5, 6, 'i');
                    prueba.insertar(1, 7, 'd');
                    prueba.insertar(7, 8, 'd');
                    prueba.insertar(8, 9, 'i');
                    prueba.insertar(9, 10, 'i');
                    prueba.insertar(9, 11, 'd');
                    prueba.insertar(8, 12, 'd');
                    break;
                case 9:
                    System.out.println(prueba.preorden().toString());
                    break;
                case 10:
                    System.out.println(prueba.posOrden().toString());
                    break;
                case 11:
                    System.out.println(prueba.inOrden().toString());
                    break;
                case 13:
                    Lista lista = new Lista();
                    lista.insertar(1, 1);
                    lista.insertar(2, 2);
                    lista.insertar(3, 3);

                    if (prueba.verificarPatron(lista)) {
                        System.out.println("Existe un camino");
                    } else {
                        System.out.println("No existe camino");
                    }
                    break;
                case 14:
                    Lista asd;
                    asd = prueba.frontera();
                    System.out.println(asd.toString());
            }

        } while (opcion != 15);
    }

}
