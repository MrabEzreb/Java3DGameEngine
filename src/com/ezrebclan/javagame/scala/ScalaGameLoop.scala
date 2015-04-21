package com.ezrebclan.javagame.scala

import org.lwjgl.util.vector.Vector3f
import com.ezrebclan.javagame.java.entities.Camera
import com.ezrebclan.javagame.java.entities.Entity
import com.ezrebclan.javagame.java.entities.Light
import com.ezrebclan.javagame.java.models.RawModel
import com.ezrebclan.javagame.java.models.TexturedModel
import com.ezrebclan.javagame.java.textures.ModelTexture
import com.ezrebclan.javagame.scala.gameloop.GraphicsGameLoop
import com.ezrebclan.javagame.shaders.StaticShader
import org.lwjgl.input.Keyboard
import java.util.ArrayList
import java.util.Arrays.ArrayList
import java.util.List
import java.util.ArrayList

object ScalaGameLoop extends App {

  override def main(args: Array[String]): Unit = {
    GraphicsGL.startGameLoop()
  }
  
  object GraphicsGL extends GraphicsGameLoop {
    
    var model: RawModel = null
    var blockModel: RawModel = null
    var dragonModel: RawModel = null
    var dragonEntity: TexturedModel = null
    var texture: ModelTexture = null
    var dragonTexture: ModelTexture = null
    var tmodel: TexturedModel = null
    var catBlockModel: TexturedModel = null
    var entity: Entity = null
    var catBlock: Entity = null
    var dragon: Entity = null
    var light: Light = null
    var light1: Light = null
    var light2: Light = null
    var light3: Light = null
    var camera: Camera = null
    var bulb: Entity = null
    var grass: Entity = null
    var pos = 0.006f
    var rot = 3f
    var z = 0.05f
    def loadIn(): Unit = {
      texture = new ModelTexture(loader.loadTexture("cat"))
      model = OBJLoader.loadObjModel("stall", loader)
      blockModel = OBJLoader.loadObjModel("cat", loader)
      tmodel = new TexturedModel(model, new ModelTexture(loader.loadTexture("stallTexture")), renderer)
      catBlockModel = new TexturedModel(blockModel, texture, renderer)
      dragonTexture = new ModelTexture(loader.loadTexture("dragon"))
      dragonTexture.setShineDamper(10);
      dragonTexture.setReflectivity(1);
      dragonModel = OBJLoader.loadObjModel("d", loader)
      dragonEntity = new TexturedModel(dragonModel, dragonTexture, renderer)
      dragon = new Entity(dragonEntity, new Vector3f(0,0,-25), 0, 0, 0, 1, renderer)
//      entity = new Entity(tmodel, new Vector3f(-0.5f, 0, -50f), 0, 180, 0, 1)
//      catBlock = new Entity(catBlockModel, new Vector3f(-0.5f,0,-25f), 0, 0, 0, 1)
      camera = new Camera
      var tmodel2 = new TexturedModel(OBJLoader.loadObjModel("lightbulb", loader), new ModelTexture(loader.loadTexture("dragon")), renderer);
      bulb = new Entity(tmodel2, new Vector3f(0,0,-20), 0, 0, 0, 0.1f, renderer);
      light = new Light(new Vector3f(0,0,-20), new Vector3f(0.25f,1,0.25f),new Vector3f(0,0,-20), bulb);
      grass = new Entity(new TexturedModel(OBJLoader.loadObjModel("grass", loader), new ModelTexture(loader.loadTexture("grass")), renderer), new Vector3f(0,0,-21), 90, 0, 0, 0.1f, renderer);
//      light2 = new Light(new Vector3f(0,0,-20), new Vector3f(1,0.25f,0.25f),new Vector3f(0,0,-20));
    }
    def cleanUp(): Unit = {
      
    }
    def runPhysics(): Unit = {
//      catBlock.increasePosition(pos, 0, z)
//      dragon.increaseRotation(1f, 6f, 3f)
      if(Keyboard.isKeyDown(Keyboard.KEY_PERIOD)) {
        dragonTexture.setReflectivity(0);
        dragonEntity = new TexturedModel(dragonModel, dragonTexture, renderer)
        dragon = new Entity(dragonEntity, new Vector3f(0,0,-25), 0, 0, 0, 1, renderer)
      }
      if(Keyboard.isKeyDown(Keyboard.KEY_COMMA)) {
        dragonTexture.setReflectivity(1);
        dragonEntity = new TexturedModel(dragonModel, dragonTexture, renderer)
        dragon = new Entity(dragonEntity, new Vector3f(0,0,-25), 0, 0, 0, 1, renderer)
      }
      camera.move()
      dragon.move()
      light.move()
    }
    def render(): Unit = {
      shader.loadViewMatrix(camera)
      shader.loadLight(light)
      //shader.loadLight(light2)
//      renderer.render(entity, shader)
//      renderer.render(catBlock, shader)
      renderer.renderAll()
    }
    def postRender(): Unit = {
//      if(entity.getPosition.x >= 0.5f) {
//        pos = -0.006f
//      } else if(entity.getPosition.x <= -0.5f) {
//        pos = 0.006f
//      }
//      if(entity.getPosition.x > 0) {
//        z = 0.01f
//      } else if(entity.getPosition.x < 0) {
//        z = -0.01f
//      }
    }
  }
}