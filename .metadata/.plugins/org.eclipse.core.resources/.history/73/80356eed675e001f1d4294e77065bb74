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
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.Persona;
import model.ResultAuth;

@RestController
public class PersonasController {
	@Autowired
	RestTemplate template;
	
	String urlService="http://localhost:8000";
	String urlKeycloak="http://localhost:8080/realms/ContactosRealm/protocol/openid-connect/token";
	final String USERNAME="user1";
	final String PASSWORD="user1";
	final String CLIENT_ID="login";
	final String GRANT_TYPE="password";
	HttpHeaders headersService=new HttpHeaders();
	@PostConstruct()
	public void autenticar() {
		HttpHeaders headersKeycloak=new HttpHeaders();
		//cabecera con tipo de contenido del cuerpo
		headersKeycloak.add("Content-type", "application/x-www-form-urlencoded");
		//parametros que enviamos en el cuerpo de la llamada a Keycloak
		MultiValueMap<String,String> authData=new LinkedMultiValueMap<>();
		authData.add("client_id", CLIENT_ID);
		authData.add("username", USERNAME);
		authData.add("password", PASSWORD);
		authData.add("grant_type", GRANT_TYPE);
		ResponseEntity<ResultAuth> response=template.exchange(urlKeycloak, 
				HttpMethod.POST, 
				new HttpEntity<MultiValueMap<String,String>>(authData,headersKeycloak),
				ResultAuth.class);
		
		
		
		headersService.add("Authorization", "Bearer "+response.getBody().getAccess_token());
		System.out.println(response.getBody().getAccess_token());
	}
	
	@GetMapping(value="/personas/{nombre}/{email}/{edad}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> altaPersona(@PathVariable("nombre") String nombre,
			@PathVariable("email") String email, 
			@PathVariable("edad") int edad){
		Persona persona=new Persona(nombre,email,edad);
		template.exchange(urlService+"/contactos",HttpMethod.POST, new HttpEntity<Persona>(persona,headersService),String.class);
		Persona[] personas=template.exchange(urlService+"/contactos",HttpMethod.GET,new HttpEntity<>(headersService), Persona[].class).getBody();
		return Arrays.asList(personas);
	}
	@GetMapping(value="/personas/{edad1}/{edad2}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> buscarEdades(@PathVariable("edad1") int edad1, @PathVariable("edad2") int edad2){
		Persona[] personas=template.exchange(urlService+"/contactos",HttpMethod.GET,new HttpEntity<>(headersService), Persona[].class).getBody();
		return Arrays.stream(personas)
			.filter(p->p.getEdad()>=edad1&&p.getEdad()<=edad2)
			.collect(Collectors.toList());
	}
	
	
}
