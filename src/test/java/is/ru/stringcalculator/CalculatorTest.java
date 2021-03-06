package is.ru.stringcalculator;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest {

	public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("is.ru.stringcalculator.CalculatorTest");
    }

	@Test
	public void testEmptyString() {
		assertEquals(0, Calculator.add(""));
	}

	@Test
	public void testOneNumber() {
		assertEquals(1, Calculator.add("1"));
	}

	@Test
	public void testTwoNumbers() {
		assertEquals(3, Calculator.add("1,2"));
	}

	@Test
    public void testMultipleNumbers(){
    	assertEquals(6, Calculator.add("1,2,3"));
    }
	@Test
    public void testNewlineDelimiter(){
		assertEquals(6, Calculator.add("1\n2,3"));
    }
	@Test
	public void testHandleDifferentDelimiter(){
		assertEquals(3, Calculator.add("//;\n1;2"));
	}
	@Test
	public void testNegativeNumbersException(){
		try{
			Calculator.add("-1,2");
			fail("Exception expected");
		}catch(RuntimeException e){
			assertEquals("Negatives not allowed: -1", e.getMessage());
		}
		try{
			Calculator.add("2,-4,3,-5");
			fail("Exception expected");
		}catch(RuntimeException e){
			assertEquals("Negatives not allowed: -4, -5", e.getMessage());
		}
	}
	@Test
	public void testIgnoreNumbersOver1000(){
		assertEquals(2, Calculator.add("1001,2"));
	}
	@Test
	public void testDelimitersOfAnyLength(){
		assertEquals(6, Calculator.add("//[***]\n1***2***3"));
	}
	@Test
	public void testMultipleDelimiters(){
		assertEquals(6, Calculator.add("//[*][%]\n1*2%3"));
	}
	@Test
	public void testMultipleDelimitersOfAnyLength(){
		assertEquals(10, Calculator.add("//[***][%%][;;;;;]\n1***2%%3;;;;;4"));
		assertEquals(15, Calculator.add("//[..][,,,][#][!!!!]\n1..2,,,3#4!!!!5"));
	}
	@Test
	public void testSingleNumberLargerThan1000(){
		assertEquals(0, Calculator.add("1001"));
	}
	@Test
	public void testIllegalInputException(){
		try{
			Calculator.add(";1;2");
			fail("Exception expected");
		}catch(RuntimeException e){
			assertEquals("Illegal input", e.getMessage());
		}
	}
	@Test
	public void testDifferentSingleNumbers(){
		assertEquals(2, Calculator.add("2"));
		assertEquals(1000, Calculator.add("1000"));
	}
	@Test
	public void testSpecialCharactersAsDelimiters(){
		assertEquals(6, Calculator.add("//*\n1*2*3"));
	}
}
