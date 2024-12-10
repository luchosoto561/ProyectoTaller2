import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador {
	
	private Vista vista;
	private Modelo modelo;
	
	public Controlador(Vista vista, Modelo modelo) {
		this.vista = vista;
		this.modelo = modelo;
		
		vista.getPanelPrincipal().getRegistrarseButton().addActionListener(new BotonRegistrarsePanelPrincipal());
		vista.getPanelRegistro().getCierreButton().addActionListener(new BotonCerrarPanelRegistro());
		vista.getPanelPrincipal().getLoginButton().addActionListener(new BotonIniciarSesionPanelPrincipal());
		vista.getPanelRegistro().getRegistrarButton().addActionListener(new BotonRegistrarPanelRegistro());
		
	}
	
	public class BotonRegistrarsePanelPrincipal implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			vista.mostrarPanel(vista.getPanelRegistro());
			vista.repaint();
		}
		
	}
	
	public class BotonCerrarPanelRegistro implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			vista.mostrarPanel(vista.getPanelPrincipal());
			vista.repaint();
		}
		
	}
	
	public class BotonIniciarSesionPanelPrincipal implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			vista.mostrarPanel(vista.getPanelActivos());
			vista.repaint();
		}
		
	}
	
	public class BotonRegistrarPanelRegistro implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			vista.mostrarPanel(vista.getPanelActivos());
			vista.repaint();
		}
		
	}
}
