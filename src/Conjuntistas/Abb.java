package Conjuntistas;

import jerarquicas.NodoArbolInt;
import lineales.dinamicas.Lista;

public class Abb {

    public Abb() {
        this.raiz = null;
    }

    private NodoArbol raiz;

    public boolean pertenece(Comparable elem) {
        boolean exito = true;
        if (this.raiz != null) {
            perteneceRec(this.raiz, elem);
        } else {
            exito = false;
        }
        return exito;
    }

    private boolean perteneceRec(NodoArbol n, Comparable elem) {
        boolean seEncuentra = false;
        if (n != null) {
            if (n.getElemento().compareTo(elem) == 0) {
                seEncuentra = true;
            } else if (n.getElemento().compareTo(elem) < 0) {
                seEncuentra = perteneceRec(n.getDerecho(), elem);
            } else {
                seEncuentra = perteneceRec(n.getIzquierdo(), elem);
            }

        }
        return seEncuentra;
    }

    public boolean esVacio() {
        boolean exito = false;
        if (this.raiz == null) {
            exito = true;
        }
        return exito;
    }

    public boolean insertar(Comparable elem) {
        boolean sePudo = true;
        if (this.raiz == null) {
            this.raiz = new NodoArbol(elem);
        } else {
            sePudo = insertarRec(this.raiz, elem);
        }

        return sePudo;

    }

    private boolean insertarRec(NodoArbol n, Comparable elem) {
        boolean exito = true;
        if (n != null) {
            if (elem.compareTo(n.getElemento()) == 0) {
                exito = false;
            } else if (elem.compareTo(n.getElemento()) < 0) {
                if (n.getIzquierdo() != null) {
                    exito = insertarRec(n.getIzquierdo(), elem);
                } else {
                    n.setIzquierdo(new NodoArbol(elem));
                }
            } else if (n.getDerecho() != null) {
                exito = insertarRec(n.getDerecho(), elem);
            } else {
                n.setDerecho(new NodoArbol(elem));
            }

        }
        return exito;
    }

    public Lista listar() {
        Lista elementos = new Lista();
        llenarLista(this.raiz, elementos);
        return elementos;
    }

    private void llenarLista(NodoArbol n, Lista list) {
        if (n.getIzquierdo() != null) {
            llenarLista(n.getIzquierdo(), list);
        }
        list.insertar(n.getElemento(), 1);
        if (n.getDerecho() != null) {
            llenarLista(n.getDerecho(), list);
        }
    }

    public Lista listarRango(Comparable elemMin, Comparable elemMax) {
        Lista elementos = new Lista();
        llenarLista2(this.raiz, elementos, elemMin, elemMax);
        return elementos;
    }

    private void llenarLista2(NodoArbol n, Lista list, Comparable min, Comparable max) {

    }

    public String toString() {
        String cad;
        cad = stringRecursivo(this.raiz);
        return cad;
    }

    private String stringRecursivo(NodoArbol n) {
        String cadena = "";
        if (n != null) {
            if (n.getIzquierdo() != null) {
                cadena += "Nodo: " + n.getElemento() + " Hijo Izquierdo: " + n.getIzquierdo().getElemento();
            } else {
                cadena += "Nodo: " + n.getElemento() + " Hijo Izquierdo: vacio";
            }
            if (n.getDerecho() != null) {
                cadena += " Hijo Derecho: " + n.getDerecho().getElemento();
            } else {
                cadena += " Hijo Derecho: vacio ";
            }
            cadena += "\n";
            if (n.getIzquierdo() != null) {
                cadena += stringRecursivo(n.getIzquierdo());
            }
            if (n.getDerecho() != null) {
                cadena += stringRecursivo(n.getDerecho());
            }
        }

        return cadena;
    }

    public boolean eliminar(Comparable elem) {
        boolean sePudo = false;
        sePudo = eliminarAux(elem, this.raiz);
        return sePudo;
    }

    private boolean eliminarRec(Comparable elem, NodoArbol n) {
        boolean res = false;
        if (n.getIzquierdo().getElemento().compareTo(elem) == 0) {
            NodoArbol aux = n.getIzquierdo();
            if (aux.getDerecho() == null && aux.getIzquierdo() == null) {
                n.setIzquierdo(null);
            }
            if (aux.getDerecho() != null && aux.getIzquierdo() == null) {
                n.setIzquierdo(aux.getDerecho());
            }
            if (aux.getDerecho() == null && aux.getIzquierdo() != null) {
                n.setIzquierdo(aux.getIzquierdo());
            }
            if (aux.getDerecho() != null && aux.getIzquierdo() != null) {
                NodoArbol aux2 = aux.getDerecho();
                while (aux2.getIzquierdo() != null) {
                    aux2 = aux2.getIzquierdo();
                }
                n.setDerecho(aux2);
                aux2.setDerecho(aux.getDerecho());
                aux2.setIzquierdo(aux.getIzquierdo());
            }
            res = eliminarRec(elem, n.getIzquierdo());

        }

        if (n.getDerecho().getElemento().compareTo(elem) == 0) {
            NodoArbol aux = n.getDerecho();
            if (aux.getDerecho() == null && aux.getIzquierdo() == null) {
                n.setDerecho(null);
            }
            if (aux.getDerecho() != null && aux.getIzquierdo() == null) {
                n.setDerecho(aux.getDerecho());
            }
            if (aux.getDerecho() == null && aux.getIzquierdo() != null) {
                n.setDerecho(aux.getIzquierdo());
            }
            if (aux.getDerecho() != null && aux.getIzquierdo() != null) {
                NodoArbol aux2 = aux.getDerecho();
                while (aux2.getIzquierdo() != null) {
                    aux2 = aux2.getIzquierdo();
                }
                n.setDerecho(aux2);
                aux2.setDerecho(aux.getDerecho());
                aux2.setIzquierdo(aux.getIzquierdo());

            }
            res = eliminarRec(elem, n.getDerecho());

        }
        return res;
    }

