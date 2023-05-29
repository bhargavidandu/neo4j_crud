package com.neo4j.starwars.dto;

import lombok.Data;

@Data
public class CharacterUpdateDto {
  private String name;
  private String hair_color;
  private String height;
  private String birth_year;
}
