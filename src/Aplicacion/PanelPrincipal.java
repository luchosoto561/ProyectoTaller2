import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class PanelLogIn extends JPanel {
    private JTextField gmail;
    private JButton registrarse;
    private JButton login;
    private JLabel titulo;
    private JPasswordField password;
    private JButton cierre;

    public PanelLogIn() {
        // Configuración general del panel
        setLayout(null);
        setBackground(new Color(222, 184, 135)); // Marroncito

        // Configurar el título
        titulo = new JLabel("Bienvenido");
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(new Color(40, 40, 40)); // Negro suave
        titulo.setBounds(200, 50, 200, 30);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo);

        // Configurar el campo de Gmail
        JLabel gmailLabel = new JLabel("Correo Electrónico:");
        gmailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gmailLabel.setBounds(150, 120, 300, 25);
        gmailLabel.setForeground(new Color(40, 40, 40)); // Negro suave
        add(gmailLabel);

        gmail = new JTextField();
        gmail.setBounds(150, 150, 300, 30);
        gmail.setFont(new Font("Arial", Font.PLAIN, 18));
        gmail.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 1));
        add(gmail);

        // Configurar el campo de contraseña
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setBounds(150, 200, 300, 25);
        passwordLabel.setForeground(new Color(40, 40, 40)); // Negro suave
        add(passwordLabel);

        password = new JPasswordField();
        password.setBounds(150, 230, 300, 30);
        password.setFont(new Font("Arial", Font.PLAIN, 18));
        password.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 1));
        add(password);

        // Configurar el botón de Login
        login = new JButton("Iniciar Sesión");
        login.setBounds(150, 280, 140, 40);
        login.setFont(new Font("Arial", Font.BOLD, 18));
        login.setBackground(new Color(139, 69, 19)); // Marrón oscuro
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);
        login.setBorder(BorderFactory.createLineBorder(new Color(40, 40, 40), 1));
        add(login);

        // Configurar el botón de Registrarse
        registrarse = new JButton("¿No tienes cuenta?");
        registrarse.setBounds(310, 280, 140, 40);
        registrarse.setFont(new Font("Arial", Font.BOLD, 14));
        registrarse.setBackground(new Color(139, 69, 19)); // Marrón oscuro
        registrarse.setForeground(Color.WHITE);
        registrarse.setFocusPainted(false);
        registrarse.setBorder(BorderFactory.createLineBorder(new Color(40, 40, 40), 1));
        add(registrarse);

        // Configurar el botón de Cierre
        cierre = new JButton("Cerrar");
        cierre.setBounds(196, 380, 200, 30);
        cierre.setFont(new Font("Arial", Font.BOLD, 18));
        cierre.setBackground(new Color(255, 69, 0)); // Rojo anaranjado
        cierre.setForeground(Color.WHITE);
        cierre.setFocusPainted(false);
        cierre.setBorder(BorderFactory.createLineBorder(new Color(40, 40, 40), 1));
        add(cierre);
        
       
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
