package propiasTP;

/**
 *
 * @author JMJ - FAI-1629, FAI-1648, FAI-974
 */
public class Ciudad implements Comparable {

    private String nombre;
    private int cantHabitantes, superficie;
    private boolean esSede;

    public Ciudad(String nombre, int cantHabitantes, int superficie, boolean esSede) {
        this.nombre = nombre;
        this.cantHabitantes = cantHabitantes;
        this.superficie = superficie;
        this.esSede = esSede;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public boolean getEsSede() {
        return esSede;
    }

    public String toString() {
        String salida = nombre;
        return salida;
    }

    public String informacionCiudad() {
        String salida = "La ciudad " + nombre + " tiene " + cantHabitantes + " cantidad de habitantes "
                + " y una superficie de " + superficie;
        if (esSede) {
            salida += ", ES SEDE";
        } else {
            salida += ", NO ES SEDE";

        }
        return salida;
    }

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantHabitantes() {
        return cantHabitantes;
    }

    public void setCantHabitantes(int cantHabitantes) {
        this.cantHabitantes = cantHabitantes;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setEsSede(boolean esSede) {
        this.esSede = esSede;
    }


    @Override
    public int compareTo(Object t) {
        Ciudad c = (Ciudad) t;
        return this.nombre.compareTo(c.nombre);
    }
}
