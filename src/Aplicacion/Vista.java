package Aplicacion;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Vista extends JFrame {
	//todos los paneles como atributo
	private PanelPrincipal principal;
	private PanelRegistro registro;
	private PanelActivos activos;
	private PanelMisOperaciones operaciones;
	private PanelCotizaciones cotizaciones;
	private PanelCompra compra;
	
	private JPanel contenedor;
	
	public Vista() {
		setTitle("BILLETERA VIRTUAL");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setSize(600, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(222, 184, 135));

     // Configurar el contenedor principal con CardLayout
        contenedor = new JPanel(new CardLayout());
        contenedor.setBounds(0, 0, 600, 500); // Ocupa toda la ventana
        add(contenedor);

        // Inicializar paneles
        principal = new PanelPrincipal();
        registro = new PanelRegistro();
        activos = new PanelActivos();
        operaciones = new PanelMisOperaciones();
        cotizaciones = new PanelCotizaciones();
        compra = new PanelCompra();

        // Añadir paneles al contenedor con nombres
        contenedor.add(principal, "login");
        contenedor.add(registro, "signup");
        contenedor.add(activos, "activos");
        contenedor.add(operaciones, "operaciones");
        contenedor.add(cotizaciones, "cotizaciones");
        contenedor.add(compra, "compra");

        setVisible(true);
        
        // Mostrar el panel inicial
        mostrarPanel(principal);
		
	}/*los creo para que */
	
	public void mostrarPanel(JPanel panelVisible) {
	    // Oculta todos los paneles
	    principal.setVisible(false);
	    registro.setVisible(false);
	    activos.setVisible(false);
	    operaciones.setVisible(false);
	    cotizaciones.setVisible(false);
	    compra.setVisible(false);

	    // Muestra el panel especificado
	    panelVisible.setVisible(true);
	    repaint();
	    
	}
	
	public PanelPrincipal getPanelPrincipal() {
		return principal;
	}
	public PanelRegistro getPanelRegistro() {
		return registro;
	}
	public PanelActivos getPanelActivos() {
		return activos;
	}
	public PanelMisOperaciones getPanelMisOperaciones() {
		return operaciones;
	}
	public PanelCotizaciones getPanelCotizaciones() {
		return cotizaciones;
	}
	public PanelCompra getPanelCompra() {
		return compra;
	}
	
}
