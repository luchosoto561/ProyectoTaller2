package dao;

import java.util.List;

import modelos.Activo;

public interface ActivoCriptoDAO{
	
	public void generarActivoCripto(double cantidad, String nomenclatura);
	
	public List<Activo> listarActivoCripto();
	
	public void sumarActivoCripto(double cant, String nomen);
	

}
