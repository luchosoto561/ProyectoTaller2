package modelos;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import gestoresDAO.DataBaseConnection;
import gestoresDAO.FactoryDAO;
import java.util.List;
import comparators.*;
import dao.*;

public class Principal {
	public static void main(String[] args) throws SQLException {
		creacionDeTablasEnBD(DataBaseConnection.getInstancia().getConexion());
        Scanner scanner= new Scanner(System.in);
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("Elige una opción:");
            System.out.println("1 - Crear Moneda");
            System.out.println("2 - Listar Monedas");
            System.out.println("3 - Generar Stock");
            System.out.println("4 - Listar Stocks");
            System.out.println("5 - Generar Activo");
            System.out.println("6 - Listar Mis Activos");
            System.out.println("7 - Comprar Criptomoneda");
            System.out.println("8 - Swap");
            System.out.println("9 - Salir");
            
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    crearMoneda(scanner);
                    break;

                case 2:
                    listarMonedas();
                    break;

                case 3:
                    generarStock();
                    break;

                case 4:
                    listarStocks();
                    break;

                case 5:
                    generarActivo();
                    break;

                case 6:
                    listarMisActivos();
                    break;

                case 7:
                    comprarCriptomoneda();
                    break;

                case 8:
                    realizarSwap();
                    break;

                case 9:
                    continuar = false;
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        }
        scanner.close();
    }
	private static void creacionDeTablasEnBD(Connection connection) throws SQLException {
		Statement stmt;
		stmt = connection.createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS Moneda " + "(" + " tipo       TEXT    NOT NULL, "+ " nombre       TEXT    NOT NULL, " + " nomenclatura TEXT  PRIMARY KEY   NOT NULL, "+ " valordolar	REAL     NOT NULL, " + " stock	REAL     NULL, "+ " volatilidad	REAL     NULL "  + ")";
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE IF NOT EXISTS ActivoCripto" + "(" + " nomenclatura TEXT  PRIMARY KEY     NOT NULL, "+ " cantidad	REAL    NOT NULL " + ")";
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE IF NOT EXISTS ActivoFiat" + "(" + " nomenclatura TEXT  PRIMARY KEY     NOT NULL, "+ " cantidad	REAL    NOT NULL " + ")";
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE IF NOT EXISTS Transaccion" + "(" + " resumen TEXT   NOT NULL, "+ " fechahora		TEXT  NOT NULL " + ")";
		stmt.executeUpdate(sql);
		stmt.close();
	}
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    private static void crearMoneda(Scanner scanner) {
        /*se solicita al usuario que ingrese tipo,(cripto o fiat) nombre, nomenclatura, valor en dolar y el stock disponible. una vez ingresados los datos si el usuario confirma
         * se guardan los datos en la base de datos. para el tipo de moneda el usuario no deberia porder ingresar cualquier valor, deberia estar acotado*/
    	 String tipo = obtenerTipoMoneda(scanner);
         String nombre = obtenerNombre(scanner);
         String nomenclatura = obtenerNomenclatura(scanner);
         double valorDolar = obtenerValorDolar(scanner);
         double stock = obtenerStock(scanner);
         double volatilidad = obtenerVolatilidad(scanner);

         System.out.println("Confirma los datos ingresados:");
         System.out.println("Tipo: " + tipo + ", Nombre: " + nombre + ", Nomenclatura: " + nomenclatura + ", Valor en Dólar: " +valorDolar+ ", Stock: " +stock+ ", Volatilidad: " +volatilidad);
         
         System.out.print("¿Confirma los datos? (si/no): ");
         String confirmacion = scanner.next().trim().toLowerCase();/*se convierten todos los caracteres a minusculas y se eliminan espacios en blanco*/
         
         if ("si".equals(confirmacion)) {
        	 Moneda moneda = new Moneda(tipo, nombre, nomenclatura, valorDolar, stock, volatilidad);
        	 MonedaDAOJDBC mdao = new MonedaDAOJDBC();
        	 mdao.guardarMoneda(moneda);/*SE GUARDA LA MONEDA EN LA BASE DE DATOS*/
         }
         
     }

     private static String obtenerTipoMoneda(Scanner scanner) {/*devuelve el tipo de moneda que se quiere crear en minuscula*/
         String tipo;
         while (true) {
             System.out.print("Ingrese el tipo de moneda (cripto/fiat): ");
             tipo = scanner.next().trim().toLowerCase();
             if ("cripto".equals(tipo) || "fiat".equals(tipo)) {
                 break;
             }
             System.out.println("Tipo inválido. Debe ser 'cripto' o 'fiat'.");
         }
         return tipo;
     }

     private static String obtenerNombre(Scanner scanner) {
         System.out.print("Ingrese el nombre de la moneda: ");
         return scanner.next().trim();
     }

     private static String obtenerNomenclatura(Scanner scanner) {
         System.out.print("Ingrese la nomenclatura de la moneda: ");
         return scanner.next().trim();
     }

     private static double obtenerValorDolar(Scanner scanner) {
         System.out.print("Ingrese el valor en dólares: ");
         return scanner.nextDouble();
     }
     
     private static double obtenerVolatilidad(Scanner scanner) {
         System.out.print("Ingrese la volatilidad de la moneda: ");
         return scanner.nextDouble();
     }
     
     private static double obtenerStock(Scanner scanner) {
         System.out.print("Ingrese el stock disponible: ");
         return scanner.nextDouble();
     }

 /*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    private static void listarMonedas() {
        /*mostrar en pantalla la informacion de las monedas disponibles. si bien las monedas se mostraran por valor en dolar, debe ser capaz de ordenarse por nomenclatura, usar alguno de los mecanismos de interface*/
    	Scanner scanner = new Scanner(System.in);
        String criterio = "";

        // Pide al usuario que elija un criterio de ordenación válido
        while (!(criterio.equalsIgnoreCase("nomenclatura") || (criterio.equalsIgnoreCase("valorEnDolar")))) {
            System.out.println("Seleccione el criterio para listar las monedas:");
            System.out.println("1. Nomenclatura");
            System.out.println("2. Valor en Dólar");
            String opcion = scanner.nextLine();
            
            MonedaDAOJDBC mdao = FactoryDAO.getMonedaDAO();
            List<Moneda> lmoneda = mdao.listarMonedas();/*tengo la lista de la base de datos*/
            // Asigna el criterio según la opción seleccionada
            if (opcion.equals("1")) {
                criterio = "nomenclatura";
                /*tengo que ordenar la lista por el criterio e imprimirla*/
                lmoneda.sort(new ComparadorNomenclatura());
            } else if (opcion.equals("2")) {
                criterio = "valorEnDolar";
                /*tengo que ordenar la lista por el criterio e imprimirla*/
                lmoneda.sort(new ComparadorValor());
            } else {
                System.out.println("Opción no válida. Por favor, elija '1' o '2'.");
            }
            for(Moneda moneda : lmoneda) {
            	System.out.println("nomenclatura: "+moneda.getNomenclatura()+" valor en dolar: "+moneda.getValorEnDolar()+" nombre: "+moneda.getNombre());
            }
        }
        
    }
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    private static void generarStock() {
        /*de manera aleatoria genera una cantidad de monedas disponibles para todos los usuarios de las billeteras*/
    	MonedaDAOJDBC moneda = FactoryDAO.getMonedaDAO();
    	moneda.generarStock();
    }
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    private static void listarStocks() {
        /*mostrar en pantalla informacion del stock disponible. si bien el stock se mostrara ordenado por cantidad de manera descendente debe ser capaz de ordenarse por nomenclatura*/
    	Scanner scanner = new Scanner(System.in);
        String criterio = "";

        // Pide al usuario que elija un criterio de ordenación válido
        while (!(criterio.equalsIgnoreCase("nomenclatura") || criterio.equalsIgnoreCase("cantidad"))) {
            System.out.println("Seleccione el criterio para listar las monedas:");
            System.out.println("1. nomenclatura");
            System.out.println("2. cantidad");
            String opcion = scanner.nextLine();
            
            MonedaDAOJDBC mdao = FactoryDAO.getMonedaDAO();
            List<Moneda> lmoneda = mdao.listarMonedas();/*tengo la lista con todas las monedas de la base de datos*/
            // Asigna el criterio según la opción seleccionada
            if (opcion.equals("1")) {
                criterio = "nomenclatura";
                /*tengo que ordenar la lista por el criterio e imprimirla*/
                lmoneda.sort(new ComparadorNomenclatura());
            } else if (opcion.equals("2")) {/*TE PIDE QUE ORDENES DE MANERA DESCENDENTE*/
                criterio = "cantidad";
                /*tengo que ordenar la lista por el criterio e imprimirla*/
                lmoneda.sort(new ComparadorCantidad());
            } else {
                System.out.println("Opción no válida. Por favor, elija '1' o '2'.");
            }
            for(Moneda moneda : lmoneda) {
            	System.out.println("el stock de la moneda "+moneda.getNombre()+" es: "+moneda.getStock());
            }
        }
        
    }
