package Ensamblador;
//Clase Etiqueta
public class Etiqueta {
    
    //Valida si la longitud no exceda los 8 caracteres
    public boolean longitud(String palabra){
        return palabra.length() <= 8;
    }
    //Valida si la letra esta en la primera posicion de la linea
    public boolean primeraletra(String palabra){
       
        char[] prueba = palabra.toCharArray();
        Character.isLetter(prueba[0]);
        return Character.isLetter(prueba[0]);
    }
      //Validacion de numero del 0 al 9 letras o _
    public boolean caracteres(String palabra){
         return palabra.matches(".?+[a-zA-Z_]*+([a-zA-Z_0-9]*)?");
    }
    public String Etiqueta(String palabra){
        return palabra;
    }
}
                
