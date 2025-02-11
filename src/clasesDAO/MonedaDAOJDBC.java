package clasesDAO;

import clases.Moneda;
import java.util.*;
import java.sql.*;
import gestoresDAO.DataBaseConnection;


public class MonedaDAOJDBC implements MonedaDAO {
	
	public List<Moneda> traerMonedas() {
	    List<Moneda> monedas = new ArrayList<>();
	    String sql = "SELECT * FROM MONEDA ORDER BY ID ASC";/*trae las monedas ordenadas por id de menor a mayor? segun el chat si pero es muy importante que sea asi*/

	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        stmt = DataBaseConnection.getInstancia().getConexion().prepareStatement(sql);
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("ID");
	            String tipo = rs.getString("TIPO");
	            String nombre = rs.getString("NOMBRE");
	            String nomenclatura = rs.getString("NOMENCLATURA");
	            float valorDolar = rs.getFloat("VALOR_DOLAR");
	            float volatilidad = rs.getFloat("VOLATILIDAD");
	            float stock = rs.getFloat("STOCK");
	            String nombreIcono = rs.getString("NOMBRE_ICONO");

	            Moneda moneda = new Moneda(id, tipo, nombre, nomenclatura, valorDolar, volatilidad, stock, nombreIcono);
                monedas.add(moneda);/*me las agrega adelante*/
	            /*// Ajustar el tamaño de la lista para que los índices coincidan con los IDs
	            while (monedas.size() < id) {
	                monedas.add(null);
	            }

	            // Colocar la moneda en la posición correcta
	            monedas.set(id - 1, moneda);*/
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
	    
	    return monedas;
	}
	
