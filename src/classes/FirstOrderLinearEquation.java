package classes;

/**
 * Representa una ecuación diferencial de primer orden b y + c y = 0
 */
public class FirstOrderLinearEquation extends LinearEquation implements Solvable {
    private int b, c;

    public FirstOrderLinearEquation(int b, int c) throws InvalidEquationException {
        super(1);
        // Validación: para primer orden, b (coef de y') no puede ser 0
        if (b == 0) throw new InvalidEquationException("Coeficiente 'b' no puede ser 0 en una ecuación de primer orden");
        this.b = b;
        this.c = c;
    }

    @Override
    public String solveLatex() {
        // Reutiliza la lógica de frmSolver.buildY1FirstOrderLatex pero aquí devolvemos la solución completa
        int gcd = mathUtils.gcd(b, c);
        int bb = b / gcd;
        int cc = c / gcd;
        cc = -cc;
        if (bb < 0) { bb = -bb; cc = -cc; }

        String coef;
        if (bb == 1 && cc == 1) {
            coef = "1";
        } else if (cc == 1) {
            coef = "\\frac{1}{" + bb + "}";
        } else if (bb == 1) {
            coef = cc + "";
        } else {
            coef = "\\frac{" + cc + "}{" + bb + "}";
        }

        if ("0".equals(coef)) return "Y_c = C";
        if ("1".equals(coef)) return "Y_c = Ce^{x}";
        if ("-1".equals(coef)) return "Y_c = Ce^{-x}";

        return "Y_c = Ce^{" + coef + "x}";
    }

    @Override
    public Pair<Complex, Complex> getRoots() {
        return null; // no aplica para primer orden
    }

    public int getB() { return this.b; }
    public int getC() { return this.c; }

    @Override
    public String toLatex() {
        return solveLatex();
    }

    @Override
    public String toString() {
        return "FirstOrderLinearEquation{" + b + "," + c + "}";
    }
}
