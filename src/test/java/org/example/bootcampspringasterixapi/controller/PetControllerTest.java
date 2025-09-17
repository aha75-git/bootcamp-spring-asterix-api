package org.example.bootcampspringasterixapi.controller;

import org.example.bootcampspringasterixapi.model.Pet;
import org.example.bootcampspringasterixapi.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PetRepository repo;

    @Test
    void getAllPetsTest() throws Exception {
        // GIVEN
        Pet pet = new Pet("1", "Meggi", "Katze");
        repo.save(pet);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/pets"))
        // THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                        [
                          {
                            "id": "1",
                            "name": "Meggi",
                            "type": "Katze"
                          }
                        ]
                        """
                ));
    }
}