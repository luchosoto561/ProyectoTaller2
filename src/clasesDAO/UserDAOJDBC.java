package clasesDAO;

import clases.User;
import java.sql.*;
import gestoresDAO.DataBaseConnection;

public class UserDAOJDBC implements UserDAO {
	public User existeUsuario(String gmail, String password) {
	    User usuario = null; // Si no se encuentra, se devuelve null

	    Connection connection = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        // Obtener la conexión desde la clase Singleton
	        connection = DataBaseConnection.getInstancia().getConexion();

	        // Definir la consulta SQL
	        String sql = "SELECT ID, ID_PERSONA, EMAIL, PASSWORD, ACEPTA_TERMINOS FROM USUARIO WHERE EMAIL = ? AND PASSWORD = ?";

	        // Preparar la consulta
	        pstmt = connection.prepareStatement(sql);
	        pstmt.setString(1, gmail);
	        pstmt.setString(2, password);

	        // Ejecutar la consulta
	        rs = pstmt.executeQuery();

	        // Si existe un resultado, crear el objeto User
	        if (rs.next()) {
	            int usuarioID = rs.getInt("ID");
	            int idPersona = rs.getInt("ID_PERSONA");
	            String email = rs.getString("EMAIL");
	            String pass = rs.getString("PASSWORD");
	            boolean aceptaTerminos = rs.getBoolean("ACEPTA_TERMINOS");

	            // Crear el usuario con los datos obtenidos
	            usuario = new User(usuarioID, idPersona, email, pass, aceptaTerminos);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Cerrar recursos para evitar fugas de memoria
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	           /* if (connection != null) connection.close();*/
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return usuario; // Devuelve el usuario encontrado o null si no existe
	}
	public boolean chequeodeEmail(String mail) {
	    Connection conexion = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    boolean existe = false;

	    try {
	        // Obtener la conexión desde el Singleton
	        conexion = DataBaseConnection.getInstancia().getConexion();
	        
	        // Definir la consulta SQL con un parámetro
	        String sql = "SELECT 1 FROM USUARIO WHERE EMAIL = ? LIMIT 1";
	        
	        // Crear PreparedStatement y establecer el parámetro
	        pstmt = conexion.prepareStatement(sql);
	        pstmt.setString(1, mail);
	        
	        // Ejecutar la consulta
	        rs = pstmt.executeQuery();

	        // Si hay un resultado, significa que el correo existe en la BD
	        if (rs.next()) {
	            existe = true;
	        }

	    } catch (SQLException e) {
	        System.out.println("ERROR EN METODO: chequeodeEmail (clase UsuariosDaoJDBC)");
	        System.out.println(e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	           /* if (conexion != null) conexion.close();*/
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return existe;
	}
	public int insertarUsuario(int idPersona, String email, String password, boolean aceptaTerminos) {
        Connection conexion = null;
        PreparedStatement pstmtUsuario = null;
        ResultSet rsUsuario = null;
        int idUsuario = -1;

        try {
            conexion = DataBaseConnection.getInstancia().getConexion();
            String sqlUsuario = "INSERT INTO USUARIO (ID_PERSONA, EMAIL, PASSWORD, ACEPTA_TERMINOS) VALUES (?, ?, ?, ?)";
            pstmtUsuario = conexion.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
            pstmtUsuario.setInt(1, idPersona);
            pstmtUsuario.setString(2, email);
            pstmtUsuario.setString(3, password);
            pstmtUsuario.setBoolean(4, aceptaTerminos);
            pstmtUsuario.executeUpdate();

            rsUsuario = pstmtUsuario.getGeneratedKeys();
            if (rsUsuario.next()) {
                idUsuario = rsUsuario.getInt(1);
            } else {
                throw new SQLException("Error al obtener el ID del usuario.");
            }

        } catch (SQLException e) {
            System.out.println("ERROR EN METODO: insertarUsuario (clase UsuarioDAO)");
            e.printStackTrace();
        } finally {
            try {
                if (rsUsuario != null) rsUsuario.close();
                if (pstmtUsuario != null) pstmtUsuario.close();
              /*  if (conexion != null) conexion.close();*/
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return idUsuario;
    }
}
