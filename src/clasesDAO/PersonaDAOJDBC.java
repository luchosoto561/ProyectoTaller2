package clasesDAO;

import java.sql.*;
import clases.Persona;
import gestoresDAO.DataBaseConnection;

public class PersonaDAOJDBC implements PersonaDAO {
	public Persona buscarPersona(int idPersona) {
	    Connection conexion = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    Persona personaEncontrada = null;

	    try {
	        // Obtener la conexión desde el Singleton
	        conexion = DataBaseConnection.getInstancia().getConexion();
	        
	        // Definir la consulta SQL con un parámetro
	        String sql = "SELECT * FROM PERSONA WHERE ID = ?";
	        
	        // Crear PreparedStatement y establecer el parámetro
	        pstmt = conexion.prepareStatement(sql);
	        pstmt.setInt(1, idPersona);
	        
	        // Ejecutar la consulta
	        rs = pstmt.executeQuery();

	        // Verificar si se encontró una persona
	        if (rs.next()) {
	            personaEncontrada = new Persona(rs.getString("NOMBRES"), rs.getString("APELLIDOS"), rs.getInt("ID"));
	        }

	    } catch (SQLException e) {
	        System.out.println("ERROR EN METODO: buscarPersona (clase PersonaDaoJDBC)");
	        System.out.println(e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            /*if (conexion != null) conexion.close();*/
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return personaEncontrada;
	}
	public int insertarPersona(String nombre, String apellido) {
        Connection conexion = null;
        PreparedStatement pstmtPersona = null;
        ResultSet rsPersona = null;
        int idPersona = -1;

        try {
            conexion = DataBaseConnection.getInstancia().getConexion();
            String sqlPersona = "INSERT INTO PERSONA (NOMBRES, APELLIDOS) VALUES (?, ?)";
            pstmtPersona = conexion.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS);
            pstmtPersona.setString(1, nombre);
            pstmtPersona.setString(2, apellido);
            pstmtPersona.executeUpdate();

            rsPersona = pstmtPersona.getGeneratedKeys();
            if (rsPersona.next()) {
                idPersona = rsPersona.getInt(1);
            } else {
                throw new SQLException("Error al obtener el ID de la persona.");
            }

        } catch (SQLException e) {
            System.out.println("ERROR EN METODO: insertarPersona (clase PersonaDAO)");
            e.printStackTrace();
        } finally {
            try {
                if (rsPersona != null) rsPersona.close();
                if (pstmtPersona != null) pstmtPersona.close();
               /*if (conexion != null) conexion.close();*/
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return idPersona;
    }

}
