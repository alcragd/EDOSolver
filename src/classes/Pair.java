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
    
    /**
     * Compara dos Pairs por sus elementos
     * @param obj objeto a comparar
     * @return true si ambos first y second son iguales
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Pair<?, ?> other = (Pair<?, ?>) obj;
        
        boolean firstEqual = (first == null) ? (other.first == null) : first.equals(other.first);
        boolean secondEqual = (second == null) ? (other.second == null) : second.equals(other.second);
        
        return firstEqual && secondEqual;
    }
    
    
    /**
     * Método toString sobrecargado con parámetro
     * @param detailed si true muestra formato detallado
     * @return representación en String
     */
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
