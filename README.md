# ApiPokemon

### Contenido
- [Description](#description) 
- [Dependencies](#dependencies) 
- [Pre-requisites to run the application](#prerequisitestoruntheapplication) 
- [Built with](#builtwith) 
- [Exposed endpoints](#exposedendpoints) 

----------
<div id='description'/>

### Description
The project allow:
List pokemons and their description with pagination.

----------
<div id='dependencies'/>

### Dependencies
- The API "https://pokeapi.co/" is called to obtain the data.

----------
<div id='prerequisitestoruntheapplication'/>

### Pre-requisites to run the application
- Have Java 8 version installed
- Download the sources from GitHub
- Download dependencies (Maven)
- Run the project

----------
<div id='builtwith'/>

### Built with
* ***Java 1.8 (spring boot 2.0.5.RELEASE)*** - Base language of the entire application
	* ***RestTemplate***: Spring class for HTTP requests (Communication with external API's)
* ***Maven*** - Dependency manager

----------
<div id='exposedendpoints'/>

### Public deploy with heroku
Commands with sdk of Heroku:

git init

git add .

git commit -m "deploy"

heroku apps:create unique-api-name

git push heroku master

### Exposed endpoints

- List items (local): (GET) http://localhost:8080/v1/pokemons?limit=5&offset=1
- List items (public): (GET) https://api-pokemon-1.herokuapp.com/v1/pokemons?limit=5&offset=1

