/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package propiasTP;

import DiccionarioAVL.Diccionario;
import DiccionarioAVL.NodoDiccionario;
import Grafo.Grafo;
import Utiles.*;
import java.io.*;
import java.util.HashMap;
import java.util.Set;
import lineales.dinamicas.Lista;

/**
 *
 * @author JMJ - FAI-1629, FAI-1648, FAI-974
 */
public class Mundial {

    private static Grafo grafoRusia = new Grafo();
    private static Diccionario avlEquipos = new Diccionario();
    private static Diccionario avlPosiciones = new Diccionario();
    private static Diccionario avlCiudades = new Diccionario();
    private static HashMap hashPartidos = new HashMap();

    private static File txtCambios = new File("archivoLOG.txt");
    private static File txtEquipos = new File("leeEquipo.txt");
    private static File txtCiudades = new File("leeCiudad.txt");
    private static File txtPartidos = new File("leePartido.txt");
    private static File txtRutas = new File("leeRuta.txt");

    public static void menu() {
        int opcion;
        do {
            System.out.println("Ingrese una opcion numérica, 0 para salir");
            System.out.println("1- Mostrar grafo de Rusia");
            System.out.println("2- Mostrar partidos jugados");
            System.out.println("3- Mostrar equipos de acuerdo al puntaje (FALTA)");
            System.out.println("4- Mostrar todos los equipos participantes del mundial");
            System.out.println("5- Dar de alta una CIUDAD");
            System.out.println("6- Modificar una CIUDAD");
            System.out.println("7- Dar de baja una CIUDAD");
            System.out.println("8- Dar de alta un EQUIPO");
            System.out.println("9- Modificar un EQUIPO");
            System.out.println("10- Dar de baja un EQUIPO");
            System.out.println("11- Dar de alta un PARTIDO");
            System.out.println("12- Obtener camino más corto en km, desde A a B");
            System.out.println("13- Obtener camino más corto en ciudades, desde A a B");
            System.out.println("14- Obtener todos los caminos posibles, desde A a B");
            System.out.println("15- Obtener camino más corto en ciudades, desde A a B, y que pase por C");
            System.out.println("16- Obtener los equipos con diferencia de gol negativa");
            System.out.println("17- Listar equipos entre rango de string");

            opcion = TecladoIn.readLineInt();
            switch (opcion) {
                case 0:
                    System.out.println("Adios, vuelva pronto");
                    break;
                case 1:
                    System.out.println("-------GRAFO DE RUSIA------- \n" + grafoRusia.toString());
                    break;
                case 2:
                    System.out.println("-------PARTIDOS JUGADOS------- \n" + hashPartidos.toString());

                    break;
                case 3:
                    System.out.println("-------EQUIPOS POR PUNTAJE------- \n");
                    listarEquiposPorPuntos();
                    break;
                case 4:
                    System.out.println("-------EQUIPOS PARTICIPANTES DE MUNDIAL------ \n" + avlEquipos.toString());
                    break;
                case 5:
                    agregarCiudad();
                    break;
                case 6:
                    modificarCiudad();
                    break;
                case 7:
                    bajaCiudad();
                    break;
                case 8:
                    agregarEquipo();
                    break;
                case 9:
                    modificarEquipo();
                    break;
                case 10:
                    bajaEquipo();
                    break;
                case 11:
                    agregarPartidoExtra();
                    break;
                case 12:
                    caminoMasCortoEnKm();
                    break;
                case 13:
                    caminoMasCorto();
                    break;
                case 14:
                    caminosPosibles();
                    break;
                case 15:
                    caminoMasCortoQuePasaPorC();
                    break;
                case 16:
                    System.out.println(avlEquipos.listarEquiposConDiferenciaDeGolesNegativa().toString());
                    break;
                case 17:
                    listarEquiposEntreRangoDeString();
                    break;
            }

        } while (opcion != 0);

    }

    public static void main(String[] args) {

        cargarDatos();
        menu();

    }

