package controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.Persona;
import service.AccesoService;

@RestController
public class PersonasController {

	@Autowired
	AccesoService service;
	
	@GetMapping(value="/personas/{nombre}/{email}/{edad}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> altaPersona(@PathVariable("nombre") String nombreC, @PathVariable("email") String emailC, @PathVariable("edad") int edadC){
		Persona persona = new Persona(nombreC,emailC,edadC);
		CompletableFuture<List<Persona>> resultado = service.llamadaServicio(persona);
		for(int i = 1; i<50; i++) {
			System.out.println("esperando ");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		try {
			return resultado.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
