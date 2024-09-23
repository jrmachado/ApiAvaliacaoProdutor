package br.com.jrm.test.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jrm.test.model.Film;
import br.com.jrm.test.repository.FilmRepository;
import br.com.jrm.test.vo.ResponseVO;

@Service
public class FilmService {

	@Autowired
	private FilmRepository filmRepository;

	public List<Film> getAllFilms() {
		return filmRepository.findAll();
	}

	public List<ResponseVO> getObterProdutorMaiorPeriodo() {

		List<ResponseVO> premiacoes = new ArrayList<>();
		List<Film> films = filmRepository.findAll();
		Map<String, List<Integer>> premiosProdutor = new HashMap<>();

		organizarPremiosProdutor(films, premiosProdutor);

		calcularPeriodoPremios(premiacoes, premiosProdutor);

		OptionalInt maiorIntervalo = premiacoes.stream().mapToInt(ResponseVO::getInterval).max();

		List<ResponseVO> result = maiorIntervalo.isPresent() ? premiacoes.stream()
				.filter(r -> r.getInterval() == maiorIntervalo.getAsInt()).collect(Collectors.toList())
				: Collections.emptyList();

		return result;
	}

	public List<ResponseVO> getObterProdutorPremiosRapidos() {
		List<ResponseVO> premiacoes = new ArrayList<>();

		List<Film> films = filmRepository.findAll();
		Map<String, List<Integer>> premiosProdutor = new HashMap<>();

		organizarPremiosProdutor(films, premiosProdutor);

		calcularPeriodoPremios(premiacoes, premiosProdutor);

		OptionalInt menorIntervalo = premiacoes.stream().mapToInt(ResponseVO::getInterval).min();

		List<ResponseVO> result = menorIntervalo.isPresent() ? premiacoes.stream()
				.filter(r -> r.getInterval() == menorIntervalo.getAsInt()).collect(Collectors.toList())
				: Collections.emptyList();

		return result;
	}

	private void calcularPeriodoPremios(List<ResponseVO> results, Map<String, List<Integer>> premiosProdutor) {
		for (Map.Entry<String, List<Integer>> entry : premiosProdutor.entrySet()) {
			List<Integer> years = entry.getValue();

			if (years.size() <= 1) {
				continue;
			}

			Collections.sort(years);

			int maxInterval = 0;
			int minInterval = Integer.MAX_VALUE;
			int previousWin = 0;
			int followingWin = 0;

			for (int i = 1; i < years.size(); i++) {
				int interval = years.get(i) - years.get(i - 1);
				if (interval > maxInterval) {
					maxInterval = interval;
					previousWin = years.get(i - 1);
					followingWin = years.get(i);
				}
				if (interval < minInterval) {
					minInterval = interval; // Pode armazenar este valor se desejar
				}
			}

			results.add(new ResponseVO(entry.getKey(), maxInterval, previousWin, followingWin));
		}
	}

	private void organizarPremiosProdutor(List<Film> films, Map<String, List<Integer>> premiosProdutor) {
		for (Film film : films) {
			if (film.isWinner()) {
				premiosProdutor.computeIfAbsent(film.getProducer(), k -> new ArrayList<>())
						.add(Integer.parseInt(film.getYear()));

			}
		}
	}

}
