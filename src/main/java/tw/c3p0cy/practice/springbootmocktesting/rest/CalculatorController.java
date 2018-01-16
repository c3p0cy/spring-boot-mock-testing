package tw.c3p0cy.practice.springbootmocktesting.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tw.c3p0cy.practice.springbootmocktesting.service.CalculatorService;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

  @Autowired
  private CalculatorService calculatorService;

  @RequestMapping("/minus")
  public int minus(@RequestParam("base") int base, @RequestParam("operator") int operator) { return calculatorService.minus(base, operator); };
}
