package clases;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class ApInfoCripto {
	
	private static final String URL_API = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum,usd-coin,tether,dogecoin&vs_currencies=usd";
	private JSONObject json;
	
	public ApInfoCripto() {
		actualizarApiCriptos();
	}
	
	public JSONObject getJson() {
		return json;
	}
	
	public double getBTC() {
		return json.getJSONObject("bitcoin").getDouble("usd");
	}
	
	public double getETH() {
		return json.getJSONObject("ethereum").getDouble("usd");
	}
	
	public double getUSDC() {
		return json.getJSONObject("usd-coin").getDouble("usd");
	}
	
	public double getUSDT() {
		return json.getJSONObject("tether").getDouble("usd");
	}
	
	public double getDOGE() {
		return json.getJSONObject("dogecoin").getDouble("usd");
	}
	public void ConsultarPrecioCripto() {
		
		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest solicitud = HttpRequest.newBuilder().uri(URI.create(URL_API)).GET().build();
			try {
		           HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
		           
		           if (respuesta.statusCode() == 200) {
		        	   json = new JSONObject(respuesta.body());
		           } else {
		               System.out.println("Error: " + respuesta.statusCode());
		           }
		       } catch (IOException | InterruptedException e) {
		           e.printStackTrace();
		       }
	
	}
	/*private void parsearYMostrarPrecios(String cuerpoRespuesta) {
		 JSONObject json = new JSONObject(cuerpoRespuesta);
	     System.out.println("Precios de Criptomonedas (en USD):");
	     double precioBTC = json.getJSONObject("bitcoin").getDouble("usd");
	     System.out.println("BTC: $" + precioBTC);
	     double precioETH = json.getJSONObject("ethereum").getDouble("usd");
	     System.out.println("ETH: $" + precioETH);
	     double precioUSDC = json.getJSONObject("usd-coin").getDouble("usd");
	     System.out.println("USDC: $" + precioUSDC);
	     double precioUSDT = json.getJSONObject("tether").getDouble("usd");
	     System.out.println("USDT: $" + precioUSDT);
	     double precioDOGE = json.getJSONObject("dogecoin").getDouble("usd");
	     System.out.println("DOGE: $" + precioDOGE);
	}*/
	public void actualizarApiCriptos() {
		/**/
	}

}
