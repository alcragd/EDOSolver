package classes;

/**
 * Tercer nivel de la jerarquía: ecuación lineal de segundo orden.
 * Implementa la interfaz Solvable y reutiliza la lógica de ecCuadraticas.
 */
public class SecondOrderLinearEquation extends LinearEquation implements Solvable {
    private int a, b, c;
    private ecCuadraticas ec;

    public SecondOrderLinearEquation(int a, int b, int c) throws InvalidEquationException {
        super(2);
        // Validación: para segundo orden, a no puede ser 0
        if (a == 0) throw new InvalidEquationException("Coeficiente 'a' no puede ser 0 en una ecuación de segundo orden");
        this.a = a;
        this.b = b;
        this.c = c;
        this.ec = new ecCuadraticas(a, b, c);
    }

    @Override
    public String solveLatex() {
        Pair<Complex, Complex> roots = ec.getRoots();
        Complex r1 = roots.getFirst();
        Complex r2 = roots.getSecond();

        int disc = ec.discriminante();

        String alpha = normalizeCoef(r1.getAlpha());
        String beta  = normalizeCoef(r1.getBeta());
        String alpha2 = normalizeCoef(r2.getAlpha());

        String y1, y2;
        if (disc > 0) {
            y1 = buildRealExp(alpha);
            y2 = buildRealExp(alpha2);
        } else if (disc == 0) {
            if ("0".equals(alpha)) {
                y1 = "";
                y2 = "x";
            } else {
                y1 = "e^{" + alpha + "x}";
                y2 = "x" + y1;
            }
        } else {
            if ("0".equals(alpha)) {
                y1 = "\\cos(" + beta + "x)";
                y2 = "\\sin(" + beta + "x)";
            } else {
                y1 = "e^{" + alpha + "x}\\cos(" + beta + "x)";
                y2 = "e^{" + alpha + "x}\\sin(" + beta + "x)";
            }
        }

        return "Y_c = C_1" + y1 + " + C_2" + y2;
    }

    @Override
    public Pair<Complex, Complex> getRoots() {
        return ec.getRoots();
    }

    /**
     * Obtiene la representación interna de la ecuación cuadrática
     */
    public ecCuadraticas getUnderlyingEc() {
        return this.ec;
    }

    @Override
    public String toLatex() {
        return solveLatex();
    }

    // Redefinición de toString (ejemplo de override)
    @Override
    public String toString() {
        return "SecondOrderLinearEquation{" + a + "," + b + "," + c + "}";
    }

    // --------- Helpers (internal, no UI dependencies) ---------
    private String normalizeCoef(String v) {
        if (v == null) return "0";
        switch (v) {
            case "1": return "";
            case "-1": return "-";
            default: return v;
        }
    }

    private String buildRealExp(String alpha) {
        return "0".equals(alpha) ? "" : "e^{" + alpha + "x}";
    }
}
