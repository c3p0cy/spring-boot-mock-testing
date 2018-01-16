Demo project for Spring Boot with Mock testing
----------------------------------------------------
## via MockServletContext
1. Use MockServletContext
```java
import org.springframework.mock.web.MockServletContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MockServletContext.class})
@WebAppConfiguration
public class HelloSpringBootControllerTest { ... }
```
2. Init MockMvc
```java
  private MockMvc mockMvc;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(new HelloSpringBootController()).build();
  }
```
3. Test method
```java
  @Test
  public void hello() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/hello")
           .accept(MediaType.APPLICATION_JSON_UTF8))
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(content().string(equalTo("Hello Spring Boot!")))
           .andDo(MockMvcResultHandlers.print())
           .andReturn();
  }
```
----------------------------------------------------
## Test for a Controller that injects other services
#### via MockServletContext and MockMvcBuilders.standaloneSetup():
```java
org.springframework.web.util.NestedServletException: Request processing failed; nested exception is java.lang.NullPointerException
```
* Cause: When you are using standalone setup, Spring doesn't have a chance to inject services as it doesn't control instances creation and wiring.

#### Solutions:
1. via WebApplicationContext:
  ```java
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
    
    ...
  }
  ```
2. via Mockito:
  ```java
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
      mockMvc.perform ...
    }
  }
  ```
----------------------------------------------------
## References:
* [java Spring boot的最簡單最詳細的入門案例](https://www.jianshu.com/p/66166899c8f3)
* [SpringBoot+MockMvc测试Controller笔记整理](http://blog.csdn.net/congge_1993/article/details/78541182)
* [Spring MVC Test failing](https://stackoverflow.com/questions/32348270/spring-mvc-test-failing)