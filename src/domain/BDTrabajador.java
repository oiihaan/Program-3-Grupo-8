package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BDTrabajador extends Usuario {
	private HashSet<BDTarea> tareasAsignadas;
	private LocalDateTime entrada; //formateador DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private HashMap<LocalDate, ArrayList<LocalDateTime>> registrosFichaje;
	
	
	public BDTrabajador(int id, String nombre,  String contrasenya) {
		super(id, nombre, contrasenya);
		this.tareasAsignadas = new HashSet<BDTarea>();
		this.entrada = null;
		this.registrosFichaje = new HashMap<LocalDate, ArrayList<LocalDateTime>>();
	}
	
	public void metodoDesfichar() {
		LocalDateTime entrada = this.getEntrada();
		LocalDateTime salida = LocalDateTime.now();
		registrosFichaje.get(LocalDate.now()).add(salida);
		this.setEntrada(null);

	}
	public void metodoFichar() {
		LocalDateTime entrada = LocalDateTime.now();
		this.setEntrada(entrada);
		registrosFichaje.putIfAbsent(LocalDate.now(), new ArrayList<LocalDateTime>());
		registrosFichaje.get(LocalDate.now()).add(entrada);

		

	}


	public HashSet<BDTarea> getTareasAsignadas() {
		return tareasAsignadas;
	}


	public void setTareasAsignadas(HashSet<BDTarea> tareasAsignadas) {
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
	
}


