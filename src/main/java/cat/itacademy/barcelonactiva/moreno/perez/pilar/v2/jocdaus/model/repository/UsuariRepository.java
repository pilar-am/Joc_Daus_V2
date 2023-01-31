package cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.v2.jocdaus.model.domain.Usuari;

@Repository
public interface UsuariRepository extends MongoRepository<Usuari, String>{

}
