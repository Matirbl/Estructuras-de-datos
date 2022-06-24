/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propiasTP;

/**
 *
 * @author JMJ - FAI-1629, FAI-1648, FAI-974
 */
public class Partido {

    private String equipo1;
    private String equipo2;
    private int golesEquipo1;
    private int golesEquipo2;
    private String ronda;
    private Ciudad sedePartido;

    @Override
    public String toString() {
        return "(" + this.golesEquipo1 + " " + this.golesEquipo2 + ")"
                + "RONDA:" + this.ronda + "\n";
    }

    public Partido(String equipo1, String equipo2, int golesEquipo1, int golesEquipo2, String ronda) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
        this.ronda = ronda;
    }



    public void setEquipo1(String e1) {
        this.equipo1 = e1;
    }

    public void setEquipo2(String e2) {
        this.equipo2 = e2;
    }

    public void setGoles1(int golese1) {
        this.golesEquipo1 = golese1;
    }

    public void setGoles2(int golese2) {
        this.golesEquipo2 = golese2;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }

    public void getEquipo1(Ciudad dondejugo) {
        this.sedePartido = dondejugo;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public int getGoles1() {
        return golesEquipo1;
    }

    public int getGoles2() {
        return golesEquipo2;
    }

    public String getRonda() {
        return ronda;
    }

    public Ciudad getSedePartido() {
        return sedePartido;
    }

}
