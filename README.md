# JOC DE DAUS VERSIÓ 2


### API on es gestiona un joc de daus, amb usuaris i partides dels usuaris amb base de dades no relacional(MongoDb)


http://localhost:8080/players  Un jugador es pot inscriure amb el seu nom o de forma anònima<br>
http://localhost:8080/players  Retorna la llista dels usuaris amb el ranking actualitzat<br>
http://localhost:8080/players/{id}/games  Un jugador/a específic realitza una tirada dels daus<br>
http://localhost:8080/players/{id}/games  Retorna el llistat de jugades per un jugador/a<br>
http://localhost:8080/players/{id}/games  Elimina les tirades del jugador/a<br>
http://localhost:8080/players/ranking     Retorna el ranking mig dels jugadors<br>
http://localhost:8080/players/ranking/loser Retorna el jugador/a amb pitjor percentatge d'èxit<br>
http://localhost:8080/players/ranking/winner Retorna el jugador/a  amb millor percentatge d’èxit<br>
http://localhost:8080/players/{id} Modifica el nom del jugador<br>
