/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestLineales;

import Utiles.*;
import lineales.dinamicas.Cola;
import lineales.dinamicas.PilaInt;

public class TestCola {

    public static void main(String[] args) {
        Cola colaPrueba;
        colaPrueba = new Cola();
        int opcion;

        do {
            opcion = menu();
            switch (opcion) {

                case 1:
//                    System.out.println("Ingrese elemento a agregar a la cola");
//                    int x = TecladoIn.readLineInt();
//                    boolean sePudo = colaPrueba.poner(x);
//                    if (sePudo) {
//                        System.out.println("Se pudo agregar elemento");
//                    } else {
//                        System.err.println("No se pudo agregar elemento");
//                    }
                    colaPrueba.poner(1);
                    colaPrueba.poner(2);
                    colaPrueba.poner(3);
                    colaPrueba.poner(0);
                    colaPrueba.poner(4);
                    colaPrueba.poner(5);
                    colaPrueba.poner(0);

                    break;

                case 2:
                    boolean sePudo2 = colaPrueba.sacar();
                    if (sePudo2) {
                        System.out.println("Se pudo sacar elemento");
                    } else {
                        System.out.println("No se pudo sacar elemento");
                    }
                    break;

                case 3:
                    if (colaPrueba.esVacia()) {
                        System.out.println("La cola es vacia");
                    } else {
                        System.out.println("La cola no es vacia");
                    }
                    break;

                case 4:
                    System.out.println("El frente es: " + colaPrueba.obtenerFrente());

                    break;
                case 5:
                    colaPrueba.vaciar();
                    break;
                case 6:
                    //todavia no implemente el metodo clonar en la clase de ColaDinamica
                    //           Cola clon = colaPrueba.clon();
                    //         System.out.println(clon.toString());

                    break;
                case 7:
                    System.out.println(colaPrueba.toString());

                    break;
                case 8:

//                    System.out.println(generarCola(colaPrueba).toString());
                    break;

            }
        } while (opcion != 9);

    }

    public static int menu() {
        int opcion;
        do {
            System.out.println("------------MENU-----------");
            System.out.println("1.Poner elemento");
            System.out.println("2.Sacar elemento");
            System.out.println("3.Ver si la cola es vacia");
            System.out.println("4.Obtener el frente de la cola");
            System.out.println("5.Vaciar cola");
            System.out.println("6.Clonar la cola");
            System.out.println("7.Convertir en una cadena");
            System.out.println("8.Generar cola");
            System.out.println("9.Salir");
            opcion = TecladoIn.readLineInt();
        } while (opcion > 10 && opcion < 1);
        return opcion;
    }

//    public static Cola generarCola(Cola cola) {
//        Cola aux1 = new Cola();
//        Cola aux2 = new Cola();
//        Cola resultante = new Cola();
//        PilaInt pilaAux = new PilaInt();
//
//        while (!cola.esVacia()) {
//            if (cola.obtenerFrente() != 0) {
//                pilaAux.apilar(cola.obtenerFrente());
//                aux1.poner(cola.obtenerFrente());
//                aux2.poner(cola.obtenerFrente());
//            } else {
//                while (!aux1.esVacia()) {
//                    resultante.poner(aux1.obtenerFrente());
//                    aux1.sacar();
//                }
//                while (!pilaAux.esVacia()) {
//                    resultante.poner(pilaAux.obtenerTope());
//                    pilaAux.desapilar();
//                }
//                while (!aux2.esVacia()) {
//                    resultante.poner(aux2.obtenerFrente());
//                    aux2.sacar();
//                }
//                resultante.poner(0);
//            }
//            cola.sacar();
//
//        }
//        return resultante;
//
//    }
}
