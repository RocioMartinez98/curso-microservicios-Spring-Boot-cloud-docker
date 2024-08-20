package com.boot.ejemplo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController {
	 
	@GetMapping(value="saludo",produces = MediaType.TEXT_PLAIN_VALUE)
	public String saludo() {
		return "Microservicio Spring Boot";
	}
	
	@GetMapping(value="saludo/{name}", produces=MediaType.TEXT_PLAIN_VALUE)
	public String saludo(@PathVariable("name") String nombre) {
		return "Bienvenido a Spring Boot sr.a: " +nombre;
	}
	
	//localhost:4000/myservice/saludopersonal?name=Rocio&age=25
	@GetMapping(value="saludopersonal", produces=MediaType.TEXT_PLAIN_VALUE)
	public String saludo(@RequestParam("name") String nombre,@RequestParam("age") int edad) {
		return "Bienvenido "+nombre+", tienes "+edad+" años";
	}
}
