package Aplicacion;
import gestoresDAO.DataBaseConnection;
import gestoresDAO.FactoryDAO;
import org.json.JSONObject;
import java.util.*;
import clases.*;
import java.sql.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.FileWriter;
import clasesDAO.*;

/*aca lo que tengo que hacer es llamar a los metodos de las clases dao que son los que se comunican con la base de datos*/
public class Modelo {
	private User usuario;
	private Persona persona;
	private UserDAOJDBC usuarioDAO;
	private PersonaDAOJDBC personaDAO;
	private MonedaDAOJDBC monedaDAO;
	private ActivoCriptoDAOJDBC activoCriptoDAO;
	private ActivoFiatDAOJDBC activoFiatDAO;
	private TransaccionDAOJDBC transaccionDAO;
	private ApInfoCripto apInfoCripto;
	
	public Modelo(UserDAOJDBC usuarioDAO, PersonaDAOJDBC personaDAO,MonedaDAOJDBC monedaDAO, ActivoCriptoDAOJDBC activoCriptoDAO, ActivoFiatDAOJDBC activoFiatDAO,TransaccionDAOJDBC transaccionDAO) {
		super();
		this.usuarioDAO = usuarioDAO;
		this.personaDAO = personaDAO;
		this.monedaDAO = monedaDAO;
		this.activoCriptoDAO = activoCriptoDAO;
		this.activoFiatDAO = activoFiatDAO;
		this.transaccionDAO = transaccionDAO;
	}

	public boolean existeCuenta(String gmail, String password) {
		User usr=usuarioDAO.existeUsuario(gmail, password);/*retorna null si no existe, sino retorna usuario*/
		if(usr != null ) {
			this.usuario=usr;
			this.persona=personaDAO.buscarPersona(usr.getIdPersona());/*busca en la base de datos la persona con ese id y la devuelve*/
			return true;
		}
		else 
			return false;
	}

	public boolean existeGmail(String gmail) {
		/*tengo que chequear si una cuenta ya usa el gmail pasado como parametro, si ya existe devuelve true*/
		return usuarioDAO.chequeodeEmail(gmail);
	}
	public void exportar() throws IOException {
		List<ArrayList<String>> filas = new ArrayList<ArrayList<String>>();
		List<ActivoCripto> activosCripto = this.listarActivoCripto();/*me tengo que traer los activos cripto de todo el usuario*/ 
		List<ActivoFiat> activosFiat = this.listarActivoFiat();/*me tengo que traer los activos fiat de todo el usuario*/
		List<Moneda> monedas = this.listarMonedas();/*me trae todas las monedas de la base de datos en un arrayList*/
		ArrayList<String> aux;
		for (ActivoCripto ac : activosCripto) {
			aux= new ArrayList<String>();
			aux.add(monedas.get(ac.getId()-1).getNomenclatura());/*nomenclatura*/
			aux.add(monedas.get(ac.getId()-1).getNombre());/*nombre de la moneda*/
			aux.add(String.valueOf(ac.getCantidad() * monedas.get(ac.getId()-1).getValorDolar()));/*cantidad * precio*/
			filas.add(aux);
		}
		
		for (ActivoFiat af : activosFiat) {
			aux= new ArrayList<String>();
			aux.add(monedas.get(af.getId()-1).getNomenclatura());
			aux.add(monedas.get(af.getId()-1).getNombre());
			aux.add(String.valueOf(af.getCantidad() * monedas.get(af.getId()-1).getValorDolar()));
			filas.add(aux);
		}
		
		FileWriter csvWriter = new FileWriter("DatosBalance.csv");
		csvWriter.append("Nomenclatura");
		csvWriter.append(",");
		csvWriter.append("Nombre");
		csvWriter.append(",");
		csvWriter.append("Balance(USD)");
		csvWriter.append('\n');				
		for (List<String> datos_fila : filas) {
			csvWriter.append(String.join(",", datos_fila));
			csvWriter.append('\n');
		}
		csvWriter.close();
		
	}
	public void generarStock() {
		/*todos los stocks de las monedas los tiene que poner en aleatorio*/
		monedaDAO.generarStockAleatorio();
	}
	
	public void generarCantidad() {
		/*todos las cantidades de los activos los tiene que poner en aleatorio*/
		activoCriptoDAO.generarCantAleatorio(usuario.getId());
		activoFiatDAO.generarCantAleatorio(usuario.getId());
		
	}
	public void insertarUsuarioyPersona(String nombre, String apellido, String email, String password) { 

	    int idPersona = personaDAO.insertarPersona(nombre, apellido);
	    if (idPersona == -1) {
	        System.out.println("Error al insertar persona.");
	        return;
	    }

	    int idUsuario = usuarioDAO.insertarUsuario(idPersona, email, password, true);
	    if (idUsuario == -1) {
	        System.out.println("Error al insertar usuario.");
	        return;
	    }

	    this.usuario = new User(idUsuario, idPersona, email, password, true);
	    this.persona = new Persona(nombre, apellido, idPersona);
	}
	public List<Moneda> listarMonedas(){
		return monedaDAO.traerMonedas();
	}
	public List<ActivoCripto> listarActivoCripto(){
		return activoCriptoDAO.traerActivosCripto(usuario.getId());
	}
	public List<ActivoFiat> listarActivoFiat(){
		return activoFiatDAO.traerActivosFiat(usuario.getId());
	}
	
