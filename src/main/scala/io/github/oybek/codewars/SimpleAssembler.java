package io.github.oybek.codewars;

import java.util.Map;

public class SimpleAssembler {
    public static Map<String, Integer> interpret(String[] program) {
        return null;
    }

    private static void _interpret(String[] program, int cur, Map<String, Integer> registers) {
        if (cur >= program.length) {
            return;
        } else {
            String[] tokens = program[cur].split(" ");
            String cmd = tokens[0];
            String x = tokens[1];
            switch (tokens[0]) {
                case "mov":
                    int y = getValue(tokens[2], registers);
                    registers.put(x, y);
                    break;
                case "inc":
                    registers.put(x, registers.get(x)+1);
                    break;
                case "dec":
                    registers.put(x, registers.get(x)-1);
                    break;
                case "jnz":
                    int v = getValue(tokens[1], registers);
                    if (v != 0) {
                    } else {
                    }
                    break;
            }
            _interpret(program, cur+1, registers);
        }
    }

    private static int getValue(String src, Map<String, Integer> registers) {
        if (src.matches("-?[0-9]+")) {
            return Integer.parseInt(src);
        } else {
            return registers.get(src);
        }
    }
}

