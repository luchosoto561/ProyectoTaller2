package Aplicacion;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.text.*;;

@SuppressWarnings("serial")
public class PanelCompra extends JPanel{
	JButton btnVolver;
	JButton btnCompra;
	JButton btnConvertir;
	JLabel Stock2;
	JLabel Precio2;
	JTextField cant;
	JComboBox<String> Opcion;
	JLabel Equi2;
	
	public PanelCompra() {
		setLayout(null);
        setBackground(new Color(222, 184, 135)); // Fondo marroncito
		
		JLabel Stock = new JLabel("Stock: ");
		Stock.setFont(new Font("Arial", Font.BOLD, 20));
		Stock.setBounds(20, 70, 300, 30);
        add(Stock);
        
        Stock2 = new JLabel("100 Bitcoin (BTC)");
		Stock2.setFont(new Font("Arial", Font.BOLD, 20));
		Stock2.setBounds(85, 70, 300, 30);
        add(Stock2);
        
        JLabel Precio = new JLabel("Precio de Compra:");
        Precio.setFont(new Font("Arial", Font.BOLD, 20));
        Precio.setBounds(20, 137, 300, 30);
        add(Precio);
        
        Precio2 = new JLabel("$66,960.39");
        Precio2.setFont(new Font("Arial", Font.BOLD, 20));
        Precio2.setBounds(205, 137, 300, 30);
        add(Precio2);

        JLabel Conversion = new JLabel("Quiero comprar con:");
        Conversion.setFont(new Font("Arial", Font.BOLD, 20));
        Conversion.setBounds(20, 204, 300, 30);
        add(Conversion);
        
        cant = new JTextField();
        cant.setBounds(235, 204, 100, 30);
        cant.setFont(new Font("Arial", Font.BOLD, 20));
        cant.setBorder(BorderFactory.createCompoundBorder(
        	    BorderFactory.createLineBorder(new Color(70, 130, 180), 1), // Borde externo
        	    BorderFactory.createEmptyBorder(5, 10, 5, 10)               // Margen interno
        	));
        ((AbstractDocument) cant.getDocument()).setDocumentFilter(new FiltroNumerico());
        add(cant);
        
        String[] monedas = { "ARS", "USD", "EUR", "JPY" };
        Opcion = new JComboBox<>(monedas);
        Opcion.setFont(new Font("Arial", Font.PLAIN, 18));
        Opcion.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 1));
        Opcion.setBounds(350, 204, 70, 30);
        add(Opcion);
        
        btnConvertir = new JButton("Convertir");
        btnConvertir.setFont(new Font("Arial", Font.PLAIN, 18));
        btnConvertir.setBackground(new Color(34, 139, 34));
        btnConvertir.setForeground(Color.WHITE);
        btnConvertir.setFocusPainted(false);
        btnConvertir.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        btnConvertir.setBounds(440, 199, 100, 40);
        add(btnConvertir);
        
        JLabel Equi = new JLabel("Equivale a");
        Equi.setFont(new Font("Arial", Font.BOLD, 20));
        Equi.setBounds(20, 270, 300, 30);
        add(Equi);
        
        Equi2 = new JLabel(" ");
        Equi2.setFont(new Font("Arial", Font.BOLD, 20));
        Equi2.setBounds(125, 270, 500, 30);
        add(Equi2);

        btnCompra = new JButton("Realizar Compra");
        btnCompra.setFont(new Font("Arial", Font.BOLD, 16));
        btnCompra.setBackground(new Color(139, 69, 19)); // Marrón oscuro
        btnCompra.setForeground(Color.WHITE);
        btnCompra.setFocusPainted(false);
        btnCompra.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        btnCompra.setBounds(120, 350, 150, 40);
        add(btnCompra);
        
        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.setBackground(new Color(139, 69, 19)); // Marrón oscuro
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        btnVolver.setBounds(310, 350, 150, 40);
        add(btnVolver);
        
	}
	
	public JButton getBtnVolver() {
        return btnVolver;
    }
	
	public JButton getBtnCompra() {
        return btnCompra;
    }
	
	public JButton getBtnConvertir() {
        return btnConvertir;
    }
	
	public JTextField getCant() {
        return cant;
    }
	
	public JComboBox<String> getOpcion() {
        return Opcion;
    }
	
	public void setTipo(String tipo) {
        Equi2.setText(tipo);
    }
	
	public JLabel getEqui() {
        return Equi2;
    }
	public void setEqui(String equivalencia, String tipo) {
        Equi2.setText(equivalencia + " " + tipo);
    }
	
	public void setStock(String cant, String tipo) {
        Stock2.setText(cant + " " + tipo);;
    }
	
	public JLabel getPrecio() {
        return Precio2;
    }
	
	public void setPrecio(String precio) {
        Precio2.setText(precio);
    }
	
	class FiltroNumerico extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
            if (text.matches("\\d+")) { // Solo permite números
                super.insertString(fb, offset, text, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text.matches("\\d+")) { // Solo permite números
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
}
