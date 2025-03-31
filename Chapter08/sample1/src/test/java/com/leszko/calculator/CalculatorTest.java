package com.leszko.calculator;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
*javadoc
*comment edit new feature branch
*comment to trigger PR
*/
public class CalculatorTest {
     private Calculator calculator = new Calculator();

     @Test
     public void testSum() {
          assertEquals(5, calculator.sum(2, 3));
     }
}
