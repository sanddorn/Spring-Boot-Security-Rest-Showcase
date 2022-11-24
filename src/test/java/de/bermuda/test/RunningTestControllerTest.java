package de.bermuda.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {RunningTestController.class})
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class RunningTestControllerTest {
    @Autowired
    MockMvc mockMvc;


    @Autowired
    private RequestMappingHandlerMapping requestHandlerMapping;

    @BeforeEach
    void setUp() {
        this.requestHandlerMapping.getHandlerMethods()
                                  .forEach((key, value) ->
                                                   System.out.println(key));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGet() throws Exception {
        mockMvc.perform(get("/running/test"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.part1").isString());
    }
}
