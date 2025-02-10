package clasesDAO;

import java.util.List;

import clases.ActivoFiat;

public interface ActivoFiatDAO {
	public List<ActivoFiat> traerActivosFiat(int idUsuario);
	public void generarCantAleatorio(int idUsuario);
	
}
