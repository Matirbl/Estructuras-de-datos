/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestArbolGen;

import jerarquicas.ArbolGen;
import lineales.dinamicas.*;

public class TestArbolGen {

    public static void main(String[] args) {
        Lista l1 = new Lista();

        ArbolGen a1 = new ArbolGen();

        a1.insertar(0, 20);
        a1.insertar(20, 13);
        a1.insertar(20, 3);
        a1.insertar(20, 18);
        a1.insertar(13, 15);
        a1.insertar(13, 12);
        a1.insertar(3, 5);
        a1.insertar(3, 22);
//        a1.insertar(5, 16);
//        a1.insertar(5, 1);
//        a1.insertar(5, 24);
//        a1.insertar(5, 9);
//        a1.insertar(5, 31);
        a1.insertar(18, 11);
        a1.insertar(18, 27);
        a1.insertar(27, 8);
        a1.insertar(27, 18);

        l1.insertar(4, 1);
        l1.insertar(2, 2);
        l1.insertar(12, 3);
        l1.insertar(5, 4);
        System.out.println(a1.toString());
        if (a1.verificarCamino(l1)) {
            System.out.println("Camino verdadero");
        } else {
            System.out.println("No es un camino valido");
        }
        System.out.println(a1.listarEntreNiveles(1, 2));
        if (a1.eliminarElemento(2)) {
            System.out.println("Se pudo eliminar");

        } else {
            System.out.println("No se pudo");
        }
        System.out.println(a1.toString());
        System.out.println("El grado del arbol es : " + a1.grado());

//        System.out.println("la altura es " + a1.altura());
//        System.out.println("El nivel es " + a1.nivel(8));
//        System.out.println(" Los ancestros son: " + a1.ancestros(8));
    }
}
