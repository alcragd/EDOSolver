package classes;

/* Excepción personalizada para ecuaciones inválidas */
public class InvalidEquationException extends Exception {
    public InvalidEquationException(String message) {
        super(message);
    }

    public InvalidEquationException(String message, Throwable cause) {
        super(message, cause);
    }
}
