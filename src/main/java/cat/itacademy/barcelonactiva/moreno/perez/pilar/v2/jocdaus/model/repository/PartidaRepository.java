package cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.domain.Partida;

@Repository
public interface PartidaRepository extends MongoRepository<Partida, String>{

}
