package tw.c3p0cy.practice.springbootmocktesting.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
  public int minus(int base, int operator) {
    return base - operator;
  }
}
