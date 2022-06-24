package jerarquicas;

import lineales.dinamicas.Lista;

public class ArbolGen {

    private NodoGen raiz;

    private NodoGen buscarNodo(NodoGen a, Object bus) {
        NodoGen retorna = null;
        NodoGen aux = null;
        if (a != null) {
            if (a.getElem().equals(bus)) {
                retorna = a;
            } else {
                aux = a.getHEI();
                while (aux != null && retorna == null) {
                    retorna = buscarNodo(aux, bus);
                    aux = aux.getHerD();
                }
            }
        }
        return retorna;

    }

    public boolean insertar(Object padre, Object dato) {
        boolean retorna = true;
        NodoGen aux3 = null;
        NodoGen aux2 = new NodoGen();
        aux2.setElem(dato);
        if (this.raiz == null) {
            this.raiz = aux2;
        } else {
            NodoGen aux = buscarNodo(this.raiz, padre);
            if (aux == null) {
                retorna = false;
            } else if (aux.getHEI() == null) {
                aux.setHEI(aux2);
            } else {
                aux3 = aux.getHEI();
                while (aux3.getHerD() != null && retorna != false) {
                    if (aux3.equals(aux2)) {
                        retorna = false;
                    }
                    aux3 = aux3.getHerD();

                }
                aux3.setHerD(aux2);
            }
        }
        return retorna;
    }

    public String toString() {

        return (StringRec(this.raiz));
    }

    private String StringRec(NodoGen x) {
        String cad = "";
        if (x != null) {
            cad += x.getElem() + "->";

            NodoGen hijo = x.getHEI();
            while (hijo != null) {
                cad += hijo.getElem();
                hijo = hijo.getHerD();
                if (hijo != null) {
                    cad += ",";
                }
            }
            hijo = x.getHEI();

            while (hijo != null) {
                cad += "\n" + StringRec(hijo);
                hijo = hijo.getHerD();
            }

        }
        return cad;
    }

    public int altura() {
        return alturaAux(this.raiz);

    }

    private int alturaAux(NodoGen n) {
        int cont = -1, aux1;
        NodoGen aux;
        if (n != null) {
//            if (n.getHEI() != null) {
            cont = alturaAux(n.getHEI()) + 1;
//            }
            aux = n.getHerD();
            while (aux != null) {
                aux1 = alturaAux(aux);
                aux = aux.getHerD();
                if (cont < aux1) {
                    cont = aux1;
                }
            }
        }

        return cont;
    }

    public int nivel(Object n) {
        return nivelRec(n, this.raiz, 0);
    }

    private int nivelRec(Object elem, NodoGen n, int niv) {
        int nivel = -1;
        NodoGen aux = null;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                nivel = niv;
            } else {
                aux = n.getHEI();
                while (aux != null && nivel == -1) {
                    nivel = nivelRec(elem, aux, niv + 1);
                    aux = aux.getHerD();
                }

            }
        }

        return nivel;
    }

    public Lista ancestros(Object n) {
        Lista list = new Lista();
        ancestrosRecursivo(this.raiz, list, n);
        return list;
    }

    private boolean ancestrosRecursivo(NodoGen n, Lista list, Object p) {
        boolean res = false;
        NodoGen aux = null;
        if (n != null) {
            if (n.getElem().equals(p)) {
                list.insertar(n.getElem(), 1);
                res = true;
            } else {
                aux = n.getHEI();
                while (aux != null && !res) {
                    res = ancestrosRecursivo(aux, list, p);
                    aux = aux.getHerD();
                }
                if (res) {
                    list.insertar(n.getElem(), 1);
                }
            }
        }
        return res;
    }

    private boolean verificarCaminoAux(Lista listado, NodoGen a, int pos) {
        NodoGen aux;
        boolean respuesta = false;
        if (a != null) {
            if (pos < listado.longitud()) {
                if (a.getElem().equals(listado.recuperar(pos))) {
                    aux = a.getHEI();
                    while (respuesta == false && aux != null) {
                        respuesta = verificarCaminoAux(listado, aux, pos + 1);
                        aux = aux.getHerD();

                    }
                }
            } else if (pos == listado.longitud()) {
                respuesta = a.getElem().equals(listado.recuperar(pos));
            } else {
                respuesta = true;
            }
        }
        return respuesta;
    }

    public boolean verificarCamino(Lista listado) {
        boolean resultado;
        resultado = verificarCaminoAux(listado, this.raiz, 1);
        return resultado;
    }

    public Lista listarEntreNiveles(int niv1, int niv2) {
        Lista elementos = new Lista();

        listarNivelesAux(niv1, niv2, elementos, this.raiz, 0);

        return elementos;
    }

    private void listarNivelesAux(int nivelMin, int nivelMax, Lista list, NodoGen nodo, int actual) {

        if (nodo != null) {
            if (actual + 1 <= nivelMax) {
                listarNivelesAux(nivelMin, nivelMax, list, nodo.getHEI(), actual + 1);

            }
            if (actual >= nivelMin) {
                list.insertar(nodo.getElem(), list.longitud() + 1);
            }
            if (nodo.getHEI() != null) {
                NodoGen aux = nodo.getHEI().getHerD();

                while (aux != null) {
                    listarNivelesAux(nivelMin, nivelMax, list, aux, actual + 1);
                    aux = aux.getHerD();
                }
            }
        }
    }

    public boolean eliminarElemento(Object elem) {
        boolean res = false;
        if (this.raiz.getElem().equals(elem)) {
            this.raiz = null;
            res = true;
        } else {
            res = eliminarAux(elem, this.raiz);
        }
        return res;
    }

    private boolean eliminarAux(Object elemento, NodoGen a) {
        boolean res = false;
        if (a != null) {
            if (a.getHEI() != null && elemento.equals(a.getHEI().getElem())) {
                a.setHEI(a.getHEI().getHerD());
                res = true;
            }
            if (a.getHerD() != null && elemento.equals(a.getHerD().getElem())) {
                a.setHerD(a.getHerD().getHerD());
                res = true;
            } else {
                NodoGen aux = a.getHEI();
                while (aux != null) {
                    res = eliminarAux(elemento, aux);
                    aux = aux.getHerD();
                }
            }
        }
        return res;
    }

    public int grado() {

        int grado = gradoAux(this.raiz, 0);

        return grado;
    }

    private int gradoAux(NodoGen nodo, int cantMax) {
        int cant = 1;
        if (nodo != null) {
            NodoGen hijo = nodo.getHEI();
            while (hijo != null) {
                gradoAux(hijo, cantMax);
                hijo = hijo.getHerD();
                cant++;
                if (cant >= cantMax) {
                    cantMax = cant;
                }
            }
        }
        return cantMax;
    }

}
