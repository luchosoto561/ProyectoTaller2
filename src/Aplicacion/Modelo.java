package Aplicacion;


import java.util.*;
import clases.*;
import java.sql.*;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import clasesDAO.*;

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
		monedaDAO.cargarmonedas();
		apInfoCripto = new ApInfoCripto();
	}

	public boolean existeCuenta(String gmail, String password) {
		User usr=usuarioDAO.existeUsuario(gmail, password);
		if(usr != null ) {
			this.usuario=usr;
			this.persona=personaDAO.buscarPersona(usr.getIdPersona());
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
		List<ActivoCripto> activosCripto = activoCriptoDAO.traerActivosCripto(usuario.getId());/*me tengo que traer los activos cripto de todo el usuario*/ 
		List<ActivoFiat> activosFiat = activoFiatDAO.traerActivosFiat(usuario.getId());/*me tengo que traer los activos fiat de todo el usuario*/
		List<Moneda> monedas = monedaDAO.traerMonedas();/*me trae todas las monedas de la base de datos en un arrayList*/
		
		ArrayList<String> aux;
		for (ActivoCripto ac : activosCripto) {
			aux= new ArrayList<String>();
			aux.add(monedas.get(ac.getIdMoneda()-1).getNomenclatura());/*nomenclatura*/
			aux.add(monedas.get(ac.getIdMoneda()-1).getNombre());/*nombre de la moneda*/
			aux.add(String.valueOf(ac.getCantidad() * monedas.get(ac.getId()-1).getValorDolar()));/*cantidad * precio*/
			filas.add(aux);
		}
		
		for (ActivoFiat af : activosFiat) {
			aux= new ArrayList<String>();
			aux.add(monedas.get(af.getIdMoneda()-1).getNomenclatura());
			aux.add(monedas.get(af.getIdMoneda()-1).getNombre());
			aux.add(String.valueOf(af.getCantidad() * monedas.get(af.getId()-1).getValorDolar()));
			filas.add(aux);
		}
		try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter("Archivo.csv", false))) {
            // Escribe los encabezados
            csvWriter.write("Nomenclatura,Nombre,Balance(USD)");
            csvWriter.newLine(); // Agrega salto de línea

            // Escribe cada fila en el CSV
            for (List<String> datos_fila : filas) {
                csvWriter.write(String.join(",", datos_fila));
                csvWriter.newLine();
            }
        } 
		
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
	public void generarActivoFiat() {
	        // Llamada al DAO para generar los activos fiat del usuario
	        activoFiatDAO.generarActivoFiat(usuario.getId());
	    
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
	
	public void comprar(ActivoFiat activoFiat, Moneda monedaComprar) throws SQLException {

	    // 1. Obtener el valor en dólares de las monedas involucradas
	    double valorFiatDolar = monedaDAO.obtenerValorDolar(activoFiat.getIdMoneda());
	    double valorCompraDolar = monedaDAO.obtenerValorDolar(monedaComprar.getId());

	    // 2. Calcular la cantidad de la criptomoneda que se puede comprar
	    double cantidadComprar = (activoFiat.getCantidad() * valorFiatDolar) / valorCompraDolar;

	    // 3. Verificar si el usuario ya tiene esa criptomoneda
	    Double cantidadActual = activoCriptoDAO.obtenerCantidadCripto(activoFiat.getIdUsuario(), monedaComprar.getId());

	    if (cantidadActual != null) {
	        // Si el activo ya existe, actualizar la cantidad
	        double nuevaCantidad = cantidadActual + cantidadComprar;
	        activoCriptoDAO.actualizarCantidadCripto(activoFiat.getIdUsuario(), monedaComprar.getId(), nuevaCantidad);
	    } else {
	        // Si no existe, crear el activo cripto
	        activoCriptoDAO.insertarActivoCripto(activoFiat.getIdUsuario(), monedaComprar.getId(), cantidadComprar);
	    }
	}

	public ActivoFiat tengoActivo(String nomenclatura, double cantidad) throws SQLException {
	    
	    // Buscar el activo en la base de datos
	    ActivoFiat activo = activoFiatDAO.obtenerActivoPorNomenclatura(nomenclatura);

	    if (activo != null && activo.getCantidad() >= cantidad) {
	        // Actualizar la cantidad en la base de datos
	        activoFiatDAO.actualizarCantidad(activo.getId(), cantidad);
	        
	        // Devolver el activo con la cantidad pagada
	        return new ActivoFiat(activo.getId(), activo.getIdUsuario(), activo.getIdMoneda(), cantidad);
	    }

	    return null; // No hay suficiente cantidad o no existe el activo
	}
	
	public TransaccionDAOJDBC getTransaccionDAO() {
		return this.transaccionDAO;
	}
	public User getUsuario() {
		return usuario;
	}
	public MonedaDAOJDBC getMonedaDAO() {
		return this.monedaDAO;
	}
	public ActivoCriptoDAOJDBC getActivoCriptoDAO() {
		return this.activoCriptoDAO;
	}
	public ActivoFiatDAOJDBC getActivoFiatDAO() {
		return this.activoFiatDAO;
	}
	public ApInfoCripto getApInfoCripto() {
		return this.apInfoCripto;
	}
}

	



