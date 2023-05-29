package com.neo4j.starwars.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.neo4j.core.schema.Id;


@NodeEntity(label = "Species")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Species {


  @Id
  private Long id;

  @Property(name = "classification")
  private String classification;
}
