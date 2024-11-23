package comparators;
import java.util.Comparator;
import modelos.Activo;


public class ComparadorNomenclaturaActivo implements Comparator<Activo> {
	  /*devuelve 1 si el primer objeto es mayor que el segundo, devuelve cero si son iguales y negativo si el segundo objeto es mas grande que el primero*/
		@Override
		public int compare(Activo a1, Activo a2){
			if(a1.getNomenclatura().compareTo(a2.getNomenclatura()) < 0)
				return -1;
			else 
				if(a1.getNomenclatura().compareTo(a2.getNomenclatura())>0)
					return 1;
				else return 0;
		}
}
