package clasesDAO;

import clases.*;

public interface UserDAO {
	public User existeUsuario(String gmail, String password);
	public boolean chequeodeEmail(String mail);
	public int insertarUsuario(int idPersona, String email, String password, boolean aceptaTerminos);

}
