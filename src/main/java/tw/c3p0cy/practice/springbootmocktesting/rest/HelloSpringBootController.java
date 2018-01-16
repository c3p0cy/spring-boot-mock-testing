package tw.c3p0cy.practice.springbootmocktesting.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringBootController {

  @RequestMapping("/hello")
  public String hello() {
    return "Hello Spring Boot!";
  }
}
