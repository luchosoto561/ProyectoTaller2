import javax.swing.*;
import java.awt.*;
public class PanelMisOperaciones extends JPanel {
    private JList<String> listaOperaciones; // Lista para mostrar las operaciones
    private DefaultListModel<String> modeloLista; // Modelo para gestionar los elementos de la lista
    private JButton btnVolver; // Botón para volver
    
    public PanelMisOperaciones() {
        setLayout(null); // Usamos un layout absoluto
        // Título del panel
        JLabel lblTitulo = new JLabel("Lista de Operaciones");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBounds(120, 10, 200, 30);
        add(lblTitulo);
        // Modelo para la lista
        modeloLista = new DefaultListModel<>();
        listaOperaciones = new JList<>(modeloLista);
        listaOperaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Scroll para la lista
        JScrollPane scrollPane = new JScrollPane(listaOperaciones);
        scrollPane.setBounds(50, 50, 300, 200);
        add(scrollPane);
        // Botón Volver
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(150, 270, 100, 30);
        add(btnVolver);
    }
    // Métodos get para los componentes
    public JList<String> getListaOperaciones() {
        return listaOperaciones;
    }
    public DefaultListModel<String> getModeloLista() {
        return modeloLista;
    }
    public JButton getBtnVolver() {
        return btnVolver;
    }
}
