/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;


import java.util.LinkedHashMap;
import java.util.Map;


/**
 *
 * @author angel
 */
public class mathUtils {

    public static Map<Integer, Integer> getPrimeFactorization(int n) {
    Map<Integer, Integer> map = new LinkedHashMap<>();

    n=Math.abs(n);
    
    while (n % 2 == 0) {
        map.put(2, map.getOrDefault(2, 0) + 1);
        n /= 2;
    }

    for (int i = 3; i * i <= n; i += 2) {
        while (n % i == 0) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            n /= i;
        }
    }

    if (n > 2) {
        map.put(n, map.getOrDefault(n, 0) + 1);
    }

    return map;
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