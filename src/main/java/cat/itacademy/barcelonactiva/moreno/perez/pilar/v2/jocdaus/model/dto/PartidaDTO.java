package cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartidaDTO {
	private String id;
	
	private int tirada1;
	private int tirada2;
	private int puntuacio;
	
	public int numAleatori() {
		int numero = (int)(Math.random()*6+1);
		return numero;
	}
}
