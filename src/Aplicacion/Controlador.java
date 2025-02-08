package Aplicacion;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import Exceptions.CompletarCamposException;
import Exceptions.DatosNoValidosException;
import Exceptions.ExceptionAbstract;
import Exceptions.NoAceptoTerminosException;
import clases.ActivoCripto;
import clases.ActivoFiat;
import clases.Moneda;

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
		vista.getPanelRegistro().getCuentaButton().addActionListener(new BotonCuentaPanelRegistro());/*NO ENTIENDO QUE BOTON ES ESTE*/
		vista.getPanelRegistro().getCierreButton().addActionListener(new BotonCerrar());
        configurarMouseListener(vista.getPanelRegistro().getRegistrarButton());
        configurarMouseListener(vista.getPanelRegistro().getCuentaButton());
        configurarMouseListener2(vista.getPanelRegistro().getCierreButton());
		/*hasta aca hice los listeners*/
		vista.getPanelActivos().getBtnCerrarSesion().addActionListener(new BotonCerrarPanelMenu());
		vista.getPanelActivos().getBtnCotizaciones().addActionListener(new BotonCotizacionesMisActivos());
		vista.getPanelActivos().getBtnMisOperaciones().addActionListener(new BotonOperacionesMisActivos());
		configurarMouseListener2(vista.getPanelActivos().getBtnCerrarSesion());
        configurarMouseListener(vista.getPanelActivos().getBtnCotizaciones());
        configurarMouseListener(vista.getPanelActivos().getBtnMisOperaciones());
        configurarMouseListener(vista.getPanelActivos().getBtnExportarCSV());
        configurarMouseListener(vista.getPanelActivos().getBtnGenerarDatos());
		/*queda el boton exportar como csv, generar datos de prueba, en el panel activos*/
        
		vista.getPanelCotizaciones().getBtnVolver().addActionListener(new BotonVolverCotizaciones());
		configurarMouseListener2(vista.getPanelCotizaciones().getBtnCerrarSesion());
		configurarMouseListener(vista.getPanelCotizaciones().getBtnVolver());
		
		vista.getPanelMisOperaciones().getBtnVolver().addActionListener(new BotonVolverOperaciones());
		configurarMouseListener(vista.getPanelMisOperaciones().getBtnVolver());

		vista.getPanelMisOperaciones().getBtnCerrarSesion().addActionListener(new BotonCerrarPanelMenu());
		vista.getPanelCotizaciones().getBtnCerrarSesion().addActionListener(new BotonCerrarPanelMenu());
		
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
			vista.mostrarPanel(vista.getPanelRegistro());/*muestra la pantalla de registro y oculta las demas*/
		}
	}
	
	public class BotonIniciarSesionPanelPrincipal implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			 /*tengo que sacar el gmail que escribio, y darselo al modelo, ademas la contrasenia tambien se la tengo que dar al modelo*/
				try {
				if (vista.getPanelPrincipal().getGmail().getText().isBlank() || new String(vista.getPanelPrincipal().getPassword().getPassword()).isBlank()) /*si no lleno los campos, lanzamos error*/
					throw new CompletarCamposException();
				if ( ! modelo.existeCuenta(vista.getPanelPrincipal().getGmail().getText(), new String(vista.getPanelPrincipal().getPassword().getPassword())))/*si no esta en la base de datos lanzamos error*/
					throw new DatosNoValidosException();
				cargarPanelActivos();
				vista.mostrarPanel(vista.getPanelActivos());/*muestra el panel de los activos*/
			}
			catch (ExceptionAbstract x) {
				JOptionPane.showMessageDialog(vista.getPanelPrincipal(),x.getCuerpo(),x.getTitulo(),x.getIcono());	
			}
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
			try{
				if (vista.getPanelRegistro().getEmail().getText().isBlank() || new String (vista.getPanelRegistro().getPassword().getPassword()).isBlank() || vista.getPanelRegistro().getApellidos().getText().isBlank() || vista.getPanelRegistro().getNombres().getText().isBlank()  ) /*si no lleno los campos, lanzamos error*/
					throw new CompletarCamposException();	
		    	if(!vista.getPanelRegistro().getAceptarTerminos().isSelected())/*si no acepto las condiciones salta error*/
		    		throw new NoAceptoTerminosException();
		    	if(modelo.existeGmail(vista.getPanelRegistro().getEmail().getText()))/*si el gmail elegido existe, salta error*/
					throw new DatosNoValidosException();
		    	modelo.insertarUsuarioyPersona(vista.getPanelRegistro().getNombres().getText(), vista.getPanelRegistro().getApellidos().getText(), vista.getPanelRegistro().getEmail().getText(), vista.getPanelRegistro().getPassword().getText());
				cargarPanelActivos();
		    	vista.mostrarPanel(vista.getPanelActivos());
				
			}
			catch (ExceptionAbstract x ) {
					JOptionPane.showMessageDialog(vista.getPanelRegistro(),x.getCuerpo(),x.getTitulo(),x.getIcono());
			}
		}
	}
	
