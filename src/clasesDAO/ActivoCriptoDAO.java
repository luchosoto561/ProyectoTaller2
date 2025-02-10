package clasesDAO;

import java.util.List;

import clases.ActivoCripto;

public interface ActivoCriptoDAO {
	public List<ActivoCripto> traerActivosCripto(int idUsuario);
	public void generarCantAleatorio(int idUsuario);
	

}
