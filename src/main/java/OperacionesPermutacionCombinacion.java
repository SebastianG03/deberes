import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.math.BigInteger;
import java.util.Arrays;

public class OperacionesPermutacionCombinacion {

    public BigInteger permutacionSinRepeticion(int n) {
        return BigInteger.valueOf(CombinatoricsUtils.factorial(n));
    }

    public BigInteger permutacionConRepeticion(int n, int[] arr) {
        BigInteger result =  BigInteger.valueOf(
                CombinatoricsUtils.factorial(n) / (Arrays.stream(arr).mapToLong(CombinatoricsUtils::factorial).sum())
        );
        return result;
    }

    public BigInteger combinacionSinRepeticion(int n, int m) {
        return BigInteger.valueOf(
                CombinatoricsUtils.factorial(n) / (CombinatoricsUtils.factorial(n) * CombinatoricsUtils.factorial(m - n)));
    }

    public BigInteger combinacionConRepeticion(int n, int m) {
        return BigInteger.valueOf(
                CombinatoricsUtils.factorial(m + n - 1) / (CombinatoricsUtils.factorial(n) * CombinatoricsUtils.factorial(m - 1)));
    }
}
