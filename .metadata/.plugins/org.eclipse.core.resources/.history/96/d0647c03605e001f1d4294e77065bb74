package controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
	
	String url="http://localhost:8080";
	String user="user1";
	String pwd="user1";
	String token;
	HttpHeaders headers=new HttpHeaders();
	@PostConstruct()
	public void autenticar() {
		token=template.postForObject(url+"/login?user="+user+"&pwd="+pwd, null, String.class);
		headers.add("Authorization", "Bearer "+token);
		System.out.println(token);
	}
	
	@GetMapping(value="/personas/{nombre}/{email}/{edad}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> altaPersona(@PathVariable("nombre") String nombre,
			@PathVariable("email") String email, 
			@PathVariable("edad") int edad){
		Persona persona=new Persona(nombre,email,edad);
		template.exchange(url+"/contactos",HttpMethod.POST, new HttpEntity<Persona>(persona,headers),String.class);
		Persona[] personas=template.exchange(url+"/contactos",HttpMethod.GET,new HttpEntity<>(headers), Persona[].class).getBody();
		return Arrays.asList(personas);
	}
	@GetMapping(value="/personas/{edad1}/{edad2}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> buscarEdades(@PathVariable("edad1") int edad1, @PathVariable("edad2") int edad2){
		Persona[] personas=template.exchange(url+"/contactos",HttpMethod.GET,new HttpEntity<>(headers), Persona[].class).getBody();
		return Arrays.stream(personas)
			.filter(p->p.getEdad()>=edad1&&p.getEdad()<=edad2)
			.collect(Collectors.toList());
	}
	
	
}
