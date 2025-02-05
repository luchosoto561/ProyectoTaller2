package Exceptions;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class DatosNoValidosException extends ExceptionAbstract{
	private final String cuerpo = "gmail o password incorrectos";
	private final String titulo = "Datos invalidos";
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