/*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    private static void generarActivo() {
        
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Ingrese la cantidad deseada: ");
		int cantidad = sc.nextInt();
		sc.nextLine();
		System.out.println("Ingrese la nomenclatura: ");
		String nomenclatura = sc.nextLine();
		
		
		/*chequeo si existe la nomenclatura en la base de datos, me fijo que tipo de moneda es*/
		boolean existe = false;
		MonedaDAOJDBC monedao = FactoryDAO.getMonedaDAO();
		List<Moneda> lista = monedao.listarMonedas();
		String tipo = " ";/*la utilizo para ver si lo mando a la tabla de cripto o de fiat*/
		for (Moneda moneda : lista) {
		    if (moneda.getNomenclatura().equals(nomenclatura)) {
		        existe = true;
		        tipo = moneda.getTipo();
		        break;  
		    }
		}

		
		/*si existe (la moneda con la nomenclatura elegida) lo cargo en la base de datos, sino informo error*/
		if (existe) {
		    System.out.println("La nomenclatura está en la lista.");
		    
		    /*confirmacion de que quiere crear la criptomoneda*/
			System.out.println("¿Desea confirmar la cantidad " + cantidad+ " de la criptomoneda con nomenclatura " + nomenclatura + "? Responda si o no");
			String answer = sc.nextLine().toLowerCase();
			while (!(answer.equals("si")) && !(answer.equals("no")) ) {
				System.out.println ("Responda Si o No");
				answer = sc.nextLine();
			}
			
			
			/*si el tipo es cripto lo guardamos en la base de datos de cripto, si el tipo es fiat lo guardamos en la base de datos de fiat*/
			if (answer.equals("si")) {
				if(tipo.equals("cripto")) {
					
					ActivoCriptoDAOJDBC acd = FactoryDAO.getActivoCriptoDAO();/*tengo instancia del activoCriptoDAO*/
					acd.generarActivoCripto(cantidad, nomenclatura);/*genero el activo y lo guardo en la base de datos en la tabla de ActivoCripto*/
					System.out.println("se guardo el activoCripto con exito en la base de datos");
				}
				else {
					ActivoFiatDAOJDBC  afd = FactoryDAO.getActivoFiatDAO();/*tengo instancia del activoFiatDAO*/
					afd.generarActivoFiat(cantidad, nomenclatura);/*genero el activo y lo guardo en la base de datos en la tabla de ActivoFiat*/
				    System.out.println("se guardo el activoaFiat con exito en la base de datos");
				}
			}
			else System.out.println("Operación Cancelada");    
		} 
		else {
		    System.out.println("La nomenclatura no está en la lista, por lo que se cancela la operacion");
		}
		
		
    }
