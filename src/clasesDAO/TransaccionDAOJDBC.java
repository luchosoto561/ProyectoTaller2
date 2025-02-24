package clasesDAO;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import clases.Transaccion;
import java.sql.*;
import gestoresDAO.DataBaseConnection;

public class TransaccionDAOJDBC implements TransaccionDAO {
	public void cargarTransaccion(int IdUsuario, String descripcion, LocalDateTime fechaHs) {
	    String sql = "INSERT INTO TRANSACCION (RESUMEN, FECHA_HORA, ID_USUARIO) VALUES (?, ?, ?)";

	    try {
	    	PreparedStatement stmt = DataBaseConnection.getInstancia().getConexion().prepareStatement(sql);
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String fechaStr = fechaHs.format(formatter);

	        // Asignar valores a los parámetros
	        stmt.setString(1, descripcion);
	        stmt.setString(2, fechaStr);
	        stmt.setInt(3, IdUsuario);

	        // Ejecutar la consulta
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        System.err.println("Error al registrar la transacción: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	public List<Transaccion> actualizarTransacciones(int idUsuario) {
	    List<Transaccion> transacciones = new ArrayList<>();
	    String sql = "SELECT ID, RESUMEN, FECHA_HORA, ID_USUARIO FROM TRANSACCION WHERE ID_USUARIO = ?";

	    try {
	    	 Connection connection = DataBaseConnection.getInstancia().getConexion(); // Asegúrate de tener un método para obtener la conexión
	         PreparedStatement pstmt = connection.prepareStatement(sql);

	        pstmt.setInt(1, idUsuario);
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Transaccion transaccion = new Transaccion();
	            transaccion.setId(rs.getInt("ID"));
	            transaccion.setResumen(rs.getString("RESUMEN"));
	            transaccion.setFechayHs(rs.getString("FECHA_HORA"));
	            transaccion.setIdUsuario(rs.getInt("ID_USUARIO"));

	            transacciones.add(transaccion);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace(); // Manejo de errores, puedes mejorarlo con logs
	    }

	    return transacciones;
	}
}
