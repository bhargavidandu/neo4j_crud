NEO4JDBCRUD
An application illustrating crud operations of Neo4J DB

Queries Used
For Insertion @Query("MERGE (character:Characters {name: $name,height:$height,mass:$mass,"
      +"hair_color:$hair_color,skin_color:$skin_color,eye_color:$eye_color,birth_year:$birth_year,gender:$gender,homeworld:$homeworld,species:$species})"
      +" MERGE(planets:Planets {name : $homeworld})"
      +" MERGE(species:Species {name : $species})"
      + "MERGE (character)-[:FROM]->(planets)"
      + "MERGE (character)-[:IDENTIFIED_AS]->(species)"
      + "RETURN character.name")

For Finding character by name @Query("MATCH (character:Characters) "
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
For Deleting character node @Query("MATCH (character:Characters) WHERE character.name=$name   DETACH DELETE character")
For updating character node @Query("MATCH (character:Characters) WHERE character.name=$name   set character.hair_color = $hair_color,"
         +"character.birth_year=$birth_year,character.height = $height")

How to run the application
1)Clone this code in to your local folder git clone https://github.com/bhargavidandu/neo4j_crud.git

2)Open Git bash at NEO4J_CRUD which points to main branch Execute mvn clean install Now Navigate to NEO4J_CRUD\target

3)Run the below command java -jar neoCrud-0.0.1-SNAPSHOT.jar

4)Application starts running at port :9090

Rest End points
3.Display the character and show’s detail using name. GET : http://localhost:9090/chars/{name}

Retrieve all the characters and shows in database. GET :http://localhost:9090/chars

Insert the new character and show.--multiple data documents allowed POST:http://localhost:9090/chars

Sample data:

[
    {
        "name":"bhargavi123",
        "height":"456",
        "hair_color":"brown",
        "birth_year":"24BBY",
        "gender":"female",
        "homeworld":"Naboo",
        "species":"Ewok",
        "eye_color":"brown",
        "mass":"250",
        "skin_color":"black"
    }
]
Update the character information using name. (By update only name, hair_colors, height and birth_year). PATCH:http://localhost:9090/chars/{name} Sample Data:

{
        "name":"bhargavi123",
        "height":"666",
        "hair_color":"brown",
        "birth_year":"24CCY"
    }
Delete the character and show information using name. DELETE:http://localhost:9090/chars/{name}