/*---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    private static void listarMisActivos() {
    	Scanner scanner = new Scanner(System.in);
        String criterio = "";

        // Pide al usuario que elija un criterio de ordenación válido
        while (!(criterio.equalsIgnoreCase("nomenclatura") || criterio.equalsIgnoreCase("cantidad"))) {
            System.out.println("Seleccione el criterio para listar los activos:");
            System.out.println("1. nomenclatura");
            System.out.println("2. cantidad");
            String opcion = scanner.nextLine();
            
            ActivoCriptoDAOJDBC acdao = FactoryDAO.getActivoCriptoDAO();
            List<Activo> lactivoCripto = acdao.listarActivoCripto();/*tengo la lista con todos los activos cripto de la base de datos*/
            
            ActivoFiatDAOJDBC afdao = FactoryDAO.getActivoFiatDAO();
            List<Activo> lactivoFiat =  afdao.listarActivoFiat();/*tengo la lista con todos los activos fiat de la base de datos*/
            lactivoCripto.addAll(lactivoFiat);/*meto todo en la lista de activoCripto*/
            // Asigna el criterio según la opción seleccionada
            if (opcion.equals("1")) {
                criterio = "nomenclatura";
                /*tengo que ordenar la lista por nomenclatura*/
                lactivoCripto.sort(new ComparadorNomenclaturaActivo());
            } else if (opcion.equals("2")) {/*TE PIDE QUE ORDENES DE MANERA DESCENDENTE*/
                criterio = "cantidad";
                /*tengo que ordenar la lista por cantidad*/
                lactivoCripto.sort(new ComparadorCantidadActivo());
            } else {
                System.out.println("Opción no válida. Por favor, elija '1' o '2'.");
            }
            for(Activo activo : lactivoCripto) {
            	System.out.println("nomenclatura: "+activo.getNomenclatura()+", cantidad: "+activo.getCantidad());
            }
            
        }
    	
    }
