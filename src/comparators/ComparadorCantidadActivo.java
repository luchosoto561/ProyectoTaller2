package comparators;
import java.util.Comparator;
import modelos.Activo;

public class ComparadorCantidadActivo implements Comparator<Activo> {
	/*devuelve -1 si el primer objeto es menor que el segundo, devuelve cero si son iguales y -1 si el segundo objeto es mas grande que el primero*/
	@Override
	public int compare(Activo a1, Activo a2){
		if(a1.getCantidad() > a2.getCantidad() )
			return 1;
		else 
			if(a1.getCantidad() < a2.getCantidad() )
				return -1;
			else return 0;
	}

}
