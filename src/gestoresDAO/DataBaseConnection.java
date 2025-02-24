package gestoresDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static DataBaseConnection instanciaUnica;
    private static Connection conexion;
    private static final String URL = "jdbc:sqlite:basedatos.db";

    private DataBaseConnection() {/*establece la coneccion con la base de datos*/
        try {
            conexion = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al establecer la conexión con la base de datos.");
        }
    }

    public static DataBaseConnection getInstancia() {/*se crea instancia de dataBaseconection y se la devuelve o si ya esta creada se devuelve la instancia ya creada*/
        if (instanciaUnica == null) {
            instanciaUnica = new DataBaseConnection();
        }
        return instanciaUnica;
    }

    public Connection getConexion() {
        return conexion;
    }

    // Método para cerrar la conexión
    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión a la base de datos cerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al cerrar la conexión.");
            } finally {
                instanciaUnica = null;  // Libera la instancia para posibles futuras conexiones
            }
        }
    }
}