/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    private static void comprarCriptomoneda() { 
    	Scanner sc = new Scanner(System.in); 
		MonedaDAOJDBC monedadb = FactoryDAO.getMonedaDAO();
		ActivoCriptoDAOJDBC activoCriptoDAO = FactoryDAO.getActivoCriptoDAO();
		ActivoFiatDAOJDBC activoFiatDAO = FactoryDAO.getActivoFiatDAO();
		TransaccionDAOJDBC trans = FactoryDAO.getTransaccionDAO();
		System.out.println("Ingrese la nomenclatura de la criptomoneda a comprar: ");
		String nomencripto = sc.nextLine();
		System.out.println("ingrese la cantidad de la moneda que queres comprar");
		double cantcripto = sc.nextDouble();
		Moneda cripto = new Moneda();
		
		/*vemos si existe o no la moneda*/
		List<Moneda> lista = monedadb.listarMonedas();
        boolean existe = false;	
        for (Moneda moneda : lista) {
		    if (moneda.getNomenclatura().equals(nomencripto) && moneda.getStock() >= cantcripto ) {
		        existe = true;
		        cripto = moneda;/*la uso para ver el dinero a pagar usando el metodo convertir*/
		        break;  
		    }
		}
		
		if(existe) {/*si existe la moneda que queremos comprar y tiene stock suficiente para la compra que queremos hacer*/
			
			/*ingreso de la moneda fiat, */
			System.out.println("datos de la moneda fiat con la cual abonara: ");
			System.out.println("nomenclatura: ");
			String nomenFiat = sc.nextLine();
			
			/*chequeamos si tenemos el activo con el que se quiere pagar*/
			List<Activo> listaActivoFiat = FactoryDAO.getActivoFiatDAO().listarActivoFiat();/*la lista de los activosFiat que tengo*/
			double cantFiatDisp = 0;/*cantidad de activoFiat con la cual voy a pagar*/
			existe = false;
		    for (Activo activo : listaActivoFiat) {
		    	if (activo.getNomenclatura().equals(nomenFiat)) {
		    		existe = true;
		    		cantFiatDisp = activo.getCantidad();
				    break;  
				}
		    }
		    double dineroaPagar = convertir(cantcripto, cripto, nomenFiat);/*dinero en la moneda Fiat que tengo que pagar*/
		    if(existe &&  cantFiatDisp >= dineroaPagar) {/*si existe el activo con el que vamos a pagar, tenemos suficiente plata cantFiat para pagar*/
		    	
		    	/*confirmacion de que se quiere realizar la compra de la criptomoneda*/
		    	System.out.println("quiere confirmar la compra de "+cantcripto+" "+nomencripto+" ?");
		    	String answer = sc.nextLine();
		    	while (answer.equals("si") && answer.equals("no")) {
		    		System.out.println ("Responda Si o No");
		    		answer = sc.nextLine().toLowerCase();
		    	}
		    	
		    	if(answer.equals("si")) {/*si se confirmo la compra*/
		    		/*tengo que actualizar los activos y guardar la transaccion hay dos formas, que ya exista el activo o que no exista el activo en la base de datos*/
		    		
		    		/*chequeo si ya existe el activo que quiero comprar o no existe*/
		    		List<Activo> listaActivoCripto = FactoryDAO.getActivoCriptoDAO().listarActivoCripto();
					existe = false;
				    for (Activo activo : listaActivoCripto) {
				    	if (activo.getNomenclatura().equals(nomenFiat)) {
				    		existe = true;
						    break;  
						}
				    }
				    if(existe) {
				    	/*solo tengo que sumarle al activo cripto y restarle al activo fiat*/
				    	activoCriptoDAO.sumarActivoCripto(cantcripto,nomencripto );
				    	activoFiatDAO.sumarActivoFiat(-1*dineroaPagar,nomenFiat );
				    }
				    else {
				    	/*tengo que crear el activoCripto, guardarlo en la base de datos y restarle al activo fiat*/
				    	activoFiatDAO.sumarActivoFiat(-1*dineroaPagar, nomenFiat);
				    	activoCriptoDAO.generarActivoCripto(cantcripto, nomencripto);
				    	/*tengo que aniadir la descripcion a transaccion y agregar la transaccion a la base de datos*/
				    }
				    /*tengo que aniadir la descripcion a transaccion y agregar la transaccion a la base de datos*/
			    	String resumen ="la transaccion que se realizo fue una compra de la criptomoneda "+nomencripto+", comprandose una cantidad de "+cantcripto+" lo que equivale a "+dineroaPagar+" "+nomenFiat;
			    	
			        // Obtener la fecha y hora actual
			        LocalDateTime fechaHoraActual = LocalDateTime.now();
			        // Formatear la fecha y hora
			        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			        String fechaHoraFormateada = fechaHoraActual.format(formato);

			      
			    	String fyh =fechaHoraFormateada;
			    	trans.añadirDesc(resumen, fyh);/*aniade la descripcion a la base de datos*/
		    		
		    	}
		    	else System.out.println("la compra no fue confirmada ");
		    	
		    	
		    }
		    else System.out.println("no se puede realizar la compra, no hay fondos");
		      
		}
		else System.out.println("error: no existe la criptomoneda que queres comprar");
    }
    
    /*tengo que entregar la cantidad de moneda fiat que se tiene que pagar para obtener cantCripto*/
    private static double convertir(double cantCripto, Moneda cripto, String nomenFiat) {
    	MonedaDAOJDBC monedadb = FactoryDAO.getMonedaDAO(); 
    	List<Moneda> lista = monedadb.listarMonedas();
        double valorFD = 0; 
        for (Moneda moneda : lista) {
		    if (moneda.getNomenclatura().equals(nomenFiat)) {
		        valorFD = moneda.getValorEnDolar();/*valor en dolar de ma moneda*/
		        break;  
		    }
		}
        /*se que si o si va a existir la moneda fiat porque ya lo chequeo antes*/
        
    	
    	return (cantCripto * cripto.getValorEnDolar())/valorFD;
    }
