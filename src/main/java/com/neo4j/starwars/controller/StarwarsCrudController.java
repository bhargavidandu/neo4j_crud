package com.neo4j.starwars.controller;

import com.neo4j.starwars.dto.CharacterDto;
import com.neo4j.starwars.dto.CharacterUpdateDto;
import com.neo4j.starwars.entity.Character;
import com.neo4j.starwars.service.StarwarCrudService;
import java.util.Collection;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Log4j2
@RestController
@RequestMapping("/chars")
public class StarwarsCrudController {

  @Autowired
  private StarwarCrudService starwarCrudService;

  /*
  1.	Insert the new character information.
  */
  @PostMapping()
  public ResponseEntity insertStarwarCharacter(@RequestBody List<CharacterDto> characterDtoList) {
    try{
      if(!CollectionUtils.isEmpty(characterDtoList)){
        log.info("Inserting Starwar Character  : "+characterDtoList.size());
        try{
          starwarCrudService.insertCharacter(characterDtoList);
          return new ResponseEntity("Starwar character inserted successfully",HttpStatus.OK);
        }catch(Exception exc){
          return new ResponseEntity<>("Insertion of character failed ..... due to "+exc.getMessage(),HttpStatus.CONFLICT);
        }
      }else
        return new ResponseEntity<>("Empty character cannot be inserted...",HttpStatus.BAD_REQUEST);
    }catch(Exception exc){
      return new ResponseEntity<>(exc.getMessage(),HttpStatus.CONFLICT);
    }
  }

  /*
  2.	Update the character information using name. (By update only name, hair_colors, height and birth_year)
   */
  @PatchMapping("/{name}")
  public ResponseEntity updateByName(@PathVariable(required = true) String name,@RequestBody(required = true)
  CharacterUpdateDto characterUpdateDto) {
    log.info("Updating hair_colors,height and birth_year : "+name);
    try{
      if(!ObjectUtils.isEmpty(characterUpdateDto) && StringUtils.isNotBlank(name)){
        Character movie = starwarCrudService.isCharacterAvailable(name);
        if(!ObjectUtils.isEmpty(movie) ){
          starwarCrudService.updateByName(characterUpdateDto,name);
        return new ResponseEntity<>("Starwar details updated successfully...",HttpStatus.OK);
        }else{
          return new ResponseEntity<>("Character doesnot exist in database to update...with name::     "+name,HttpStatus.OK);
        }
      }
      else{
        return new ResponseEntity<>("Error occurred while updating...",HttpStatus.CONFLICT);
      }
    }
    catch (Exception exc){
      return new ResponseEntity<>(exc.getMessage(),HttpStatus.CONFLICT);
    }
  }

  /*
  3.Delete the character information using name.
  */
  @DeleteMapping("/{name}")
  public ResponseEntity deleteCharacterByName(@PathVariable(required = true) String name) {
    log.info("Deleting Characters by name : "+name);
    try{
      starwarCrudService.deleteByName(name);
      return new ResponseEntity<>("Starwars Character Deleted Successfully",HttpStatus.OK);
    }catch (Exception exc){
      return new ResponseEntity(exc.getMessage(), HttpStatus.CONFLICT);
    }
  }

  /*
  4.	Retrieve all the characters in database.
  */
  @GetMapping
  public ResponseEntity getAllCharacters() {
    log.info("Retrieve all Characters of Starwars.....");
    try{
      Collection<Character> starwarCharacters = starwarCrudService.retrieveAllCharacters();
      if(!CollectionUtils.isEmpty(starwarCharacters))
        return new ResponseEntity(starwarCharacters, HttpStatus.OK);
      else
        return new ResponseEntity("No StarwarCharacters found in database", HttpStatus.CONFLICT);
    }catch (Exception exc){
      return new ResponseEntity(exc.getMessage(), HttpStatus.CONFLICT);
    }
  }
  /*
  5.Display the characters’ details using name.
   */
  @GetMapping("/{name}")
  public ResponseEntity getCharacterByName(@PathVariable(required = true) String name) {
    log.info("Retrieve Starwars character by name .....");
    try{
      Character movieResponse = starwarCrudService.getCharacterByName(name);
      if(movieResponse!= null )
      return new ResponseEntity(movieResponse, HttpStatus.OK);
      else
        return new ResponseEntity<>("No Starwar Characters Node found with this name..."+name,HttpStatus.CONFLICT);
    }catch (Exception exc){
      return new ResponseEntity(exc.getMessage(), HttpStatus.CONFLICT);
    }
  }
}
