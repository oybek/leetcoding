package io.github.oybek.codewars;

import java.util.HashMap;
import java.util.Map;

public class SimpleAssembler {
    public static Map<String, Integer> interpret(String[] program) {
        Map<String, Integer> registers = new HashMap<>();
        for (int cur = 0; cur < program.length; ++cur) {
            String[] tokens = program[cur].split(" ");
            String cmd = tokens[0];
            String x = tokens[1];
            switch (cmd) {
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
                    int xv = getValue(tokens[1], registers);
                    int yv = getValue(tokens[2], registers);
                    if (xv != 0)
                        cur += yv-1;
                    break;
            }
        }
        return registers;
    }

    private static int getValue(String src, Map<String, Integer> registers) {
        if (src.matches("-?[0-9]+")) {
            return Integer.parseInt(src);
        } else {
            return registers.get(src);
        }
    }
}

