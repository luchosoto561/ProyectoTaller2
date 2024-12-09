package dao;
import modelos.Moneda;
import java.util.ArrayList;
import gestoresDAO.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.ResultSet;

public class MonedaDAOJDBC implements MonedaDAO{
    
    // Método para guardar una moneda en la base de datos
    public int guardarMoneda(Moneda moneda) {
        // Definimos la conexión
        Connection conexion = DataBaseConnection.getInstancia().getConexion();/*tengo la coneccion a la base de datos*/
        
        try {

            // Definimos la consulta SQL con valores preparados para evitar inyección SQL
            String sql = "INSERT INTO Moneda ( tipo, nombre, nomenclatura, valordolar, stock, volatilidad) VALUES (?, ?, ?, ?, ?, ?)";

            // Preparamos la consulta
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, moneda.getTipo());
            pstmt.setString(2, moneda.getNombre());
            pstmt.setString(3, moneda.getNomenclatura());
            pstmt.setDouble(4, moneda.getValorEnDolar());
            pstmt.setDouble(5, moneda.getStock());
            pstmt.setDouble(6, moneda.getVolatilidad());
            // Ejecutamos la consulta de inserción
            int resultado = pstmt.executeUpdate();
           
            // Cerramos el PreparedStatement
            pstmt.close();
            
            DataBaseConnection.cerrarConexion();/*me cierra la coneccion de la base de datos ejecutando el metodo que esta en la clase donde hago el singletone*/
            
            if (resultado == 0) {
                System.out.println("ERROR: No se pudo insertar la moneda en la base de datos.");
            } else {
                System.out.println("La moneda fue insertada correctamente en la base de datos.");
            }
            // Retornamos el resultado de la ejecución (1 si se insertó correctamente, 0 si no)
            return resultado;
            
        } catch (SQLException e) {
            // Manejamos cualquier error que ocurra en la inserción
            System.out.println("ERROR EN MÉTODO: guardarMoneda (clase MonedaDAO)");
            System.out.println(e.getMessage());
            return -1;
        }
    }
    public List<Moneda> listarMonedas() {
    	List<Moneda> monedas = new ArrayList<>();  // Lista donde almacenamos los resultados
        Connection conexion = DataBaseConnection.getInstancia().getConexion();
        
        // Definir la consulta según el criterio
        String sql = "SELECT * FROM MONEDA";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Iterar sobre el ResultSet y crear objetos Moneda
            while (rs.next()) {
                Moneda moneda = new Moneda();
                moneda.setNomenclatura(rs.getString("nomenclatura"));
                moneda.setNombre(rs.getString("nombre"));
                moneda.setTipo(rs.getString("tipo"));
                moneda.setValorEnDolar(rs.getDouble("valordolar"));
                moneda.setStock(rs.getDouble("stock"));
                moneda.setVolatilidad(rs.getDouble("volatilidad"));
                
                monedas.add(moneda);  // Agregar a la lista
            }
        } catch (SQLException e) {
            System.out.println("Error al listar monedas: " + e.getMessage());
        } finally {
            DataBaseConnection.cerrarConexion();
        }

        return monedas;
    }
    public void generarStock() {
		try {
		Connection con=DataBaseConnection.getInstancia().getConexion();
		Statement st = con.createStatement();
		
		String sql = "SELECT nombre FROM Moneda";
		st.executeUpdate(sql);
		
		ResultSet result = st.executeQuery(sql);
		int random = (int) Math.random();
		String pos;
		while (result.next()) {
			pos = result.getString("nombre");
			sql = "UPDATE Moneda SET stock = ? WHERE nombre = ?";
			PreparedStatement ust = con.prepareStatement(sql);
	        ust.setInt(1, random);
	        ust.setString(2, pos);
	        st.executeUpdate(sql);
	        
			random = (int) Math.random();
		}
		st.close();
		} catch (SQLException e) {
		System.out.println("no se pudo conectar a la BD");
		}finally {
			DataBaseConnection.cerrarConexion();
		}
	}
    
    public int chequearStock(String nomencripto) {
		try {
		Connection con=DataBaseConnection.getInstancia().getConexion();
		Statement st = con.createStatement();
		
		String sql = "SELECT stock FROM Moneda WHERE nomenclatura = ?";
		PreparedStatement ust = con.prepareStatement(sql);
        ust.setString(1, nomencripto);
		st.executeUpdate(sql);
		ResultSet result = st.executeQuery(sql);
		int cant = result.getInt("stock");
		
		
		st.close();
		return cant;
		} catch (SQLException e) {
		System.out.println("no se pudo conectar a la BD");
		return -1;
		}finally {
			DataBaseConnection.cerrarConexion();
		}
	}
    
    public String buscarNomen(String nombre) {
		try {
		Connection con=DataBaseConnection.getInstancia().getConexion();
		Statement st = con.createStatement();
		
		String sql = "SELECT nomenclatura FROM Moneda WHERE nombre = ?";
		PreparedStatement ust = con.prepareStatement(sql);
        ust.setString(1, nombre);
        st.executeUpdate(sql);
        
		ResultSet result = st.executeQuery(sql);
		String nomen = result.getString("nomenclatura");
		
		st.close();
		return nomen;
		} catch (SQLException e) {
		System.out.println("no se pudo conectar a la BD");
		return null;
		}finally {
			DataBaseConnection.cerrarConexion();
		}
	}
}