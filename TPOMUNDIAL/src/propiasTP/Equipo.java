/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propiasTP;

import lineales.dinamicas.Lista;

/**
 *
 * @author JMJ - FAI-1629, FAI-1648, FAI-974
 */
public class Equipo {

    private String tecnico, nombreEquipo, grupo;
    private Lista equiposEnfrentados;
    private int puntosGanados, golesAfavor, golesEnContra;

    public Equipo(String nombreEquipo, String tecnico, String fase, int puntos, int golesAFavor, int golesEnContra) {
        this.tecnico = tecnico;
        this.grupo = fase;
        this.golesAfavor = golesAFavor;
        this.golesEnContra = golesEnContra;
        this.nombreEquipo = nombreEquipo;
        this.equiposEnfrentados = new Lista();
    }

    public String toString() {

        return nombreEquipo;

    }

    public String infoEquipo() {
        String salida = "";
        salida += "El equipo: " + this.nombreEquipo + " con Tecnico: " + this.tecnico + " tiene: "
                + "\n puntos a favor: " + this.puntosGanados + " \n goles metidos: " + this.golesAfavor
                + " \n goles en contra: " + this.golesEnContra
                + " \n y jugo con estos equipos: " + this.equiposEnfrentados.toString();

        return salida;

    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String fase) {
        this.grupo = fase;
    }

    public Lista getEquiposEnfrentados() {
        return equiposEnfrentados;
    }

    public void agregarEquipoEnfrentado(Equipo equipo) {
        this.equiposEnfrentados.insertar(equipo, equiposEnfrentados.getLongitud() + 1);
    }

    public int getPuntosGanados() {
        return puntosGanados;
    }

    public void setPuntosGanados(int puntosGanados) {
        this.puntosGanados += puntosGanados;
    }

    public int getGolesAfavor() {
        return golesAfavor;
    }

    public void setGolesAfavor(int golesAfavor) {
        this.golesAfavor = golesAfavor;
    }

    public int getGolesEnContra() {
        return golesEnContra;
    }

    public void setGolesEnContra(int golesEnContra) {
        this.golesEnContra = golesEnContra;
    }

    public boolean diferenciaNegativa() {
        boolean res = false;
        int diferencia = (golesAfavor - golesEnContra);
        if (diferencia < 0) {
            res = true;
        }
        return res;
    }

}
