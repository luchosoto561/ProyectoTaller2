package Aplicacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador {
	private Modelo modelo;
	private Vista vista;
	
	public Controlador(Vista vista, Modelo modelo) {
		this.vista = vista;
		this.modelo = modelo;
		
		/*tengo que asignar todos los listener a cada boton de toda la aplicacion que haya, para eso creo una clase por cada pantallaso, entonces cada pantallaso tiene como atributos todos los botones y todo,
		 *  entonces cuando asigno la vista ya tengo todos los botones para acceder con punto*/
		
		
		
		
		
		
	}
	
	public class BotonVentanaRegistrarListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			/*tengo que hacer que no se vea mas la pantalla de login y que se pase a ver la pantalla para registrarse*/
		}
		
	}

}
