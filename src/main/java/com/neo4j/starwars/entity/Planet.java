package com.neo4j.starwars.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.neo4j.core.schema.Id;


@NodeEntity(label = "Planets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Planet {


  @Id
  private Long id;

  @Relationship(type = "IN",direction = Relationship.INCOMING)
  @Property(name = "Genre")
  private String genre;
}
