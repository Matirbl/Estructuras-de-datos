/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPObligatorio;

public class NodoChar {
    //atributos

    private char elemento;
    private NodoChar enlace;

    //constructor
    public NodoChar(char elem, NodoChar enlace) {
        this.elemento = elem;
        this.enlace = enlace;
    }

    public NodoChar(char elem) {
        this.elemento = elem;
        this.enlace = null;
    }
//observadoras

    public char getElemento() {
        return this.elemento;
    }

    public NodoChar getEnlace() {
        return this.enlace;
    }
//modificadoras

    public void setElemento(char elemento) {
        this.elemento = elemento;
    }

    public void setEnlace(NodoChar enlace) {
        this.enlace = enlace;
    }
}
