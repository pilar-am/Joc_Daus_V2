package cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.dto;

import java.time.LocalDate;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuariDTO implements Comparable<UsuariDTO>{
	
	private String id;
	private String nom;
	private int ranking;
	
	private LocalDate dataRegistre = LocalDate.now();
	
	private ArrayList<PartidaDTO> partidas = new ArrayList<>();
	
	//Recoge las partidas ganadas, las divide por las totales y las multiplica por 100
	public void rankingJugador() {
		int numPartides = this.partidas.size();
		int numPartidesGuanyades=0;
		
		if(numPartides>0) {
			for(PartidaDTO p: this.partidas) {
				if(p.getPuntuacio() == 7) {
					numPartidesGuanyades++;
				}
			}
			if(numPartidesGuanyades > 0) {
				this.ranking =((numPartidesGuanyades)*100/numPartides);
			}else {
				this.ranking=0;
			}
			
		}else {
			this.ranking=0;
		}
		
	}
	
	//Compara usuarios según el ranking, para poder ordenarlos y después recoger el mejor y el peor
	@Override
	public int compareTo(UsuariDTO o) {
		if ( ranking > o.ranking) {
            return -1;
        }
        if (ranking < o.ranking) {
            return 1;
        }
        return 0;
	}
	
	public void afegirPartida(PartidaDTO partidaDTO) {
		partidas.add(partidaDTO);
	}
}
