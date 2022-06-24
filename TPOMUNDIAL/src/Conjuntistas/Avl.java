/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conjuntistas;

/**
 *
 * @author Mati
 */
public class Avl {

    NodoAvl raiz;

    public boolean insertar(Comparable elem) {
        boolean res = true;
        if (this.raiz != null) {
            res = insertar(this.raiz, null, elem);
        } else {
            this.raiz = new NodoAvl(elem);
        }
        return res;
    }

    private boolean insertar(NodoAvl n, NodoAvl padre, Comparable elem) {
        
        boolean res = true;
        if (n != null) {
            if (n.getElem().compareTo(elem) == 0) {
                res = false;
            } else {
                if (n.getElem().compareTo(elem) > 0) {
                    if (n.getIzq() != null) {
                        res = insertar(n.getIzq(), n, elem);
                    } else {
                        n.setIzq(new NodoAvl(elem));
                    }
                } else {
                    if (n.getDer() != null) {
                        res = insertar(n.getDer(), n, elem);
                    } else {
                        n.setDer(new NodoAvl(elem));
                    }
                }
                n.recalcularAltura();
                equilibrar(n, padre, balance(n));
            }
        }

        return res;
    }

    private void equilibrar(NodoAvl n, NodoAvl padre, int balanceN) {
        
        if (n != null) {
            if (n.getAltura() != 0) {
                int balanceHi = balance(n.getIzq()),
                        balanceHd = balance(n.getDer());
                int lado = 1;
                if (padre != null) {
                    if (n.getElem().compareTo(padre.getElem()) > 0) {
                        //ubicado a la derecha del padre
                        lado = -1;
                    } else {
                        //ubicado a la izquierda del padre
                        lado = 1;
                    }
                }
                if (balanceN == 2 || balanceN == -2) {
                    if (balanceN == 2) {
                        if (balanceHi >= 0) {
                            if (padre != null) {
                                if (lado == -1) {
                                    padre.setDer(rotarDerecha(n));
                                } else {
                                    padre.setIzq(rotarDerecha(n));
                                }
                            } else {
                                this.raiz = rotarDerecha(this.raiz);
                                this.raiz.recalcularAltura();
                            }

                        } else {
                            if (padre != null) {
                                if (lado == -1) {
                                    padre.setDer(rotarDobleIzqDer(n));
                                } else {
                                    padre.setIzq(rotarDobleIzqDer(n));
                                }
                            } else {
                                this.raiz = rotarDobleIzqDer(this.raiz);
                                this.raiz.recalcularAltura();
                            }
                        }
                    } else {
                        if (balanceHd <= 0) {
                            if (padre != null) {
                                if (lado == -1) {
                                    padre.setDer(rotarIzquierda(n));
                                } else {
                                    padre.setIzq(rotarIzquierda(n));
                                }
                            } else {
                                this.raiz = rotarIzquierda(this.raiz);
                                this.raiz.recalcularAltura();
                            }
                        } else {
                            if (padre != null) {
                                if (lado == -1) {
                                    padre.setDer(rotarDobleDerIzq(n));
                                } else {
                                    padre.setIzq(rotarDobleDerIzq(n));
                                }
                            } else {
                                this.raiz = rotarDobleDerIzq(this.raiz);
                                this.raiz.recalcularAltura();
                            }
                        }
                    }

                }

            }
        }
    }

    public void eliminarMinimo() {
        if (this.raiz != null) {
            if (this.raiz.getIzq() == null) {
                this.raiz = this.raiz.getDer();
            } else {
                eliminarMinimo(this.raiz);
            }
        }
    }

    private void eliminarMinimo(NodoAvl n) {
        if (n.getIzq().getIzq() == null) {
            n.setIzq(n.getIzq().getDer());
        } else {
            eliminarMinimo(n.getIzq());
        }
    }

    public boolean pertenece(Comparable elem) {
        boolean exito;

        if (this.raiz != null) {
            exito = pertenece(this.raiz, elem);
        } else {
            exito = false;
        }

        return exito;
    }

