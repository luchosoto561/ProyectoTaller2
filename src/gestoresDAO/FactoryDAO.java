package gestoresDAO;
import dao.*;
/*esta clase retorna instancias de las clases DAO*/
public class FactoryDAO {
	public static MonedaDAOJDBC getMonedaDAO() {
		return new MonedaDAOJDBC();
	}
	public static ActivoCriptoDAOJDBC getActivoCriptoDAO() {
		return new ActivoCriptoDAOJDBC();
	}
	public static ActivoFiatDAOJDBC getActivoFiatDAO() {
		return new ActivoFiatDAOJDBC();
	}	
	public static TransaccionDAOJDBC getTransaccionDAO() {
		return new TransaccionDAOJDBC();
	}
}
