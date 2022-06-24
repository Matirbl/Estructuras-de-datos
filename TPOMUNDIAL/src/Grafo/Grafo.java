package Grafo;

import lineales.dinamicas.*;

/**
 *
 * @author JMJ - FAI-1629, FAI-1648, FAI-974
 */
public class Grafo {

    private NodoVert NodoInicial;

    public Grafo() {
        NodoInicial = null;
    }

    private NodoVert ubicarVertice(Object buscado) {
        NodoVert aux = this.NodoInicial;
        while (aux != null && !aux.getElem().equals(buscado)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public boolean insertarVertice(Object nuevoElemento) {
        boolean exito = false;
        NodoVert aux = this.ubicarVertice(nuevoElemento);
        //Verifico que el nodo no exista en el grafo
        if (aux == null) {
            this.NodoInicial = new NodoVert(nuevoElemento, this.NodoInicial);
            exito = true;
        }
        return exito;
    }

    public boolean eliminarVertice(Object aBorrar) {
        
        //IMPLEMENTADO PARA DIGRAFO
        
        boolean exito = false;
        if (NodoInicial != null) {
            if (NodoInicial.getElem().equals(aBorrar)) {
                NodoInicial = NodoInicial.getSigVertice();
                NodoVert aux = NodoInicial;
                while (aux != null) {
                    eliminarReferencias(aux, aBorrar);
                    aux = aux.getSigVertice();
                }
                exito = true;
            } else {
                NodoVert aux = NodoInicial;
                while (aux != null) {
                    eliminarReferencias(aux, aBorrar);
                    if (aux.getSigVertice() != null
                            && aux.getSigVertice().getElem().equals(aBorrar)) {
                        aux.setSigVertice(aux.getSigVertice().getSigVertice());
                        exito = true;
                    }
                    aux = aux.getSigVertice();
                }
            }
        }

        return exito;
    }

    private void eliminarReferencias(NodoVert NodoDondeBusco, Object buscado) {
        while (NodoDondeBusco.getPrimerAdyacente() != null
                && NodoDondeBusco.getPrimerAdyacente().getVertice().getElem().equals(buscado)) {
            NodoDondeBusco.setPrimerAdyacente(NodoDondeBusco.getPrimerAdyacente().getSigAdyacente());
        }
        if (NodoDondeBusco.getPrimerAdyacente() != null) {
            NodoAdy aux = NodoDondeBusco.getPrimerAdyacente();
            while (aux != null) {
                //aux.getVertice != buscado
                if (aux.getSigAdyacente() != null
                        && aux.getSigAdyacente().getVertice().getElem().equals(buscado)) {

                    aux.setSigAdyacente(aux.getSigAdyacente().getSigAdyacente());

                } else {
                    aux = aux.getSigAdyacente();

                }
            }
        }
    }

    public void vaciar() {
        NodoInicial = null;
    }

    public boolean existeCamino(Object origen, Object destino) {
        boolean exito = false;
        //verifica si ambos vertices existen
        NodoVert auxO = ubicarVertice(origen);
        NodoVert auxD = ubicarVertice(destino);
        if (auxO != null && auxD != null) {
            //si ambos vertices existen busca si exite camino entre ambos
            Lista visitados = new Lista();
            exito = existeCaminoAux(auxO, destino, visitados);
        }

        return exito;
    }

    private boolean existeCaminoAux(NodoVert n, Object destino, Lista vis) {
        boolean exito = false;
        if (n != null) {
            //si el vertice n es el destino: hay camino
            if (n.getElem().equals(destino)) {
                exito = true;
            } else {
                //si no es el destino verifica si hay camino entre n y destino
                vis.insertar(n.getElem(), vis.getLongitud() + 1);
                NodoAdy ady = n.getPrimerAdyacente();
                while (!exito && ady != null) {
                    if (vis.localizar(ady.getVertice().getElem()) < 0) {
                        exito = existeCaminoAux(ady.getVertice(), destino, vis);
                    }
                    ady = ady.getSigAdyacente();
                }
            }

        }

        return exito;
    }

    public Lista caminoMasCorto(Object origen, Object destino) {
        //OK
        Lista camino = new Lista();
        System.out.println("Se desea encontrar un camino más corto (nodos) de: "+origen +" a "+destino);
        if (this.NodoInicial != null) {
            NodoVert origenAux = ubicarVertice(origen);
            NodoVert destinoAux = ubicarVertice(destino);
            if (origenAux != null && destinoAux != null) {
                Lista aux = new Lista();
                camino = caminoMasCortoAux(origenAux, destino, aux, camino);
            }
        }
        return camino;
    }

    private Lista caminoMasCortoAux(NodoVert NodoActual, Object destino,
            Lista ListaAux, Lista caminoMasCorto) {
        if (NodoActual != null) {
            System.out.println("Se inserto el Nodo Actual: "+NodoActual.getElem().toString());
            ListaAux.insertar(NodoActual.getElem(), ListaAux.getLongitud() + 1);


            if (caminoMasCorto.esVacia() || (ListaAux.getLongitud() < caminoMasCorto.getLongitud())) {
                if (NodoActual.getElem().equals(destino)) {
                   
                    caminoMasCorto = ListaAux.clonar();
                    System.out.println("El camino más corto (nodos) encontrado es: " + caminoMasCorto.toString());
                } else {
                    NodoAdy ady = NodoActual.getPrimerAdyacente();
                    
                    while (ady != null) {
                        if (ListaAux.localizar(ady.getVertice().getElem()) < 0) {
                            caminoMasCorto = caminoMasCortoAux(ady.getVertice(), destino, ListaAux, caminoMasCorto);
                        }
                        ady = ady.getSigAdyacente();
                    }
                }
            }
            ListaAux.eliminar(ListaAux.getLongitud());
        }
        return caminoMasCorto;
    }
    
//    public void cadenaPorVertices(){
//        NodoVert auxiliar = this.NodoInicial;
//        int i = 1;
//        while (auxiliar!=null) {
//            System.out.println(i+" nodo " +auxiliar.getElem().toString());
//            auxiliar = auxiliar.getSigVertice();
//            i++;
//        }
//    }

    public Lista caminoMasCortoQuePasaPorC(Object origen, Object destino, Object c) {
        Lista camino = new Lista();
        if (this.NodoInicial != null) {
            NodoVert origenAux = ubicarVertice(origen);
            NodoVert destinoAux = ubicarVertice(destino);
            if (origenAux != null && destinoAux != null) {
                Lista aux = new Lista();
                camino = caminoMasCortoQuePasaPorCAux(origenAux, destino, aux, camino, c);
                System.out.println("resultado " +camino.toString());
            }
        }
        return camino;
    }

    private Lista caminoMasCortoQuePasaPorCAux(NodoVert NodoActual, Object destino, Lista ListaAux, Lista caminoMasCorto, Object c) {
        //OK
        if (NodoActual != null) {
            ListaAux.insertar(NodoActual.getElem(), ListaAux.getLongitud() + 1);
            if (caminoMasCorto.esVacia() || (ListaAux.getLongitud() < caminoMasCorto.getLongitud())) {
                if (NodoActual.getElem().equals(destino)) {
                    if (ListaAux.localizar(c) != -1) {
                        System.out.println("El camino mas corto antes de ser reemplazado "+ caminoMasCorto.toString());
                        caminoMasCorto = ListaAux.clonar();
                        System.out.println("Nuevo camino mas corto que pasa por C: "+caminoMasCorto.toString());
                    }
                } else {
                    NodoAdy ady = NodoActual.getPrimerAdyacente();
                    while (ady != null) {
                        if (ListaAux.localizar(ady.getVertice().getElem()) < 0) {
                            caminoMasCorto = caminoMasCortoQuePasaPorCAux(ady.getVertice(), destino, ListaAux, caminoMasCorto, c);
                        }
                        ady = ady.getSigAdyacente();
                    }
                }
            }
            ListaAux.eliminar(ListaAux.getLongitud());
        }
        return caminoMasCorto;
    }

    public Lista caminosPosibles(Object origen, Object destino) {

        Lista listaCaminosPosibles = new Lista();
        if (this.NodoInicial != null) {
            NodoVert origenAux = ubicarVertice(origen);
            NodoVert destinoAux = ubicarVertice(destino);
            if (origenAux != null && destinoAux != null) {
                Lista listaAux = new Lista();
                caminosPosiblesAux(origenAux, destino, listaAux, listaCaminosPosibles);
            }
        }
        return listaCaminosPosibles;
    }

    private void caminosPosiblesAux(NodoVert nodo, Object destino, Lista listaAux, Lista listaCaminosPosibles) {
        if (nodo != null) {
            listaAux.insertar(nodo.getElem(), listaAux.getLongitud() + 1);

            //System.out.println(listaAux.toString());
            if (nodo.getElem().equals(destino)) {
                listaCaminosPosibles.insertar((Lista) listaAux.clonar(), listaCaminosPosibles.getLongitud() + 1);
                System.out.println("encontro destino, inserto " + ((Lista) listaCaminosPosibles.recuperar(listaCaminosPosibles.getLongitud())).toString());

            } else {
                NodoAdy ady = nodo.getPrimerAdyacente();

                while (ady != null) {

                    if (listaAux.localizar(ady.getVertice().getElem()) < 0) {

                        caminosPosiblesAux(ady.getVertice(), destino, listaAux, listaCaminosPosibles);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            listaAux.eliminar(listaAux.getLongitud());
        }
    }

    public boolean insertarArco(Object origen, Object destino, int etiq) {
        boolean exito = false;
        if (this.NodoInicial != null) {
            NodoVert orig = null, dest = null, aux = this.NodoInicial;
            do {
                if (orig == null && (aux.getElem() == origen)) {
                    orig = aux;
                }

                if (dest == null && (aux.getElem() == destino)) {
                    dest = aux;
                }

                aux = aux.getSigVertice();

            } while (aux != null && (orig == null || dest == null));
            //si se encontró los nodos, se procede a insertar el arco
            if (orig != null && dest != null) {
                orig.setPrimerAdyacente(new NodoAdy(dest, etiq, orig.getPrimerAdyacente()));
                dest.setPrimerAdyacente(new NodoAdy(orig, etiq, dest.getPrimerAdyacente()));
                exito = true;
            }
        }
        return exito;
    }

    public Object[] caminoMenosKm(Object origen, Object destino) {
        Lista camino = new Lista();
        Lista caminoKm = new Lista();

        NodoVert nodoOrigen = ubicarVertice(origen);
        NodoVert nodoDestino = ubicarVertice(destino);

        Object[] arreglo = new Object[2];
        arreglo[0] = caminoKm;
        arreglo[1] = 0;
        if ((nodoOrigen != null) && (nodoDestino != null)) {
            arreglo = caminoMenosKmAux(nodoOrigen, destino, camino, arreglo, 0);
        }
        return arreglo;
    }

    private Object[] caminoMenosKmAux(NodoVert aux, Object destino, Lista camino, Object[] arreglo, int suma) {

        NodoAdy nodoAdyAux;
        if (aux != null) {
            camino.insertar(aux.getElem(), camino.getLongitud() + 1);
            if (aux.getElem().equals(destino)) {
                int sumaMenorKm = suma;
                if (((suma < (int) arreglo[1]) || ((int) arreglo[1] == 0))) {
                    arreglo[0] = (Lista) camino.clonar();
                    arreglo[1] = sumaMenorKm;
                }
            } else {
                nodoAdyAux = aux.getPrimerAdyacente();
                while (nodoAdyAux != null) {
                    suma += nodoAdyAux.getEtiqueta();
                    //verificamos si no está en la lista para no entrar en un bucle
                    if (camino.localizar(nodoAdyAux.getVertice().getElem()) < 0) {
                        if (((suma < (int) arreglo[1]) || ((int) arreglo[1] == 0))) {
                            arreglo = caminoMenosKmAux(nodoAdyAux.getVertice(), destino, camino, arreglo, suma);
                        }
                    }
                    suma -= nodoAdyAux.getEtiqueta();
                    nodoAdyAux = nodoAdyAux.getSigAdyacente();
                }
            }
            camino.eliminar(camino.getLongitud());
        }
        return arreglo;
    }

    public NodoVert getInicial() {
        return this.NodoInicial;
    }

    @Override
    public String toString() {
        return toStringAux(this.NodoInicial);
    }

    private String toStringAux(NodoVert nodo) {
        String cad = "";
        if (nodo != null) {
            NodoAdy aux = nodo.getPrimerAdyacente();
            if (aux == null) {
                cad += "\n" + nodo.getElem();
            } else {
                while (aux != null) {
                    cad += "\n" + nodo.getElem() + " --- " + aux.getEtiqueta()
                            + " --> " + aux.getVertice().getElem();
                    aux = aux.getSigAdyacente();
                }
            }
            cad += toStringAux(nodo.getSigVertice());
        }
        return cad;
    }
}
