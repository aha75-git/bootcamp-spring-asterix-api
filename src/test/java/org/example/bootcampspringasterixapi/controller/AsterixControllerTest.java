package org.example.bootcampspringasterixapi.controller;

import org.example.bootcampspringasterixapi.model.AsterixCharacter;
import org.example.bootcampspringasterixapi.repository.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AsterixControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CharacterRepository repo;

    @BeforeEach
    public void setUp() {
        repo.save(new AsterixCharacter("1", "Asterix", 35, "Warrior"));
        repo.save(new AsterixCharacter("2", "Obelix", 35, "Supplier"));
    }

    @Test
    void getCharacters_shouldReturnListOfCharacters_whenCalled() throws Exception {
        // GIVEN

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/asterix/characters"))
        // THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                        [
                          {
                                   "id": "1",
                                   "name": "Asterix",
                                   "age": 35,
                                   "profession": "Warrior"
                               },
                               {
                                   "id": "2",
                                   "name": "Obelix",
                                   "age": 35,
                                   "profession": "Supplier"
                               }
                        ]
                        """
                ));
    }

    @Test
    void getCharactersById_shouldReturnCharacter_whenCalledById() throws Exception {
        // GIVEN
        String id = "1";

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/asterix/characters/" + id))
                // THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                        
                              {
                                   "id": "1",
                                   "name": "Asterix",
                                   "age": 35,
                                   "profession": "Warrior"
                               }
                        
                        """
                ));
    }

    @Test
    void deleteCharacter_shouldDeleteCharacter_whenCalled() throws Exception {
        // GIVEN
        String id = "1";

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.delete("/asterix/characters/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/asterix/characters/" + id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void addCharacter_shouldAddAndReturnCharacter_whenCalled() throws Exception {
        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/asterix/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {                               
                                     "name": "Miraculix",
                                     "age": 60,
                                     "profession": "Druid"
                                 }                      
                                """))
        // THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                  {
                                     "name": "Miraculix",
                                     "age": 60,
                                     "profession": "Druid"
                                 }
                                  """
                ))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Miraculix"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(60))
                .andExpect(MockMvcResultMatchers.jsonPath("$.profession").value("Druid"));
    }

    @Test
    void updateCharacter_shouldUpdateAndReturnCharacter_whenCalled() throws Exception {
        // GIVEN
        String id = "1";

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.put("/asterix/characters/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {                               
                                     "name": "Asterix Jr.",
                                      "age": 17,
                                      "profession": "Supplier"
                                 }                      
                                """))
                // THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                  {
                                     "name": "Asterix Jr.",
                                      "age": 17,
                                      "profession": "Supplier"
                                 }
                                  """
                ))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Asterix Jr."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(17))
                .andExpect(MockMvcResultMatchers.jsonPath("$.profession").value("Supplier"));
    }
}