package io.github.oybek.codewars.wormadventures

sealed trait Dir
case object Up extends Dir
case object Down extends Dir
case object Left extends Dir
case object Right extends Dir