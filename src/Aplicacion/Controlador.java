import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador {
	
	private Vista vista;
	@SuppressWarnings("unused")
	private Modelo modelo;
	
	public Controlador(Vista vista, Modelo modelo) {
		this.vista = vista;
		this.modelo = modelo;
		
		vista.getPanelPrincipal().getRegistrarseButton().addActionListener(new BotonRegistrarsePanelPrincipal());
		vista.getPanelPrincipal().getLoginButton().addActionListener(new BotonIniciarSesionPanelPrincipal());
		vista.getPanelPrincipal().getCierreButton().addActionListener(new BotonCerrarPanelPrincipal());
		
		vista.getPanelRegistro().getRegistrarButton().addActionListener(new BotonRegistrarPanelRegistro());
		vista.getPanelRegistro().getCierreButton().addActionListener(new BotonCerrarPanelRegistro());
		
		vista.getPanelActivos().getBtnCerrarSesion().addActionListener(new BotonCerrarPanelMenu());
		vista.getPanelActivos().getBtnCotizaciones().addActionListener(new BotonCotizacionesMisActivos());
		vista.getPanelActivos().getBtnMisOperaciones().addActionListener(new BotonOperacionesMisActivos());
		
		vista.getPanelCotizaciones().getBtnVolver().addActionListener(new BotonVolverCotizaciones());
		
		vista.getPanelMisOperaciones().getBtnVolver().addActionListener(new BotonVolverOperaciones());
	}
	
	public class BotonRegistrarsePanelPrincipal implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			vista.mostrarPanel(vista.getPanelRegistro());
			vista.repaint();
		}
	}
	
	public class BotonIniciarSesionPanelPrincipal implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			vista.getPanelActivos().nombreUsuario(vista.getPanelPrincipal().getGmail().getText());
			vista.mostrarPanel(vista.getPanelActivos());
			vista.repaint();
		}
	}
	
	public class BotonCerrarPanelPrincipal implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	public class BotonRegistrarPanelRegistro implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			vista.getPanelActivos().nombreUsuario(vista.getPanelRegistro().getEmail().getText());
			vista.mostrarPanel(vista.getPanelActivos());
			vista.repaint();
		}
	}
	
	public class BotonCerrarPanelRegistro implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			vista.mostrarPanel(vista.getPanelPrincipal());
			vista.repaint();
		}
	}
	
	public class BotonCerrarPanelMenu implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			vista.mostrarPanel(vista.getPanelPrincipal());
			vista.repaint();
		}
	}
	
	public class BotonCotizacionesMisActivos implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			vista.mostrarPanel(vista.getPanelCotizaciones());
			vista.repaint();
		}
	}
	
	public class BotonOperacionesMisActivos implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			vista.mostrarPanel(vista.getPanelMisOperaciones());
			vista.repaint();
		}
	}
	
	public class BotonVolverCotizaciones implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			vista.mostrarPanel(vista.getPanelActivos());
			vista.repaint();
		}
	}
	
	public class BotonVolverOperaciones implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			vista.mostrarPanel(vista.getPanelActivos());
			vista.repaint();
		}
	}
}
