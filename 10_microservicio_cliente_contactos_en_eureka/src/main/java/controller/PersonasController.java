package controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.Persona;

@RestController
public class PersonasController {
	@Autowired
	RestTemplate template;
	
	String url="http://servicio-contactos";
	@GetMapping(value="/personas/{nombre}/{email}/{edad}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> altaPersona(@PathVariable("nombre") String nombre,
			@PathVariable("email") String email, 
			@PathVariable("edad") int edad){
		Persona persona=new Persona(nombre,email,edad);
		template.postForLocation(url+"/contactos", persona);
		Persona[] personas=template.getForObject(url+"/contactos", Persona[].class);
		
		return Arrays.asList(personas);
	}
}
