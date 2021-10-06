package com.api.entities;

import com.api.repositories.UserRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
public class AirFranceApplicationTests {

    @Test
    void contextLoads() {

    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testFindAllUserMethod() throws Exception {
        List<User> listeUser = userRepository.findAll();
        Assertions.assertNotEquals(0, listeUser.size());
    }

    @Test
    void testSaveUserMethod() throws Exception {
        User existUser = null;
        LocalDate localdateUser = LocalDate.of(1965, 4, 3);
        User user = new User(15L, "Pierre Michel", localdateUser, "France", "0612236369", "Homme");
        User savedUser = userRepository.save(user);
        Assertions.assertTrue(userRepository.findAll().contains(savedUser));

    }

    @Test
    public void testGetUserPresent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/Michel"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", CoreMatchers.containsString("Michel")));
    }

    @Test
    public void testGetUserAbsent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/Michel2"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .equals(null);

    }


    @Test
    public void testPostUserOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/save/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":null," +
                                "\"name\":\"Alain Chardonnet\"," +
                                "\"dateNaissance\":\"1955-10-11\"," +
                                "\"countryResidence\":\"France\"," +
                                "\"phoneNumber\":\"0617738138\"," +
                                "\"gender\":\"Homme\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.containsString("Alain Chardonnet")));

    }


    @Test
    public void testPostUserKO() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/save/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":null," +
                                "\"name\":\"John Carpenter\"," +
                                "\"dateNaissance\":\"1945-10-11\"," +
                                "\"countryResidence\":\"USA\"," +
                                "\"phoneNumber\":\"510-122-1256\"," +
                                "\"gender\":\"Homme\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());


    }


}
