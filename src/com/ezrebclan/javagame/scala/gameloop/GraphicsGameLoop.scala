package com.ezrebclan.javagame.scala.gameloop

import org.lwjgl.opengl.Display

import com.ezrebclan.javagame.java.renderEngine.DisplayManager
import com.ezrebclan.javagame.java.renderEngine.EntityRenderer
import com.ezrebclan.javagame.java.renderEngine.Loader
import com.ezrebclan.javagame.shaders.StaticShader

trait GraphicsGameLoop extends GameLoop {

  var loader: Loader = new Loader
  var renderer: EntityRenderer = null
  var shader: StaticShader = null
  
  def init(): Unit = {
    DisplayManager.createDisplay()
    shader = new StaticShader
    renderer = new EntityRenderer(shader)
    loadIn()
  }
  def exit(): Unit = {
    loader.cleanUp()
    shader.cleanUp()
    cleanUp()
    DisplayManager.closeDisplay()
  }
  def runLoop(): Unit = {
    runPhysics()
    renderer.prepare()
    shader.start()
    render()
    shader.stop()
    DisplayManager.updateDisplay()
    postRender()
  }
  def shouldRun(): Boolean = {
    !Display.isCloseRequested()
  }
  def cleanUp(): Unit
  def loadIn(): Unit
  def runPhysics(): Unit
  def render(): Unit
  def postRender(): Unit
}