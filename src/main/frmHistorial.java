package main;

import javax.swing.*;
import classes.HistorialEcuaciones;
import java.util.ArrayList;
import java.awt.*;

/**
 * Frame para visualizar y gestionar el historial de ecuaciones
 * Demuestra uso de ArrayList
 */
public class frmHistorial extends JFrame {
    private JList<String> listRegistros;
    private DefaultListModel<String> modeloLista;
    private ArrayList<HistorialEcuaciones.Registro> registros;
    private HistorialEcuaciones historial;
    
    public frmHistorial(HistorialEcuaciones hist) {
        this.historial = hist;
        this.registros = new ArrayList<>();
        
        setTitle("Historial de Ecuaciones");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Etiqueta
        JLabel lblTitulo = new JLabel("Ecuaciones Resueltas (" + historial.obtenerTamaño() + ")");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(lblTitulo, BorderLayout.NORTH);
        
        // Lista con ArrayList del historial
        modeloLista = new DefaultListModel<>();
        this.registros = historial.obtenerHistorial();
        
        // ============= USAR ARRAYLIST =============
        for (HistorialEcuaciones.Registro reg : registros) {
            modeloLista.addElement(reg.toString());
        }
        
        listRegistros = new JList<>(modeloLista);
        listRegistros.setFont(new Font("Courier New", Font.PLAIN, 11));
        
        JScrollPane scroll = new JScrollPane(listRegistros);
        panel.add(scroll, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel panelBotones = new JPanel();
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(evt -> {
            int indice = listRegistros.getSelectedIndex();
            if (indice >= 0) {
                historial.eliminarRegistro(indice);
                modeloLista.removeElementAt(indice);
            }
        });
        
        JButton btnLimpiar = new JButton("Limpiar Todo");
        btnLimpiar.addActionListener(evt -> {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "¿Limpiar todo el historial?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                historial.limpiar();
                modeloLista.clear();
            }
        });
        
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        add(panel);
    }
}