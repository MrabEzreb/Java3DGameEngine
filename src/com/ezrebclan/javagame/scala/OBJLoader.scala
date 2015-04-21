package com.ezrebclan.javagame.scala

import com.ezrebclan.javagame.java.models.RawModel
import com.ezrebclan.javagame.java.renderEngine.Loader
import java.io.FileReader
import java.io.File
import collection.JavaConversions._
import java.io.FileNotFoundException
import java.io.BufferedReader
import java.util.ArrayList
import org.lwjgl.util.vector.Vector3f
import org.lwjgl.util.vector.Vector2f
import java.util.List
import java.io.InputStreamReader

object OBJLoader {

  def loadObjModel(filename: String, loader: Loader): RawModel = {
    var fr: InputStreamReader = null
    var rm: RawModel = null
    try {
      fr = new InputStreamReader(OBJLoader.getClass.getClassLoader().getResourceAsStream("res/"+filename+".obj"));
    } catch {
      case fnfe: FileNotFoundException => {
        System.err.println("Couldn't load .obj file")
        fnfe.printStackTrace()
      }
    }
    var reader = new BufferedReader(fr)
    var line = "";
    var vertices = new ArrayList[Vector3f]
    var textures = new ArrayList[Vector2f]
    var normals = new ArrayList[Vector3f]
    var indices = new ArrayList[Integer]
    var verticesArray: Array[Float] = null
    var normalsArray: Array[Float] = null
    var textureArray: Array[Float] = null
    var indicesArray: Array[Int] = null
    try {
      var doneParsing = false
      while(!doneParsing) {
        line = reader.readLine()
        var currentLine:Array[String] = line.split(" ")
        if(line.startsWith("v ")) {
          var vertex = new Vector3f(currentLine(1).toFloat, currentLine(2).toFloat, currentLine(3).toFloat)
          vertices.add(vertex)
        } else if(line.startsWith("vt ")) {
          var texture = new Vector2f(currentLine(1).toFloat, currentLine(2).toFloat)
          textures.add(texture)
        } else if(line.startsWith("vn ")) {
          var normal = new Vector3f(currentLine(1).toFloat, currentLine(2).toFloat, currentLine(3).toFloat)
          normals.add(normal)
        } else if(line.startsWith("f ")) {
          textureArray = new Array[Float](vertices.size() * 2)
          normalsArray = new Array[Float](vertices.size()*3)
          doneParsing = true
        }
      }
      while(line != null) {
        if(!line.startsWith("f ")) {
          line = reader.readLine()
        } else {
          var currentLine = line.split(" ")
          var vertex1 = currentLine(1).split("/")
          var vertex2 = currentLine(2).split("/")
          var vertex3 = currentLine(3).split("/")
          processVertex(vertex1, indices, textures, normals, textureArray, normalsArray)
          processVertex(vertex2, indices, textures, normals, textureArray, normalsArray)
          processVertex(vertex3, indices, textures, normals, textureArray, normalsArray)
          line = reader.readLine()
        }
      }
      reader.close()
    } catch {
      case e: Exception => e.printStackTrace()
    }
    verticesArray = new Array[Float](vertices.size()*3)
    indicesArray = new Array[Int](indices.size())
    var vertexPointer = 0
    vertices.foreach{
      v =>
        verticesArray(vertexPointer) = v.x
        vertexPointer += 1
        verticesArray(vertexPointer) = v.y
        vertexPointer += 1
        verticesArray(vertexPointer) = v.z
        vertexPointer += 1
    }
    var indexPointer = 0
    indices.foreach { i => 
      indicesArray(indexPointer) = i
      indexPointer += 1
    }
    rm = loader.loadToVAO(verticesArray, textureArray, normalsArray, indicesArray)
    rm
  }
  
  private def processVertex(vertexData: Array[String], indices: List[Integer], textures: List[Vector2f], normals: List[Vector3f], textureArray: Array[Float], normalsArray: Array[Float]): Unit = {
    var currentVertexPointer = Integer.parseInt(vertexData(0)) -1
    indices.add(currentVertexPointer)
    var currentTex = textures.get(Integer.parseInt(vertexData(1))-1)
    textureArray(currentVertexPointer*2) = currentTex.x
    textureArray(currentVertexPointer*2+1) = 1 - currentTex.y
    var currentNorm = normals.get(Integer.parseInt(vertexData(2))-1)
    normalsArray(currentVertexPointer*3) = currentNorm.x
    normalsArray(currentVertexPointer*3+1) = currentNorm.y
    normalsArray(currentVertexPointer*3+2) = currentNorm.z
    
  }
      
}