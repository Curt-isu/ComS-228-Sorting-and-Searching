package edu.iastate.cs228.hw2;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class AlphabetComparatorTest {

	@Test
	public void test() throws NullPointerException, FileNotFoundException {
		//fail("Not yet implemented");
		Alphabet a = new Alphabet("10.alphabet.txt");
		//AlphabetComparatorTest
		AlphabetComparator ac = new AlphabetComparator(a);
		assertEquals(ac.compare("wMlR", "wMlR"),0); 
		assertEquals(ac.compare("wMbR", "wMlR"),-1); 
		assertEquals(ac.compare("wMlR", "wMl"),1);
		assertEquals(ac.compare("wMl", "wMlR"),-1); 
		assertEquals(ac.compare("y[Rb5<[Z]9oI9Z[yoMbwIRx5ZyxwoIaZbo]awR<ZZ","[yIIwIablo]5[" ),1); 
		assertEquals(ac.compare("y[Rb5<[Z]9oI9Z[yoMbwIRx5ZyxwoIaZbo]awR<ZZ","w[wZo" ),-1); 

	}

}
