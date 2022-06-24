/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;

public class NodoGen {

    private Object elem;
    private NodoGen HEI;
    private NodoGen HerD;

    public Object getElem() {
        return elem;
    }

    public NodoGen getHEI() {
        return HEI;
    }

    public NodoGen getHerD() {
        return HerD;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public void setHerD(NodoGen HerD) {
        this.HerD = HerD;
    }

    public void setHEI(NodoGen HEI) {
        this.HEI = HEI;
    }

}
