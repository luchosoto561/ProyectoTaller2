package clases;

public class User {
	private int id;
	private int idPersona;
	private String email;
	private String password;
	private boolean aceptaTerminos;
	
	
	
	public User(int id, int idPersona, String email, String password, boolean aceptaTerminos) {
		super();
		this.id = id;
		this.idPersona = idPersona;
		this.email = email;
		this.password = password;
		this.aceptaTerminos = aceptaTerminos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAceptaTerminos() {
		return aceptaTerminos;
	}
	public void setAceptaTerminos(boolean aceptaTerminos) {
		this.aceptaTerminos = aceptaTerminos;
	}
	
	

}
