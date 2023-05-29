package com.neo4j.starwars.service;

import com.neo4j.starwars.dto.CharacterDto;
import com.neo4j.starwars.dto.CharacterUpdateDto;
import com.neo4j.starwars.entity.Character;
import com.neo4j.starwars.repository.CharacterRepository;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class StarwarCrudService {

  @Autowired
  private CharacterRepository characterRepository;

  public Collection<Character> retrieveAllCharacters(){
    return characterRepository.retrieveAllCharacters();
  }

  public Character getCharacterByName(String name){
    return characterRepository.findByName(name);
  }
  public  List<String> insertCharacter(List<CharacterDto> characterDtoList){
    characterDtoList.forEach(characterDto -> {
      Long nextId = characterRepository.getMaxId()+1;
       characterRepository.insertCharacter(characterDto.getName(),characterDto.getHeight(),
          characterDto.getMass(),characterDto.getHair_color(),characterDto.getSkin_color(),characterDto.getEye_color(),
          characterDto.getBirth_year(),
         characterDto.getGender(),characterDto.getHomeworld(),characterDto.getSpecies());
    });
    return null;
  }
  public void deleteByName(String title){
     characterRepository.deleteByName(title);
  }
  public void updateByName(CharacterUpdateDto characterUpdateDto,String name){
    characterRepository.updateCharacterDetails(name,characterUpdateDto.getHair_color(),
        characterUpdateDto.getHeight(),characterUpdateDto.getBirth_year());
  }

  public Character isCharacterAvailable(String name){
   return characterRepository.isCharacterAvailable(name);
  }

}
