package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BDTrabajador extends Usuario {
	private HashSet<BDTarea> tareasAsignadas;
	private LocalDateTime entrada; 				//formateador DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private HashMap<LocalDate, ArrayList<LocalDateTime>> registrosFichaje;

	
	public BDTrabajador(int id, String nombre, String contraseyna, HashSet<BDTarea> tareasAsignadas,
			LocalDateTime entrada, HashMap<LocalDate, ArrayList<LocalDateTime>> registrosFichaje) {
		super(id, nombre, contraseyna);
		this.tareasAsignadas = tareasAsignadas;
		this.entrada = entrada;
		this.registrosFichaje = registrosFichaje;
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
	
	// METODOS PARA LA JTABLE::
	//Guarda la hora en entrada, y también la mete en el mapa del día
	   public void registrarFichajeEntrada(LocalDateTime ahora) {
	        this.entrada = ahora;

	        if (registrosFichaje == null) {
	            registrosFichaje = new HashMap<>();
	        }

	        LocalDate hoy = ahora.toLocalDate();
	        registrosFichaje
	            .computeIfAbsent(hoy, d -> new ArrayList<>())
	            .add(ahora);
	    }
       
	// mete la salida en el mapa, y poneentrada = null
	   public void registrarFichajeSalida(LocalDateTime salida) {
	        if (registrosFichaje == null) {
	            registrosFichaje = new HashMap<>();
	        }

	        if (this.entrada == null) {
	            // por seguridad, no debería pasar si la lógica de botones está bien
	            return;
	        }

	        LocalDate hoy = salida.toLocalDate();
	        registrosFichaje
	            .computeIfAbsent(hoy, d -> new ArrayList<>())
	            .add(salida);

	        this.entrada = null;
	   }
	   //El equals para poder comparar en la ventana AsignarTareas
		    @Override
		    public boolean equals(Object o) {
		        if (this == o) return true;
		        if (!(o instanceof BDTrabajador)) return false;
		        BDTrabajador that = (BDTrabajador) o;
		        return this.getId() == that.getId();
		    }

		    @Override
		    public int hashCode() {
		        return Integer.hashCode(getId());
		    }
		


}


