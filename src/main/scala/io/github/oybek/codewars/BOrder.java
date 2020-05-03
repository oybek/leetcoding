package io.github.oybek.codewars;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

class BOrder {
    private String oo;
    private String o;

    public BOrder(final String operands, final String operators) {
        this.oo = operands;
        this.o = operators;
    }

    public BigInteger solve() {
        return BigInteger.valueOf(T(0, oo.length()-1));
    }

    private Long A(int i, int j) { return T(i, j) + F(i, j); }
    private Long T(int i, int j) { return solveFor( true, i, j); }
    private Long F(int i, int j) { return solveFor(false, i, j); }

    Map<String, Long> cache = new HashMap<>();
    private Long solveFor(boolean v, int i, int j) {
        String key = v + "," + i + "," + j;
        if (cache.containsKey(key)) {
            return cache.get(key);
        } else {
            long res;
            if (i == j) {
                if (v) res = oo.charAt(i) == 't' ? 1L : 0L;
                else   res = oo.charAt(i) == 'f' ? 1L : 0L;
            } else {
                res = IntStream
                        .range(i, j)
                        .mapToObj(formulaFor(v, i, j))
                        .reduce(Long::sum)
                        .orElse(0L);
            }
            cache.put(key, res);
            return res;
        }
    }

    private IntFunction<Long> formulaFor(boolean v, int i, int j) {
        return k -> {
            switch (o.charAt(k)) {
                case '&': return v ? T(i, k) * T(k + 1, j) : A(i, k) * A(k + 1, j) - T(i, k) * T(k + 1, j);
                case '|': return v ? A(i, k) * A(k + 1, j) - F(i, k) * F(k + 1, j) : F(i, k) * F(k + 1, j);
                case '^': return v ? F(i, k) * T(k + 1, j) + T(i, k) * F(k + 1, j) : T(i, k) * T(k + 1, j) + F(i, k) * F(k + 1, j);
            }
            return 0L;
        };
    }
}
