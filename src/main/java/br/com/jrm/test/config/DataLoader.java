package br.com.jrm.test.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.jrm.test.model.Film;
import br.com.jrm.test.repository.FilmRepository;
import br.com.jrm.test.util.NumberCheck;
import br.com.jrm.test.util.StringUtil;

@Configuration
public class DataLoader {


    private static final String YES = "yes";
    private static final Logger logger = Logger.getLogger(DataLoader.class.getName());

    @Bean
    public CommandLineRunner loadData(FilmRepository filmRepository) {
        return args -> {
            String file = "movielist.csv"; // O nome do arquivo
            String line;
            String splitBy = ";";

            // Ler o arquivo a partir do classpath
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(file);
                 BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                 
                if (inputStream == null) {
                    logger.severe("Arquivo não encontrado: " + file);
                    return;
                }

                while ((line = br.readLine()) != null) {
                    String[] filmData = line.split(splitBy);
                    if (filmData != null && NumberCheck.isInteger(filmData[0])) {
                        Film film = new Film();
                        film.setYear(filmData[0]);
                        film.setTitle(filmData[1]);
                        film.setStudios(filmData[2]);
                        film.setProducers(StringUtil.splitStringAndOuVirgula(filmData[3]));
                        if (filmData.length > 4) {
                            film.setWinner(filmData[4] != null && filmData[4].equalsIgnoreCase(YES));
                        } else {
                            film.setWinner(false);
                        }
                        filmRepository.save(film);
                        logger.info("Filme salvo: " + film.getTitle());
                    }
                }
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Erro ao ler o arquivo: " + file, e);
            }
        };
    }
}
