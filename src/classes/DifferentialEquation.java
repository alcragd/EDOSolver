package classes;

/**
 * Clase abstracta base para diferentes tipos de ecuaciones diferenciales.
 * Sirve como primer nivel de la jerarquía de herencia solicitada.
 */
public abstract class DifferentialEquation {
    /**
     * Devuelve la orden de la ecuación (1,2,...)
     */
    public abstract int order();

    /**
     * Resuelve o devuelve la representación de la solución en LaTeX
     */
    public abstract String solveLatex();

    @Override
    public String toString() {
        return "DifferentialEquation(order=" + order() + ")";
    }
}
