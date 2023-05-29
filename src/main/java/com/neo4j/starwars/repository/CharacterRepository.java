package com.neo4j.starwars.repository;

import com.neo4j.starwars.entity.Character;
import java.util.Collection;
import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface CharacterRepository extends Neo4jRepository<Character, String> {
      @Query("MATCH (c:Characters)-[FROM]->(p:Planets),(c:Characters)-[IDENTIFIED_AS]->(sp:Species)"
      +"where c.homeworld=p.name and c.species =sp.name return c.name as name,"
      + "c.height as height ,"
      + "c.mass as mass ,"
      + "c.skin_color as skin_color,"
      + "c.hair_color as hair_color , "
      + "c.eye_color as eye_color,"
      + "c.birth_year as birth_year,"
      + "c.gender as gender ,"
      + "c.homeworld as homeworld ,"
      + "c.species  as species")
  Collection<Character> retrieveAllCharacters();

  @Query("MATCH (character:Characters) "
      + "WHERE character.name = $name "
      + "RETURN character.name as name ,"
      + "character.height as height ,"
      + "character.mass as mass ,"
      + "character.skin_color as skin_color,"
      + "character.hair_color as hair_color , "
      + "character.eye_color as eye_color,"
      + "character.birth_year as birth_year,"
      + "character.gender as gender ,"
      + "character.homeworld as homeworld ,"
      + "character.species  as species ")
  Character findByName(String name);

  @Query("MATCH (character:Characters) WHERE character.name=$name   DETACH DELETE character")
   void  deleteByName(String name);

  @Query("MATCH (character:Characters) WHERE character.name=$name   set character.hair_color = $hair_color,"
         +"character.birth_year=$birth_year,character.height = $height")
  public void updateCharacterDetails(String name,String hair_color,String height,String birth_year);

  @Query("MERGE (character:Characters {name: $name,height:$height,mass:$mass,"
      +"hair_color:$hair_color,skin_color:$skin_color,eye_color:$eye_color,birth_year:$birth_year,gender:$gender,homeworld:$homeworld,species:$species})"
      +" MERGE(planets:Planets {name : $homeworld})"
      +" MERGE(species:Species {name : $species})"
      + "MERGE (character)-[:FROM]->(planets)"
      + "MERGE (character)-[:IDENTIFIED_AS]->(species)"
      + "RETURN character.name")
     public void insertCharacter(String name,String height,String mass,String hair_color,
      String skin_color,String eye_color,String birth_year,
      String gender,String homeworld,String species);

  @Query("Match (character:Characters) return count(character.names)")
  public Long getMaxId();

  @Query("Match (character:Characters) where character.name=$name return character.name as name")
  public Character isCharacterAvailable(String name);

}
