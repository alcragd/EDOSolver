package classes;

/**
 * Segundo nivel de la jerarquía: ecuaciones lineales (genéricas).
 */
public class LinearEquation extends DifferentialEquation {
    protected int order;

    public LinearEquation(int order) {
        this.order = order;
    }

    @Override
    public int order() {
        return order;
    }

    @Override
    public String solveLatex() {
        return ""; // Implementación genérica; override en subclases
    }

    @Override
    public String toString() {
        return "LinearEquation(order=" + order + ")";
    }
}
