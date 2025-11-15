package domain;
import java.time.LocalDate;
import java.util.Map;
import java.util.List;
import java.util.HashMap;


public abstract class Usuario {
    private int id;
	private String nombre;
	private String apellido;
	private String usuario; // El de la parte del login
	private String contraseyna;
	
	public Usuario(int id, String nombre, String apellido, String usuario, String contraseyna) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.contraseyna = contraseyna;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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
