package domain;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Trabajador extends Usuario {
	
	private HashSet<Tarea> tareasAsignadas;
	private LocalDateTime entrada;
	private LocalDateTime salida;
	





	public Trabajador(String nombre, String apellidos, String usuario, String contraseyna,
			Map<LocalDate, List<String>> registros, HashSet<Tarea> tareasAsignadas, LocalDateTime entrada,
			LocalDateTime salida) {
		super(nombre, apellidos, usuario, contraseyna, registros);
		this.tareasAsignadas = tareasAsignadas;
		this.entrada = entrada;
		this.salida = salida;
	}



	public Trabajador(String nombre, String apellidos, String usuario, String contraseyna,
			Map<LocalDate, List<String>> registros) {
		super(nombre, apellidos, usuario, contraseyna, registros);
		this.tareasAsignadas = new HashSet<Tarea>();
		this.entrada= null;
		this.salida = null;
	}



	public HashSet<Tarea> getTareasAsignadas() {
		return tareasAsignadas;
	}



	public void setTareasAsignadas(HashSet<Tarea> tareasAsignadas) {
		this.tareasAsignadas = tareasAsignadas;
	}



	public LocalDateTime getEntrada() {
		return entrada;
	}



	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}



	public LocalDateTime getSalida() {
		return salida;
	}



	public void setSalida(LocalDateTime salida) {
		this.salida = salida;
	}



s
	
	
	

}