    private boolean pertenece(NodoAvl n, Comparable elem) {
        boolean res = false;
        if (n != null) {
            if (n.getElem().compareTo(elem) == 0) {
                res = true;
            } else {
                if (n.getElem().compareTo(elem) > 0) {
                    res = pertenece(n.getIzq(), elem);
                } else {
                    res = pertenece(n.getDer(), elem);
                }
            }

        }
        return res;

    }

    private int balance(NodoAvl n) {
        int alturaHi = -1, alturaHd = -1, balance;
        if (n != null) {
            if (n.getIzq() != null) {
                alturaHi = n.getIzq().getAltura();
            }
            if (n.getDer() != null) {
                alturaHd = n.getDer().getAltura();
            }
        }
        balance = alturaHi - alturaHd;
        return balance;
    }

    public boolean eliminar(Comparable elem) {
        boolean res = false;
        if (this.raiz != null) {
            res = eliminar(null, this.raiz, elem);
        }
        return res;
    }

    private boolean eliminar(NodoAvl padre, NodoAvl nodo, Comparable elem) {
        boolean exito = false;
        if (nodo != null) {
            if (nodo.getElem().compareTo(elem) == 0) {
                exito = true;
                if (nodo.getDer() == null && nodo.getIzq() == null) {
                    casoUno(padre, elem);
                } else {
                    if ((nodo.getDer() == null && nodo.getIzq() != null)
                            || (nodo.getDer() != null && nodo.getIzq() == null)) {
                        casoDos(padre, elem);
                    } else {
                        if (padre != null) {
                            casoTres(nodo);
                        } else {
                            casoTres(null);
                        }
                    }
                }
            } else {
                if (nodo.getElem().compareTo(elem) > 0) {
                    exito = eliminar(nodo, nodo.getIzq(), elem);
                } else {
                    exito = eliminar(nodo, nodo.getDer(), elem);
                }
            }
            if (exito) {
                //(si se elimino)
                //la vuelta de la recursion, pregunto por el balance.
                nodo.recalcularAltura();
                equilibrar(nodo, padre, balance(nodo));
            }
        }
        return exito;
    }

    private void casoUno(NodoAvl nodo, Comparable elem) {
        /**
         * Caso 1: El nodo a eliminar es una hoja. Si el nodo a eliminar no
         * tiene hijos, simplemente lo desconectamos de su padre, dejando el
         * enlace que los une en nulo. NodoAvl nodo: es el padre, y Comparable
         * elem, el elemento a eliminar.
         */
        if (nodo != null) {
            if (nodo.getElem().compareTo(elem) < 0) {
                nodo.setDer(null);
            } else {
                nodo.setIzq(null);
            }
        } else {
            //si nodo es nulo, quiere decir que lo que ingreso fue la raiz, por lo tanto.
            this.raiz = null;
        }
    }

    private void casoDos(NodoAvl nodo, Comparable elem) {
        /**
         * Caso 2: El nodo tiene un solo hijo Si el nodo a eliminar tiene un
         * solo hijo, dicho hijo debe tomar el lugar del padre. Para ello, se
         * conecta el padre de N con el hijo de N. NodoAvl nodo: es el padre de
         * N y Comparable elem es el elemento que quiero eliminar.
         *
         */
        if (nodo != null) {
            if (nodo.getElem().compareTo(elem) < 0) {
                if (nodo.getDer().getDer() != null) {
                    nodo.setDer(nodo.getDer().getDer());
                } else {
                    nodo.setDer(nodo.getDer().getIzq());
                }
            } else {
                if (nodo.getIzq().getDer() != null) {
                    nodo.setIzq(nodo.getIzq().getDer());
                } else {
                    nodo.setIzq(nodo.getIzq().getIzq());
                }

            }
        } else {
            //si nodo es nulo, ingreso la raiz, por lo tanto
            if (this.raiz.getDer() == null) {
                this.raiz = this.raiz.getIzq();
            } else {
                this.raiz = this.raiz.getDer();
            }
            this.raiz.recalcularAltura();
        }
    }

