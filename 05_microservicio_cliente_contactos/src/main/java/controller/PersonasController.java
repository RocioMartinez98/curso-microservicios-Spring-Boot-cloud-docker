package controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
	
	String url= "http://localhost:8080";
	
	@GetMapping(value="/personas/{nombre}/{email}/{edad}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> altaPersona(@PathVariable("nombre") String nombreC, @PathVariable("email") String emailC, @PathVariable("edad") int edadC){
		Persona persona = new Persona(nombreC,emailC,edadC);
		template.postForLocation(url+ "/contactos", persona);
		Persona[] personas=template.getForObject(url+"/contactos", Persona[].class);
		return Arrays.asList(personas);
	}
	
	@GetMapping(value="/personas/{nombre}/{email}/{edad}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> buscarEdades(@PathVariable("edad1") int e1, @PathVariable("edad2") int e2){
		Persona[] personas=template.getForObject(url+"/contactos", Persona[].class);
		return Arrays.stream(personas)
				.filter(p->p.getEdad()>=e1&&p.getEdad()<=e2)
				.collect(Collectors.toList());
	}
	
}
