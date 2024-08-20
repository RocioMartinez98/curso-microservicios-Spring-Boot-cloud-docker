package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.VuelosDao;
import model.Vuelo;
@Service
public class VuelosServiceImpl implements VuelosService {
	@Autowired
	VuelosDao dao;
	@Override
	public List<Vuelo> recuperarVuelosDisponibles(int plazas) {
		return dao.devolverVuelos()
				.stream()
				.filter(t->t.getPlazas()>=plazas)
				.collect(Collectors.toList());
	}

	@Override
	public void actualizarPlazas(int id, int plazas) {
		Vuelo vuelo = dao.devolverVuelo(id);
		if(vuelo!=null) {
			vuelo.setPlazas(vuelo.getPlazas()-plazas);
			dao.actualizarVuelo(vuelo);
		}

	}

}
