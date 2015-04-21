package com.ezrebclan.javagame.scala

import engineTester.MainGameLoop

object JavaGame extends App {

  override def main(args: Array[String]): Unit = {
    println("Launching scala graphics gameloop")
    ScalaGameLoop.GraphicsGL.startGameLoop()
    println("Launched successfully")
  }
}