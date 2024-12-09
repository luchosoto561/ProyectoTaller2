package dao;

import java.util.List;

import modelos.Activo;

public interface ActivoFiatDAO {
	
	public void generarActivoFiat(double cantidad, String nomenclatura);
	
	public void sumarActivoFiat(double cant, String nomen);
	
	public List<Activo> listarActivoFiat();
	

}
