/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamicas;



/*@author JMJ - FAI-1629, FAI-1648, FAI-974*/
public class Cola {

    private Nodo frente;
    private Nodo fin;

    public Cola() {
        this.fin = null;
        this.frente = null;

    }

    public boolean poner(int n) {
        boolean sePudo = true;
        Nodo aux = new Nodo(n, null);
        if (this.fin == null && this.frente == null) {
            this.frente = aux;
            this.fin = aux;
        } else {
            this.fin.setEnlace(aux);
        }
        this.fin = aux;
        return sePudo;
    }

    public boolean esVacia() {
        boolean vacia = false;
        if (this.fin == null && this.frente == null) {
            vacia = true;
        }
        return vacia;
    }

    public boolean sacar() {
        boolean sePudo = true;
        if (this.esVacia()) {
            sePudo = false;
        } else {
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
        }
        return sePudo;
    }

    public Object obtenerFrente() {
        return this.frente.getElemento();
    }

    public void vaciar() {
        this.fin = null;
        this.frente = null;

    }

    public String toString() {
        String cadena = "";
        if (this.esVacia()) {
            cadena = "Es vacía";
        } else {
            cadena += "[ ";
            Nodo aux = this.frente;
            while (aux.getEnlace() != null) {
                cadena += aux.getElemento();
                aux = aux.getEnlace();
                if (aux.getEnlace() != null) {
                    cadena += ',';
                }
            }
            cadena += ',';
            cadena += aux.getElemento();
            cadena += "]";
        }
        return cadena;
    }

}
