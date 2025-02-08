package clasesDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import clases.ActivoCripto;
import clases.ActivoFiat;
import gestoresDAO.DataBaseConnection;

public class ActivoCriptoDAOJDBC {
	
	public List<ActivoCripto> traerActivosCripto(int idUsuario){
		/*devuelve una lista con los activosCripto que tienen el id pasado como parametro*/
		    List<ActivoCripto> activosCripto = new ArrayList<>();
		    String sql = "SELECT ID, ID_USUARIO, ID_MONEDA, CANTIDAD FROM ACTIVO_CRIPTO WHERE ID_USUARIO = ?";
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
		            // Crear objeto ActivoCripto y agregarlo a la lista
		            ActivoCripto activo = new ActivoCripto(id, idUsuario, idMoneda, cantidad);
		            activosCripto.add(activo);
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
		    
		    return activosCripto;
		
	}
	public void generarCantAleatorio(int idUsuario) {
	    String sql = "UPDATE ACTIVO_CRIPTO SET CANTIDAD = ? WHERE ID = ?";

	    PreparedStatement stmt = null;
	    Random random = new Random();

	    try {
	        stmt = DataBaseConnection.getInstancia().getConexion().prepareStatement(sql);

	        // Obtener todos los IDs de activos cripto del usuario
	        List<Integer> ids = obtenerIdsActivosCripto(idUsuario);
	        
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
	private List<Integer> obtenerIdsActivosCripto(int idUsuario) {
	    List<Integer> ids = new ArrayList<>();
	    String sql = "SELECT ID FROM ACTIVO_CRIPTO WHERE ID_USUARIO = ?";
	    
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
