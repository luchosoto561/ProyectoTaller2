package Aplicacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
@SuppressWarnings("serial")
public class PanelCotizaciones extends JPanel {
    private JTable tablaCotizaciones;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;
    private JButton btnCerrarSesion;
    JButton btnBTCComprar;
    JButton btnETHComprar;
    JButton btnUSDCComprar;
    JButton btnUSDTComprar;
    JButton btnDOGEComprar;
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

        //agregar imagen Bitcoin
        
        JLabel lblBitcoinNombre = new JLabel("Bitcoin (BTC)");
        lblBitcoinNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblBitcoinNombre.setBounds(70, 10, 200, 30);
        panelBitcoin.add(lblBitcoinNombre);

        lblBitcoinPrecio = new JLabel("$66,960.39");
        lblBitcoinPrecio.setFont(new Font("Arial", Font.PLAIN, 14));
        lblBitcoinPrecio.setBounds(260, 10, 150, 30);
        panelBitcoin.add(lblBitcoinPrecio);

        btnBTCComprar = new JButton("Comprar");
        btnBTCComprar.setFont(new Font("Arial", Font.BOLD, 14));
        btnBTCComprar.setBackground(new Color(34, 139, 34)); // Verde
        btnBTCComprar.setForeground(Color.WHITE);
        btnBTCComprar.setFocusPainted(false);
        btnBTCComprar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        btnBTCComprar.setBounds(440, 10, 100, 30);
        panelBitcoin.add(btnBTCComprar);

        add(panelBitcoin);

        // Panel Ethereum (ETH)
        JPanel panelEthereum = new JPanel();
        panelEthereum.setLayout(null);
        panelEthereum.setBackground(new Color(245, 245, 220)); // Beige claro
        panelEthereum.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelEthereum.setBounds(20, 140, 550, 50);

        //agregar imagen Ethereum
        
        JLabel lblEthereumNombre = new JLabel("Ethereum (ETH)");
        lblEthereumNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblEthereumNombre.setBounds(70, 10, 200, 30);
        panelEthereum.add(lblEthereumNombre);

        lblEthereumPrecio = new JLabel("$2,478.33");
        lblEthereumPrecio.setFont(new Font("Arial", Font.PLAIN, 14));
        lblEthereumPrecio.setBounds(260, 10, 150, 30);
        panelEthereum.add(lblEthereumPrecio);

        btnETHComprar = new JButton("Comprar");
        btnETHComprar.setFont(new Font("Arial", Font.BOLD, 14));
        btnETHComprar.setBackground(new Color(34, 139, 34)); // Verde
        btnETHComprar.setForeground(Color.WHITE);
        btnETHComprar.setFocusPainted(false);
        btnETHComprar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        btnETHComprar.setBounds(440, 10, 100, 30);
        panelEthereum.add(btnETHComprar);

        add(panelEthereum);

        // Panel Usdc (USDC)
        JPanel panelUsdc = new JPanel();
        panelUsdc.setLayout(null);
        panelUsdc.setBackground(new Color(245, 245, 220)); // Beige claro
        panelUsdc.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelUsdc.setBounds(20, 200, 550, 50);
        
      //agregar imagen Usdc

        JLabel lblUsdcNombre = new JLabel("Usdc (USDC)");
        lblUsdcNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblUsdcNombre.setBounds(70, 10, 200, 30);
        panelUsdc.add(lblUsdcNombre);

        lblUsdcPrecio = new JLabel("$0.9999");
        lblUsdcPrecio.setFont(new Font("Arial", Font.PLAIN, 14));
        lblUsdcPrecio.setBounds(260, 10, 150, 30);
        panelUsdc.add(lblUsdcPrecio);

        btnUSDCComprar = new JButton("Comprar");
        btnUSDCComprar.setFont(new Font("Arial", Font.BOLD, 14));
        btnUSDCComprar.setBackground(new Color(34, 139, 34)); // Verde
        btnUSDCComprar.setForeground(Color.WHITE);
        btnUSDCComprar.setFocusPainted(false);
        btnUSDCComprar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        btnUSDCComprar.setBounds(440, 10, 100, 30);
        panelUsdc.add(btnUSDCComprar);

