package br.inatel.labs.labrest.server.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.inatel.labs.labrest.server.model.Curso;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CursoControllerTest {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void deveListarCursos() {
		webTestClient.get()
		.uri("/curso")
		.exchange()
		.expectStatus()
		.isOk()
		.expectBody()
		.returnResult();
	}
	
	@Test
	void dadoCursoIdValido_quandoGetCursoPeloId_entaoRespondeComCursoValido() {
		Long cursoIdValido = 1L;
		
		Curso cursoRespondido = webTestClient.get()
				.uri("/curso/" + cursoIdValido)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody(Curso.class)
				.returnResult()
				.getResponseBody();
		
		assertNotNull(cursoRespondido);
		assertEquals(cursoRespondido.getId(), cursoIdValido);
	}
	
	@Test
	void dadoCursoIdInvalido_quandoGetCursoPeloId_entaoRespondeComStatusNotFound() {
		Long cursoIdInvalido = 99L;
		
		webTestClient.get()
		.uri("/curso/" + cursoIdInvalido)
		.exchange()
		.expectStatus()
		.isNotFound();
	}

	@Test
	void dadoCursoValido_quandoPostCurso_entaoRespondeComCursoValido() {
		Curso cursoValido = new Curso();
		cursoValido.setDescricao("SpringBoot Test");
		cursoValido.setCargaHoraria(80);
		
		Curso cursoRespondido = webTestClient
				.post()
				.uri("/curso")
				.bodyValue(cursoValido)
				.exchange()
				.expectStatus()
				.isCreated()
				.expectBody(Curso.class)
				.returnResult()
				.getResponseBody();

		assertNotNull(cursoRespondido.getId());
		assertEquals(cursoRespondido.getDescricao(), cursoValido.getDescricao());
		assertEquals(cursoRespondido.getCargaHoraria(), cursoValido.getCargaHoraria());
	}
	
	@Test
	void dadoCursoIdValido_quandoPutCurso_entaoRespondeComStatusAccept() {
		Curso cursoValido = new Curso();
		cursoValido.setId(5L);
		cursoValido.setDescricao("SpringBoot Test");
		cursoValido.setCargaHoraria(80);
		
		Long cursoIdExistente = 2L;
		
		webTestClient.put()
		.uri("/curso/" + cursoIdExistente)
		.bodyValue(cursoValido)
		.exchange()
		.expectStatus()
		.isAccepted();
	}
	
	@Test
	void dadoCursoIdValido_quandoDeleteCurso_entaoRespondeComStatusNoContent() {
		Long cursoIdValido = 3L;
		
		webTestClient.delete()
		.uri("/curso/" + cursoIdValido)
		.exchange()
		.expectStatus()
		.isNoContent();
	}
	
	@Test
	void dadoCursoIdInvalido_quandoDeleteCurso_entaoRespondeComStatusNotFound() {
		Long cursoIdInvalido = 99L;
		
		webTestClient.delete()
		.uri("/curso/" + cursoIdInvalido)
		.exchange()
		.expectStatus()
		.isNotFound();
	}
}
