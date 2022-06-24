package TPObligatorio;

public class PilaChar {

    private NodoChar tope;

    public PilaChar() {
        this.tope = null;
    }

    public boolean apilar(char nuevoElem) {
        NodoChar nodoNuevo = new NodoChar(nuevoElem, this.tope);
        this.tope = nodoNuevo;
        return true;
    }

    public boolean desapilar() {
        boolean condicion = false;

        if (this.tope != null) {
            this.tope = this.tope.getEnlace();
            condicion = true;
        }
        return condicion;
    }

    public char obtenerTope() {
        return this.tope.getElemento();
    }

    public boolean esVacia() {
        boolean condicion = false;
        if (this.tope == null) {
            condicion = true;
        }
        return condicion;
    }

    public void vaciar() {
        this.tope = null;
    }

    public PilaChar clonarPila() {
        PilaChar clon = new PilaChar();
        if (this.tope == null) {
            clon.tope = null;
        } else {
            NodoChar aux1 = this.tope;
            NodoChar aux2;
            clon.tope = new NodoChar(aux1.getElemento());
            aux2 = clon.tope;
            aux1 = aux1.getEnlace();
            while (aux1 != null) {
                NodoChar nodoNuevo = new NodoChar(aux1.getElemento());
                aux2.setEnlace(nodoNuevo);
                aux2 = nodoNuevo;
                aux1 = aux1.getEnlace();
            }
        }
        return clon;
    }

    public String toString() {
        String cad = "";

        if (this.tope == null) {
            cad = "la cadena es vacía";
        } else {
            cad = "[";
            NodoChar aux = this.tope;
            while (aux != null) {
                cad += aux.getElemento();
                aux = aux.getEnlace();
                if (aux != null) {
                    cad += ",";
                }

            }
            cad += "]";
        }
        return cad;
    }
}
