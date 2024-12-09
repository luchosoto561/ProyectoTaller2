package Aplicacion;

import javax.swing.*;

public class PanelPrincipal extends JPanel {
    private JTextField gmail;
    private JButton registrarse;
    private JButton login;
    private JLabel titulo;
    private JPasswordField password;
    private JButton cierre;

    public PanelPrincipal() {
        // Crear y configurar los componentes
        setLayout(null); // Usamos un layout nulo para establecer las posiciones manualmente

        // Configurar el título
        titulo = new JLabel("Panel de login");
        titulo.setBounds(100, 20, 300, 30);
        add(titulo);  // Agregar el título al panel

        // Configurar el campo de Gmail
        gmail = new JTextField();
        gmail.setBounds(100, 60, 250, 30);
        add(gmail);  // Agregar el campo de texto al panel

        // Configurar la contraseña
        password = new JPasswordField();
        password.setBounds(100, 100, 250, 30);
        add(password);  // Agregar el campo de contraseña al panel

        // Configurar el botón de Login
        login = new JButton("Login");
        login.setBounds(100, 140, 100, 30);
        add(login);  // Agregar el botón de login al panel

        // Configurar el botón de Registrarse
        registrarse = new JButton("Registrarse");
        registrarse.setBounds(100, 180, 250, 30);
        add(registrarse);  // Agregar el botón de registrarse al panel

        // Configurar el botón de Cierre
        cierre = new JButton("Cerrar");
        cierre.setBounds(320, 20, 100, 30);
        add(cierre);  // Agregar el botón de cierre al panel
    }

    // Métodos para obtener los valores de los componentes
    public JTextField getGmail() {
        return gmail;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public JButton getLoginButton() {
        return login;
    }

    public JButton getRegistrarseButton() {
        return registrarse;
    }

    public JButton getCierreButton() {
        return cierre;
    }
}