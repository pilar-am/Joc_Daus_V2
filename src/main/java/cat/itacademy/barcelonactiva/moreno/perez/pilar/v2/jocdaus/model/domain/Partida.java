package cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Partida {
	
	@Id
	private String id;
	
	private int tirada1;
	private int tirada2;
	private int puntuacio;
	
}
