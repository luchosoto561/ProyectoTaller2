package modelos;

public class Moneda {
	private String tipo;
	private String nombre;
	private String nomenclatura;
	private double valorEnDolar;
	private double stock;
	private double volatilidad;
	
	public Moneda(String tipo, String nombre, String nomenclatura, double valorEnDolar, double stock, double volatilidad) {
		super();
		this.tipo = tipo;
		this.nombre = nombre;
		this.nomenclatura = nomenclatura;
		this.valorEnDolar = valorEnDolar;
		this.stock = stock;
		this.volatilidad = volatilidad;
	}
	public Moneda() {};/*creo este contructor porque en el listar moneda lo necesito*/
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNomenclatura() {
		return nomenclatura;
	}
	public void setNomenclatura(String nomenclatura) {
		this.nomenclatura = nomenclatura;
	}
	public double getValorEnDolar() {
		return valorEnDolar;
	}
	public void setValorEnDolar(double valorEnDolar) {
		this.valorEnDolar = valorEnDolar;
	}
	public double getStock() {
		return stock;
	}
	public void setStock(double stock) {
		this.stock = stock;
	}
	public double getVolatilidad() {
		return volatilidad;
	}
	public void setVolatilidad(double volatilidad) {
		this.volatilidad = volatilidad;
	}
	
	

}
