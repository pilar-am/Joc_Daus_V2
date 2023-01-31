package cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.dto.PartidaDTO;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.dto.UsuariDTO;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.services.JocService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
public class JocController {

	@Autowired
	JocService jocService;
		
	//POST: Crea un jugador/a. 
	@PostMapping("/players")
	public ResponseEntity<?> crearUsuari(@RequestBody UsuariDTO usuariDTO){
		try {
			
			if(usuariDTO.getNom() == null) {
				usuariDTO.setNom("ANÒNIM");
			}
			boolean repetit = false;
			List<UsuariDTO> usuaris = jocService.llistaUsuaris();
			for(UsuariDTO u:usuaris) {
				if(u.getNom().equals(usuariDTO.getNom()) && !u.getNom().equals("ANÒNIM")) {
					repetit=true;
				}
			}
			if(repetit) {
				return new ResponseEntity<>("Nom duplicat", HttpStatus.INTERNAL_SERVER_ERROR);
			}else {
				jocService.saveUsuariDTO(usuariDTO);
				return new ResponseEntity<>(usuariDTO, HttpStatus.CREATED);
			}
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//GET: Retorna el llistat de tots  els jugadors/es del sistema amb el seu  percentatge mitjà d’èxits. 
	@GetMapping("/players")	
	public ResponseEntity<List<UsuariDTO>> getAllUsers(){
		try {
			//List<UsuariDTO> usuaris = jocService.usuarisAmbRanking();
			List<UsuariDTO> usuaris = jocService.usuarisAmbRanking();
			if(usuaris.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<List<UsuariDTO>>(usuaris, HttpStatus.OK);
			}
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	//PUT: Modifica el nom del jugador/a.
	@PutMapping("/players/{id}")
	public ResponseEntity<UsuariDTO> updateUsuari(@PathVariable("id") String id, @RequestBody UsuariDTO usuariDTO){
		try {
			UsuariDTO usuari =  jocService.updateUsuariDTO(usuariDTO, id);
			return new ResponseEntity<>(usuari, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
//	POST: Un jugador/a específic realitza una tirada dels daus.  
	@PostMapping("/players/{id}/games")
	public ResponseEntity<String> partidaJugador(@PathVariable String id, @RequestBody PartidaDTO partidaDTO){
				
		UsuariDTO usuari = jocService.getUsuariById(id);
		System.out.println(usuari);
		if(usuari == null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}else {
			jocService.savePartidaDTO(partidaDTO, id);
			//Partida partida = jocService.savePartidaDTO2(partidaDTO, id);
			//System.out.println("Usuari2 " + usuari2);
			//usuari.afegirPartida(partidaDTO);
			//partidaDTO.setUsuariDTO(usuari);
			
			return new ResponseEntity<>("Partida guardada",HttpStatus.CREATED);
		}
	}
	
	//GET: Retorna el llistat de jugades per un jugador/a.
	@GetMapping("/players/{id}/games")
	public ResponseEntity<List<PartidaDTO>> partidesJugador(@PathVariable String id){
		
		List<PartidaDTO> partides = jocService.llistaPartidesByUsuari(id);

		return new ResponseEntity<>(partides, HttpStatus.OK);
		
	}
	
	//DELETE: Elimina les tirades del jugador/a.
	@DeleteMapping("/players/{id}/games")
	public ResponseEntity<String> eliminarPartides(@PathVariable String id){
		try {
			jocService.deletePartidesByUsuari(id);
			
			return new ResponseEntity<>("Partides eliminades",HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>("L'usuair no existeix", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/players/{id}")
	public ResponseEntity<UsuariDTO> getUsuariById(@PathVariable("id") String id){
		try {
			UsuariDTO usuariDTO = jocService.getUsuariById(id);
			return new ResponseEntity<>(usuariDTO, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} 
	}
	
	List<String> rankingUsuaris = new ArrayList<>();
	
	//GET: Retorna el ranking mig de tots els jugadors/es del sistema
	@GetMapping("/players/ranking")
	public ResponseEntity<List<String>> retornaRanking(){
		try {
			List<UsuariDTO> usuaris = jocService.usuarisAmbRanking();
			if(usuaris.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				Collections.sort(usuaris);
				rankingUsuaris.clear();
				for(UsuariDTO u:usuaris) {
					rankingUsuaris.add(u.getNom() + ": " + u.getRanking() + "%");
				}
				return new ResponseEntity<>(rankingUsuaris, HttpStatus.OK);
			}
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	//GET: Retorna el jugador/a  amb pitjor percentatge d’èxit.  
	@GetMapping("/players/ranking/loser")
	public ResponseEntity<UsuariDTO> getLoser(){
		try {
			UsuariDTO usuariDTO = jocService.getLoser();
			return new ResponseEntity<>(usuariDTO, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} 
	}
	
	//GET: Retorna el jugador/a  amb millor percentatge d’èxit.
	@GetMapping("/players/ranking/winner")
	public ResponseEntity<UsuariDTO> getWinner(){
		try {
			UsuariDTO usuariDTO = jocService.getWinner();
			return new ResponseEntity<>(usuariDTO, HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} 
	}
	
	
}
