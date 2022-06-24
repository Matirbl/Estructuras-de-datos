package Grafo;

/**
 *
 * @author JMJ - FAI-1629, FAI-1648, FAI-974
 */
public class NodoVert {
    
    //atributos
    private Object elemento;
    private NodoVert siguienteVertice;
    private NodoAdy primerAdyacente;

    //Constructores
    public NodoVert(Object elem) {
        this.elemento = elem;
        this.siguienteVertice = null;
        this.primerAdyacente = null;
    }
    public NodoVert(Object elem, NodoVert sigVert) {
        this.elemento = elem;
        this.siguienteVertice = sigVert;
        this.primerAdyacente = null;
    }
    
    //getters & setters

    public Object getElem() {
        return elemento;
    }

    public void setElem(Object elemento) {
        this.elemento = elemento;
    }

    public NodoVert getSigVertice() {
        return siguienteVertice;
    }

    public void setSigVertice(NodoVert siguienteVertice) {
        this.siguienteVertice = siguienteVertice;
    }

    public NodoAdy getPrimerAdyacente() {
        return primerAdyacente;
    }

    public void setPrimerAdyacente(NodoAdy primerAdyacente) {
        this.primerAdyacente = primerAdyacente;
    }
    
}
