import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class PanelCotizaciones extends JPanel {
    private JTable tablaCotizaciones; // Tabla para las cotizaciones
    private DefaultTableModel modeloTabla; // Modelo para la tabla
    private JButton btnVolver; // Botón para volver
    private JButton btnCerrarSesion; // Botón para cerrar sesión
    
    public PanelCotizaciones() {
        setLayout(null); // Usamos un layout absoluto
        // Botón Cerrar Sesión (arriba a la derecha)
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(300, 10, 120, 30);
        add(btnCerrarSesion);
        // Modelo de la tabla
        modeloTabla = new DefaultTableModel(new Object[][]{}, new String[]{"Símbolo", "Cripto", "Precio de Compra", "Comprar"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Solo el botón "Comprar" es editable
            }
        };
        // Tabla
        tablaCotizaciones = new JTable(modeloTabla);
        tablaCotizaciones.getColumn("Comprar").setCellRenderer(new ButtonRenderer());
        tablaCotizaciones.getColumn("Comprar").setCellEditor(new ButtonEditor(new JCheckBox()));
        // Scroll para la tabla
        JScrollPane scrollPane = new JScrollPane(tablaCotizaciones);
        scrollPane.setBounds(20, 50, 400, 200);
        add(scrollPane);
        // Botón Volver (abajo de la tabla)
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(150, 270, 100, 30);
        add(btnVolver);
    }
    // Métodos Getters para los componentes
    public JTable getTablaCotizaciones() {
        return tablaCotizaciones;
    }
    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }
    public JButton getBtnVolver() {
        return btnVolver;
    }
    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }
    // Clase interna para renderizar el botón en la tabla
    private class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setText("Comprar");
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, 
                                                       boolean isSelected, boolean hasFocus, 
                                                       int row, int column) {
            return this;
        }
    }
    // Clase interna para manejar la edición del botón en la tabla
    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean clicked;
        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.addActionListener(e -> fireEditingStopped());
        }
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, 
                                                     boolean isSelected, int row, int column) {
            label = "Comprar";
            button.setText(label);
            clicked = true;
            return button;
        }
        @Override
        public Object getCellEditorValue() {
            if (clicked) {
                // Acción al hacer clic en "Comprar"
                JOptionPane.showMessageDialog(button, "Comprar cripto en fila " + tablaCotizaciones.getSelectedRow());
            }
            clicked = false;
            return label;
        }
        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }
    }
}
