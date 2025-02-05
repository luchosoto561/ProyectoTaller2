package Exceptions;

import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class NoAceptoTerminosException extends ExceptionAbstract {
	
	private final String cuerpo = "debe aceptar los terminos y condiciones ";
	private final String titulo = "Aceptacion de terminos";
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


