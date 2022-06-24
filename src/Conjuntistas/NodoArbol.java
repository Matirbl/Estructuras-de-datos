package Conjuntistas;

public class NodoArbol {

    private NodoArbol izquierdo;
    private NodoArbol derecho;
    private Comparable elemento;

    public NodoArbol(Comparable elemento) {
        this.elemento = elemento;
        this.izquierdo = null;
        this.derecho = null;
    }

    public NodoArbol() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDerecho(NodoArbol nodo) {
        this.derecho = nodo;
    }

    public void setIzquierdo(NodoArbol nodo) {
        this.izquierdo = nodo;
    }

    public void setElemento(Comparable elemento) {
        this.elemento = elemento;
    }

    public NodoArbol getIzquierdo() {
        return this.izquierdo;
    }

    public NodoArbol getDerecho() {
        return this.derecho;
    }

    public Comparable getElemento() {
        return this.elemento;
    }
}
