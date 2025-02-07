package Aplicacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
@SuppressWarnings("serial")
public class PanelCotizaciones extends JPanel {
    private JTable tablaCotizaciones; // Tabla para las cotizaciones
    private DefaultTableModel modeloTabla; // Modelo para la tabla
    private JButton btnVolver; // Botón para volver
    private JButton btnCerrarSesion; // Botón para cerrar sesión
    JLabel lblBitcoinPrecio;
    JLabel lblEthereumPrecio;
    JLabel lblUsdcPrecio;
    JLabel lblTetherPrecio;
    JLabel lblDogecoinPrecio;
    
    public PanelCotizaciones() {
        setLayout(null);
        setBackground(new Color(222, 184, 135)); // Fondo marroncito
        
     // Etiqueta de título
        JLabel lblTitulo = new JLabel("Cotizaciones");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
        lblTitulo.setBounds(20, 30, 300, 30);
        add(lblTitulo);

        // Botón cerrar sesión
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(420, 10, 150, 30);
        btnCerrarSesion.setFont(new Font("Arial", Font.BOLD, 18));
        btnCerrarSesion.setBackground(new Color(255, 69, 0)); // Rojo anaranjado
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        add(btnCerrarSesion);

        // Panel Bitcoin (BTC)
        JPanel panelBitcoin = new JPanel();
        panelBitcoin.setLayout(null);
        panelBitcoin.setBackground(new Color(245, 245, 220)); // Beige claro
        panelBitcoin.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelBitcoin.setBounds(20, 80, 550, 50);

        JLabel lblBitcoinNombre = new JLabel("Bitcoin (BTC)");
        lblBitcoinNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblBitcoinNombre.setBounds(10, 10, 200, 30);
        panelBitcoin.add(lblBitcoinNombre);

        lblBitcoinPrecio = new JLabel("$66,960.39");
        lblBitcoinPrecio.setFont(new Font("Arial", Font.PLAIN, 14));
        lblBitcoinPrecio.setBounds(220, 10, 150, 30);
        panelBitcoin.add(lblBitcoinPrecio);

        JButton btnBitcoinComprar = new JButton("Comprar");
        btnBitcoinComprar.setFont(new Font("Arial", Font.BOLD, 14));
        btnBitcoinComprar.setBackground(new Color(34, 139, 34)); // Verde
        btnBitcoinComprar.setForeground(Color.WHITE);
        btnBitcoinComprar.setFocusPainted(false);
        btnBitcoinComprar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        btnBitcoinComprar.setBounds(400, 10, 100, 30);
        panelBitcoin.add(btnBitcoinComprar);

        add(panelBitcoin);

        // Panel Ethereum (ETH)
        JPanel panelEthereum = new JPanel();
        panelEthereum.setLayout(null);
        panelEthereum.setBackground(new Color(245, 245, 220)); // Beige claro
        panelEthereum.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelEthereum.setBounds(20, 140, 550, 50);

        JLabel lblEthereumNombre = new JLabel("Ethereum (ETH)");
        lblEthereumNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblEthereumNombre.setBounds(10, 10, 200, 30);
        panelEthereum.add(lblEthereumNombre);

        lblEthereumPrecio = new JLabel("$2,478.33");
        lblEthereumPrecio.setFont(new Font("Arial", Font.PLAIN, 14));
        lblEthereumPrecio.setBounds(220, 10, 150, 30);
        panelEthereum.add(lblEthereumPrecio);

        JButton btnEthereumComprar = new JButton("Comprar");
        btnEthereumComprar.setFont(new Font("Arial", Font.BOLD, 14));
        btnEthereumComprar.setBackground(new Color(34, 139, 34)); // Verde
        btnEthereumComprar.setForeground(Color.WHITE);
        btnEthereumComprar.setFocusPainted(false);
        btnEthereumComprar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        btnEthereumComprar.setBounds(400, 10, 100, 30);
        panelEthereum.add(btnEthereumComprar);

        add(panelEthereum);

        // Panel Usdc (USDC)
        JPanel panelUsdc = new JPanel();
        panelUsdc.setLayout(null);
        panelUsdc.setBackground(new Color(245, 245, 220)); // Beige claro
        panelUsdc.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelUsdc.setBounds(20, 200, 550, 50);

        JLabel lblUsdcNombre = new JLabel("Usdc (USDC)");
        lblUsdcNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblUsdcNombre.setBounds(10, 10, 200, 30);
        panelUsdc.add(lblUsdcNombre);

        lblUsdcPrecio = new JLabel("$0.9999");
        lblUsdcPrecio.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUsdcPrecio.setBounds(220, 10, 150, 30);
        panelUsdc.add(lblUsdcPrecio);

        JButton btnUsdcComprar = new JButton("Comprar");
        btnUsdcComprar.setFont(new Font("Arial", Font.BOLD, 14));
        btnUsdcComprar.setBackground(new Color(34, 139, 34)); // Verde
        btnUsdcComprar.setForeground(Color.WHITE);
        btnUsdcComprar.setFocusPainted(false);
        btnUsdcComprar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        btnUsdcComprar.setBounds(400, 10, 100, 30);
        panelUsdc.add(btnUsdcComprar);

        add(panelUsdc);

        // Panel Dogecoin (DOGE)
        JPanel panelTether = new JPanel();
        panelTether.setLayout(null);
        panelTether.setBackground(new Color(245, 245, 220)); // Beige claro
        panelTether.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelTether.setBounds(20, 260, 550, 50);

        JLabel lblTetherNombre = new JLabel("Tether (USDT)");
        lblTetherNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblTetherNombre.setBounds(10, 10, 200, 30);
        panelTether.add(lblTetherNombre);

        lblTetherPrecio = new JLabel("$0.9986");
        lblTetherPrecio.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTetherPrecio.setBounds(220, 10, 150, 30);
        panelTether.add(lblTetherPrecio);

        JButton btnTetherComprar = new JButton("Comprar");
        btnTetherComprar.setFont(new Font("Arial", Font.BOLD, 14));
        btnTetherComprar.setBackground(new Color(34, 139, 34)); // Verde
        btnTetherComprar.setForeground(Color.WHITE);
        btnTetherComprar.setFocusPainted(false);
        btnTetherComprar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        btnTetherComprar.setBounds(400, 10, 100, 30);
        panelTether.add(btnTetherComprar);

        add(panelTether);
        
     // Panel Dogecoin (DOGE)
        JPanel panelDogecoin = new JPanel();
        panelDogecoin.setLayout(null);
        panelDogecoin.setBackground(new Color(245, 245, 220)); // Beige claro
        panelDogecoin.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelDogecoin.setBounds(20, 320, 550, 50);

        JLabel lblDogecoinNombre = new JLabel("Dogecoin (DOGE)");
        lblDogecoinNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblDogecoinNombre.setBounds(10, 10, 200, 30);
        panelDogecoin.add(lblDogecoinNombre);

        lblDogecoinPrecio = new JLabel("$0.1359");
        lblDogecoinPrecio.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDogecoinPrecio.setBounds(220, 10, 150, 30);
        panelDogecoin.add(lblDogecoinPrecio);

        JButton btnDogecoinComprar = new JButton("Comprar");
        btnDogecoinComprar.setFont(new Font("Arial", Font.BOLD, 14));
        btnDogecoinComprar.setBackground(new Color(34, 139, 34)); // Verde
        btnDogecoinComprar.setForeground(Color.WHITE);
        btnDogecoinComprar.setFocusPainted(false);
        btnDogecoinComprar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        btnDogecoinComprar.setBounds(400, 10, 100, 30);
        panelDogecoin.add(btnDogecoinComprar);

        add(panelDogecoin);
        

        // Botón Volver
        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 14));
        btnVolver.setBackground(new Color(139, 69, 19)); // Marrón oscuro
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        btnVolver.setBounds(210, 400, 150, 40);
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
    
    public void actualizarPrecioBTC(String nuevoTitulo) {
        lblBitcoinPrecio.setText("$" + nuevoTitulo);
    }
    public void actualizarPrecioUSDT(String nuevoTitulo) {
    	lblTetherPrecio.setText("$" + nuevoTitulo);
    }
    public void actualizarPrecioUSDC(String nuevoTitulo) {
    	lblUsdcPrecio.setText("$" + nuevoTitulo);
    }
    public void actualizarPrecioDOGE(String nuevoTitulo) {
    	lblDogecoinPrecio.setText("$" + nuevoTitulo);
    }
    public void actualizarPrecioETH(String nuevoTitulo) {
    	lblEthereumPrecio.setText("$" + nuevoTitulo);
    }

    // Clase interna para renderizar el botón en la tabla
    @SuppressWarnings("unused")
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
    @SuppressWarnings("unused")
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
