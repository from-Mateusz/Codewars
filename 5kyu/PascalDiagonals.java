import java.math.BigInteger;
import java.util.function.BiFunction;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

public class PascalDiagonals {
    
    @Test
    public void findNNumbersAlongsideDiagonal() {
        assertThat("Ones", new long[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, equalTo(generateDiagonal(0, 10)));
        assertThat("Natural numbers", new long[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, equalTo(generateDiagonal(1, 10)) );
        assertThat("Triangular numbers", new long[] { 1, 3, 6, 10, 15, 21, 28, 36, 45, 55 }, equalTo(generateDiagonal(2, 10)) );
        assertThat("Tetrahedral numbers", new long[] {1, 4, 10, 20, 35, 56, 84, 120, 165, 220}, equalTo(generateDiagonal(3, 10)));
        assertThat("Pentatope numbers", new long[] {1, 5, 15, 35, 70, 126, 210, 330, 495, 715}, equalTo(generateDiagonal(4, 10)));

        assertThat("Ones", new long[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, equalTo(simplifiedGenerateDiagonal(0, 10)));
        assertThat("Natural numbers", new long[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, equalTo(simplifiedGenerateDiagonal(1, 10)) );
        assertThat("Triangular numbers", new long[] { 1, 3, 6, 10, 15, 21, 28, 36, 45, 55 }, equalTo(simplifiedGenerateDiagonal(2, 10)) );
        assertThat("Tetrahedral numbers", new long[] {1, 4, 10, 20, 35, 56, 84, 120, 165, 220}, equalTo(simplifiedGenerateDiagonal(3, 10)));
        assertThat("Pentatope numbers", new long[] {1, 5, 15, 35, 70, 126, 210, 330, 495, 715}, equalTo(simplifiedGenerateDiagonal(4, 10)));
    }

    private long[] generateDiagonal(int n, int l) {
        if(n == Diagononals.ONES.ordinal()) {
            return Diagononals.ONES.generator.apply(n, l);
        }
        if(n == Diagononals.NATURAL_NUMBERS.ordinal()) {
            return Diagononals.NATURAL_NUMBERS.generator.apply(n, l);
        }
        if(n == Diagononals.TRIANGULAR_NUMBERS.ordinal()) {
            return Diagononals.TRIANGULAR_NUMBERS.generator.apply(n, l);
        }
        if(n == Diagononals.TETRAHEDRAL_NUMBERS.ordinal()) {
            return Diagononals.TETRAHEDRAL_NUMBERS.generator.apply(n, l);
        }
        if(n == Diagononals.PENTATOPE_NUMBERS.ordinal()) {
            return Diagononals.PENTATOPE_NUMBERS.generator.apply(n, l);
        }

        return Diagononals.OTHERS.generator.apply(n, l);
    }

    private enum Diagononals {
        ONES((n, l) -> {
            return LongStream.range(1, l + 1)
                    .map(number -> 1L)
                    .toArray();
        }),

        NATURAL_NUMBERS((n, l) -> {
            return LongStream.range(1, l + 1)
                    .toArray();
        }),

        TRIANGULAR_NUMBERS((n, l) -> {
            return LongStream.range(1, l + 1)
                    .map(number -> binominal(number + 1, 2))
                    .toArray();
        }),

        TETRAHEDRAL_NUMBERS((n, l) -> {
            return LongStream.range(1, l + 1)
                    .map(number -> {
                        long sum = 0;
                        for(int k = 1; k <= number; k++) {
                            sum += binominal(k + 1, 2);
                        }
                        return sum;
                    })
                    .toArray();
        }),

        PENTATOPE_NUMBERS((n, l) -> {
            return LongStream.range(1, l + 1)
                    .map(number -> binominal(number + 3, 4))
                    .toArray();
        }),

        OTHERS((n, l) -> {
            return LongStream.range(0, l + 1)
                    .map(number -> {
                        if(number == 0) return 1;
                        if(number == (n + number)) return 1;
                        return binominal(n + number, number);
                    })
                    .toArray();
        });

        private final BiFunction<Integer, Integer, long[]> generator;
        Diagononals(BiFunction<Integer, Integer, long[]> generator) {
            this.generator = generator;
        }
    }

    private static long binominal(long n, long r) {
        if( r == 0 ) return 1;
        if( r == 1 ) return n;
        if( n == r ) return 1;
        final BigInteger distributedFactorialN = product(n - r + 1, n);
        final BigInteger result = distributedFactorialN.divide(factorialBI(n - (n - r)));
        return result.longValue();
    }

    private static long factorial(long n) {
        return LongStream.range(1, n + 1)
                .reduce(1, (a, b) -> a * b);
    }

    private static BigInteger factorialBI(long n) {
       BigInteger factorial = BigInteger.ONE;
       for(long i = 1; i <= n; i++) {
           factorial = factorial.multiply(BigInteger.valueOf(i));
       }
       return factorial;
    }

    private static BigInteger product(long initial, long n) {
        BigInteger product = BigInteger.ONE;
        for(long i = initial; i <= n; i++) {
            product = product.multiply(BigInteger.valueOf(i));
        }
        return product;
    }

    // Simplified, efficient version (according with: https://en.wikipedia.org/wiki/Pascal's_triangle#Calculating_a_row_or_diagonal_by_itself)
    private static long[] simplifiedGenerateDiagonal(int n, int l) {
        if( l == 0 ) return new long[0];
        long[] result = new long[l];
        long recent = 1;
        result[0] = recent;
        for(int i = 1; i < l; i++) {
            long value = recent * (n + i) / i;
            result[i] = value;
            recent = value;
        }
        return result;
    }
}
