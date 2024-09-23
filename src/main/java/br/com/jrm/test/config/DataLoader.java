package br.com.jrm.test.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.jrm.test.model.Film;
import br.com.jrm.test.repository.FilmRepository;
import br.com.jrm.test.util.NumberCheck;

@Configuration
public class DataLoader {
	
	private static final String YES = "yes";

	@Bean
	public CommandLineRunner loadData(FilmRepository filmRepository) {
		return args -> {
			String file = "C:/kit/file/movielist.csv";
			String line;
			String splitBy = ";";
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				while ((line = br.readLine()) != null) {
					String[] filmData = line.split(splitBy);
                    if(filmData != null && NumberCheck.isInteger(filmData[0])) {
                    	Film film = new Film();
                    	film.setYear(filmData[0]);
                    	film.setTitle(filmData[1]);
                    	film.setStudios(filmData[2]);
                    	film.setProducer(filmData[3]);
                    	if(filmData.length > 4){
                    		film.setWinner(filmData[4] != null && filmData[4].equalsIgnoreCase(YES));
                    	}else {
                    		film.setWinner(false);
                    	}
                    	filmRepository.save(film);
                    }
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}
}
