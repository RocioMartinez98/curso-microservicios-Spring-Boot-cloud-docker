package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cursos.model.Curso;

import jakarta.annotation.PostConstruct;


@RestController
public class CursosController {
	
	private List<Curso> cursos;
	
	@PostConstruct
	public void init() {
		cursos=new ArrayList<>();
		cursos.add(new Curso("Spring",20, "mañana"));
		cursos.add(new Curso("Spring boot",25, "mañana"));
		cursos.add(new Curso("Java EE",30, "tarde"));
		cursos.add(new Curso("Java Básico",50, "tarde"));
		cursos.add(new Curso("Angular",30, "mañana"));
		cursos.add(new Curso("Python",20, "tarde"));
		cursos.add(new Curso("HTML y css",25, "mañana"));
		cursos.add(new Curso("seguridad informática",40, "tarde"));
	}

	@GetMapping(value="curso",produces=MediaType.APPLICATION_XML_VALUE)
	public Curso getCurso() {
		return new Curso("Java", 100, "mañana");
	}
	
	@GetMapping(value="cursos",produces=MediaType.APPLICATION_XML_VALUE)
	public List<Curso> getCursos(){
		return cursos;
	}
	
	@GetMapping(value = "cursos/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> buscarCursos(@PathVariable("name") String nombre) {
	    List<Curso> aux = new ArrayList<>();
	    for (Curso c : cursos) {
	        if (c.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
	            aux.add(c);
	        }
	    }
	    return aux;
	}

	
	
}
