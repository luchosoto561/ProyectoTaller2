package Aplicacion;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class PanelRegistro extends JPanel {
    private JTextField nombres;
    private JTextField apellidos;
    private JTextField email;
    private JPasswordField password;
    private JCheckBox aceptarTerminos;
    private JButton registrar;
    private JButton cuenta;
    private JButton cierre;
    
    public PanelRegistro() {
    	// Configuración del panel
    	setLayout(null);
    	setBackground(new Color(222, 184, 135)); // Marroncito
    	
    	// Configuración del título
    	JLabel titulo = new JLabel("Crea tu cuenta");
    	titulo.setFont(new Font("Arial", Font.BOLD, 28));
    	titulo.setBounds(195, 40, 200, 30);
    	add(titulo);
    	
    	// Configuración del campo de Nombres
    	JLabel lblNombres = new JLabel("Nombres:");
    	lblNombres.setBounds(110, 100, 100, 30);
    	lblNombres.setFont(new Font("Arial", Font.PLAIN, 18));
    	add(lblNombres);
    	nombres = new JTextField();
    	nombres.setBounds(210, 100, 250, 30);
    	nombres.setFont(new Font("Arial", Font.PLAIN, 18));
    	nombres.setBorder(BorderFactory.createCompoundBorder(
        	    BorderFactory.createLineBorder(new Color(70, 130, 180), 1), // Borde externo
        	    BorderFactory.createEmptyBorder(5, 10, 5, 10)               // Margen interno
        	));
    	add(nombres);
    	
    	// Configuración del campo de Apellidos
    	JLabel lblApellidos = new JLabel("Apellidos:");
    	lblApellidos.setBounds(110, 140, 100, 30);
    	lblApellidos.setFont(new Font("Arial", Font.PLAIN, 18));
    	add(lblApellidos);
    	apellidos = new JTextField();
    	apellidos.setBounds(210, 140, 250, 30);
    	apellidos.setFont(new Font("Arial", Font.PLAIN, 18));
    	apellidos.setBorder(BorderFactory.createCompoundBorder(
        	    BorderFactory.createLineBorder(new Color(70, 130, 180), 1), // Borde externo
        	    BorderFactory.createEmptyBorder(5, 10, 5, 10)               // Margen interno
        	));
    	add(apellidos);
    	
    	// Configuración del campo de Email
    	JLabel lblEmail = new JLabel("Email:");
    	lblEmail.setBounds(110, 180, 100, 30);
    	lblEmail.setFont(new Font("Arial", Font.PLAIN, 18));
    	add(lblEmail);
    	email = new JTextField();
    	email.setBounds(210, 180, 250, 30);
    	email.setBorder(BorderFactory.createCompoundBorder(
        	    BorderFactory.createLineBorder(new Color(70, 130, 180), 1), // Borde externo
        	    BorderFactory.createEmptyBorder(5, 10, 5, 10)               // Margen interno
        	));
    	email.setFont(new Font("Arial", Font.PLAIN, 18));
    	add(email);
    	
    	// Configuración del campo de Contraseña
    	JLabel lblPassword = new JLabel("Contraseña:");
    	lblPassword.setBounds(110, 220, 100, 30);
    	lblPassword.setFont(new Font("Arial", Font.PLAIN, 18));
    	add(lblPassword);
    	password = new JPasswordField();
    	password.setBounds(210, 220, 250, 30);
    	password.setBorder(BorderFactory.createCompoundBorder(
        	    BorderFactory.createLineBorder(new Color(70, 130, 180), 1), // Borde externo
        	    BorderFactory.createEmptyBorder(5, 10, 5, 10)               // Margen interno
        	));
    	password.setFont(new Font("Arial", Font.PLAIN, 18));
    	add(password);
    	
    	// Configuración del checkbox para aceptar términos
        aceptarTerminos = new JCheckBox("Aceptar términos y condiciones");
    	aceptarTerminos.setBounds(195, 260, 250, 30);
    	aceptarTerminos.setBackground(new Color(222, 184, 135)); // Marroncito
    	add(aceptarTerminos);
    	
    	// Configuración del botón de Registrar
    	registrar = new JButton("Registrar");
    	registrar.setBounds(150, 300, 150, 30);
    	registrar.setFont(new Font("Arial", Font.BOLD, 18));
    	registrar.setForeground(Color.WHITE);
        registrar.setFocusPainted(false);
        registrar.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
    	registrar.setBackground(new Color(139, 69, 19)); // Marrón oscuro
    	add(registrar);
    	
    	cuenta = new JButton("Ya tengo cuenta");
    	cuenta.setBounds(310, 300, 150, 30);
    	cuenta.setFont(new Font("Arial", Font.BOLD, 17));
    	cuenta.setForeground(Color.WHITE);
    	cuenta.setFocusPainted(false);
    	cuenta.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
    	cuenta.setBackground(new Color(139, 69, 19)); // Marrón oscuro
    	add(cuenta);
    	
    	// Configurar el botón de Cierre
        cierre = new JButton("Cerrar");
        cierre.setBounds(196, 380, 200, 30);
        cierre.setFont(new Font("Arial", Font.BOLD, 18));
        cierre.setBackground(new Color(255, 69, 0)); // Rojo anaranjado
        cierre.setForeground(Color.WHITE);
        cierre.setFocusPainted(false);
        cierre.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
        add(cierre);
    }
    // Métodos para obtener los valores de los campos
    public JTextField getNombres() {
    	return nombres;
    }
    public JTextField getApellidos() {
    	return apellidos;
    }
    public JTextField getEmail() {
    	return email;
    }
    public JPasswordField getPassword() {
    	return password;
    }
    public JCheckBox getAceptarTerminos() {
    	return aceptarTerminos;
    }
    public JButton getRegistrarButton() {
    	return registrar;
    }
    public JButton getCuentaButton() {
    	return cuenta;
    }
    public JButton getCierreButton() {
    	return cierre;
    }
}
