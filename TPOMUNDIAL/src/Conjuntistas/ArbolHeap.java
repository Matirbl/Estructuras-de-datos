/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conjuntistas;

import lineales.dinamicas.Lista;

public class ArbolHeap {

    int TAM;
    private Comparable[] arreglo;
    int ultimo;

    public ArbolHeap() {
        TAM = 20;
        arreglo = new Comparable[TAM];
        ultimo = 0;
        arreglo[0] = null;
    }

    public boolean insertar(Comparable elem) {
        boolean res = true;
        if (arreglo[0] == null) {
            arreglo[0] = elem;
            ultimo++;
        } else if (ultimo <= TAM - 1) {
            ultimo = ultimo + 1;
            arreglo[ultimo] = elem;
            int pos = ultimo;
            while (arreglo[pos] != null && arreglo[pos/2] != null && arreglo[pos].compareTo(arreglo[pos / 2]) >= 0) {

                Comparable aux = arreglo[pos / 2];
                arreglo[pos / 2] = arreglo[pos];
                arreglo[pos] = aux;
                pos = pos / 2;
            }
        } else {
            res = false;
        }
        return res;
    }

    public String toString() {
        int i;
        String cad = "";
        for (i = 1; i <= ultimo; i++) {
            cad += arreglo[i];
        }
        return cad;
    }
    int res = 0;

    public Comparable recuperarCima() {
        Comparable res = 0;
        if (arreglo[0] != null) {
            res = arreglo[1];
        }
        return res;
    }

    public boolean eliminarCima() {
        boolean exito;
        if (this.ultimo == Integer.MAX_VALUE) {
            exito = false;
        } else {
            this.arreglo[1] = this.arreglo[ultimo];
            this.ultimo--;
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }

    private void hacerBajar(int posicion) {
        int hijoMenor;
        Comparable temp = this.arreglo[posicion];
        boolean salir = false;
        while (!salir) {
            hijoMenor = posicion * 2;
            if (hijoMenor <= this.ultimo) {
                if (hijoMenor < this.ultimo) {
                    if (this.arreglo[hijoMenor + 1].compareTo(this.arreglo[hijoMenor]) < 0) {
                        hijoMenor++;
                    }
                }
                if (this.arreglo[hijoMenor].compareTo(temp) < 0) {
                    this.arreglo[posicion] = this.arreglo[hijoMenor];
                    posicion = hijoMenor;
                } else {
                    salir = true;
                }
            } else {
                salir = true;
            }
        }
    }
}
