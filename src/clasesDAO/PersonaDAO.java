package clasesDAO;

import clases.Persona;

public interface PersonaDAO {
	
	public Persona buscarPersona(int idPersona);
	public int insertarPersona(String nombre, String apellido);
	
}