    public static void cargarDatos() {
        modificarTxt(txtCambios, "SE INGRESO AL METODO CARGAR DATOS");
        String lineaEquipo = "";
        String lineaCiudad = "";
        String lineaPartido = "";
        String lineaRuta = "";

        try {
            FileReader leerEquipo = new FileReader(txtEquipos); //Selecciona todo un archivo txt para leer
            BufferedReader bufferEquipo = new BufferedReader(leerEquipo); //Lee una linea entera del archivo
            while ((lineaEquipo = bufferEquipo.readLine()) != null) {
                altaEquipo(lineaEquipo);
            }
            bufferEquipo.close();
        } catch (Exception ex) {

        }

        try {
            FileReader leerCiudad = new FileReader(txtCiudades);
            BufferedReader bufferCiudad = new BufferedReader(leerCiudad);
            while ((lineaCiudad = bufferCiudad.readLine()) != null) {
                altaCiudad(lineaCiudad);
            }
            bufferCiudad.close();
        } catch (Exception ex) {
        }
        try {
            FileReader leerRuta = new FileReader(txtRutas);
            BufferedReader bufferRuta = new BufferedReader(leerRuta);
            while ((lineaRuta = bufferRuta.readLine()) != null) {
                altaRuta(lineaRuta);
            }
            bufferRuta.close();
        } catch (Exception ex) {
        }
        try {
            FileReader leerPartido = new FileReader(txtPartidos);
            try (BufferedReader bufferPartido = new BufferedReader(leerPartido)) {
                while ((lineaPartido = bufferPartido.readLine()) != null) {
                    altaPartido(lineaPartido);
                }
            }
        } catch (Exception ex) {
        }
    }

    public static void modificarTxt(File archivo, String cadena) {
        try {
            FileWriter escribirArchivo = new FileWriter(archivo, true);
            BufferedWriter buffer = new BufferedWriter(escribirArchivo);
            buffer.write(cadena);
            buffer.newLine();
            buffer.close();
        } catch (Exception ex) {
        }
    }

    public static void altaEquipo(String cadenaEquipo) {
        modificarTxt(txtCambios, "CARGA EQUIPO: " + cadenaEquipo);
        int i = 0, posicionAtributo = 0;

        //los datos del equipo estan separados por un ;
        String atributoEquipo = "";
        Equipo nuevoEquipo;

        //arreglo para guardar los atributos del equipo que está siendo leido
        Object[] atributosEquipo = new Object[6];

        //while que recorre la linea
        while (i < cadenaEquipo.length() - 1 && cadenaEquipo.charAt(i) != '.') {
            //while que recorre atributo
            while (cadenaEquipo.charAt(i) != ';' && cadenaEquipo.charAt(i) != '.') {

                if (cadenaEquipo.charAt(i) != ' ') {
                    atributoEquipo += cadenaEquipo.charAt(i);
                }
                i++;
            }

            i++;

            atributosEquipo[posicionAtributo] = atributoEquipo;
            atributoEquipo = "";
            posicionAtributo++;
        }

        String nombre = ((String) atributosEquipo[0]).toUpperCase();
        String dt = ((String) atributosEquipo[1]).toUpperCase();
        String grupo = ((String) atributosEquipo[2]).toUpperCase();
        int puntos = Integer.parseInt((String) atributosEquipo[3]);
        int cantidadGolesFavor = Integer.parseInt((String) atributosEquipo[4]);
        int cantidadGolesContra = Integer.parseInt((String) atributosEquipo[5]);
        nuevoEquipo = new Equipo(nombre, dt, grupo, puntos, cantidadGolesFavor, cantidadGolesContra);
        boolean res = avlEquipos.insertar(nuevoEquipo, nuevoEquipo.getNombreEquipo());
        if (res) {
            modificarTxt(txtCambios, "EQUIPO CARGADO CORRECTAMENTE" + nombre);
        } else {
            modificarTxt(txtCambios, "EQUIPO NO CARGADO, REVISAR " + nombre);
        }
    }

    public static void altaCiudad(String cadenaCiudad) {
        modificarTxt(txtCambios, "CARGA CIUDAD: " + cadenaCiudad);
        int i = 0;
        int posicionAtributo = 0;
        String atributoCiudad = "";
        Ciudad nuevaCiudad;
        Object[] atributosCiudad = new Object[4];

        while (i < cadenaCiudad.length() && cadenaCiudad.charAt(i) != '.') {
            while (cadenaCiudad.charAt(i) != ';' && cadenaCiudad.charAt(i) != '.') {
                if (cadenaCiudad.charAt(i) != ' ') {
                    atributoCiudad += cadenaCiudad.charAt(i);
                }
                i++;
            }
            atributosCiudad[posicionAtributo] = atributoCiudad;
            i++;
            atributoCiudad = "";
            posicionAtributo++;
        }

        String nombre = (((String) atributosCiudad[0]).toUpperCase());
        int superficie = Integer.parseInt(((String) atributosCiudad[1]).toUpperCase());
        int habitantes = Integer.parseInt(((String) atributosCiudad[2]).toUpperCase());
        boolean esSede = ((String) atributosCiudad[3]).equalsIgnoreCase("true");
        nuevaCiudad = new Ciudad(nombre, superficie, habitantes, esSede);
        grafoRusia.insertarVertice(nuevaCiudad);
        avlCiudades.insertar(nuevaCiudad, nombre);
    }

