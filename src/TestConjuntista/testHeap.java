/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestConjuntista;

import Conjuntistas.ArbolHeap;

public class testHeap {

    public static void main(String[] args) {

        ArbolHeap arbol = new ArbolHeap();

        arbol.insertar(1);
        arbol.insertar(2);
        arbol.insertar(3);
        arbol.insertar(4);
        arbol.insertar(5);
        arbol.insertar(7);
        arbol.insertar(9);
        arbol.insertar(12);

        System.out.println(arbol.toString());

    }
}
