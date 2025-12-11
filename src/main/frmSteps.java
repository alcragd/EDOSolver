package main;

import javax.swing.*;
import javax.swing.Box;
import org.scilab.forge.jlatexmath.*;
import java.awt.*;

public class frmSteps extends JFrame {

    public frmSteps(String p1, String p2, String p3, String p4) {
        setTitle("Pasos de Resolución - Ecuación Diferencial");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Panel principal con fondo blanco
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel title = new JLabel("Pasos de Resolución");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        mainPanel.add(title);

        // Separador
        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        sep.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(sep);
        mainPanel.add(Box.createVerticalStrut(15));

        // Agregar pasos con paneles individuales
        mainPanel.add(createStepPanel(p1));
        mainPanel.add(Box.createVerticalStrut(10));
        
        mainPanel.add(createStepPanel(p2));
        mainPanel.add(Box.createVerticalStrut(10));
        
        mainPanel.add(createStepPanel(p3));
        mainPanel.add(Box.createVerticalStrut(10));
        
        mainPanel.add(createStepPanel(p4));

        // Glue para empujar contenido hacia arriba
        mainPanel.add(Box.createVerticalGlue());

        JScrollPane scroll = new JScrollPane(mainPanel);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBorder(null);
        add(scroll);
    }

    /**
     * Crea un panel para cada paso con borde y fondo
     */
    private JPanel createStepPanel(String latexText) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(245, 245, 250));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 220), 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        JLabel latexLabel = latexLabel(latexText);
        panel.add(latexLabel, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Crea un JLabel con renderizado LaTeX
     */
    private JLabel latexLabel(String tex) {
        try {
            TeXFormula f = new TeXFormula(tex);
            TeXIcon icon = f.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
            icon.setInsets(new Insets(5, 5, 5, 5));
            JLabel lbl = new JLabel(icon);
            lbl.setAlignmentX(LEFT_ALIGNMENT);
            return lbl;
        } catch (Exception e) {
            // Si hay error en LaTeX, mostrar texto plano
            JLabel errorLabel = new JLabel("Error al renderizar: " + tex);
            errorLabel.setForeground(Color.RED);
            return errorLabel;
        }
    }
}