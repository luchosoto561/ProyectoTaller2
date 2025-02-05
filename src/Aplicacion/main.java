package Aplicacion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class main {
	
	private static void creaciónDeTablasEnBD(Connection connection) throws SQLException {
		Statement stmt;
		stmt = connection.createStatement();
		String sql = "CREATE TABLE  IF NOT EXISTS PERSONA "
				+ "("
				+ " ID       INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
				+ " NOMBRES       VARCHAR(50)    NOT NULL, "
				+ " APELLIDOS       VARCHAR(50)    NOT NULL "
				+ ")";
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE  IF NOT EXISTS USUARIO " + "(" + " ID       INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
				+ " ID_PERSONA       INTEGER   NOT NULL, "
				+ " EMAIL       VARCHAR(50)    NOT NULL, "
				+ " PASSWORD       VARCHAR(50)    NOT NULL, "
				+ " ACEPTA_TERMINOS       BOOLEAN    NOT NULL, "
				+ " FOREIGN KEY(ID_PERSONA) REFERENCES PERSONA(ID)"
				+ ")";
		stmt.executeUpdate(sql);
				
		sql = "CREATE TABLE  IF NOT EXISTS MONEDA "
				+ "("
				+ " ID       INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
				+ " TIPO       VARCHAR(1)    NOT NULL, "
				+ " NOMBRE       VARCHAR(50)    NOT NULL, "
				+ " NOMENCLATURA VARCHAR(10)  NOT NULL, "
				+ " VALOR_DOLAR	REAL     NOT NULL, "
				+ " VOLATILIDAD	REAL     NULL, "
				+ " STOCK	REAL     NULL, "
				+ " NOMBRE_ICONO       VARCHAR(50)    NOT NULL "
				+ ")";
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE  IF NOT EXISTS ACTIVO_CRIPTO"
				+ "("
				+ " ID       INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
				+ " ID_USUARIO INTEGER    NOT NULL, "
				+ " ID_MONEDA INTEGER    NOT NULL, "
				+ " CANTIDAD	REAL    NOT NULL, "
				+ " FOREIGN KEY(ID_USUARIO) REFERENCES USUARIO(ID),"
				+ " FOREIGN KEY(ID_MONEDA) REFERENCES MONEDA(ID) "
				+ ")";
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE  IF NOT EXISTS ACTIVO_FIAT"
				+ "("
				+ " ID       INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
				+ " ID_USUARIO INTEGER    NOT NULL, "
				+ " ID_MONEDA INTEGER    NOT NULL, "
				+ " CANTIDAD	REAL    NOT NULL, "
				+ " FOREIGN KEY(ID_USUARIO) REFERENCES USUARIO(ID),"
				+ " FOREIGN KEY(ID_MONEDA) REFERENCES MONEDA(ID)"
				+ ")";
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE  IF NOT EXISTS TRANSACCION"
				+ "("
				+ " ID     INTEGER   PRIMARY KEY AUTOINCREMENT NOT NULL , "
				+ " RESUMEN VARCHAR(1000)   NOT NULL, "
				+ " FECHA_HORA		DATETIME  NOT NULL, "
				+ " ID_USUARIO INTEGER    NOT NULL, "
				+ " FOREIGN KEY(ID_USUARIO) REFERENCES USUARIO(ID)"
				+ ")";
		stmt.executeUpdate(sql);
		
		stmt.close();
	}
	public static void main (String [] args) {
		 String url = "jdbc:sqlite:mi_base_de_datos.db"; 
	        
	        try (Connection connection = DriverManager.getConnection(url)) {
	            // Llamamos al método pasando la conexión como parámetro
	            creaciónDeTablasEnBD(connection);
	            System.out.println("Tablas creadas correctamente.");
	            new Controlador(new Vista(),new Modelo());
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
    }
}
