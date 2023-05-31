import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.math.BigInteger;
import java.util.Arrays;

public class OperacionesPermutacionCombinacion {

    /**
     * @param n
     *          Acepta un número entero y retorna un BigInteger resultado de una permutación
     *          sin repetición
     * */
    public BigInteger permutacionSinRepeticion(int n) {
        return BigInteger.valueOf(CombinatoricsUtils.factorial(n));
    }

    /**
     * @param n
     *         n es el número total de datos que hay en el problema
     * @param arr
     *          Es un arreglo de datos que contiene el número de veces que se puede repetir un dato
     *          dentro de la permutación. Todos los datos deben ser positivos y mayores a cero.
     * @return Retorna un BigInteger resultado de una permutación con repetición
     * */

    public BigInteger permutacionConRepeticion(int n, int[] arr) {
        return BigInteger.valueOf(
                CombinatoricsUtils.factorial(n) / (Arrays.stream(arr).mapToLong(CombinatoricsUtils::factorial).sum())
        );
    }


    /**
     * @param n
     *          n es el número total de datos que hay en el problema
     * @param m
     *          Es la cantidad de espacios que el dato m ocupará en n.
     *@return   Retorna un BigInteger resultado de una combinación sin repetición
     * */
    public BigInteger combinacionSinRepeticion(int n, int m) {
        return BigInteger.valueOf(
                CombinatoricsUtils.factorial(n) / (CombinatoricsUtils.factorial(n) * CombinatoricsUtils.factorial(m - n)));
    }


    /**
     * @param n
     *          n es el número total de datos que hay en el problema
     * @param m
     *           Es la cantidad de espacios que el dato m ocupará en n considerando que los elementos pueden repetirse.
     *@return   Retorna un BigInteger resultado de una combinación con repetición
     * */
    public BigInteger combinacionConRepeticion(int n, int m) {
        return BigInteger.valueOf(
                CombinatoricsUtils.factorial(m + n - 1) / (CombinatoricsUtils.factorial(n) * CombinatoricsUtils.factorial(m - 1)));
    }
}
