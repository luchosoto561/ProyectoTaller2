package comparators;
import java.util.Comparator;
import modelos.Moneda;

public class ComparadorCantidad implements Comparator<Moneda> {
	/*devuelve 1 si el primer objeto es menor que el segundo, devuelve cero si son iguales y 1 si el segundo objeto es mas grande que el primero*/
	@Override
	public int compare(Moneda m1, Moneda m2){
		if(m1.getStock() > m2.getStock())
			return -1;
		else 
			if(m1.getStock() < m2.getStock() )
				return 1;
			else return 0;
	}

}
