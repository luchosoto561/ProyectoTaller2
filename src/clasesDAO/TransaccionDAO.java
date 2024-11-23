package clasesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import gestoresDAO.DataBaseConnection;

public class TransaccionDAO {
	
	public void añadirDesc(String resumen, String fyh) {
		Connection con=null;
		try {
		con=DataBaseConnection.getInstancia().getConexion();
		Statement st = con.createStatement();
		
		String sql = "INSERT INTO Transaccion (resumen, fechahora) VALUES (?, ?)";
		PreparedStatement ust = con.prepareStatement(sql);
        ust.setString(1, resumen);
        ust.setString(2, fyh);
		st.executeUpdate(sql);
		
		st.close();
		
		} catch (SQLException e) {
		System.out.println("no se pudo conectar a la BD");
		} finally {
			DataBaseConnection.cerrarConexion();
		} 
	}

}
