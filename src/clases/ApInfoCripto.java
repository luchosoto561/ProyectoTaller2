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
		consultarPrecioCripto();
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
	public void consultarPrecioCripto() {
		
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
	

}