    public static void modificarCiudad() {

        System.out.println("Ingrese nombre de ciudad a modificar");
        String nombreCiudad = TecladoIn.readLine().toUpperCase().trim();

        Ciudad ciudadAux = (Ciudad) avlCiudades.obtenerDato(nombreCiudad);

        if (ciudadAux != null) {
            System.out.println("Ingrese cantidad de habitantes");
            ciudadAux.setCantHabitantes(TecladoIn.readLineInt());

            System.out.println("Ingrese superficie");
            ciudadAux.setSuperficie(TecladoIn.readLineInt());

            System.out.println("Es sede?");
            ciudadAux.setEsSede(TecladoIn.readLineBoolean());

        } else {
            System.out.println("No existe ciudad con ese nombre");
        }

    }

    public static void bajaCiudad() {
        System.out.println("Ingrese nombre de la ciudad que desea dar de baja");
        String nombre = TecladoIn.readLine().toUpperCase().trim();

        Ciudad ciudadAux = (Ciudad) avlCiudades.obtenerDato(nombre);

        if (ciudadAux != null) {
            if (grafoRusia.eliminarVertice(ciudadAux)) {
                System.out.println("Ciudad dada de baja con exito");
            }
            avlCiudades.eliminar(nombre);
        } else {
            System.out.println("No existe ciudad");
        }

    }

    public static void altaPartido(String cadenaPartido) {

        modificarTxt(txtCambios, "CARGA PARTIDO: " + cadenaPartido);
        int i = 0;
        int contadorAtributo = 0;
        String datoParametro = "";
        Object[] atributosPartido = new Object[5];

        while (i < cadenaPartido.length() && cadenaPartido.charAt(i) != '.') {
            while (cadenaPartido.charAt(i) != ';' && cadenaPartido.charAt(i) != '.') {
                if (cadenaPartido.charAt(i) != ' ') {
                    datoParametro += cadenaPartido.charAt(i);
                }
                i++;
            }
            atributosPartido[contadorAtributo] = datoParametro;
            i++;
            datoParametro = "";
            contadorAtributo++;
        }

        String nombreEquipo1 = ((String) atributosPartido[0]).toUpperCase().trim();
        Equipo equipo1 = (Equipo) avlEquipos.obtenerDato(nombreEquipo1);
        if (equipo1 != null) {
            String nombreEquipo2 = ((String) atributosPartido[1]).toUpperCase().trim();
            Equipo equipo2 = (Equipo) avlEquipos.obtenerDato(nombreEquipo2);
            if (equipo2 != null) {
                String ronda = ((String) atributosPartido[2]).toUpperCase().trim();

                int golesEquipo1 = Integer.parseInt((String) atributosPartido[3]);
                int golesEquipo2 = Integer.parseInt((String) atributosPartido[4]);

                Object clave;
                Partido nuevoPartido;

                if (nombreEquipo1.compareToIgnoreCase(nombreEquipo2) < 0) {
                    clave = nombreEquipo1 + "/" + nombreEquipo2 + "/" + ronda;
                    nuevoPartido = new Partido(nombreEquipo1, nombreEquipo2, golesEquipo1, golesEquipo2, ronda);
                } else {
                    clave = nombreEquipo2 + "/" + nombreEquipo1 + "/" + ronda;
                    nuevoPartido = new Partido(nombreEquipo2, nombreEquipo1, golesEquipo2, golesEquipo1, ronda);
                }

                if (golesEquipo1 == golesEquipo2) {
                    equipo1.setPuntosGanados(1);
                    equipo2.setPuntosGanados(1);
                } else {
                    if (golesEquipo1 > golesEquipo2) {
                        equipo1.setPuntosGanados(3);
                    } else {
                        equipo2.setPuntosGanados(3);
                    }
                }
                //ACTULIZA LOS PUNTOS Y GOLES A FAVOR/CONTRA PARA CADA EQUIPO DEL DICCIONARIO EQUIPOS.
                equipo1.setGolesAfavor(golesEquipo1);
                equipo1.setGolesEnContra(golesEquipo2);
                equipo2.setGolesAfavor(golesEquipo2);
                equipo2.setGolesEnContra(golesEquipo1);

                hashPartidos.put(clave, nuevoPartido);
            }
        }
    }

