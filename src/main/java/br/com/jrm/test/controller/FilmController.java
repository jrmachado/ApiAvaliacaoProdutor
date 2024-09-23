package br.com.jrm.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jrm.test.service.FilmService;
import br.com.jrm.test.vo.ResponseVO;

@RestController
@RequestMapping("/api/films")
public class FilmController {
	
	private static final String AVALICAO_NAO_ENCONTRADA = "Avaliação não encontrada.";

	@Autowired
	private FilmService filmService;

	@GetMapping("/maior-periodo")
	public ResponseEntity<?> getObterProdutorMaiorPeriodo() {

		List<ResponseVO> response = filmService.getObterProdutorMaiorPeriodo();
		if (!response.isEmpty()) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(AVALICAO_NAO_ENCONTRADA);
		}

	}

	@GetMapping("/premio-mais-rapido")
	public ResponseEntity<?> getObterProdutorPremiosRapidos() {
		List<ResponseVO> response = filmService.getObterProdutorPremiosRapidos();

		if (!response.isEmpty()) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(AVALICAO_NAO_ENCONTRADA);
		}
	}
}
