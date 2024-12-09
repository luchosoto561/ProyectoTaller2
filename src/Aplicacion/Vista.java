package Aplicacion;

import javax.swing.*;


public class Vista extends JFrame {
	/*todos los pantallasos como atributo*/
	private PanelPrincipal principal;
	private PanelRegistro registro;
	private PanelActivos activos;
	private PanelMisOperaciones operaciones;
	private PanelCotizaciones cotizaciones;
	private PanelCompra compra;
	
	
	public Vista() {
		setTitle("Villetera Virtual");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLayout(null);
    	setSize(500,400);
    	setLocationRelativeTo(null);
		/*inicio todos los paneles aca porque despues cada vez que se toque el boton puede ser que se cambie la informacion pro ejemplo en el compra pero no es que se va creando una nueva instancia como si pasaria si se coloca en el listener*/
		
		principal= new PanelPrincipal();
		add(principal);
		principal.setVisible(true);
		
		registro = new PanelRegistro();
		add(registro);
		activos = new PanelActivos();
		add(activos);
		operaciones = new PanelMisOperaciones();
		add(operaciones);
		cotizaciones = new PanelCotizaciones();
		add(cotizaciones);
		compra = new PanelCompra();
		add(compra);
		
		
		
		setVisible(true);/*hago visible el frame*/
	
	}/*los creo para que */
	public PanelPrincipal getPantelPrincipal() {
		return principal;
	}
	public PanelRegistro getPantelRegistro() {
		return registro;
	}
	public PanelActivos getPantelActivos() {
		return activos;
	}
	public PanelMisOperaciones getPantelMisOperaciones() {
		return operaciones;
	}
	public PanelCotizaciones getPantelCotizaciones() {
		return cotizaciones;
	}
	public PanelCompra getPantelCompra() {
		return compra;
	}
	
	

}
