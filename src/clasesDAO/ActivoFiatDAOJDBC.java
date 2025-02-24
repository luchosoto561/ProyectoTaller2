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
	public void generarActivoFiat(int idUsuario) {
	    String sqlCheck = "SELECT COUNT(*) FROM ACTIVO_FIAT WHERE ID_USUARIO = ?";
	    String sqlInsert = "INSERT INTO ACTIVO_FIAT (ID_USUARIO, ID_MONEDA, CANTIDAD) VALUES (?, ?, ?)";
	    PreparedStatement pstmtCheck = null;
	    PreparedStatement pstmtInsert = null;
	    ResultSet rs = null;

	    try {
	        // Verificar si el usuario ya tiene activos fiat
	        pstmtCheck = DataBaseConnection.getInstancia().getConexion().prepareStatement(sqlCheck);
	        pstmtCheck.setInt(1, idUsuario);
	        rs = pstmtCheck.executeQuery();
	        
	        if (rs.next() && rs.getInt(1) > 0) 
	            return;

	        // Si no tiene activos fiat, proceder con la inserción
	        int[] idsMonedas = {5, 6, 7, 9};
	        pstmtInsert = DataBaseConnection.getInstancia().getConexion().prepareStatement(sqlInsert);

	        for (int idMoneda : idsMonedas) {
	            pstmtInsert.setInt(1, idUsuario);
	            pstmtInsert.setInt(2, idMoneda);
	            pstmtInsert.setDouble(3, 0.0);
	            pstmtInsert.executeUpdate();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmtCheck != null) pstmtCheck.close();
	            if (pstmtInsert != null) pstmtInsert.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public ActivoFiat obtenerActivoPorNomenclatura(String nomenclatura) throws SQLException {
        String query = "SELECT AF.ID, AF.ID_USUARIO, AF.ID_MONEDA, AF.CANTIDAD, M.NOMENCLATURA "
                     + "FROM ACTIVO_FIAT AF "
                     + "JOIN MONEDA M ON AF.ID_MONEDA = M.ID "
                     + "WHERE M.NOMENCLATURA = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = DataBaseConnection.getInstancia().getConexion().prepareStatement(query);
            stmt.setString(1, nomenclatura);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return new ActivoFiat(
                    rs.getInt("ID"),
                    rs.getInt("ID_USUARIO"),
                    rs.getInt("ID_MONEDA"),
                    rs.getDouble("CANTIDAD")
                );
            }
            return null; // No se encontró el activo
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
    }

    public void actualizarCantidad(int idActivo, double cantidad) throws SQLException {
        String updateQuery = "UPDATE ACTIVO_FIAT SET CANTIDAD = CANTIDAD - ? WHERE ID = ?";
        PreparedStatement stmt = null;

        try {
            stmt = DataBaseConnection.getInstancia().getConexion().prepareStatement(updateQuery);
            stmt.setDouble(1, cantidad);
            stmt.setInt(2, idActivo);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
        }
    }
}
