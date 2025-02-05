package Exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class CompletarCamposException  extends ExceptionAbstract {
	private final String cuerpo = "Obligatorio ingresar los datos requeridos";
	private final String titulo = "Campos vacios";
	private final int icono = JOptionPane.ERROR_MESSAGE;
	
	public String getCuerpo() {
		return cuerpo;
	}
	public String getTitulo() {
		return titulo;
	}
	public int getIcono() {
		return icono;
	}
	
	

}