    private boolean eliminarAux(Comparable elem, NodoArbol n) {
        boolean res = false;

        if (compararHijos(n, elem)) {
            if (elem.compareTo(n.getElemento()) < 0) {
                NodoArbol aux = n.getIzquierdo();
                if (aux.getDerecho() == null && aux.getIzquierdo() == null) {
                    n.setIzquierdo(null);
                }
                if (aux.getDerecho() != null && aux.getIzquierdo() == null) {
                    n.setIzquierdo(aux.getDerecho());
                }
                if (aux.getDerecho() == null && aux.getIzquierdo() != null) {
                    n.setIzquierdo(aux.getIzquierdo());
                }
                if (aux.getDerecho() != null && aux.getIzquierdo() != null) {
                    NodoArbol aux2 = aux;
                    aux = aux.getIzquierdo();
                    while (aux.getDerecho() != null) {
                        aux2 = aux;
                        aux = aux.getDerecho();
                    }

                    n.getIzquierdo().setElemento(aux.getElemento());
                    eliminarAux(aux.getElemento(), aux2);

                }
            } else {

                NodoArbol aux = n.getDerecho();
                if (aux.getDerecho() == null && aux.getIzquierdo() == null) {
                    n.setDerecho(null);
                }
                if (aux.getDerecho() != null && aux.getIzquierdo() == null) {
                    n.setDerecho(aux.getDerecho());
                }
                if (aux.getDerecho() == null && aux.getIzquierdo() != null) {
                    n.setDerecho(aux.getIzquierdo());
                }
                if (aux.getDerecho() != null && aux.getIzquierdo() != null) {
                    NodoArbol aux2 = aux;
                    aux = aux.getDerecho();
                    while (aux.getIzquierdo() != null) {
                        aux2 = aux;  // el padre de aux
                        aux = aux.getIzquierdo();
                    }
                    n.getDerecho().setElemento(aux.getElemento());
                    eliminarAux(aux.getElemento(), aux2);

                }

            }

        } else if (elem.compareTo(n.getElemento()) < 0) {
            res = eliminarAux(elem, n.getIzquierdo());
        } else {
            res = eliminarAux(elem, n.getDerecho());
        }
        return res;

    }

    private boolean compararHijos(NodoArbol x, Comparable elem) {
        boolean exito = false;
        if (x != null) {
            if (x.getIzquierdo() != null) {
                if (elem.compareTo(x.getIzquierdo().getElemento()) == 0) {
                    exito = true;
                }
            }
            if (x.getDerecho() != null) {

                if (elem.compareTo(x.getDerecho().getElemento()) == 0) {
                    exito = true;
                }
            }
        }
        return exito;
    }

    public boolean eliminarMenorElem(NodoArbol a) {
        boolean res = false;
        if (a.getIzquierdo() == null) {
            a = a.getDerecho();
        } else {
            res = eliminarMenorAux(a);
        }
        return res;
    }

    private boolean eliminarMenorAux(NodoArbol x) {
        boolean res = false;
        if (x != null) {
            if (x.getIzquierdo().getIzquierdo() == null) {
                if (x.getIzquierdo().getDerecho() != null) {
                    x.setIzquierdo(x.getIzquierdo().getDerecho());
                } else {
                    x.setIzquierdo(null);
                }
            } else {
                res = eliminarMenorAux(x.getIzquierdo());
            }

        }
        return res;
    }

    public boolean eliminarMinimoEliminar(Comparable elemento) {
        boolean respuesta;
        respuesta = eliminarMinimoAux(this.raiz, elemento);
        return respuesta;
    }

    private boolean eliminarMinimoAux(NodoArbol a, Comparable elemento) {
        boolean respuesta = false;
        if (a != null) {
            if (elemento.compareTo(a.getElemento()) == 0) {

                eliminarMenorElem(a);
                respuesta = true;
            } else if (elemento.compareTo(a.getIzquierdo().getElemento()) < 0) {
                respuesta = eliminarMinimoAux(a.getIzquierdo(), elemento);
            } else {
                respuesta = eliminarMinimoAux(a.getDerecho(), elemento);
            }
        }
        return respuesta;
    }
}
//    public Abb clonarInvertido(Abb arbol) {
//        Abb clonIn = new Abb();
//        clonIn.raiz = clonarInvertidoAux(this.raiz);
//        return clonIn;
//    }

