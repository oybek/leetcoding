package io.github.oybek.codewars;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

public class ShortestKnightPath {
    public static int knight(String sstart, String sfinish) {
        Queue<Coord> q = new LinkedBlockingQueue<>();
        Set<Coord> marked = new HashSet<>();

        Coord start = new Coord(sstart);
        Coord finish = new Coord(sfinish);

        q.add(start);
        marked.add(start);

        while (!q.isEmpty()) {
            Coord cur = q.poll();
            if (cur.equals(finish)) return cur.w;

            cur.getNear()
               .filter(x -> !marked.contains(x))
               .forEach(x -> {
                   q.add(x);
                   marked.add(x);
               });
        }
        return -1;
    }

    private static class Coord {
        private int x, y, w;

        public Coord(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
            this.w = 0;
        }

        public Coord(String s) {
            this.x = s.charAt(0)-'a';
            this.y = s.charAt(1)-'1';
            this.w = 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coord coord = (Coord) o;
            return x == coord.x && y == coord.y;
        }

        public Stream<Coord> getNear() {
            return Stream.of(
                new Coord(x-2, y-1, w+1),
                new Coord(x-2, y+1, w+1),
                new Coord(x+2, y-1, w+1),
                new Coord(x+2, y+1, w+1),
                new Coord(x-1, y-2, w+1),
                new Coord(x-1, y+2, w+1),
                new Coord(x+1, y-2, w+1),
                new Coord(x+1, y+2, w+1))
                .filter(p -> p.x >= 0 && p.x < 8 && p.y >= 0 && p.y < 8);
        }

        @Override
        public int hashCode() { return Objects.hash(x, y); }
    }
}
