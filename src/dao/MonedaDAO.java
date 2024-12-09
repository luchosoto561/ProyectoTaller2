package dao;

import java.util.List;

import modelos.Moneda;

public interface MonedaDAO {
	
	public int guardarMoneda(Moneda moneda);

	public List<Moneda> listarMonedas();
	
	public void generarStock();
	
	public int chequearStock(String nomencripto);
	
	public String buscarNomen(String nombre);
	 
	 
}