/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    private static void realizarSwap() {
    	
		Scanner sc = new Scanner(System.in); 
		MonedaDAOJDBC monedadb = FactoryDAO.getMonedaDAO();
		ActivoCriptoDAOJDBC activodb = FactoryDAO.getActivoCriptoDAO();
		TransaccionDAOJDBC trandb = FactoryDAO.getTransaccionDAO();
		
		/*ingresamos las nomenclaturas de las dos criptomonedas*/
		System.out.println("Ingrese la nomenclatura de la criptomoneda que quiere adquirir: ");
		String nomencriptoadquirir = sc.nextLine();
		
		System.out.println("Ingrese la nomenclatura de la criptomoneda con la que quiere pagar");
		String nomencriptopagar = sc.nextLine();
		
		double mdisponible = 0;
		double cantm = 0;/*guardo la cantidad de la moneda que tengo como activo de la moneda que quiero adquirir mas aun, lo hago para el chequeo de stock*/
		/*chequeo si existen los dos activos*/
		List<Activo> lista = activodb.listarActivoCripto();
		boolean existempagar = false, existemadquirir = false;
		for(Activo activo : lista) {
			if(activo.getNomenclatura().equals(nomencriptoadquirir))
				existemadquirir = true;
			    cantm = activo.getCantidad();
			if(activo.getNomenclatura().equals(nomencriptopagar)) {
				existempagar = true;
				mdisponible = activo.getCantidad();/*aca tengo la cantidad del activo con el que voy a pagar que tengo disponible*/
			}
			   
		}
		/*si tenemos las dos criptomonedas como activos*/
		if(existemadquirir && existempagar) {
			System.out.println("ingrese la cantidad de cripto a adquirir que queres");
			double cantmadquirir = sc.nextDouble();
			Moneda monedadquirir = new Moneda();;
			/*consigo stock de la moneda que voy a adquirir */
			List<Moneda> lista2 = monedadb.listarMonedas();
	        double stockadquirir = 0;  
			for (Moneda moneda : lista2) {
			    if (moneda.getNomenclatura().equals(nomencriptoadquirir)) {
			        stockadquirir = moneda.getStock();
			        monedadquirir = moneda;
			        break;  
			    }
			}
			
			/*si el stock de la moneda que quiero adquirir es suficiente*/
			if(stockadquirir >= (cantm + cantmadquirir)) {
				
				/*tengo el monto a pagar en la moneda que se quiere pagar*/
				double montopagar = convertir(cantmadquirir, monedadquirir,nomencriptopagar );
				
				
				/*si tengo cripto para pagar*/
				if(montopagar <= mdisponible) {
					/*tengo que sumar a una cripto y restar a la otra cripto*/
					activodb.sumarActivoCripto(cantmadquirir, nomencriptoadquirir);
					activodb.sumarActivoCripto(montopagar*-1, nomencriptopagar);

					/*tengo que subir a la base de datos las transacciones realizadas*/
			    	String resumen ="la transaccion que se realizo fue un swap de "+cantmadquirir+" "+nomencriptoadquirir+" por "+montopagar+" "+nomencriptopagar;
			    	
			        // Obtener la fecha y hora actual
			        LocalDateTime fechaHoraActual = LocalDateTime.now();
			        // Formatear la fecha y hora
			        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			        String fechaHoraFormateada = fechaHoraActual.format(formato);

			      
			    	String fyh =fechaHoraFormateada;
			    	trandb.añadirDesc(resumen, fyh);/*aniade la descripcion a la base de datos*/
					
				}
				else System.out.println("no tienes cripto suficiente para swapear");
				
			}
			else System.out.println("no es suficiente el stock de la moneda que quiero adquirir");
			
		}
		else System.out.println("no tenes activos de las dos criptos que queres swapear");
		
    }
/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/	
	
}