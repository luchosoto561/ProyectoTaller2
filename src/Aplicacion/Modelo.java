package Aplicacion;
import gestoresDAO.DataBaseConnection;
import gestoresDAO.FactoryDAO;
import org.json.JSONObject;
import java.util.*;
import clases.*;
import java.sql.*;
import java.io.IOException;
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
	/*public void ConsultarPrecioCripto() {
		
		private static final String URL_API = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum,usd-coin,tether,dogecoin&vs_currencies=usd";
		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest solicitud = HttpRequest.newBuilder().uri(URI.create(URL_API)).GET().build();
			try {
		           HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
		           
		           if (respuesta.statusCode() == 200) {
		               parsearYMostrarPrecios(respuesta.body());
		           } else {
		               System.out.println("Error: " + respuesta.statusCode());
		           }
		       } catch (IOException | InterruptedException e) {
		           e.printStackTrace();
		       }
	
	}*/
	/*private void parsearYMostrarPrecios(String cuerpoRespuesta) {
		 JSONObject json = new JSONObject(cuerpoRespuesta);
	     System.out.println("Precios de Criptomonedas (en USD):");
	     double precioBTC = json.getJSONObject("bitcoin").getDouble("usd");
	     System.out.println("BTC: $" + precioBTC);
	     double precioETH = json.getJSONObject("ethereum").getDouble("usd");
	     System.out.println("ETH: $" + precioETH);
	     double precioUSDC = json.getJSONObject("usd-coin").getDouble("usd");
	     System.out.println("USDC: $" + precioUSDC);
	     double precioUSDT = json.getJSONObject("tether").getDouble("usd");
	     System.out.println("USDT: $" + precioUSDT);
	     double precioDOGE = json.getJSONObject("dogecoin").getDouble("usd");
	     System.out.println("DOGE: $" + precioDOGE);
	}*/
}

	



