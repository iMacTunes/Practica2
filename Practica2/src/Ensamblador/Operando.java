package Ensamblador;
//Clase Operando
public class Operando {
    //Funcion que recibe cadena
        public String Operando(String palabra){
          palabra= palabra.replaceAll("\t","");//Reemplaza las tabulaciones 
          palabra = palabra.trim(); // elimina espacios del inicio y del final de una cadena
        return palabra;
    } 
}
