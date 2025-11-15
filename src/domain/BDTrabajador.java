package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BDTrabajador extends Usuario {
	private HashSet<Tarea> tareasAsignadas;
	private LocalDateTime entrada; //formateador DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private HashMap<LocalDate, ArrayList<LocalDateTime>> registrosFichaje;
	
	
	public BDTrabajador(int id, String nombre, String apellido, String usuario, String contraseyna) {
		super(id, nombre, apellido, usuario, contraseyna);
		this.tareasAsignadas = new HashSet<Tarea>();
		this.entrada = null;
		this.registrosFichaje = new HashMap<LocalDate, ArrayList<LocalDateTime>>();
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


	public HashMap<LocalDate, ArrayList<LocalDateTime>> getRegistrosFichaje() {
		return registrosFichaje;
	}


	public void setRegistrosFichaje(HashMap<LocalDate, ArrayList<LocalDateTime>> registrosFichaje) {
		this.registrosFichaje = registrosFichaje;
	}
	
	public void metodoFichar() {
		LocalDateTime entrada = this.getEntrada();
		LocalDateTime salida = LocalDateTime.now();
		registrosFichaje.putIfAbsent(LocalDate.now(), new ArrayList<LocalDateTime>());
		registrosFichaje.get(LocalDate.now()).add(entrada);
		registrosFichaje.get(LocalDate.now()).add(salida);
		this.setEntrada(null);

	}
	
	
	
	
	


}


