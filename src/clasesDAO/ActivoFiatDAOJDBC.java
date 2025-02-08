package clasesDAO;

import java.util.*;
import clases.ActivoFiat;
import gestoresDAO.DataBaseConnection;

import java.sql.*;

public class ActivoFiatDAOJDBC implements ActivoFiatDAO {
	
	public List<ActivoFiat> traerActivosFiat(int idUsuario) {
	    List<ActivoFiat> activosFiat = new ArrayList<>();
	    String sql = "SELECT ID, ID_USUARIO, ID_MONEDA, CANTIDAD FROM ACTIVO_FIAT WHERE ID_USUARIO = ?";
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        stmt = DataBaseConnection.getInstancia().getConexion().prepareStatement(sql);
	        stmt.setInt(1, idUsuario);
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("ID");
	            int idMoneda = rs.getInt("ID_MONEDA");
	            double cantidad = rs.getDouble("CANTIDAD");
	            // Crear objeto ActivoFiat y agregarlo a la lista
	            ActivoFiat activo = new ActivoFiat(id, idUsuario, idMoneda, cantidad);
	            activosFiat.add(activo);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return activosFiat;
	}
	public void generarCantAleatorio(int idUsuario) {
	    String sql = "UPDATE ACTIVO_FIAT SET CANTIDAD = ? WHERE ID = ?";

	    PreparedStatement stmt = null;
	    Random random = new Random();

	    try {
	        stmt = DataBaseConnection.getInstancia().getConexion().prepareStatement(sql);

	        // Obtener todos los IDs de activos fiat del usuario
	        List<Integer> ids = obtenerIdsActivosFiat(idUsuario);
	        
	        for (int id : ids) {
	            double cantidadAleatoria = 1 + (1000 - 1) * random.nextDouble(); // Número entre 1 y 1000
	            stmt.setDouble(1, cantidadAleatoria);
	            stmt.setInt(2, id);
	            stmt.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (stmt != null) stmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	private List<Integer> obtenerIdsActivosFiat(int idUsuario) {
	    List<Integer> ids = new ArrayList<>();
	    String sql = "SELECT ID FROM ACTIVO_FIAT WHERE ID_USUARIO = ?";
	    
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        stmt = DataBaseConnection.getInstancia().getConexion().prepareStatement(sql);
	        stmt.setInt(1, idUsuario);
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            ids.add(rs.getInt("ID"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return ids;
	}
}
