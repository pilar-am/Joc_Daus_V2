package cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.services;

import java.util.List;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.dto.PartidaDTO;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.dto.UsuariDTO;

public interface JocService {
	List<UsuariDTO> llistaUsuaris();
	UsuariDTO getUsuariById(String id);
	void saveUsuariDTO(UsuariDTO usuariDTO);
	List<UsuariDTO> usuarisAmbRanking();
	UsuariDTO updateUsuariDTO(UsuariDTO usuariDTO, String id);
	UsuariDTO getLoser();
	UsuariDTO getWinner();
	public void savePartidaDTO(PartidaDTO partidaDTO, String id);
	List<PartidaDTO> llistaPartidesByUsuari(String id); 
	void deletePartidesByUsuari(String id);
	
}