    public static void altaRuta(String cadenaRuta) {
        modificarTxt(txtCambios, "CARGA RUTA " + cadenaRuta);
        int i = 0, posicionAtributo = 0;
        String atributoRuta = "";
        Object[] atributosRuta = new Object[3];

        while (i < cadenaRuta.length() && cadenaRuta.charAt(i) != '.') {
            while (cadenaRuta.charAt(i) != ';' && cadenaRuta.charAt(i) != '.') {
                if (cadenaRuta.charAt(i) != ' ') {
                    atributoRuta += cadenaRuta.charAt(i);
                }
                i++;
            }

            atributosRuta[posicionAtributo] = atributoRuta;

            atributoRuta = "";
            i++;
            posicionAtributo++;
        }

        String nombreCiudad1 = ((String) atributosRuta[0]).toUpperCase();
        Ciudad ciudad1 = (Ciudad) avlCiudades.obtenerDato(nombreCiudad1);
        String nombreCiudad2 = ((String) atributosRuta[1]).toUpperCase();
        Ciudad ciudad2 = (Ciudad) avlCiudades.obtenerDato(nombreCiudad2);
        if (ciudad1 != null && ciudad2 != null) {

            int km = Integer.parseInt((String) atributosRuta[2]);
            boolean res = grafoRusia.insertarArco(ciudad1, ciudad2, km);
            if (res) {
                modificarTxt(txtCambios, "RUTA CARGADA CORRECTAMENTE" + "[" + nombreCiudad1 + "," + nombreCiudad2 + "]");
            } else {
                modificarTxt(txtCambios, "NO SE CARGO LA RUTA, REVISAR" + "[" + nombreCiudad1 + "," + nombreCiudad2 + "]");
            }

        }
    }

    public static void agregarPartidoExtra() {
        Equipo equipoA, equipoB;
        String nombreEquipoA, nombreEquipoB;
        System.out.println("ingrese nombre del equipo A");
        nombreEquipoA = TecladoIn.readLine().toUpperCase().trim();

        System.out.println("ingrese nombre del equipo B");
        nombreEquipoB = TecladoIn.readLine().toUpperCase().trim();

        if (nombreEquipoA.compareToIgnoreCase(nombreEquipoB) != 0) {
            equipoA = (Equipo) avlEquipos.obtenerDato(nombreEquipoA);
            equipoB = (Equipo) avlEquipos.obtenerDato(nombreEquipoB);
            if (equipoA != null && equipoB != null) {

                System.out.println("INGRESE GOLES EQUIPO A");
                int golesA = TecladoIn.readLineInt();
                System.out.println("INGRESE GOLES EQUIPO B");
                int golesB = TecladoIn.readLineInt();
                System.out.println("INGRESE RONDA O GRUPO");
                String ronda = TecladoIn.readLine().toUpperCase().trim();
                String clave;
                Partido partido;
                if (nombreEquipoA.compareToIgnoreCase(nombreEquipoB) < 0) {
                    clave = nombreEquipoA + "/" + nombreEquipoB;
                    partido = new Partido(nombreEquipoA, nombreEquipoB, golesA, golesB, ronda);
                } else {
                    clave = nombreEquipoB + "/" + nombreEquipoA;
                    partido = new Partido(nombreEquipoB, nombreEquipoA, golesB, golesA, ronda);
                }
                hashPartidos.put(clave, partido);
                modificarTxt(txtCambios, "SE DIO DE ALTA UN PARTIDO" + clave);
                System.out.println("PARTIDO DADO DE ALTA CORRECTAMENTE" + clave);

                equipoA.setGolesEnContra(golesB);
                equipoA.setGolesAfavor(golesA);
                equipoB.setGolesEnContra(golesA);
                equipoB.setGolesAfavor(golesB);

                if (golesA > golesB) {
                    equipoA.setPuntosGanados(3);
                } else {
                    if (golesA == golesB) {
                        equipoA.setPuntosGanados(1);
                        equipoB.setPuntosGanados(1);
                    } else {
                        equipoB.setPuntosGanados(3);
                    }
                }
                modificarTxt(txtCambios, "SE SETEARON LOS PUNTOS DE CADA EQUIPO DEL PARTIDO: " + clave);

            }
        }
    }

