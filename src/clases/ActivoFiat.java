package clases;

public class ActivoFiat {
	private int id;
	private int idUsuario;
	private int idMoneda;
	private double cantidad;
	public ActivoFiat(int id, int idUsuario, int idMoneda, double cantidad) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.idMoneda = idMoneda;
		this.cantidad = cantidad;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdMoneda() {
		return idMoneda;
	}
	public void setIdMoneda(int idMoneda) {
		this.idMoneda = idMoneda;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
