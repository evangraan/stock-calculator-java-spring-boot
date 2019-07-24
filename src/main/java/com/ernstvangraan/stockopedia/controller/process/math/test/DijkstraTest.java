package com.ernstvangraan.stockopedia.controller.process.math.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.jboss.logging.Logger;
import org.junit.*;
import com.ernstvangraan.stockopedia.controller.process.math.Dijkstra;

public class DijkstraTest {
	private Logger logger = Logger.getLogger(DijkstraTest.class);
	private Dijkstra dijkstra;

	@BeforeClass
    public static void onceExecutedBeforeAll() {
    }
  
    @Before
    public void executedBeforeEach() {
		dijkstra = new Dijkstra();
    }

    @Test
    public void Addition() {
    	logger.info("In order to perform addition, given I have two numbers, an addition expression should add the numbers");
    	Float a = new Float(1.234);
    	Float b = new Float(2.345);
    	String expression = a.toString() + "+" + b.toString();
    	assertEquals(a + b, dijkstra.evaluateShuntingYardPostFix(expression));

    	logger.info("... spaces in the expression do not matter");
    	expression = a.toString() + " +  " + b.toString();
    	assertEquals(a + b, dijkstra.evaluateShuntingYardPostFix(expression));
    }
    
    @Test
    public void Subtraction() {
    	logger.info("In order to perform subtraction, given I have two numbers, a subtraction expression should subtract the numbers");
    	Float a = new Float(1.234);
    	Float b = new Float(2.345);
    	String expression = a.toString() + "-" + b.toString();
    	assertEquals(a - b, dijkstra.evaluateShuntingYardPostFix(expression));
    	
    	logger.info("... Negative answers are OK");
    	expression = b.toString() + "-" + a.toString();
    	assertEquals(b - a, dijkstra.evaluateShuntingYardPostFix(expression));
    	
    	logger.info("... spaces in the expression do not matter");
    	expression = b.toString() + "    -          " + a.toString();
    	assertEquals(b - a, dijkstra.evaluateShuntingYardPostFix(expression));
    }
    
    @Test
    public void Multiplication() {
    	logger.info("In order to perform multiplication, given I have two numbers, a multiplicaton expression should multiply the numbers");
    	Float a = new Float(1.234);
    	Float b = new Float(2.345);
    	String expression = a.toString() + "*" + b.toString();
    	assertEquals(a * b, dijkstra.evaluateShuntingYardPostFix(expression));
    	
    	logger.info("... spaces in the expression do not matter");
    	expression = a.toString() + "  *   " + b.toString();
    	assertEquals(a * b, dijkstra.evaluateShuntingYardPostFix(expression));
    }
 
    @Test
    public void Divison() {
    	logger.info("In order to perform division, given I have two numbers, a division expression should divide the numbers");
    	Float a = new Float(1.234);
    	Float b = new Float(2.345);
    	String expression = a.toString() + "/" + b.toString();
    	assertEquals(a / b, dijkstra.evaluateShuntingYardPostFix(expression));
    	
    	logger.info("... spaces in the expression do not matter");
    	expression = a.toString() + "  /   " + b.toString();
    	assertEquals(a / (float)b, dijkstra.evaluateShuntingYardPostFix(expression));
    	
    	logger.info("... division by 0 is not allowed");
    	final String expression2 = a.toString() + "/0";
    	assertThrows(UnsupportedOperationException.class, () -> {
    		dijkstra.evaluateShuntingYardPostFix(expression2);
    	});   	
    }
    
    @Test
    public void OperatorPrecedence() {
    	logger.info("In order to perform comples algebra, operator precedence should be honored");
    	assertEquals(1 + 9 * 3 / (float)2 - 5, dijkstra.evaluateShuntingYardPostFix("1 + 9 * 3 / 2 - 5"));
    	
    	logger.info("... parentheses should be supported");
    	assertEquals((1 + 9) * 3 / (float)((1+1)+(2-2)) - 5, dijkstra.evaluateShuntingYardPostFix("(1 + 9) * 3 / ((1+1)+(2-2)) - 5"));
    }
    
}
