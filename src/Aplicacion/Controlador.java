import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class Controlador {
	
	private Vista vista;
	@SuppressWarnings("unused")
	private Modelo modelo;
	
	public Controlador(Vista vista, Modelo modelo) {
		this.vista = vista;
		this.modelo = modelo;
		
		vista.getPanelPrincipal().getRegistrarseButton().addActionListener(new BotonRegistrarsePanelPrincipal());
		vista.getPanelPrincipal().getLoginButton().addActionListener(new BotonIniciarSesionPanelPrincipal());
		vista.getPanelPrincipal().getCierreButton().addActionListener(new BotonCerrar());
		configurarMouseListener(vista.getPanelPrincipal().getLoginButton());
        configurarMouseListener(vista.getPanelPrincipal().getRegistrarseButton());
        configurarMouseListener2(vista.getPanelPrincipal().getCierreButton());
		
		vista.getPanelRegistro().getRegistrarButton().addActionListener(new BotonRegistrarPanelRegistro());
		vista.getPanelRegistro().getCuentaButton().addActionListener(new BotonCuentaPanelRegistro());
		vista.getPanelRegistro().getCierreButton().addActionListener(new BotonCerrar());
        configurarMouseListener(vista.getPanelRegistro().getRegistrarButton());
        configurarMouseListener(vista.getPanelRegistro().getCuentaButton());
        configurarMouseListener2(vista.getPanelRegistro().getCierreButton());
		
		vista.getPanelActivos().getBtnCerrarSesion().addActionListener(new BotonCerrarPanelMenu());
		vista.getPanelActivos().getBtnCotizaciones().addActionListener(new BotonCotizacionesMisActivos());
		vista.getPanelActivos().getBtnMisOperaciones().addActionListener(new BotonOperacionesMisActivos());
		configurarMouseListener2(vista.getPanelActivos().getBtnCerrarSesion());
        configurarMouseListener(vista.getPanelActivos().getBtnCotizaciones());
        configurarMouseListener(vista.getPanelActivos().getBtnMisOperaciones());
        configurarMouseListener(vista.getPanelActivos().getBtnExportarCSV());
        configurarMouseListener(vista.getPanelActivos().getBtnGenerarDatos());
		
		vista.getPanelCotizaciones().getBtnVolver().addActionListener(new BotonVolverCotizaciones());
		configurarMouseListener2(vista.getPanelCotizaciones().getBtnCerrarSesion());
		configurarMouseListener(vista.getPanelCotizaciones().getBtnVolver());
		
		vista.getPanelMisOperaciones().getBtnVolver().addActionListener(new BotonVolverOperaciones());
		configurarMouseListener(vista.getPanelMisOperaciones().getBtnVolver());
		
    }

    private void configurarMouseListener(javax.swing.JButton boton) {
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
                boton.setBackground(new Color(159, 89, 39));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(new Color(139, 69, 19)); // Marrón oscuro
                boton.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
            }
        });
    }
    
    private void configurarMouseListener2(javax.swing.JButton boton) {
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
                boton.setBackground(new Color(255, 89, 20));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(new Color(255, 69, 0)); // Rojo anaranjado
                boton.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
            }
        });
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
	
	public class BotonCerrar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
	        
	        if (respuesta == JOptionPane.YES_OPTION) System.exit(0);
		}
	}
	
	public class BotonRegistrarPanelRegistro implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (vista.getPanelRegistro().getAceptarTerminos().isSelected()) {
				vista.getPanelActivos().nombreUsuario(vista.getPanelRegistro().getEmail().getText());
				vista.mostrarPanel(vista.getPanelActivos());
				vista.repaint();
			}
			else JOptionPane.showMessageDialog(null, "Debes aceptar los términos y condiciones");
		}
	}
	
	public class BotonCuentaPanelRegistro implements ActionListener{
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
