/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Coyol Moreno Angel Zoe
 */
public class Complex {
    private String alpha;  //parte real (a)
    private String beta;   //parte imaginaria (b)
    
    // Variable estática para contar instancias
    private static int instanceCount = 0;
    private static final String DEFAULT_VALUE = "0";
    
    // Constructor original
    public Complex(){
        this.alpha = DEFAULT_VALUE;
        this.beta = DEFAULT_VALUE;
        instanceCount++;
    }
    
    // Constructor con parámetros
    public Complex(String alpha, String beta){
        this.alpha = alpha;
        this.beta = beta;
        instanceCount++;
    }
    
    // SOBRECARGA 1: Constructor solo con parte real
    public Complex(String alpha) {
        this.alpha = alpha;
        this.beta = DEFAULT_VALUE;
        instanceCount++;
    }
    
    // SOBRECARGA 2: Método setter sobrecargado - acepta int
    public void setAlpha(int alpha) {
        this.alpha = String.valueOf(alpha);
    }
    
    // Método setter original - acepta String
    public void setAlpha(String alpha){
        this.alpha = alpha;
    }
    
    // SOBRECARGA 3: Método setter sobrecargado - acepta int
    public void setBeta(int beta) {
        this.beta = String.valueOf(beta);
    }
    
    // Método setter original - acepta String
    public void setBeta(String beta){
        this.beta = beta;
    }
    
    public String getAlpha(){
        return this.alpha;
    }
    
    public String getBeta(){
        return this.beta;
    }
    
    // MÉTODO ESTÁTICO FACTORY
    public static Complex createReal(String value) {
        Complex c = new Complex(value);
        c.setBeta("0");
        return c;
    }
    
    // MÉTODO ESTÁTICO FACTORY SOBRECARGADO
    public static Complex createReal(int value) {
        return createReal(String.valueOf(value));
    }
    
    // MÉTODO ESTÁTICO FACTORY
    public static Complex createImaginary(String value) {
        Complex c = new Complex();
        c.setAlpha("0");
        c.setBeta(value);
        return c;
    }
    
    // MÉTODO ESTÁTICO FACTORY SOBRECARGADO
    public static Complex createImaginary(int value) {
        return createImaginary(String.valueOf(value));
    }
    
    // Obtener número total de instancias creadas
    public static int getInstanceCount() {
        return instanceCount;
    }
    
    @Override
    public String toString(){
        if ("0".equals(beta) || "".equals(beta)) {
            return alpha;
        }
        if ("0".equals(alpha) || "".equals(alpha)) {
            return beta + "i";
        }
        return alpha + " + " + beta + "i";
    }
}
