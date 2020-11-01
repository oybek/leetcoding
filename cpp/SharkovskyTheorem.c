#include <stdio.h>
#include <stdbool.h>

bool sharkovsky(int a, int b) {
  int rangOfA = 0;
  int rangOfB = 0;
  for (; a%2 == 0; a /= 2) rangOfA++;
  for (; b%2 == 0; b /= 2) rangOfB++;
  if (a == 1) return a == b;
  if (a == 1 && b == 1) return rangOfA < rangOfB;
  return rangOfA == rangOfB ? a < b : rangOfA < rangOfB;
}

int main() {
  printf("%d", sharkovsky(32, 32));
}

