package Aplicacion;

import Exceptions.NoPuedoPagarException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Exceptions.CompletarCamposException;
import Exceptions.DatosNoValidosException;
import Exceptions.ExceptionAbstract;
import Exceptions.NoAceptoTerminosException;
import clases.ActivoCripto;
import clases.ActivoFiat;
import clases.Moneda;
import clases.ApInfoCripto;

import java.lang.*;
import java.util.concurrent.TimeUnit;
import java.sql.*;

public class Controlador {
	
	private Vista vista;
	@SuppressWarnings("unused")
	private Modelo modelo;
	private ActualizarThread actualizarThread;
	private String cripto;
	private String precio;
	
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
        
		vista.getPanelCotizaciones().getBtnVolver().addActionListener(new BotonVolver());
		configurarMouseListener2(vista.getPanelCotizaciones().getBtnCerrarSesion());
		configurarMouseListener(vista.getPanelCotizaciones().getBtnVolver());
		
		vista.getPanelMisOperaciones().getBtnVolver().addActionListener(new BotonVolver());
		configurarMouseListener(vista.getPanelMisOperaciones().getBtnVolver());

		vista.getPanelMisOperaciones().getBtnCerrarSesion().addActionListener(new BotonCerrarPanelMenu());
		vista.getPanelCotizaciones().getBtnCerrarSesion().addActionListener(new BotonCerrarPanelMenu());
		
		vista.getPanelCotizaciones().getCompraBTC().addActionListener(new CompraBTC());
		configurarMouseListener3(vista.getPanelCotizaciones().getCompraBTC());
		vista.getPanelCotizaciones().getCompraETH().addActionListener(new CompraETH());
		configurarMouseListener3(vista.getPanelCotizaciones().getCompraETH());
		vista.getPanelCotizaciones().getCompraUSDC().addActionListener(new CompraUSDC());
		configurarMouseListener3(vista.getPanelCotizaciones().getCompraUSDC());
		vista.getPanelCotizaciones().getCompraUSDT().addActionListener(new CompraUSDT());
		configurarMouseListener3(vista.getPanelCotizaciones().getCompraUSDT());
		vista.getPanelCotizaciones().getCompraDOGE().addActionListener(new CompraDOGE());
		configurarMouseListener3(vista.getPanelCotizaciones().getCompraDOGE());
		
		vista.getPanelCompra().getBtnVolver().addActionListener(new BotonVolver2());
		configurarMouseListener(vista.getPanelCompra().getBtnVolver());
		vista.getPanelCompra().getBtnCompra().addActionListener(new Comprar());
		configurarMouseListener(vista.getPanelCompra().getBtnCompra());
		vista.getPanelCompra().getBtnConvertir().addActionListener(new Convertir());
		configurarMouseListener3(vista.getPanelCompra().getBtnConvertir());
		
		this.actualizarThread= new ActualizarThread();
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
    
