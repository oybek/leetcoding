package io.github.oybek.codewars

object GetPlanetName {
  def get_planet_name(n: Int): String = n match {
    case 1 => "Mercury"
    case 2 => "Venus"
    case 3 => "Earth"
    case 4 => "Mars"
    case 5 => "Jupiter"
    case 6 => "Saturn"
    case 7 => "Uranus"
    case 8 => "Neptune"
  }
}
