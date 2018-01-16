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
## References:
* [java Spring boot的最簡單最詳細的入門案例](https://www.jianshu.com/p/66166899c8f3)