package Aplicacion;

import java.util.*;

import clases.User;

import java.io.IOException;
import java.io.FileWriter;


public class Modelo {
	User usuario;
	
	public boolean existeCuenta(String gmail, String Password) {
		/*tengo que cheauear si existe la cuenta en la base de datos(con ese gmail y con esa contrasenia), si existe retorno verdadero y hago new de usuario con toda la informacion, sino retorno falso*/
		
		
		return true;
	}
	public boolean existeGmail(String Gmail) {
		/*tengo que chequear si una cuenta ya usa el gmail pasado como parametro, si ya existe devuelve true*/
		
		return true;
	}
	public void exportar() throws IOException {
		List<ArrayList<String>> filas = new ArrayList<ArrayList<String>>();
		List<Moneda> datos = stockDao.selectMonedasUsuario(usuarioLogeado.getIdUsuario());
		ArrayList<String> aux;
		
		for (Moneda m : datos) {
			aux= new ArrayList<String>();
			aux.add(m.getNomenclatura());
			aux.add(m.getNombre());
			aux.add(String.valueOf(m.getCantidad()*m.getPrecio()));
			filas.add(aux);
		}
		
		FileWriter csvWriter = new FileWriter("DatosBalance.csv");
		csvWriter.append("Nomenclatura");
		csvWriter.append(",");
		csvWriter.append("Nombre");
		csvWriter.append(",");
		csvWriter.append("Balance(USD)");
		csvWriter.append('\n');				
		for (List<String> datos_fila : filas) {
			csvWriter.append(String.join(",", datos_fila));
			csvWriter.append('\n');
		}
		csvWriter.close();
		
	}
	public void generarStock() {
		
		
	}
	
	public void generarActivos() {
		
		
	}
	/*tiene que cargar el panel activos con la informacion del usuario, a este se lo llama en los listener
	 *  de login y registrarse, luego se muestra el panel activos*/
	public void cargarPanelActivos() {
		
	}
}
