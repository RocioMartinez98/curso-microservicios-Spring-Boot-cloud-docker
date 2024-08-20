package dao;

import java.util.List;

import model.Contacto;

public interface AgendaDao {
	void agragarContacto(Contacto contacto);
	
	Contacto recuperarContacto(String email);
	
	void eliminarContacto(String email);
	
	List<Contacto> devolverContactos();
	
	void eliminarContacto(int idContacto);
	
	Contacto recuperarContacto(int idContacto);
	
	void actualizarContacto(Contacto contacto);
	
}
