package primefactor;

import static org.junit.Assert.assertEquals;
import java.util.List;

import org.junit.Test;

public class PrimeFactorTest {

	@Test
	public void factorTest() {
		assertEquals(List.of(),PrimeFactors.findFactors(1));
		assertEquals(List.of(2),PrimeFactors.findFactors(2));
		assertEquals(List.of(3),PrimeFactors.findFactors(3));
		assertEquals(List.of(2,2),PrimeFactors.findFactors(4));
		assertEquals(List.of(5),PrimeFactors.findFactors(5));
		assertEquals(List.of(2,3),PrimeFactors.findFactors(6));
		assertEquals(List.of(7),PrimeFactors.findFactors(7));
		assertEquals(List.of(2,2,2),PrimeFactors.findFactors(8));
		assertEquals(List.of(3,3),PrimeFactors.findFactors(9));
		assertEquals(List.of(2,5),PrimeFactors.findFactors(10));
		assertEquals(List.of(2,2,3,5,7),PrimeFactors.findFactors(4*3*5*7));
	}
}
