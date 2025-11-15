package domain;
import java.time.LocalDate;
import java.util.Map;
import java.util.List;
import java.util.HashMap;


public abstract class Usuario {
    private int id;
	private String nombre;
	//private String apellido;
	//private String usuario; // El de la parte del login
	private String contrasenya;
	

	public Usuario(int id, String nombre, String contraseyna) {
		super();
		this.id = id;
		this.nombre = nombre;
		//this.apellido = apellido;
		//this.usuario = usuario;
		this.contrasenya = contraseyna;
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


	public String getContraseyna() {
		return contrasenya;
	}

	public void setContraseyna(String contraseyna) {
		this.contrasenya = contraseyna;
	}
	
}