    public static void agregarCiudad() {

        System.out.println("Ingrese nombre de la ciudad a agregar");
        String nombre = TecladoIn.readLine().toUpperCase().trim();

        if (avlCiudades.obtenerDato(nombre) == null) {
            System.out.println("Ingrese la cantidad de habitantes");
            int habitantes = TecladoIn.readLineInt();

            System.out.println("Ingrese la superficie de la ciudad");
            int superficie = TecladoIn.readLineInt();

            System.out.println("Ingrese true/false si es sede o no");
            boolean esSede = TecladoIn.readLineBoolean();

            Ciudad nuevaCiudad = new Ciudad(nombre, habitantes, superficie, esSede);
            //REVISAR
            grafoRusia.insertarVertice(nuevaCiudad);
            avlCiudades.insertar(nuevaCiudad, nombre);
            System.out.println("Carga exitosa de ciudad");

        } else {
            System.out.println("Ya existe una ciudad con ese nombre");
        }

    }

    public static void agregarEquipo() {

        System.out.println("Ingrese nombre del equipo a agregar");
        String nombre = TecladoIn.readLine().toUpperCase().trim();

        if (avlEquipos.obtenerDato(nombre) == null) {
            System.out.println("Ingrese el tecnico");
            String tecnico = TecladoIn.readLine().toUpperCase().trim();

            System.out.println("Ingrese letra de grupo");
            String fase = TecladoIn.readLine().toUpperCase().trim();

            Equipo nuevoEquipo = new Equipo(nombre, tecnico, fase, 0, 0, 0);
            if (avlEquipos.insertar(nuevoEquipo, nombre)) {
                System.out.println("Carga exitosa del Equipo");
            }

        } else {
            System.out.println("Ya existe un Equipo con ese nombre");
        }

    }

    public static void modificarEquipo() {

        System.out.println("Ingrese nombre de equipo a modificar");
        String nombreEquipo = TecladoIn.readLine().toUpperCase().trim();

        Equipo EquipoAux = (Equipo) avlEquipos.obtenerDato(nombreEquipo);

        if (EquipoAux != null) {
            System.out.println("Ingrese tecnico");
            EquipoAux.setTecnico(TecladoIn.readLine());

            System.out.println("Ingrese fase");
            EquipoAux.setGrupo(TecladoIn.readLine());

        } else {
            System.out.println("No existe equipo con ese nombre");
        }
    }

    public static void bajaEquipo() {
        System.out.println("Ingrese nombre del equipo que desea dar de baja");
        String nombre = TecladoIn.readLine().toUpperCase().trim();

        Equipo equipoAux = (Equipo) avlEquipos.obtenerDato(nombre);

        if (equipoAux != null) {
            if (avlEquipos.eliminar(equipoAux.getNombreEquipo())) {
                System.out.println("Ciudad dada de baja con exito");
            }
        } else {
            System.out.println("No existe el equipo");
        }
    }

    public static void caminoMasCortoEnKm() {
        modificarTxt(txtCambios, "SE INGRESO AL METODO CAMINOMASCORTOENKM");

        System.out.println("Ingrese la ciudad origen");
        String nombreCiudad1 = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese la ciudad destino");
        String nombreCiudad2 = TecladoIn.readLine().toUpperCase();
        Ciudad ciudadAux1 = (Ciudad) avlCiudades.obtenerDato(nombreCiudad1);
        Ciudad ciudadAux2 = (Ciudad) avlCiudades.obtenerDato(nombreCiudad2);
        if (ciudadAux1 != null && ciudadAux2 != null) {
            System.out.println("Lista del camino mas corto en km:" + ((Lista) grafoRusia.caminoMenosKm(ciudadAux1, ciudadAux2)[0]).toString() + " con longitud: " + (grafoRusia.caminoMenosKm(ciudadAux1, ciudadAux2)[1]).toString() + " KM ");
        }
    }

