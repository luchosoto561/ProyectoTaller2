package clasesDAO;

import java.sql.SQLException;
import java.util.List;

import clases.ActivoCripto;

public interface ActivoCriptoDAO {
	public List<ActivoCripto> traerActivosCripto(int idUsuario);
	public void generarCantAleatorio(int idUsuario);
	public Double obtenerCantidadCripto(int idUsuario, int idMoneda) throws SQLException;
	public void actualizarCantidadCripto(int idUsuario, int idMoneda, double nuevaCantidad) throws SQLException;
	public void insertarActivoCripto(int idUsuario, int idMoneda, double cantidad) throws SQLException;

}
