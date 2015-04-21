package com.ezrebclan.javagame.scala.gameloop

import com.ezrebclan.javagame.java.renderEngine.DisplayManager
import com.ezrebclan.javagame.java.renderEngine.Loader
import com.ezrebclan.javagame.java.renderEngine.Renderer
import com.ezrebclan.javagame.shaders.StaticShader
import org.lwjgl.opengl.Display;
import com.ezrebclan.javagame.java.renderEngine.EntityRenderer

@deprecated
trait OpenGL extends GameLoop {

  var loader:  Loader = new Loader
  var renderer: EntityRenderer = new EntityRenderer(shader)
  var shader: StaticShader = null
  
  abstract override def runLoop(): Unit = {
    renderer.prepare()
    shader.start()
    super.runLoop()
    shader.stop()
    DisplayManager.updateDisplay()
  }
  
  override def shouldRun(): Boolean = !Display.isCloseRequested()
  
  def getShader(): StaticShader = {
    shader = new StaticShader
    shader
  }
  
  abstract override def init(): Unit = {
    DisplayManager.createDisplay()
    println("test2")
    super.init()
  }
  
  abstract override def exit(): Unit = {
    loader.cleanUp()
    shader.cleanUp()
    DisplayManager.closeDisplay()
    super.exit()
  }
}