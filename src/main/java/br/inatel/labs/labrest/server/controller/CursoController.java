package br.inatel.labs.labrest.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.labs.labrest.server.service.CursoService;
import br.inatel.labs.labrest.server.model.Curso;

@RestController
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	private CursoService servico;
	
	public List<Curso> listar(){
		List<Curso> listaCurso = servico.pesquisarCurso();
		return listaCurso;
	}

}
