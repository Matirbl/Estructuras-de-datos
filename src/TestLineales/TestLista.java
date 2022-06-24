/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestLineales;

import Utiles.*;
import lineales.dinamicas.Lista;

public class TestLista {

    public static void main(String[] args) {

        Lista ListaPrueba;
        ListaPrueba = new Lista();
        int opcion;

        do {
            opcion = menu();
            switch (opcion) {

                case 1:
                    System.out.println("Ingrese elemento a agregar a la lista");
                    int x = TecladoIn.readLineInt();
                    System.out.println("Ingrese la posicion en la que desea insertar entre 1 y " + (ListaPrueba.longitud() + 1));
                    int y = TecladoIn.readLineInt();
                    boolean sePudo = ListaPrueba.insertar(x, y);
                    if (sePudo) {
                        System.out.println("Se pudo agregar elemento");
                    } else {
                        System.err.println("No se pudo agregar elemento");
                    }
                    System.out.println(ListaPrueba.toString());
                    break;

                case 2:
                    System.out.println("Que posicion desea eliminar?");
                    int pos = TecladoIn.readLineInt();
                    boolean sePudo2 = ListaPrueba.eliminar(pos);
                    if (sePudo2) {
                        System.out.println("Se pudo sacar elemento");
                    } else {
                        System.out.println("No se pudo sacar elemento");
                    }
                    break;

                case 3:
                    System.out.println("Ingrese elemento a buscar");
                    int buscado = TecladoIn.readLineInt();
                    int encontrado = ListaPrueba.localizar(buscado);
                    if (encontrado == -1) {

                        System.out.println("El elemento no esta en la lista");
                    } else {
                        System.out.println("El elemento esta en la posicon" + encontrado);
                        break;
                    }
                case 4:
                    int longi = ListaPrueba.longitud();
                    System.out.println("La longitud es " + longi);

                    break;
                case 5:
                    System.out.println("Ingrese la posicion de la que desea obtener elemento entre 1 y " + ListaPrueba.longitud());
                    int posi = TecladoIn.readLineInt();
                    int elemento = ListaPrueba.recuperar(posi);
                    System.out.println("El elemento en en la posicion " + posi + " es " + elemento);
                    break;
                case 6:
                    if (ListaPrueba.esVacia()) {
                        System.out.println("La lista es vacia");
                    } else {
                        System.out.println("La lista no es vacia");
                    }
                    break;
                case 7:
                    System.out.println(ListaPrueba.toString());

                    break;
                case 8:
                    ListaPrueba.insertarPromedio();
                    System.out.println(ListaPrueba.toString());
                    break;
                case 9:
                    System.out.println("Ingrese elemento a eliminar");
                    int p = TecladoIn.readLineInt();
                    ListaPrueba.eliminarRepetido(p);
                    System.out.println(ListaPrueba.toString());
            }
        } while (opcion != 10);

    }

    public static int menu() {
        int opcion;
        do {
            System.out.println("------------MENU-----------");
            System.out.println("1.Insertar elemento a la lista");
            System.out.println("2.Eliminar elemento");
            System.out.println("3.Localizar elemento");
            System.out.println("4. Obtener Longitud");
            System.out.println("5.Recuperar elemento");
            System.out.println("6.Ver si la lista es vacia ");
            System.out.println("7.Convertir en una cadena");
            System.out.println("8.Insertar promedio");
            System.out.println("9.Eliminar repeticiones");
            System.out.println("10.Salir");
            opcion = TecladoIn.readLineInt();
        } while (opcion > 11 && opcion < 1);
        return opcion;
    }

}
