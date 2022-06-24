/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

/**
 *
 * @author JMJ - FAI-1629, FAI-1648, FAI-974
 */
public class NodoAdy {
    
    //atributos
    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private int etiqueta;
    
    //constructores
    public NodoAdy(NodoVert vertice) {
        this.vertice = vertice;
        this.sigAdyacente = null;
    }
    public NodoAdy(NodoVert vertice, int etiqueta) {
        this.vertice = vertice;
        this.etiqueta = etiqueta;
        this.sigAdyacente = null;
    }
    public NodoAdy(NodoVert vertice, int etiq, NodoAdy sigAdy) {
        this.vertice = vertice;
        this.sigAdyacente = sigAdy;
        this.etiqueta = etiq;
    }
    
    //getters & setters

    public NodoVert getVertice() {
        return vertice;
    }

    public void setVertice(NodoVert vertice) {
        this.vertice = vertice;
    }

    public NodoAdy getSigAdyacente() {
        return sigAdyacente;
    }

    public void setSigAdyacente(NodoAdy sigAdyacente) {
        this.sigAdyacente = sigAdyacente;
    }

    public int getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(int etiqueta) {
        this.etiqueta = etiqueta;
    }
    
    
    
}
