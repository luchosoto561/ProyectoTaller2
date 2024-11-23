package comparators;
import java.util.Comparator;
import modelos.Moneda;
public class ComparadorValor implements Comparator<Moneda> {
	/*devuelve 1 si el primer objeto es mayor que el segundo, devuelve cero si son iguales y negativo si el segundo objeto es mas grande que el primero*/
	@Override
	public int compare(Moneda m1, Moneda m2){
		if(m1.getValorEnDolar()-m2.getValorEnDolar() < 0)
			return -1;
		else 
			if(m1.getValorEnDolar()-m2.getValorEnDolar() > 0)
				return 1;
			else 
				return 0;
	}

}
