package Aplicacion;

import javax.swing.*;
import java.awt.*;

public class PanelRegistro extends JPanel {
    private JTextField nombres;
    private JTextField apellidos;
    private JTextField email;
    private JPasswordField password;
    private JCheckBox aceptarTerminos;
    private JButton registrar;
    private JButton cierre;

    public PanelRegistro() {
    	// Configuración del panel
    	setLayout(null); // Usamos un layout nulo para establecer las posiciones manualmente

    	// Configuración del título
    	JLabel titulo = new JLabel("Registración");
    	titulo.setFont(new Font("Arial", Font.BOLD, 18));
    	titulo.setBounds(170, 20, 200, 30);
    	add(titulo);

    	// Configuración del campo de Nombres
    	JLabel lblNombres = new JLabel("Nombres:");
    	lblNombres.setBounds(50, 60, 100, 30);
    	add(lblNombres);
    	nombres = new JTextField();
    	nombres.setBounds(150, 60, 250, 30);
    	add(nombres);

    	// Configuración del campo de Apellidos
    	JLabel lblApellidos = new JLabel("Apellidos:");
    	lblApellidos.setBounds(50, 100, 100, 30);
    	add(lblApellidos);
    	apellidos = new JTextField();
    	apellidos.setBounds(150, 100, 250, 30);
    	add(apellidos);

    	// Configuración del campo de Email
    	JLabel lblEmail = new JLabel("Email:");
    	lblEmail.setBounds(50, 140, 100, 30);
    	add(lblEmail);
    	email = new JTextField();
    	email.setBounds(150, 140, 250, 30);
    	add(email);

    	// Configuración del campo de Contraseña
    	JLabel lblPassword = new JLabel("Contraseña:");
    	lblPassword.setBounds(50, 180, 100, 30);
    	add(lblPassword);
    	password = new JPasswordField();
    	password.setBounds(150, 180, 250, 30);
    	add(password);

    	// Configuración del checkbox para aceptar términos
    	aceptarTerminos = new JCheckBox("Acepto términos y condiciones");
    	aceptarTerminos.setBounds(150, 220, 250, 30);
    	add(aceptarTerminos);

    	// Configuración del botón de Registrar
    	registrar = new JButton("Registrar");
    	registrar.setBounds(150, 260, 100, 30);
    	add(registrar);

    	// Configuración del botón de Cierre
    	cierre = new JButton("Cerrar");
    	cierre.setBounds(380, 10, 80, 30);
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

    public JButton getCierreButton() {
    	return cierre;
    }
}