	public void generarStockAleatorio() {
	    Statement stmt = null;
	    ResultSet rs = null;
	    java.sql.PreparedStatement pstmt = null;
	    Random random = new Random();
	    
	    try {
	        // Obtener las monedas de la base de datos
	        stmt = DataBaseConnection.getInstancia().getConexion().createStatement();
	        String sql = "SELECT ID FROM MONEDA";  // Seleccionar solo el ID de las monedas
	        rs = stmt.executeQuery(sql);
	        
	        // Recorrer cada moneda y actualizar su stock con un valor aleatorio
	        while (rs.next()) {
	            int monedaId = rs.getInt("ID");
	            double stockAleatorio = random.nextDouble() * 1000; // Stock aleatorio entre 0 y 1000
	            
	            // Preparar la actualización
	            String updateSql = "UPDATE MONEDA SET STOCK = ? WHERE ID = ?";
	            pstmt = DataBaseConnection.getInstancia().getConexion().prepareStatement(updateSql);
	            
	            // Establecer los parámetros y ejecutar la actualización
	            pstmt.setDouble(1, stockAleatorio); // Establecer el valor aleatorio de stock
	            pstmt.setInt(2, monedaId); // Establecer el ID de la moneda
	            pstmt.executeUpdate(); // Ejecutar la actualización
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (pstmt != null) pstmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void actualizarPrecio(String nombreMoneda, double precioRespectoDolar) {
	    // Conectar con la base de datos
	    Connection connection = null;
	    PreparedStatement pstmt = null;

	    try {
	        // Asumiendo que ya tienes un método para obtener la conexión
	        connection = DataBaseConnection.getInstancia().getConexion(); // Método ficticio que obtendría la conexión a tu BD
	        
	        // Obtener todas las monedas (usando el nombreMoneda si es necesario)
	        String sqlSelect = "SELECT * FROM MONEDA WHERE NOMBRE = ?";
	        pstmt = connection.prepareStatement(sqlSelect);
	        pstmt.setString(1, nombreMoneda);
	        ResultSet rs = pstmt.executeQuery();

	        // Verificamos si la moneda existe
	        if (rs.next()) {
	            // Actualizamos el precio en la base de datos
	            String sqlUpdate = "UPDATE MONEDA SET VALOR_DOLAR = ? WHERE NOMBRE = ?";
	            pstmt = connection.prepareStatement(sqlUpdate);
	            pstmt.setDouble(1, precioRespectoDolar);
	            pstmt.setString(2, nombreMoneda);
	            pstmt.executeUpdate();
	        }
	    } catch (SQLException ex) {
	            ex.printStackTrace();
	    }
	      finally {
	        // Cerrar recursos
	        try {
	            if (pstmt != null) {
	                pstmt.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	public double getStock(String nombre) {
        String sql = "SELECT STOCK FROM MONEDA WHERE NOMBRE = ?";
        try (PreparedStatement pstmt = DataBaseConnection.getInstancia().getConexion().prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("STOCK");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retorna -1 si no se encuentra la moneda
    }
	public Moneda getMoneda(String nomenclatura) {
	    Moneda moneda = null;
	    String sql = "SELECT * FROM MONEDA WHERE NOMENCLATURA = ?";

	    try {
	    	 Connection connection = DataBaseConnection.getInstancia().getConexion();
	         PreparedStatement pstmt = connection.prepareStatement(sql); 
	        
	         pstmt.setString(1, nomenclatura);
	         ResultSet rs = pstmt.executeQuery();

	         if (rs.next()) {
	             int id = rs.getInt("ID");
	             String tipo = rs.getString("TIPO");
	             String nombre = rs.getString("NOMBRE");
	             float valorDolar = rs.getFloat("VALOR_DOLAR");
	             float volatilidad = rs.getFloat("VOLATILIDAD");
	             double stock = rs.getDouble("STOCK");
	             String nombreIcono = rs.getString("NOMBRE_ICONO");

	            moneda = new Moneda(id, tipo, nombre, nomenclatura, valorDolar, volatilidad, stock, nombreIcono);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return moneda;
	}
	public void cargarmonedas() {
	    // Array de monedas con la información que queremos cargar
	    String[][] monedas = {
	        {"C", "Bitcoin", "BTC", "9728.37", "0.0", "0.0", "bitcoin.png"},
	        {"C", "Ethereum", "ETH", "2661.6", "0.0", "0.0", "ethereum.png"},
	        {"C", "Tether", "USDT", "1.0", "0.0", "0.0", "tether.png"},
	        {"C", "Dogecoin", "DOGE", "0.2496", "0.0", "0.0", "dogecoin.png"},
	        {"F", "Dólar", "USD", "1.0", "0.0", "0.0", "dolar.png"},
	        {"F", "Euro", "EUR", "1.03", "0.0", "0.0", "euro.png"},
	        {"F", "Yen Japonés", "JPY", "0.009", "0.0", "0.0", "yen.png"},
	        {"C", "USD coin", "USDC", "1.35", "0.0", "0.0", "libra.png"},
	        {"F", "Peso Argentino", "ARS", "0.001", "0.0", "0.0", "peso.png"}
	    };

	    PreparedStatement stmt = null;

	    try {
	        // Iterar sobre el array de monedas y crear una inserción para cada una
	        String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, VALOR_DOLAR, VOLATILIDAD, STOCK, NOMBRE_ICONO) "
	                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
	        stmt = DataBaseConnection.getInstancia().getConexion().prepareStatement(sql);

	        for (String[] moneda : monedas) {
	            stmt.setString(1, moneda[0]);          // TIPO
	            stmt.setString(2, moneda[1]);          // NOMBRE
	            stmt.setString(3, moneda[2]);          // NOMENCLATURA
	            stmt.setDouble(4, Double.parseDouble(moneda[3])); // VALOR_DOLAR
	            stmt.setDouble(5, Double.parseDouble(moneda[4])); // VOLATILIDAD
	            stmt.setDouble(6, Double.parseDouble(moneda[5])); // STOCK
	            stmt.setString(7, moneda[6]);          // NOMBRE_ICONO
	            stmt.executeUpdate();
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al cargar monedas: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        try {
	            if (stmt != null) {
	                stmt.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

}
