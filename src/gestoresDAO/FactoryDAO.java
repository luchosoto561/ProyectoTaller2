package gestoresDAO;
import clasesDAO.*;
/*esta clase retorna instancias de las clases DAO*/
public class FactoryDAO {
	public static MonedaDAO getMonedaDAO() {
		return new MonedaDAO();
	}
	public static ActivoCriptoDAO getActivoCriptoDAO() {
		return new ActivoCriptoDAO();
	}
	public static ActivoFiatDAO getActivoFiatDAO() {
		return new ActivoFiatDAO();
	}	
	public static TransaccionDAO getTransaccionDAO() {
		return new TransaccionDAO();
	}
}
