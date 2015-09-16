package Ensamblador;
//Clase Codop
public class Codop  {
    
            //Valida si la longitud no exceda los 5 caracteres
    public boolean longitud(String palabra){
        return palabra.length() <= 5;
    }
    //Validacion del primer caracter
    public boolean primercaracter(String palabra ){
        char[] arreglo = palabra.toCharArray();
        return Character.isLetter(arreglo[0]);
    }
    //Validacion de caracteres
    public boolean caracteres(String palabra){
        return palabra.matches(".?+[a-zA-Z_]*+([\\.]{1}+([a-zA-Z_]*)?)?");
    }
    public String Codop(String palabra){
        return palabra;
    }
}
