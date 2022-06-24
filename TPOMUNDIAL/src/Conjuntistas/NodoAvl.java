/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conjuntistas;

/**
 *
 * @author Pinku
 */
public class NodoAvl {

    private Comparable elem;
    private int altura = 0;
    private NodoAvl izq = null;
    private NodoAvl der = null;

    public NodoAvl(Comparable elemN) {
        this.elem = elemN;
    }

    public Comparable getElem() {
        return elem;
    }

    public int getAltura() {
        return altura;
    }

    public NodoAvl getIzq() {
        return izq;
    }

    public NodoAvl getDer() {
        return der;
    }

    public void setElem(Comparable elem) {
        this.elem = elem;
    }

    public void setIzq(NodoAvl izq) {
        this.izq = izq;
        if (izq != null) {
            izq.recalcularAltura();
        }
        this.recalcularAltura();
    }

    public void setDer(NodoAvl der) {
        this.der = der;
        if (der != null) {
            der.recalcularAltura();
        }
        this.recalcularAltura();
    }

    public void recalcularAltura() {
        //la altura de un nodo vacio es -1
        int alturaHi = -1, alturaHd = -1;
//si mi hijoIzq no es nulo
        if (this.izq != null) {
            alturaHi = this.izq.getAltura();
        }
        // si mi hijoDer no es nulo
        if (this.der != null) {
            alturaHd = this.der.getAltura();
        }

        if (alturaHi >= alturaHd) {
            //sumo 1 porque cuento el nivel del padre ( altura padre = alturaMaxHijo +1)
            this.altura = alturaHi + 1;
        } else {
            this.altura = alturaHd + 1;
        }
    }
}
