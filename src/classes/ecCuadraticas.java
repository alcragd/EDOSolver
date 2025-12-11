/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;
import java.util.Map;

/**
 *
 * @author Coyol Moreno Angel Zoe
 */
public class ecCuadraticas {
    private int a;
    private int b;
    private int c;
    
    // Constructor original
    public ecCuadraticas(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    // SOBRECARGA 1: Constructor sin parámetros
    public ecCuadraticas() {
        this(1, 0, 0); // valores por defecto
    }
    
    // SOBRECARGA 2: Constructor con solo dos parámetros (para ecuaciones de primer orden)
    public ecCuadraticas(int b, int c) {
        this(0, b, c);
    }
    
    public int getA(){
        return this.a;
    }
    
    public int getB(){
        return this.b;
    }
    
    public int getC(){
        return this.c;
    }
    
    public void setA(int a){
        this.a=a;
    }
    
    public void setB(int b){
        this.b=b;
    }
    
    public void setC(int c){
        this.c=c;
    }
    
    public int discriminante(){
        return b*b -4*a*c;
    }
    
    private String frac(int num, int den) {
    if (den == 1) return "" + num;
    return "\\frac{" + num + "}{" + den + "}";
    }
    
    private String imagPart(int qNum, int qDen, int Im) {

    // Determinar si hay radical
    String radical = (Im == 1) ? "" : "\\sqrt{" + Im + "}";

    // Coeficiente sin signo
    int n = Math.abs(qNum);
    int d = qDen;

    String coef;

    if (n == 1 && d == 1) {
        // 1 → se omite
        coef = "";
    } 
    else if (d == 1) {
        // n / 1  → n
        coef = "" + n;
    } 
    else if (n == 1) {
        // 1/d → 1/d
        coef = "\\frac{1}{" + d + "}";
    } 
    else {
        // n/d
        coef = "\\frac{" + n + "}{" + d + "}";
    }

    // Unir coeficiente + radical
    String term = coef + radical;

    // Si todo se canceló (ej: coef="" y radical="") → coef era 1, Im=1
    if (term.equals("")) term = "1";


    // Aplicar signo
    if (qNum < 0) return "-" + term;
    return term;
    }

    
    public Pair<Complex, Complex> getRoots() {
        // versión original completa
        // p = -b / (2a)
        // q = sqrt(b^2 - 4ac) / (2a)
        Complex r1 = new Complex(), r2 = new Complex();

        int D = discriminante();
        int a2 = 2 * a;

        // ----------- PARTE P ( -b / 2a ) -----------
        int gcdP = mathUtils.gcd(-b, a2);
        int pNum = -b / gcdP;
        int pDen = a2 / gcdP;

        // -------------- D = 0 ----------------------
        if (D == 0) {
            if (pNum == 0) {
                r1.setAlpha("0");
            } else if (pDen == 1) {
                r1.setAlpha(pNum + "");
            } else {
                r1.setAlpha("\\frac{" + pNum + "}{" + pDen + "}");
            }
            r2 = r1;
            return new Pair<>(r1, r2);
        }

        // ----------- FACTORIZACIÓN DEL DISCRIMINANTE -----------
        Map<Integer, Integer> discFactors = mathUtils.getPrimeFactorization(Math.abs(D));

        int dsqrt = 1;  // parte que queda dentro de la raíz
        int dcoef = 1;  // coeficiente que sale de la raíz

        for (Map.Entry<Integer, Integer> e : discFactors.entrySet()) {
            int prime = e.getKey();
            int exp = e.getValue();

            // Parte que sale de la raiz
            for (int i = 0; i < exp / 2; i++)
                dcoef *= prime;

            // Parte que queda dentro de la raiz
            if (exp % 2 == 1)
                dsqrt *= prime;
        }

        // ----------- PARTE Q ( dcoef*sqrt(dsqrt) / 2a ) -----------
        int gcdQ = mathUtils.gcd(dcoef, a2);
        int qNum = dcoef / gcdQ;
        int qDen = a2 / gcdQ;

        // -------------------- D > 0 ------------------------
        if (D > 0) {

            // ---- Caso √D exacta: dsqrt = 1 ----
            if (dsqrt == 1) {

                // r1 = p + q
                int num1 = pNum * qDen + qNum * pDen;
                int den1 = pDen * qDen;
                int g1 = mathUtils.gcd(num1, den1);
                num1 /= g1; den1 /= g1;

                // r2 = p - q
                int num2 = pNum * qDen - qNum * pDen;
                int den2 = pDen * qDen;
                int g2 = mathUtils.gcd(num2, den2);
                num2 /= g2; den2 /= g2;

                // r1
                if (num1 == 0) r1.setAlpha("0");
                else if (den1 == 1) r1.setAlpha(num1 + "");
                else r1.setAlpha("\\frac{" + num1 + "}{" + den1 + "}");

                // r2
                if (num2 == 0) r2.setAlpha("0");
                else if (den2 == 1) r2.setAlpha(num2 + "");
                else r2.setAlpha("\\frac{" + num2 + "}{" + den2 + "}");

                return new Pair<>(r1, r2);
            }

            // ----------- Caso raíz irracional: dsqrt > 1 -----------
            // r1 = p + (qNum/qDen)*sqrt(dsqrt)
            // r2 = p - (qNum/qDen)*sqrt(dsqrt)

            String pStr;
            if (pNum == 0)               pStr = "0";
            else if (pDen == 1)          pStr = pNum + "";
            else                         pStr = "\\frac{" + pNum + "}{" + pDen + "}";

            String qStr;
            if (qNum == 1 && qDen == 1)  qStr = "\\sqrt{" + dsqrt + "}";
            else if (qDen==1)            qStr = qNum+"\\sqrt{" + dsqrt + "}";
            else                         qStr = "\\frac{" + qNum + "}{" + qDen + "}\\sqrt{" + dsqrt + "}";

            // r1 = p + q*sqrt
            if (pNum == 0)
                r1.setAlpha(qStr);
            else
                r1.setAlpha("("+pStr + " + " + qStr+")");

            // r2 = p - q*sqrt
            if (pNum == 0)
                r2.setAlpha("-" + qStr);
            else
                r2.setAlpha("("+pStr + " - " + qStr+")");

            return new Pair<>(r1, r2);
        }

        // -------------------- D < 0 (raíces complejas) ------------------------
        int Im = dsqrt; // parte imaginaria dentro de sqrt
        String pStr = frac(pNum, pDen);
        String imagStr = imagPart(qNum, qDen, Im);

        // r1 = p + qi
        r1.setAlpha(pStr);
        r1.setBeta(imagStr);

        // r2 = p - qi   (solo cambiar signo)
        r2.setAlpha(pStr);

        if(imagStr.startsWith("-"))
            r2.setBeta(imagStr.substring(1));
        else
            r2.setBeta("-" + imagStr);

        return new Pair<>(r1, r2);
    }
}


