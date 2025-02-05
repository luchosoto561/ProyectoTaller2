package Aplicacion;

import java.util.*;
import clases.*;
import java.sql.*;
import java.io.IOException;
import java.io.FileWriter;
import clases.*;

public class Modelo {
	User usuario;
	Persona persona;
	public boolean existeCuenta(String gmail, String password) {
	    boolean existe = false;
	    Connection connection = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	        // 1. Obtener la conexión (esto dependerá de cómo gestiones la conexión en tu proyecto)
	        connection = getConnection(); // Por ejemplo, un método que te retorne una Connection válida

	        // 2. Preparar la consulta
	        // Se usa JOIN para obtener datos tanto de USUARIO como de PERSONA, 
	        // suponiendo que USUARIO tiene la columna ID_PERSONA que referencia a PERSONA(ID)
	        String sql = "SELECT U.ID AS usuarioID, U.ID_PERSONA, U.EMAIL, U.PASSWORD, U.ACEPTA_TERMINOS, " +
	                     "P.NOMBRES, P.APELLIDOS " +
	                     "FROM USUARIO U " +
	                     "JOIN PERSONA P ON U.ID_PERSONA = P.ID " +
	                     "WHERE U.EMAIL = ? AND U.PASSWORD = ?";

	        pstmt = connection.prepareStatement(sql);
	        pstmt.setString(1, gmail);
	        pstmt.setString(2, password);

	        // 3. Ejecutar la consulta
	        rs = pstmt.executeQuery();

	        // 4. Verificar si se encontró un registro
	        if (rs.next()) {
	            existe = true;

	            // 5. Extraer la información necesaria de la persona y usuario
	            int usuarioID = rs.getInt("usuarioID");
	            int idPersona = rs.getInt("ID_PERSONA");
	            String email = rs.getString("EMAIL");
	            String pass = rs.getString("PASSWORD");
	            boolean aceptaTerminos = rs.getBoolean("ACEPTA_TERMINOS");
	            String nombres = rs.getString("NOMBRES");
	            String apellidos = rs.getString("APELLIDOS");

	            // 6. Crear las instancias correspondientes
	            Persona persona = new Persona(nombres, apellidos, idPersona);
	            User usuario = new User(usuarioID, idPersona, email, pass, aceptaTerminos);

	            // 7. Llamar al método del modelo que registra el usuario (por ejemplo, InsertUsuario)
	            // Suponiendo que tu modelo tiene un método similar:
	            this.insertarUsuarioyPersona(usuario, persona); // O el método que uses para guardar o establecer el usuario en tu modelo.
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // 8. Cerrar recursos para evitar fugas de memoria
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            // Dependiendo de tu gestión de la conexión, podrías cerrarla aquí o dejarla abierta para reutilizarla
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return existe;
	}

	public boolean existeGmail(String Gmail) {
		/*tengo que chequear si una cuenta ya usa el gmail pasado como parametro, si ya existe devuelve true*/
		
		return true;
	}
	public void exportar() throws IOException {
		List<ArrayList<String>> filas = new ArrayList<ArrayList<String>>();
		List<Moneda> datos = stockDao.selectMonedasUsuario(usuarioLogeado.getIdUsuario());
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
	public void insertarUsuarioypersona(User usuario, Persona persona) {
		/*tengo que crear la clase usuario y la clase persona, con la informacion que llega como parametros */
		this.usuario=usuario;
		this.persona=persona;
	}
}
