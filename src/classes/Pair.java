/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 * Clase genérica que agrupa dos objetos de diferente tipo
 * Ejemplo de agregación: una instancia de Pair contiene dos objetos
 * @param <A> Tipo del primer objeto
 * @param <B> Tipo del segundo objeto
 */
public class Pair<A, B> {
    private A first;   // Agregación del objeto de tipo A
    private B second;  // Agregación del objeto de tipo B
    
    // Constructor original
    public Pair(A first, B second){
        this.first = first;
        this.second = second;
    }
    
    // SOBRECARGA: Constructor con solo el primer parámetro
    public Pair(A first) {
        this.first = first;
        this.second = null;
    }
    
    public A getFirst() {
        return this.first;
    }
    
    public B getSecond() {
        return this.second;
    }
    
    public void setFirst(A first) {
        this.first = first;
    }
    
    public void setSecond(B second) {
        this.second = second;
    }
    
    // SOBRECARGA: Método toString con parámetro
    public String toString(boolean detailed) {
        if (detailed) {
            return "Pair{" +
                    "first=" + first +
                    ", second=" + second +
                    '}';
        }
        return "[" + first + ", " + second + "]";
    }
    
    @Override
    public String toString(){
        return toString(false);
    }
}
