package TPObligatorio;

public class ColaInt {
//ATRIBUTOS

    private int cola[];
    private int frente;
    private int fin;
    public static final int TAM = 20;
//CONSTRUCTORES 

    public ColaInt() {
        this.cola = new int[TAM];
        this.frente = 0;
        this.fin = 0;
    }

    //METODOS
    public boolean poner(int elem) {
        boolean sePudo = true;

        if ((this.fin + 1 == this.frente) || ((this.fin == TAM - 1) && (this.frente == 0))) {
            sePudo = false;
        } else {
            this.cola[fin] = elem;
            this.fin = (this.fin + 1) % TAM;
        }

        return sePudo;
    }

    public boolean sacar() {
        boolean sePudo = true;
        if (this.fin == this.frente) {
            sePudo = false;
        } else {
            this.frente = (this.frente + 1) % TAM;
            sePudo = true;
        }
        return sePudo;
    }

    //OBSERVADOR
    public int obtenerFrente() {
        int retornado;
        if (this.fin != this.frente) {
            retornado = this.cola[frente];
        } else {
            retornado = -1;
        }
        return retornado;
    }

    public boolean esVacia() {
        boolean vacia = false;
        if (this.fin == this.frente) {
            vacia = true;
        }
        return vacia;
    }

    public void vaciar() {
        this.frente = 0;
        this.fin = 0;
    }

    public ColaInt clonar() {
        ColaInt clon = new ColaInt();
        clon.cola = this.cola.clone();
        clon.fin = this.fin;
        clon.frente = this.frente;
        return clon;
    }

    public String toString() {
        String cadena = "";
        int i;
        if (this.frente < this.fin) {
            i = frente;
            while (i < this.fin) {
                cadena += cola[i];
                i++;
                cadena += ',';
            }

        } else {
            i = frente;
            while (i < TAM - 1) {
                cadena += cola[i];
                i++;
                cadena += ',';
            }
            i = 0;
            while (i < fin) {
                cadena += cola[i];
                i++;
                cadena += ',';
            }
        }
        return cadena;
    }
}
