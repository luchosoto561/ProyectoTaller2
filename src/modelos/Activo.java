package modelos;

public class Activo {
	private String nomenclatura;
	private double cantidad;
	
	public Activo() {}/*constructor vacio que lo creo cuando me doy cuenta que lo necesito en la creacion de la lista de activos*/
	
	public Activo(String nomenclatura, double cantidad) {
		this.nomenclatura = nomenclatura;
		this.cantidad = cantidad;
	}
	public String getNomenclatura() {
		return nomenclatura;
	}
	public void setNomenclatura(String nomenclatura) {
		this.nomenclatura = nomenclatura;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
