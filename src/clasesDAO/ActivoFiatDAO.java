package clasesDAO;

import java.sql.SQLException;
import java.util.List;

import clases.ActivoFiat;

public interface ActivoFiatDAO {
	public List<ActivoFiat> traerActivosFiat(int idUsuario);
	public void generarCantAleatorio(int idUsuario);
	public void generarActivoFiat(int idUsuario);
	public ActivoFiat obtenerActivoPorNomenclatura(String nomenclatura) throws SQLException;
	public void actualizarCantidad(int idActivo, double cantidad) throws SQLException;
}
