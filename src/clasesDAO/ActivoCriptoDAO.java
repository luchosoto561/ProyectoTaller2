package clasesDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelos.Activo;
import gestoresDAO.DataBaseConnection;

public class ActivoCriptoDAO {
	
	public ActivoCriptoDAO() {};
	
	public void generarActivo(double cantidad, String nomenclatura) {
		Connection con=null;
		try {
		con=DataBaseConnection.getInstancia().getConexion();
		Statement st = con.createStatement();
		
		String sql = "UPDATE ActivoCripto SET cantidad = ? WHERE nomenclatura = ?";
		PreparedStatement ust = con.prepareStatement(sql);
        ust.setDouble(1, cantidad);
        ust.setString(2, nomenclatura);
		st.executeUpdate(sql);
		
		st.close();
		} catch (SQLException e) {
		System.out.println("no se pudo conectar a la BD");
		}finally {
            DataBaseConnection.cerrarConexion();
        }

	}

	public List<Activo> listarActivoCripto() {
		List<Activo> activos = new ArrayList<>();  // Lista donde almacenamos los resultados
        Connection conexion = DataBaseConnection.getInstancia().getConexion();
        
        // Definir la consulta según el criterio
        String sql = "SELECT * FROM ActivoCripto";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Iterar sobre el ResultSet y crear objetos Moneda
            while (rs.next()) {
                Activo activo = new Activo();
                activo.setCantidad(rs.getDouble("cantidad"));
                activo.setNomenclatura(rs.getString("nomenclatura"));
                
                activos.add(activo);  // Agregar a la lista
            }
        }catch (SQLException e) {
            System.out.println("Error al listar monedas: " + e.getMessage());
        }/*en el try de arriba colocamos en activos todos los elementos de la tabla ActivoCripto de la base de datos, necesita su propio catch*/
        finally {
            DataBaseConnection.cerrarConexion();
        }

        return activos;
	}

	public void sumarActivoCripto(double cant, String nomen) {
		Connection con=null;
		try {
		con=DataBaseConnection.getInstancia().getConexion();
		Statement st = con.createStatement();
		
		String sql = "SELECT stock FROM ActivoCripto WHERE nomenclatura = ?";
		PreparedStatement ust = con.prepareStatement(sql);
        ust.setString(1, nomen);
		st.executeUpdate(sql);
		ResultSet result = st.executeQuery(sql);
		cant = cant + result.getInt("stock");
		
		sql = "UPDATE ActivoCripto SET cantidad = ? WHERE nomenclatura = ?";
		ust = con.prepareStatement(sql);
        ust.setDouble(1, cant);
        ust.setString(2, nomen);
		st.executeUpdate(sql);
		
		
		st.close();
		} catch (SQLException e) {
		System.out.println("no se pudo conectar a la BD");
		}finally {
            DataBaseConnection.cerrarConexion();
        }
	}
}