    private void casoTres(NodoAvl nodo) {
        /**
         * Caso 3: El nodo tiene ambos hijos. Si el nodo a eliminar tiene ambos
         * hijos, se debe encontrar algún elemento entre los descendientes de N
         * que "suba" a ocupar el lugar de N, de manera que se siga vericando
         * la propiedad de orden del AVL. Nodo es el que deseo eliminar y sé que
         * tiene dos hijos
         */

        if (nodo != null) {
            if (nodo.getDer().getIzq() == null) {
                nodo.setElem(nodo.getDer().getElem());
                nodo.setDer(nodo.getDer().getDer());
            } else {
                /**
                 * El metodo candidato, elimina el elemento candidato la llamada
                 * se hace ya SABIENDO que el hijo derecho no es el candidato es
                 * decir, que el hijo derecho tiene un hijo izquierdo.
                 */
                NodoAvl candidato = candidato(nodo.getDer());
                nodo.setElem(candidato.getElem());
            }
            nodo.recalcularAltura();
        } else {
            if (this.raiz.getDer().getIzq() == null) {
                this.raiz.setElem(this.raiz.getDer().getElem());
                this.raiz.setDer(this.raiz.getDer().getDer());
            } else {
                NodoAvl candidato = candidato(this.raiz.getDer());
                this.raiz.setElem(candidato.getElem());
            }
            this.raiz.recalcularAltura();
        }
    }

    private NodoAvl candidato(NodoAvl nodo) {
        /**
         * El menor elemento del subárbol derecho de N. Nodo ya esta ubicado en
         * el hijo derecho (pues cuando se llame al metodo, sera con el hijo
         * derecho directamente) del subarbol.
         */
        NodoAvl candidato = nodo;
        NodoAvl candi = null;
        boolean corte = false;
        while (candidato != null && !corte) {
            if (candidato.getIzq().getIzq() != null) {
                candidato = candidato.getIzq();
            } else {
                candi = candidato.getIzq();
                candidato.setIzq(candidato.getIzq().getDer());
                corte = true;
            }
        }
        return candi;
    }

    private NodoAvl rotarIzquierda(NodoAvl r) {
        //precondicion r no nulo
        NodoAvl h = r.getDer();
        NodoAvl temp = h.getIzq();
        h.setIzq(r);
        r.setDer(temp);
        return h; //retorna la nueva raiz del subarbol
    }

    private NodoAvl rotarDerecha(NodoAvl r) {
        //precondicion r no nulo
        NodoAvl h = r.getIzq();
        NodoAvl temp = h.getDer();
        h.setDer(r);
        r.setIzq(temp);
        return h; //nueva raiz del subarbol
    }

    private NodoAvl rotarDobleIzqDer(NodoAvl r) {
        //precondicion r no nulo
        r.setIzq(rotarIzquierda(r.getIzq()));
        return rotarDerecha(r);
    }

    private NodoAvl rotarDobleDerIzq(NodoAvl r) {
        //precondicion r no nulo
        r.setDer(rotarDerecha(r.getDer()));
        return rotarIzquierda(r);
    }

    public String toString() {
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoAvl n) {
        String res = "";
        if (n != null) {
            res += n.getElem() + "Hijos: ";
            if (n.getIzq() != null) {
                res += "HI: " + n.getIzq().getElem();
            } else {
                res += "HI: -";
            }

            if (n.getDer() != null) {
                res += "HD: " + n.getDer().getElem() + "\n";
            } else {
                res += "HD: -" + "\n";
            }
        }

        //llamado recursivo a los hijos
        if (n.getIzq() != null) {
            res += "" + toStringAux(n.getIzq());
        }
        if (n.getDer() != null) {
            res += "" + toStringAux(n.getDer());
        }
        return res;
    }

}
