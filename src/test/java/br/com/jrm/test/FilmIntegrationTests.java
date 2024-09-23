package br.com.jrm.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class FilmIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetProducerWithLongestGap() throws Exception {
        mockMvc.perform(get("/api/films/maior-periodo"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetProducerWithFastestTwoAwards() throws Exception {
        mockMvc.perform(get("/api/films/premio-mais-rapido"))
                .andExpect(status().isOk());
    }
}