    public static void caminoMasCorto() {
        modificarTxt(txtCambios, "SE INGRESO AL METODO CAMINOMASCORTO");
        System.out.println("Ingrese la ciudad origen");
        String nombreCiudad1 = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese la ciudad destino");
        String nombreCiudad2 = TecladoIn.readLine().toUpperCase();
        Ciudad ciudadAux1 = (Ciudad) avlCiudades.obtenerDato(nombreCiudad1);
        Ciudad ciudadAux2 = (Ciudad) avlCiudades.obtenerDato(nombreCiudad2);
        if (ciudadAux1 != null && ciudadAux2 != null) {

            System.out.println("Lista de caminos mas corto :" + ((Lista) grafoRusia.caminoMasCorto(ciudadAux1, ciudadAux2)).toString());

        }
    }

    public static void caminosPosibles() {
        modificarTxt(txtCambios, "SE INGRESO AL METODO CAMINOSPOSIBLES");
        System.out.println("Ingrese la ciudad origen");
        String nombreCiudad1 = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese la ciudad destino");
        String nombreCiudad2 = TecladoIn.readLine().toUpperCase();
        Ciudad ciudadAux1 = (Ciudad) avlCiudades.obtenerDato(nombreCiudad1);
        Ciudad ciudadAux2 = (Ciudad) avlCiudades.obtenerDato(nombreCiudad2);
        if (ciudadAux1 != null && ciudadAux2 != null) {

            System.out.println("");
            (grafoRusia.caminosPosibles(ciudadAux1, ciudadAux2)).toString();
        }
    }

    public static void caminoMasCortoQuePasaPorC() {
        modificarTxt(txtCambios, "SE INGRESO AL METODO CAMINOMASCORTOQUEPASAPORC");

        System.out.println("Ingrese ciudad origen");
        String ciudadOrigen = TecladoIn.readLine();
        System.out.println("Ingrese ciudad destino");
        String ciudadDestino = TecladoIn.readLine();
        System.out.println("Ingrese ciudad que debe estar incluida en la ruta ");
        String ciudadIncluida = TecladoIn.readLine();
        Ciudad ciudadAux1 = (Ciudad) avlCiudades.obtenerDato(ciudadOrigen);
        Ciudad ciudadAux2 = (Ciudad) avlCiudades.obtenerDato(ciudadDestino);
        Ciudad ciudadAux3 = (Ciudad) avlCiudades.obtenerDato(ciudadIncluida);

        System.out.println((grafoRusia.caminoMasCortoQuePasaPorC(ciudadAux1, ciudadAux2, ciudadAux3)).toString());

    }

    public static void listarEquiposEntreRangoDeString() {
        System.out.println("Ingrese string de rango minimo");
        String min = TecladoIn.readLine().toUpperCase().trim();
        System.out.println("Ingrese string de rango max");
        String max = TecladoIn.readLine().toUpperCase().trim();

        System.out.println(avlEquipos.listarRango(min, max).toString());

    }

    public static void listarEquiposPorPuntos() {

        Lista listaAux = new Lista();
        listaAux = avlEquipos.listarElementos();
        Diccionario avlPuntajes = new Diccionario();
        String clave = "";

        System.out.println("Lista aux: " + listaAux);
        while (!listaAux.esVacia()) {
            Equipo Eaux = (Equipo) listaAux.obtenerCabecera();
            clave = Eaux.getPuntosGanados() + "." + Eaux.getGolesAfavor() + "." + Eaux.getNombreEquipo();
            avlPuntajes.insertar(Eaux, clave);
            listaAux.eliminar(1);
        }

        Lista ordenada = avlPuntajes.listarElementosInOrdenInverso();

        System.out.println(ordenada);
        while (!ordenada.esVacia()) {
            Equipo Eaux = (Equipo) ordenada.obtenerCabecera();
            System.out.println("Equipo: " + Eaux.getNombreEquipo()
                    + " con los puntos: " + Eaux.getPuntosGanados()
                    + " con goles a favor: " + Eaux.getGolesAfavor() + "\n"
            );
            ordenada.eliminar(1);

        }

    }


    public Lista listarEquiposConDiferenciaDeGolesNegativa() {
        
        
        
    }

    private void listarEquiposConDiferenciaDeGolesNegativaAux(Lista lista, NodoDiccionario n) {
        if (n != null) {
 
            Equipo aux = (Equipo) n.getElem();
            if (aux.diferenciaNegativa()) {
                lista.insertar(aux.getNombreEquipo() + " " + (aux.getGolesAfavor() - aux.getGolesEnContra()), lista.getLongitud() + 1);
            }
            listarEquiposConDiferenciaDeGolesNegativaAux(lista, n.getIzq());
            listarEquiposConDiferenciaDeGolesNegativaAux(lista, n.getDer());

        }

    }

}

}