    private void configurarMouseListener3(javax.swing.JButton boton) {
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
                boton.setBackground(new Color(54, 159, 54));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(new Color(34, 139, 34));
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
		@SuppressWarnings("deprecation")
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
		    if (respuesta == JOptionPane.YES_OPTION) {
		    	vista.getPanelPrincipal().getGmail().setText("");
		    	vista.getPanelPrincipal().getPassword().setText("");
		    	vista.getPanelRegistro().getNombres().setText("");
		    	vista.getPanelRegistro().getApellidos().setText("");
		    	vista.getPanelRegistro().getEmail().setText("");
		    	vista.getPanelRegistro().getPassword().setText("");
		    	vista.mostrarPanel(vista.getPanelPrincipal());;
		    }
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
	
	public class BotonVolver implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			vista.mostrarPanel(vista.getPanelActivos());
		}
	}
	
	public class BotonVolver2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			vista.mostrarPanel(vista.getPanelCotizaciones());
			vista.getPanelCompra().setEqui("", "");
			vista.getPanelCompra().getCant().setText("0");
		}
	}
	
	public class CompraBTC implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			cripto = "BTC";
			precio = vista.getPanelCotizaciones().getPrecioBTC().getText();
			modelo.getMonedaDAO().getStock(cripto);
			vista.getPanelCompra().setStock("1000", cripto);
			vista.getPanelCompra().setPrecio(precio);
			vista.mostrarPanel (vista.getPanelCompra());
		}
	}
	public class CompraETH implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			cripto = "ETH";
			precio = vista.getPanelCotizaciones().getPrecioETH().getText();
			modelo.getMonedaDAO().getStock(cripto);
			vista.getPanelCompra().setStock("1000", cripto);
			vista.getPanelCompra().setPrecio(precio);
			vista.mostrarPanel (vista.getPanelCompra());
		}
	}
	public class CompraUSDC implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			cripto = "USDC";
			precio = vista.getPanelCotizaciones().getPrecioUSDC().getText();
			modelo.getMonedaDAO().getStock(cripto);
			vista.getPanelCompra().setStock("1000", cripto);
			vista.getPanelCompra().setPrecio(precio);
			vista.mostrarPanel (vista.getPanelCompra());
		}
	}
	public class CompraUSDT implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			cripto = "USDT";
			precio = vista.getPanelCotizaciones().getPrecioUSDT().getText();
			modelo.getMonedaDAO().getStock(cripto);
			vista.getPanelCompra().setStock("1000", cripto);
			vista.getPanelCompra().setPrecio(precio);
			vista.mostrarPanel (vista.getPanelCompra());
		}
	}
	public class CompraDOGE implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			cripto = "DOGE";
			precio = vista.getPanelCotizaciones().getPrecioDOGE().getText();
			vista.getPanelCompra().setStock(" "+ modelo.getMonedaDAO().getStock(cripto), cripto);
			vista.getPanelCompra().setPrecio(precio);
			vista.mostrarPanel (vista.getPanelCompra());
		}
	}
	
	
	public class Comprar implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	        try {
	            // Falta chequear si se puede comprar y hacerle una excepción si no es posible
	            LocalDateTime ahora = LocalDateTime.now();
	            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

	            ActivoFiat activoFiat = modelo.tengoActivo("nomenclaturaActivoConElQuePago", 0.0); // Activo con el cual ya pagué
	            if (activoFiat == null) {
	                throw new NoPuedoPagarException();
	            } else {
	                Moneda monedaComprar = modelo.getMonedaDAO().getMoneda("nomenclatura que me traigo de la vista"); // Obtener la moneda desde la vista
	                modelo.comprar(activoFiat, monedaComprar); // Comprar la moneda

	                // Convertir a String
	                String fecha = ahora.format(formatoFecha);
	                String hora = ahora.format(formatoHora);
	                String cantidad = vista.getPanelCompra().getEqui().getText();

	                if (!cantidad.equals("") && !cantidad.equals("0.0 Bitcoin (BTC)") && !cantidad.equals("0.0 Ethereum (ETH)") &&
	                    !cantidad.equals("0.0 Tether (USDT)") && !cantidad.equals("0.0 Usdc (USDC)") && !cantidad.equals("0.0 Dogecoin (DOGE)")) {
	                    vista.getPanelMisOperaciones().agregarCriptomoneda(fecha, hora, "Compra", cantidad);
	                }

	                vista.mostrarPanel(vista.getPanelCotizaciones());
	                vista.getPanelCompra().setEqui("", "");
	                vista.getPanelCompra().getCant().setText("0");
	            }
	        } catch (NoPuedoPagarException ex) {
	            JOptionPane.showMessageDialog(null, "No tienes saldo suficiente para comprar.", "Error", JOptionPane.ERROR_MESSAGE);
	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, "Error en la base de datos: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}

	
	public class Convertir implements ActionListener{
		@SuppressWarnings("removal")
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unused")
			double precio = new Double (vista.getPanelCompra().getPrecio().getText().substring(1));
			double cant = new Double (vista.getPanelCompra().getCant().getText());
			vista.getPanelCompra().setEqui(cant / precio + "", cripto);
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
	public class BotonRealizarCompra implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			/**/
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
	public class ActualizarThread extends Thread {
		/*osea nuestra clase extiende de una clase diseniada para ejecutar hilos en paralelo del hilo del main*/
		private ApInfoCripto apInfoCripto;
		
		public ActualizarThread() {
			this.apInfoCripto = modelo.getApInfoCripto();
		}

		@Override
		public void run() {
			apInfoCripto.actualizarApiCriptos();
		    if (apInfoCripto.getJson() != null) {
		    	modelo.getMonedaDAO().actualizarPrecio("BTC", apInfoCripto.getBTC());
		    	modelo.getMonedaDAO().actualizarPrecio("ETH", apInfoCripto.getETH());
		        modelo.getMonedaDAO().actualizarPrecio("USDC", apInfoCripto.getUSDC());
		        modelo.getMonedaDAO().actualizarPrecio("USDT", apInfoCripto.getUSDT());
		        modelo.getMonedaDAO().actualizarPrecio("DOGE", apInfoCripto.getDOGE());
		        SwingUtilities.invokeLater(() -> {/*es donde cargo en la tabla de cotizaciones la informacion renovada*/
		        	vista.getPanelCotizaciones().getTablaCotizaciones().getModel().setValueAt(apInfoCripto.getBTC(), 0,0 );
		        	vista.getPanelCotizaciones().getTablaCotizaciones().getModel().setValueAt(apInfoCripto.getDOGE(), 0, 0);
		        	vista.getPanelCotizaciones().getTablaCotizaciones().getModel().setValueAt(apInfoCripto.getETH(), 0,0 );
		        	vista.getPanelCotizaciones().getTablaCotizaciones().getModel().setValueAt(apInfoCripto.getUSDC(),0 , 0);
		        	vista.getPanelCotizaciones().getTablaCotizaciones().getModel().setValueAt(apInfoCripto.getUSDT(), 0,0 );
		        });
		            }

		            try {
		                TimeUnit.SECONDS.sleep(30);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		    }
	}
}

