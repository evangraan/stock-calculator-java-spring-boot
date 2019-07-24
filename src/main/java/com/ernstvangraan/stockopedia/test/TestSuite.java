package com.ernstvangraan.stockopedia.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.ernstvangraan.stockopedia.controller.process.math.test.DijkstraTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   DijkstraTest.class,
})

public class TestSuite {   
}  	