	public MonedaDAOJDBC getMonedaDAO() {
		return this.monedaDAO;
	}
	public ApInfoCripto getApInfoCripto() {
		return this.apInfoCripto;
	}
	public void comprar(ActivoFiat activoFiat, Moneda monedaComprar) throws SQLException {
	    Connection connection = null; // Suponiendo que tienes una conexión abierta
	    PreparedStatement stmt;
	    ResultSet rs;
	    
	    // 1. Obtener el valor de la moneda activa Fiat (en USD) y la moneda a comprar (también en USD)
	    String queryFiat = "SELECT M.VALOR_DOLAR FROM MONEDA M WHERE M.ID = ?";
	    stmt = DataBaseConnection.getInstancia().getConexion().prepareStatement(queryFiat);
	    stmt.setInt(1, activoFiat.getIdMoneda()); // Obtenemos la moneda asociada al activoFiat
	    rs = stmt.executeQuery();
	    
	    if (rs.next()) {
	        double valorFiatDolar = rs.getDouble("VALOR_DOLAR"); // Precio en dólares de la moneda Fiat
	        
	        // 2. Obtener el valor de la moneda a comprar (en USD)
	        String queryCompra = "SELECT M.VALOR_DOLAR FROM MONEDA M WHERE M.ID = ?";
	        stmt = DataBaseConnection.getInstancia().getConexion().prepareStatement(queryCompra);
	        stmt.setInt(1, monedaComprar.getId()); // Obtenemos la moneda que se desea comprar
	        rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            double valorCompraDolar = rs.getDouble("VALOR_DOLAR"); // Precio en dólares de la moneda a comprar
	            
	            // 3. Calcular la cantidad de moneda a comprar
	            double cantidadComprar = (activoFiat.getCantidad() * valorFiatDolar) / valorCompraDolar;
	            
	            // 4. Crear un nuevo activo cripto (por ejemplo, con la moneda comprada)
	            String insertActivoCripto = "INSERT INTO ACTIVO_CRIPTO (ID_USUARIO, ID_MONEDA, CANTIDAD) VALUES (?, ?, ?)";
	            PreparedStatement insertStmt = DataBaseConnection.getInstancia().getConexion().prepareStatement(insertActivoCripto);
	            insertStmt.setInt(1, activoFiat.getIdUsuario()); // Usuario que realiza la compra
	            insertStmt.setInt(2, monedaComprar.getId());    // Moneda comprada
	            insertStmt.setDouble(3, cantidadComprar);       // Cantidad comprada
	            
	            // 5. Guardar el nuevo activo en la base de datos
	            insertStmt.executeUpdate();
	            
	            // Cerramos los recursos
	            rs.close();
	            stmt.close();
	            insertStmt.close();
	        } else {
	            System.out.println("No se pudo encontrar el valor de la moneda a comprar.");
	        }
	    } else {
	        System.out.println("No se pudo encontrar el valor de la moneda Fiat.");
	    }
	}
	public ActivoFiat tengoActivo(String nomenclatura, double cantidad) throws SQLException {
		/*tengo que ver si en la base de datos tengo el activo con el cual pretendo pagar(que es un activo fiat), si lo tengo creo un activo el cual voy a retornar con la cantidad que pague 
		 * y todo lo que tiene este activo, ademas le resto al activo que tengo en la base de datos la cantidad, es decir pago ahora. si no lo tengo devuelvo null*/
	    Connection connection = null; // Suponiendo que tienes alguna forma de obtener la conexión
	    PreparedStatement stmt;
	    ResultSet rs;
	    
	    // Buscar el activo Fiat en la base de datos con la nomenclatura dada
	    String query = "SELECT AF.ID, AF.ID_USUARIO, AF.ID_MONEDA, AF.CANTIDAD, M.NOMENCLATURA "
	                 + "FROM ACTIVO_FIAT AF "
	                 + "JOIN MONEDA M ON AF.ID_MONEDA = M.ID "
	                 + "WHERE M.NOMENCLATURA = ?"; // Filtramos por la nomenclatura
	    stmt = DataBaseConnection.getInstancia().getConexion().prepareStatement(query);
	    stmt.setString(1, nomenclatura); // Pasamos la nomenclatura como parámetro
	    rs = stmt.executeQuery();
	    
	    if (rs.next()) {
	        // Si encontramos el activo en la base de datos
	        double cantidadActual = rs.getDouble("CANTIDAD");
	        
	        if (cantidadActual >= cantidad) {
	            // Si hay suficiente cantidad para realizar el pago
	            // Actualizamos la base de datos restando la cantidad
	            String updateQuery = "UPDATE ACTIVO_FIAT SET CANTIDAD = CANTIDAD - ? WHERE ID = ?";
	            PreparedStatement updateStmt = DataBaseConnection.getInstancia().getConexion().prepareStatement(updateQuery);
	            updateStmt.setDouble(1, cantidad);
	            updateStmt.setInt(2, rs.getInt("ID"));
	            updateStmt.executeUpdate();
	            
	            // Creamos el nuevo activo para devolver
	            ActivoFiat activoFiat = new ActivoFiat(rs.getInt("ID"), rs.getInt("ID_USUARIO"), rs.getInt("ID_MONEDA"), cantidad);
	            
	            // Cerramos los recursos
	            rs.close();
	            stmt.close();
	            
	            return activoFiat; // Retornamos el nuevo activo con la cantidad pagada
	        } else {
	            // No hay suficiente cantidad, devolvemos null
	            rs.close();
	            stmt.close();
	            return null;
	        }
	    } else {
	        // No se encontró el activo Fiat con la nomenclatura dada
	        rs.close();
	        stmt.close();
	        return null;
	    }
	}
}

	



