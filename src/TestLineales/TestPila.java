package TestLineales;

import Utiles.TecladoIn;
import lineales.estaticas.PilaInt;

public class TestPila {

    public static void main(String[] args) {

        PilaInt pilaPrueba;
        pilaPrueba = new PilaInt();
        int opcion;

        do {
            opcion = Menu();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese elemento a apilar");
                    int elem = TecladoIn.readLineInt();
                    pilaPrueba.apilar(elem);
                    break;

                case 2:
                    pilaPrueba.desapilar();
                    System.out.println(pilaPrueba.toString());
                    break;

                case 3:
                    System.out.println("TOPE: " + pilaPrueba.obtenerTope());
                    break;

                case 4:
                    if (pilaPrueba.esVacia()) {
                        System.out.println("Es vacía");
                    } else {
                        System.out.println("No es vacía");
                    }
                    break;
                case 5:
                    pilaPrueba.vaciar();
                    System.out.println("La pila esta vacía");
                    break;
                case 6:
                    PilaInt pila2;
                    pila2 = new PilaInt();

                    pila2 = pilaPrueba.clonar();
                    System.out.println("La pila original es: " + pilaPrueba.toString());
                    System.out.println("La pila clonada es : " + pila2.toString());
                    break;
                case 7:
                    System.out.println(pilaPrueba.toString());
            }

        } while (opcion != 8);

    }

    public static int Menu() {
        int opcion;
        do {
            System.out.println("--------------MENU-----------");
            System.out.println("1.Apilar elemento ");
            System.out.println("2.Desapilar elemento");
            System.out.println("3.Obtener tope");
            System.out.println("4.Es vacía");
            System.out.println("5.Vaciar");
            System.out.println("6. Clonar");
            System.out.println("7. ToString");
            System.out.println("8. salir");
            opcion = TecladoIn.readLineInt();
        } while (opcion < 1 && opcion > 7);

        return opcion;
    }
}
