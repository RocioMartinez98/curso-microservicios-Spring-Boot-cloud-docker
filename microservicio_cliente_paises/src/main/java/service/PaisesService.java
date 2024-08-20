package service;

import java.util.List;

import model.Pais;

public interface PaisesService {
	List<Pais> obtenerPaises();
	List<Pais> buscarPaises(String name);
}
