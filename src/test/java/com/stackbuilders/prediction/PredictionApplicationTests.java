package com.stackbuilders.prediction;

import com.stackbuilders.prediction.helper.PlateException;
import com.stackbuilders.prediction.model.Plate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(ProjectController.class)
@TestPropertySource(properties = "logging.level.org.springframework.web=DEBUG")
class PredictionApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void rendersMainPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("Pico y Placa Predictor")));
    }

    @Test
    public void submitsForm() throws Exception {
        mockMvc.perform(post("/predict").param("plate", "PCA1247").param("date", "12-03-2020").param("time", "07:30"))
                .andExpect(content().string(containsString("The car CANNOT be on the road")));
    }

}
