package tw.c3p0cy.practice.springbootmocktesting.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tw.c3p0cy.practice.springbootmocktesting.SpringBootMockTestingApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootMockTestingApplication.class)
public class CalculatorControllerTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void minus() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.get("/calculator/minus")
            .param("base", "4")
            .param("operator", "1")
            .accept(MediaType.APPLICATION_JSON_UTF8)
    )
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(content().string(equalTo("3")))
        .andDo(MockMvcResultHandlers.print())
        .andReturn();
  }
}