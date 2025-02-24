package clasesDAO;

import java.time.LocalDateTime;
import java.util.List;

import clases.Transaccion;

public interface TransaccionDAO {
	public void cargarTransaccion(int IdUsuario, String descripcion, LocalDateTime fechaHs);
	public List<Transaccion> actualizarTransacciones(int idUsuario);
	

}
