package primefactor;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactors {

	public static List<Integer> findFactors(int n) {
		List<Integer> list = new ArrayList<Integer>();
		for (int divisor = 2; n > 1; divisor++)
			for (; n % divisor == 0; n /= divisor)
				list.add(divisor);
		return list;
	}
}