package cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.services;

import java.util.Collections;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.domain.Partida;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.domain.Usuari;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.dto.PartidaDTO;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.dto.UsuariDTO;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.repository.PartidaRepository;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.repository.UsuariRepository;

@Service
public class JocServiceImp implements JocService{

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UsuariRepository usuariRepository;
	
	@Autowired
	PartidaRepository partidaRepository;
	
	//Retorna todos los usuariosDTO
	@Override
	public List<UsuariDTO> llistaUsuaris() {
		return usuariRepository.findAll()
				.stream()
        		.map(this::convertEntityToDto)
        		.collect(Collectors.toList());
	}

	//Retorna un usuarioDTO según su id
	@Override
	public UsuariDTO getUsuariById(String id) {
		Usuari usuari = usuariRepository.findById(id).orElseThrow();
		UsuariDTO usuariDTO = modelMapper.map(usuari, UsuariDTO.class);

		return usuariDTO;
	}

	//Guarda un usuario(nombre)
	@Override
	public void saveUsuariDTO(UsuariDTO usuariDTO) {
		Usuari usuari = new Usuari();
		usuari.setNom(usuariDTO.getNom());
		usuariRepository.save(usuari);
		
	}

	//Retorna una lista de todos los usuarios con el ranking actualizado. Ranking sólo en DTO
	@Override
	public List<UsuariDTO> usuarisAmbRanking() {
		List<UsuariDTO> usuaris = usuariRepository.findAll()
				.stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
		
		for(UsuariDTO u:usuaris) {
			u.rankingJugador();
		}
		return usuaris;
	}

	//Actualiza el usuario(nombre) y retorna usuariDTO
	@Override
	public UsuariDTO updateUsuariDTO(UsuariDTO usuariDTO, String id) {
		Usuari usuari = usuariRepository.findById(id).orElseThrow();
		usuari.setNom(usuariDTO.getNom());
		
		Usuari usuariActualitzat = usuariRepository.save(usuari);
		
		return convertEntityToDto(usuariActualitzat);
	}
	
	//Recoge todos los usuarios, los ordena según su ranking y retorna el último usuario de la lista
	@Override
	public UsuariDTO getLoser() {
		List<UsuariDTO> usuaris = usuariRepository.findAll()
				.stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
				
		for(UsuariDTO u:usuaris) {
			u.rankingJugador();
		}
		Collections.sort(usuaris);
		UsuariDTO usuariDTO = usuaris.get(usuaris.size()-1);
		return usuariDTO;
	}

	//Recoge todos los usuarios, los ordena según su ranking y retorna el primer usuario de la lista
	@Override
	public UsuariDTO getWinner() {
		List<UsuariDTO> usuaris = usuariRepository.findAll()
				.stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
				
		for(UsuariDTO u:usuaris) {
			u.rankingJugador();
		}
		Collections.sort(usuaris);
		UsuariDTO usuariDTO = usuaris.get(0);
		return usuariDTO;
	}
	
	//Guarda una partida
	@Override
	public void savePartidaDTO(PartidaDTO partidaDTO, String id) {
		
		Usuari usuari = usuariRepository.findById(id).orElseThrow();
		
		int tirada1 = partidaDTO.numAleatori();
		int tirada2 = partidaDTO.numAleatori();
		
		partidaDTO.setTirada1(tirada1);
		partidaDTO.setTirada2(tirada2);
		partidaDTO.setPuntuacio(tirada1+tirada2);
		
		Partida partida = new Partida();
		
		partida.setTirada1(partidaDTO.getTirada1());
		partida.setTirada2(partidaDTO.getTirada2());
		partida.setPuntuacio(partidaDTO.getPuntuacio());
		
		usuari.afegirPartida(partida);
		usuariRepository.save(usuari);
	}
	
	//Retorna las partidas de un usuario según su id
	@Override
	public List<PartidaDTO> llistaPartidesByUsuari(String id) {
		Usuari usuari = usuariRepository.findById(id).orElseThrow();
		UsuariDTO usuariDTO = modelMapper.map(usuari, UsuariDTO.class);
		List<PartidaDTO> partides = usuariDTO.getPartidas();
		
		return partides;
	}
	
	//Elimina las partidas de un usuario según su id
	@Override
	public void deletePartidesByUsuari(String id) {
		Usuari usuari = usuariRepository.findById(id).orElseThrow();
		usuari.eliminarTodasPartidas();
		usuariRepository.save(usuari);
		
	}
		
	//De Entity a DTO
	private UsuariDTO convertEntityToDto(Usuari usuari) {
		UsuariDTO usuariDTO = modelMapper.map(usuari, UsuariDTO.class);
		return usuariDTO;
	}







}
