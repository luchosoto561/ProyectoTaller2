package clasesDAO;

import java.util.List;

import clases.Moneda;

public interface MonedaDAO {
	public List<Moneda> traerMonedas();
	public void generarStockAleatorio();
	public void actualizarPrecio(String nombreMoneda, double precioRespectoDolar);
	public double getStock(String nombre);
	public Moneda getMoneda(String nomenclatura);
}
