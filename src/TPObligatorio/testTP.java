//TRABAJO PRACTICO OBLIGATORIO
//ALUMNO: PUCHETA MATIAS
package TPObligatorio;

import TPObligatorio.*;
import Utiles.*;
import lineales.dinamicas.PilaInt;

public class testTP {

    public static int menu() {
        int opcion;
        do {
            System.out.println("------------MENU-----------");
            System.out.println("1.Llenar/modificar cola de forma aleatoria");
            System.out.println("2.Llenar/modificar pila de forma aleatoria");
            System.out.println("3.Cargar/modificar cola de forma manual");
            System.out.println("4.Cargar/modificar pila de forma manual");
            System.out.println("5.Operar con la pila y la cola");
            System.out.println("6.Salir");
            opcion = TecladoIn.readLineInt();
        } while ((opcion > 6) && (opcion < 1));
        return opcion;
    }

    public static void main(String[] args) {
        PilaChar pilaPrueba = new PilaChar(), pilaclon = new PilaChar();
        ColaInt colaClon = new ColaInt(), colaPrueba = new ColaInt();
        PilaInt resultados = new PilaInt(), resultadosInvertidos = new PilaInt();
        char k;
        int n, i, j, z, opcion1;

        do {
            opcion1 = menu();

            switch (opcion1) {
                case 1:
                    
                    colaPrueba.vaciar();
                    System.out.println("Ingrese la cantidad de numeros de su cola. Maximo 20");
                    int m = TecladoIn.readLineInt();
                    cargarCola(colaPrueba, m);
                    System.out.println(colaPrueba.toString());

                    break;
                case 2:
                    pilaPrueba.vaciar();
                    System.out.println("Ingrese cuantos signos desea tener en la pila ");
                    z = TecladoIn.readLineInt();
                    cargarPila(pilaPrueba, z);
                    System.out.println(pilaPrueba.toString());
                    break;
                case 3:
                    colaPrueba.vaciar();
                    System.out.println("Ingrese la cantidad de numeros de la cola. Maximo 20");
                    n = TecladoIn.readLineInt();
                    for (i = 0; i < n; i++) {
                        System.out.println("Ingrese numero a poner en la cola");
                        j = TecladoIn.readLineInt();
                        colaPrueba.poner(j);
                    }
                    System.out.println(colaPrueba.toString());
                    break;
                case 4:
                    pilaPrueba.vaciar();
                    System.out.println("Cuantos caracteres desea tener?");
                    int c = TecladoIn.readLineInt();
                    i = 0;
                    while (i < c) {

                        System.out.println("Ingrese signo a poner en la pila" + " + " + " - "+" * ");
                        k = TecladoIn.readLineNonwhiteChar();
                        //VERIFICO QUE EL CARACTER INGRESADO SEA VALIDO
                        if (k == '+' || k == '-' || k == '*') {
                            pilaPrueba.apilar(k);
                            i++;
                        } else {
                            System.out.println(" Ingrese un caracter correcto ");
                        }
                    }
                    System.out.println(pilaPrueba.toString());

                    break;

                case 5:
                    //CADA VEZ QUE ENTRA A ESTA OPCION, VACIO LA PILA DE RESULTADOS Y VUELVO 
                    //A CALCULAR CON UNA NUEVA CANTIDAD DE NUMEROS, PERO SIEMPRE CON LA MISMA COLA Y PILA.
                   
                    resultados.vaciar();
                    resultadosInvertidos.vaciar();

                    pilaclon = pilaPrueba.clonarPila();
                    colaClon = colaPrueba.clonar();

                    System.out.println("Con cuantos numeros desea operar?");
                    int x = TecladoIn.readLineInt();

                    while (!pilaclon.esVacia() && !colaClon.esVacia()) {
                        resultados.apilar(operar(colaClon, pilaclon.obtenerTope(), x));
                        pilaclon.desapilar();
                    }
                    while (!resultados.esVacia()) {
//                        resultadosInvertidos.apilar(resultados.obtenerTope());
                        resultados.desapilar();
                    }
                    //CREE LA PILA DE RESULTADOS INVERTIDOS PORQUE EN EL ENUNCIADO DEL TP,
                    //LOS RESULTADOS ESTABAN APILADOS DE FORMA INVERTIDA.
                    System.out.println(resultadosInvertidos.toString());
            }
        } while (opcion1 != 6);

    }

    public static PilaChar cargarPila(PilaChar vacia, int n) {
        int i;
        for (i = 0; i < n; i++) {
            vacia.apilar(randomChar());
        }
        return vacia;
    }

    public static char randomChar() {
        char[] signos = {'+', '-', '*'};
        return signos[Aleatorio.intAleatorio(0, 3)];

    }

    public static ColaInt cargarCola(ColaInt vacia, int n) {
        int i;
        for (i = 0; i < n; i++) {
            vacia.poner(Aleatorio.intAleatorio(-200, 990));
        }
        return vacia;

    }

    public static int operar(ColaInt cola, char n, int x) {
        int i = 0;
        int aux = 0;
        switch (n) {
            case '+':
                while (i < x) {
                    if (!cola.esVacia()) {
                        aux = aux + cola.obtenerFrente();
                        cola.sacar();
                    }
                    i++;
                }
                break;
            case '*':
                aux = 1;
                while (i < x) {
                    if (!cola.esVacia()) {
                        aux = aux * cola.obtenerFrente();
                        cola.sacar();
                    }
                    i++;
                }
                break;
            case '-':
                if (!cola.esVacia()) {
                    aux = cola.obtenerFrente();
                    cola.sacar();
                    while (i < x - 1) {
                        if (!cola.esVacia()) {
                            aux = aux - cola.obtenerFrente();
                            cola.sacar();
                        }
                        i++;
                    }

                }
                break;
            default:
                System.out.println("ERROR" + n);
                break;
        }
        return aux;
    }

}
