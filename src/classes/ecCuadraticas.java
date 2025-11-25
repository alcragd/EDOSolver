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
    
    public ecCuadraticas(int a,int b, int c){
        this.a=a;
        this.b=b;
        this.c=c;
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
    
    public Pair<Complex, Complex> getRoots() {
        // p = -b / (2a)
        // q = sqrt(b^2 - 4ac) / (2a)
        Complex r1 = new Complex(), r2 = new Complex();

        int D = discriminante();
        int a2 = 2 * a;

        // ----------- PARTE P ( -b / 2a ) -----------
        int gcdP = gcd(-b, a2);
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
        Map<Integer, Integer> discFactors = PrimeFactors.getPrimeFactorization(Math.abs(D));

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
        int gcdQ = gcd(dcoef, a2);
        int qNum = dcoef / gcdQ;
        int qDen = a2 / gcdQ;

        // -------------------- D > 0 ------------------------
        if (D > 0) {

            // ---- Caso √D exacta: dsqrt = 1 ----
            if (dsqrt == 1) {

                // r1 = p + q
                int num1 = pNum * qDen + qNum * pDen;
                int den1 = pDen * qDen;
                int g1 = gcd(num1, den1);
                num1 /= g1; den1 /= g1;

                // r2 = p - q
                int num2 = pNum * qDen - qNum * pDen;
                int den2 = pDen * qDen;
                int g2 = gcd(num2, den2);
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

        // parte real p
        String pStr;
        if (pNum == 0)               pStr = "0";
        else if (pDen == 1)          pStr = pNum + "";
        else                         pStr = "\\frac{" + pNum + "}{" + pDen + "}";

        // parte imaginaria q
        String qStr;
        if(qDen==1 && qNum==1 && Im==1)
            qStr="";
        else if(qDen==1 && qNum==1)
            qStr="\\sqrt{"+Im+"}";
        else if(qDen==1 && Im==1)
            qStr=""+qNum;
        else if(qNum==1 && Im==1)
            qStr="\\frac{1}{"+qDen+"}";
        else if(qDen==1)
            qStr=qNum+"\\sqrt{"+Im+"}";
        else if(qNum==1)
            qStr="\\frac{\\sqrt{"+Im+"}}{"+qDen+"}";
        else if(Im==1)
            qStr="\\frac{"+qNum+"}{"+qDen+"}";
        else
            qStr="\\frac{"+qNum+"\\sqrt{"+Im+"}}{"+qDen+"}";

        r1.setAlpha(pStr);
        r1.setBeta(qStr);
        
        r2.setAlpha("-"+qStr);

        return new Pair<>(r1, r2);
    }

    
    
    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    
}


