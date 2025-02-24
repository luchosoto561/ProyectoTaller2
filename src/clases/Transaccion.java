package clases;

public class Transaccion {
	private int id;
	private String resumen;
	private String fechayHs;/*tengo que hacer una clase que lo represente*/
	private int idUsuario;
	
	public Transaccion() {
	}
	public Transaccion(int id, String resumen, String fechayHs, int idUsuario) {
		super();
		this.id = id;
		this.resumen = resumen;
		this.fechayHs = fechayHs;
		this.idUsuario = idUsuario;
	}
	
	public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	public String getFechayHs() {
		return fechayHs;
	}
	public void setFechayHs(String fechayHs) {
		this.fechayHs = fechayHs;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
