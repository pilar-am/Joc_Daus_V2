package cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.domain;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "usuaris")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuari {


	@Id
	private String id;
	
	private String nom;
	
	private LocalDate dataRegistre = LocalDate.now();
	
	@Field(write = Field.Write.ALWAYS)
	private ArrayList<Partida> partidas = new ArrayList<>();

	//Añade una partida al array partidas
	public void afegirPartida(Partida partida) {
		partidas.add(partida);
	}
	
	//Elimina todos los datos del array partidas
	public void eliminarTodasPartidas() {
		partidas.clear();
	}
	
	
	
}
