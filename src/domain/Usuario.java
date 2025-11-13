package domain;

public abstract class Usuario {
	
	private String nombre;
	private String usuario; // El de la parte del login
	private String contraseyna;
	
	public Usuario(String nombre, String usuario, String contraseyna) {
		super();
		this.nombre = nombre;
		this.usuario = usuario;
		this.contraseyna = contraseyna;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseyna() {
		return contraseyna;
	}

	public void setContraseyna(String contraseyna) {
		this.contraseyna = contraseyna;
	}
	
	
	
	
	
	
}
