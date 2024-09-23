package br.com.jrm.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jrm.test.model.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {
	
}