        add(panelUsdc);

        // Panel Tether (USDT)
        JPanel panelTether = new JPanel();
        panelTether.setLayout(null);
        panelTether.setBackground(new Color(245, 245, 220)); // Beige claro
        panelTether.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelTether.setBounds(20, 260, 550, 50);

      //agregar imagen Tether
        
        JLabel lblTetherNombre = new JLabel("Tether (USDT)");
        lblTetherNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblTetherNombre.setBounds(70, 10, 200, 30);
        panelTether.add(lblTetherNombre);

        lblTetherPrecio = new JLabel("$0.9986");
        lblTetherPrecio.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTetherPrecio.setBounds(260, 10, 150, 30);
        panelTether.add(lblTetherPrecio);

        btnUSDTComprar = new JButton("Comprar");
        btnUSDTComprar.setFont(new Font("Arial", Font.BOLD, 14));
        btnUSDTComprar.setBackground(new Color(34, 139, 34)); // Verde
        btnUSDTComprar.setForeground(Color.WHITE);
        btnUSDTComprar.setFocusPainted(false);
        btnUSDTComprar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        btnUSDTComprar.setBounds(440, 10, 100, 30);
        panelTether.add(btnUSDTComprar);

        add(panelTether);
        
     // Panel Dogecoin (DOGE)
        JPanel panelDogecoin = new JPanel();
        panelDogecoin.setLayout(null);
        panelDogecoin.setBackground(new Color(245, 245, 220)); // Beige claro
        panelDogecoin.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelDogecoin.setBounds(20, 320, 550, 50);
        
      //agregar imagen Dogecoin

        JLabel lblDogecoinNombre = new JLabel("Dogecoin (DOGE)");
        lblDogecoinNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblDogecoinNombre.setBounds(70, 10, 200, 30);
        panelDogecoin.add(lblDogecoinNombre);

        lblDogecoinPrecio = new JLabel("$0.1359");
        lblDogecoinPrecio.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDogecoinPrecio.setBounds(260, 10, 150, 30);
        panelDogecoin.add(lblDogecoinPrecio);

        btnDOGEComprar = new JButton("Comprar");
        btnDOGEComprar.setFont(new Font("Arial", Font.BOLD, 14));
        btnDOGEComprar.setBackground(new Color(34, 139, 34)); // Verde
        btnDOGEComprar.setForeground(Color.WHITE);
        btnDOGEComprar.setFocusPainted(false);
        btnDOGEComprar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        btnDOGEComprar.setBounds(440, 10, 100, 30);
        panelDogecoin.add(btnDOGEComprar);

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
    public JButton getCompraBTC() {
    	return btnBTCComprar;
    }
    public JButton getCompraETH() {
    	return btnETHComprar;
    }
    public JButton getCompraUSDT() {
    	return btnUSDTComprar;
    }
    public JButton getCompraUSDC() {
    	return btnUSDCComprar;
    }
    public JButton getCompraDOGE() {
    	return btnDOGEComprar;
    }
    
    public JLabel getPrecioBTC() {
    	return lblBitcoinPrecio;
    }
    public JLabel getPrecioETH() {
    	return lblEthereumPrecio;
    }
    public JLabel getPrecioUSDC() {
    	return lblUsdcPrecio;
    }
    public JLabel getPrecioUSDT() {
    	return lblTetherPrecio;
    }
    public JLabel getPrecioDOGE() {
    	return lblDogecoinPrecio;
    }
    
    public void actualizarPrecioBTC(double precioActualizado) {
        lblBitcoinPrecio.setText("$" + precioActualizado);
    }
    public void actualizarPrecioUSDT(double precioActualizado) {
    	lblTetherPrecio.setText("$" + precioActualizado);
    }
    public void actualizarPrecioUSDC(double precioActualizado) {
    	lblUsdcPrecio.setText("$" + precioActualizado);
    }
    public void actualizarPrecioDOGE(double precioActualizado) {
    	lblDogecoinPrecio.setText("$" + precioActualizado);
    }
    public void actualizarPrecioETH(double precioActualizado) {
    	lblEthereumPrecio.setText("$" + precioActualizado);
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
