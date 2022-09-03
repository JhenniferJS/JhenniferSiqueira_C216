package br.inatel.labs.labrest.server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import br.inatel.labs.labrest.server.model.Curso;

@Service
public class CursoService {
	
	private Map<Long, Curso> mapa = new HashMap<>();
	
	public List<Curso> pesquisarCurso(){
		return mapa.entrySet().stream().map(m -> m.getValue()).collect(Collectors.toList());
	}
}
