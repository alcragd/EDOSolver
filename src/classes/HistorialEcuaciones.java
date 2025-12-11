package classes;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que mantiene un historial de ecuaciones resueltas
 * Demuestra uso de ArrayList para almacenar colecciones dinámicas
 * @author Coyol Moreno Angel Zoe
 */
public class HistorialEcuaciones {
    
    /**
     * Clase interna para representar un registro de historial
     */
    public static class Registro {
        private ecCuadraticas ecuacion;
        private Pair<Complex, Complex> raices;
        private String solucion;
        private LocalDateTime fecha;
        
        public Registro(ecCuadraticas eq, Pair<Complex, Complex> r, String sol) {
            this.ecuacion = eq;
            this.raices = r;
            this.solucion = sol;
            this.fecha = LocalDateTime.now();
        }
        
        public ecCuadraticas getEcuacion() { return ecuacion; }
        public Pair<Complex, Complex> getRaices() { return raices; }
        public String getSolucion() { return solucion; }
        public LocalDateTime getFecha() { return fecha; }
        
        @Override
        public String toString() {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return "[" + fecha.format(fmt) + "] " + ecuacion + " → " + solucion;
        }
    }
    
    // ArrayList para almacenar el historial
    private ArrayList<Registro> historial;
    private static final int MAX_HISTORIAL = 100;

    // Constructor original
    public HistorialEcuaciones() {
        this.historial = new ArrayList<>();
    }

    // Constructor sobrecargado: define tamaño máximo
    public HistorialEcuaciones(int maxHistorial) {
        this.historial = new ArrayList<>();
        // si maxHistorial <= 0, se mantiene el default
        if (maxHistorial > 0) {
            this.maxSize = maxHistorial;
        }
    }

    // Constructor sobrecargado: inicia con un registro
    public HistorialEcuaciones(ecCuadraticas eq, Pair<Complex, Complex> raices, String solucion) {
        this.historial = new ArrayList<>();
        agregarRegistro(eq, raices, solucion);
    }

    // Añade este campo para usar el tamaño máximo configurable
    private int maxSize = MAX_HISTORIAL;
    
    /**
     * Agrega un nuevo registro al historial
     * @param eq ecuación resuelta
     * @param raices raíces encontradas
     * @param solucion solución en formato LaTeX
     */
    public void agregarRegistro(ecCuadraticas eq, Pair<Complex, Complex> raices, String solucion) {
        Registro reg = new Registro(eq, raices, solucion);
        historial.add(reg);

        int limite = (maxSize > 0) ? maxSize : MAX_HISTORIAL;
        if (historial.size() > limite) {
            historial.remove(0);
        }
    }
    
    /**
     * Obtiene todo el historial
     * @return ArrayList con todos los registros
     */
    public ArrayList<Registro> obtenerHistorial() {
        return new ArrayList<>(historial);
    }
    
    /**
     * Obtiene un registro específico por índice
     * @param indice índice del registro
     * @return Registro solicitado
     */
    public Registro obtenerRegistro(int indice) {
        if (indice < 0 || indice >= historial.size()) {
            return null;
        }
        return historial.get(indice);
    }
    
    /**
     * Obtiene la cantidad de registros en el historial
     * @return tamaño del historial
     */
    public int obtenerTamaño() {
        return historial.size();
    }
    
    /**
     * Busca registros que contengan un valor específico
     * @param valor valor a buscar en la solución
     * @return ArrayList con registros que coinciden
     */
    public ArrayList<Registro> buscar(String valor) {
        ArrayList<Registro> resultados = new ArrayList<>();
        for (Registro reg : historial) {
            if (reg.getSolucion().contains(valor)) {
                resultados.add(reg);
            }
        }
        return resultados;
    }
    
    /**
     * Limpia todo el historial
     */
    public void limpiar() {
        historial.clear();
    }
    
    /**
     * Elimina un registro específico
     * @param indice índice del registro a eliminar
     * @return true si se eliminó correctamente
     */
    public boolean eliminarRegistro(int indice) {
        if (indice < 0 || indice >= historial.size()) {
            return false;
        }
        historial.remove(indice);
        return true;
    }
    
    /**
     * Obtiene todos los registros en formato texto
     * @return String con el historial formateado
     */
    public String obtenerHistorialFormateado() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < historial.size(); i++) {
            sb.append((i + 1)).append(". ").append(historial.get(i)).append("\n");
        }
        return sb.toString();
    }
}