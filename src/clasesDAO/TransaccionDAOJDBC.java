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
	public List<Transaccion> actualizarTransacciones() {
	    List<Transaccion> transacciones = new ArrayList<>();
	    String sql = "SELECT ID, RESUMEN, FECHA_HORA, ID_USUARIO FROM TRANSACCION";

	    try (Statement stmt = DataBaseConnection.getInstancia().getConexion().createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int id = rs.getInt("ID");
	            String resumen = rs.getString("RESUMEN");
	            String fechaStr = rs.getString("FECHA_HORA"); // Se mantiene como String
	            int idUsuario = rs.getInt("ID_USUARIO");

	            transacciones.add(new Transaccion(id, resumen, fechaStr, idUsuario)); // Se pasa como String
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return transacciones;
	}}
