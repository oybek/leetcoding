package io.github.oybek.codewars;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NextBigNumber {

    public static long nextBiggerNumber(long n) {
        System.out.println("Input: " + n);
        String nsr = reversed(String.valueOf(n));
        Integer in = IntStream
                        .range(0, nsr.length())
                        .filter(i -> !isSorted(nsr.substring(0, i+1)))
                        .findFirst().orElse(-1);
        if (in == -1) {
            return in;
        } else {
            String slice = nsr.substring(0, in);
            Character x = nsr.substring(in, in+1).charAt(0);
            String rest = nsr.substring(in+1);

            Optional<Character> co = findFirst(slice, c -> c > x);

            String res =
                co.map(c ->
                    reversed(sorted(slice.replaceFirst(c+"", x+""))) +
                    c + rest
                ).orElse("1-");

            return Long.parseLong(reversed(res));
        }
    }

    public static Optional<Character> findFirst(String s, Predicate<Character> p) {
        String ss = sorted(s);
        for (int i = 0; i < ss.length(); ++i) {
            if (p.test(ss.charAt(i)))
                return Optional.of(ss.charAt(i));
        }
        return Optional.empty();
    }

    public static String sorted(String s) {
        return Stream.of(s.split(""))
                .sorted()
                .collect(Collectors.joining());
    }

    public static String reversed(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static boolean isSorted(String s) {
        return IntStream
                .range(0, s.length()-1)
                .allMatch(x -> s.charAt(x) <= s.charAt(x+1));
    }
}
