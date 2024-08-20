package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import inicio.PersonasFeign;
import model.Persona;

@RestController
public class PersonasController {
	@Autowired
	PersonasFeign personasFeign;
	
	@PostMapping(value="personas/{nombre}/{email}/{edad}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> altaPersonas(@PathVariable("nombre") String nombre, @PathVariable("email") String email, @PathVariable("edad") int edad){
		Persona persona=new Persona(nombre, edad, email);
		personasFeign.altaPersona(persona);
		return personasFeign.getPersonas();
	}
	
	@GetMapping(value="personas",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> recuperarPersonas(){
		return personasFeign.getPersonas();
	}
}
