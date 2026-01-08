package classes;

/**
 * Interfaz que define la capacidad de resolver y exportar una ecuación
 */
public interface Solvable {
    /**
     * Devuelve la representación LaTeX de la solución o de la ecuación
     * @return String en formato LaTeX
     */
    String toLatex();

    /**
     * Obtiene las raíces asociadas a la ecuación si aplican
     * @return Pair con las dos raíces (pueden ser iguales)
     */
    Pair<Complex, Complex> getRoots();
}
