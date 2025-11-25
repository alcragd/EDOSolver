/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author Coyol Moreno Angel Zoe
 */
public class Complex {
    private String alpha;  //a+bi
    private String beta;
    
    public Complex(){
        this.alpha="0";
        this.beta="0";
    }
    
    public Complex(String alpha, String beta){
        this.alpha=alpha;
        this.beta=beta;
    }
    
    public String getAlpha(){
        return this.alpha;
    }
    public String getBeta(){
        return this.beta;
    }
    
    public void setAlpha(String alpha){
        this.alpha=alpha;
    }
    public void setBeta(String beta){
        this.beta=beta;
    }
    
    @Override
    public String toString(){
        return alpha + beta + "i";
    }
}
