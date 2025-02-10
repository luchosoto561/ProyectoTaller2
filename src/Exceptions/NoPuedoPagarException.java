package Exceptions;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class NoPuedoPagarException extends ExceptionAbstract{
		private final String cuerpo = "usted quiere pagar con mas de lo que tiene asociado  a su usuario";
		private final String titulo = "no tiene suficiente dinero";
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
