package lineales.estaticas;

public class PilaInt {

    //Atributos
    private int Arreglo[];
    private int tope;
    public static final int TAM = 15;

    //Constructores
    public PilaInt() {
        this.Arreglo = new int[TAM];
        this.tope = -1;

    }
    //Modificadores

    public int obtenerTope() {
        int tope;
        if (!this.esVacia()) {
            tope = this.tope;
        }
        return this.tope;
    }

    public boolean apilar(int n) {
        boolean condicion = false;

        if (this.tope < TAM - 1) {
            this.tope++;
            this.Arreglo[this.tope] = n;
            condicion = true;
        }

        return condicion;
    }

    public boolean esVacia() {
        boolean condicion = false;
        if (this.tope == -1) {
            condicion = true;
        }
        return condicion;
    }

    public boolean desapilar() {
        boolean condicion = true;

        if (this.esVacia()) {
            condicion = false;
        } else {
            this.tope--;
        }
        return condicion;
    }

    public void vaciar() {
        this.tope = -1;
    }

    public PilaInt clonar() {
        PilaInt nuevaPila;
        nuevaPila = new PilaInt();
        nuevaPila.Arreglo = this.Arreglo.clone();
        nuevaPila.tope = this.tope;
        return nuevaPila;
    }

    public String toString() {
        int i;
        String cadena = "[";
        for (i = 0; i < TAM; i++) {
            cadena = cadena + this.Arreglo[i] + "|";
        }
        return cadena;
    }

}
