package Exceptions;

@SuppressWarnings("serial")
public abstract class ExceptionAbstract extends Exception {
	
	public abstract String getCuerpo();
	
	public abstract String getTitulo();
	
	public abstract int getIcono();

}
