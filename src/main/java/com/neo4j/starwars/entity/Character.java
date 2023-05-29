package com.neo4j.starwars.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;


@NodeEntity(label = "Characters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character {
  @Id
  @Property(name = "name")
  private String name;

  @Property(name = "height")
  private String height;

  @Property(name = "mass")
  private String mass;

  @Property(name = "hair_color")
  private String hair_color;

  @Property(name = "skin_color")
  private String skin_color;

  @Property(name = "eye_color")
  private String eye_color;

  @Property(name = "birth_year")
  private String birth_year;

  @Property(name = "gender")
  private String gender;

  @Property(name = "homeworld")
  private String homeworld;

  @Property(name = "species")
  private String species;

 /* @Relationship(type="IDENTIFIED_AS", direction = Relationship.OUTGOING)
  private Species sp;

  @Relationship(type="FROM", direction = Relationship.OUTGOING)
  private Planet planet ;*/

}