/*--------------------------------------------------------------------------------------------------------------------------------------------------, aca me quede*/	
	public class BotonCuentaPanelRegistro implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			vista.mostrarPanel(vista.getPanelPrincipal());
		}
	}
	
	public class BotonCerrarPanelMenu implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int respuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas cerrar sesión?", "Confirmación", JOptionPane.YES_NO_OPTION);
		    if (respuesta == JOptionPane.YES_OPTION) vista.mostrarPanel(vista.getPanelPrincipal());;
		}
	}
	
	public class BotonCotizacionesMisActivos implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			vista.mostrarPanel(vista.getPanelCotizaciones());
		}
	}
	
	public class BotonOperacionesMisActivos implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			vista.mostrarPanel(vista.getPanelMisOperaciones());
		}
	}
	
	public class BotonVolverCotizaciones implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			vista.mostrarPanel(vista.getPanelActivos());
		}
	}
	
	public class BotonVolverOperaciones implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			vista.mostrarPanel(vista.getPanelActivos());
		}
	}
	public class BotonExportarCSV implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
			    modelo.exportar();
			    JOptionPane.showMessageDialog(vista.getPanelActivos(),"Datos exportados exitosamente.", "Exportación exitosa.", JOptionPane.PLAIN_MESSAGE);
			} catch (IOException x) {
			    JOptionPane.showMessageDialog(vista.getPanelActivos(),"Error con la exportación del archivo .csv, intente nuevamente.", "Error exportación.", JOptionPane.ERROR_MESSAGE);
			}
		
		
		}
	}
	public class BotonGenerarDatos implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			/*cuando se toque este boton lo que tiene que pasar es que el stock de todas las monedas se ponga en aleatorio y la cantidad de todos los activos tambien se ponga en aleatorio*/
			modelo.generarStock();
			modelo.generarCantidad();
		}
		
		
	}
	public void cargarPanelActivos() {
		
		List<ActivoCripto> activosCripto = modelo.listarActivoCripto();/*me tengo que traer los activos cripto de todo el usuario*/ 
		List<ActivoFiat> activosFiat = modelo.listarActivoFiat();/*me tengo que traer los activos fiat de todo el usuario*/
		List<Moneda> monedas = modelo.listarMonedas();/*me trae todas las monedas de la base de datos en un arrayList*/
		
		for (ActivoCripto ac : activosCripto) {
			/*cada activo tengo que cargarlo en la tabla*/
			vista.getPanelActivos().agregarFila(monedas.get(ac.getId()-1).getId(), monedas.get(ac.getId()-1).getNombre(), ac.getCantidad() * monedas.get(ac.getId()-1).getValorDolar());
		}
		
		for (ActivoFiat af : activosFiat) {
			/*cada activo fiat tengo que cargarlo en la tabla*/
			vista.getPanelActivos().agregarFila(monedas.get(af.getId()-1).getId(), monedas.get(af.getId()-1).getNombre(), af.getCantidad() * monedas.get(af.getId()-1).getValorDolar());
		}
	}
}
