package gestoresDAO;

import clasesDAO.*;
/*esta clase retorna instancias de las clases DAO*/
public class FactoryDAO {
	public static UserDAOJDBC getUserDAOJDBC() {
		return new UserDAOJDBC();
	}
	public static PersonaDAOJDBC getPersonaDAOJDBC() {
		return new PersonaDAOJDBC();
	}
	public static MonedaDAOJDBC getMonedaDAOJDBC() {
		return new MonedaDAOJDBC();
	}
	public static ActivoCriptoDAOJDBC getActivoCriptoDAOJDBC() {
		return new ActivoCriptoDAOJDBC();
	}
	public static ActivoFiatDAOJDBC getActivoFiatDAOJDBC() {
		return new ActivoFiatDAOJDBC();
	}
	public static TransaccionDAOJDBC getTransaccionDAOJDBC() {
		return new TransaccionDAOJDBC();
	}
	
}
