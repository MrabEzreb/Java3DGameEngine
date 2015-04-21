package com.ezrebclan.javagame.scala.gameloop

import com.ezrebclan.javagame.shaders.StaticShader

abstract class GameLoop {

  var loop: Thread = null
  
  def startGameLoop(): Unit = {
     loop = new Thread(new Runnable() {
      def run(): Unit = {
        init()
        while (shouldRun()) {
          runLoop()
        }
        exit()
      }
    })
    loop.start()
  }
  
  def runLoop(): Unit
  def shouldRun(): Boolean
  def init(): Unit
  def exit(): Unit
}