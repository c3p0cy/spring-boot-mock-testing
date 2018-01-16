package tw.c3p0cy.practice.springbootmocktesting.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
import tw.c3p0cy.practice.springbootmocktesting.service.CalculatorService;

public class CalculatorControllerTestByMockito {

  @InjectMocks
  private CalculatorController calculatorController;

  @Mock
  private CalculatorService calculatorService;

  private MockMvc mockMvc;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(calculatorController).build();
  }

  @Test
  public void minus() throws Exception {
    Mockito.when(calculatorService.minus(4, 1)).thenReturn(3);
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