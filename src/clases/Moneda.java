package clases;

public class Moneda {
	private int id;
	private String tipo;
	private String nombre;
	private String nomenclatura;
	private float valorDolar;
	private float volatilidad;
	private double stock;
	private String nombreIcono;
	public Moneda(int id, String tipo, String nombre, String nomenclatura, float valorDolar, float volatilidad,
			double stock, String nombreIcono) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.nombre = nombre;
		this.nomenclatura = nomenclatura;
		this.valorDolar = valorDolar;
		this.volatilidad = volatilidad;
		this.stock = stock;
		this.nombreIcono = nombreIcono;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public float getValorDolar() {
		return valorDolar;
	}
	public void setValorDolar(float valorDolar) {
		this.valorDolar = valorDolar;
	}
	public float getVolatilidad() {
		return volatilidad;
	}
	public void setVolatilidad(float volatilidad) {
		this.volatilidad = volatilidad;
	}
	public double getStock() {
		return stock;
	}
	public void setStock(double stock) {
		this.stock = stock;
	}
	public String getNombreIcono() {
		return nombreIcono;
	}
	public void setNombreIcono(String nombreIcono) {
		this.nombreIcono = nombreIcono;
	}
	
	

}
