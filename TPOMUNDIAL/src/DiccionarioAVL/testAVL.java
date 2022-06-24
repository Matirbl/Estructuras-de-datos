/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiccionarioAVL;

public class testAVL {

    public static void main(String[] args) {
        Diccionario prueba = new Diccionario();

        prueba.insertar("argentina", "argentina");
        prueba.insertar("alemania", "alemania");
        prueba.insertar("brasil", "brasil");
        prueba.insertar("costa Rica", "costa Rica");
        prueba.insertar("colombia", "colombia");
        prueba.insertar("senegal", "senegal");

        System.out.println(prueba.listarRango("az", "zzzzzz"));

    }
}
