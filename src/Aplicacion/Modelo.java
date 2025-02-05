package Aplicacion;
import gestoresDAO.DataBaseConnection;
import gestoresDAO.FactoryDAO;

import java.util.*;
import clases.*;
import java.sql.*;
import java.io.IOException;
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
		List<Moneda> datos = stockDao.selectMonedasUsuario(usuario.getId());
		ArrayList<String> aux;
		
		for (Moneda m : datos) {
			aux= new ArrayList<String>();
			aux.add(m.getNomenclatura());
			aux.add(m.getNombre());
			aux.add(String.valueOf(m.getCantidad()*m.getPrecio()));
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
		
		
	}
	
	public void generarActivos() {
		
		
	}
	/*tiene que cargar el panel activos con la informacion del usuario, a este se lo llama en los listener
	 *  de login y registrarse, luego se muestra el panel activos*/
	public void cargarPanelActivos() {
		
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
	
}
