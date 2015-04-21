package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import com.ezrebclan.javagame.java.GameLoop;
import com.ezrebclan.javagame.java.entities.Camera;
import com.ezrebclan.javagame.java.entities.Entity;
import com.ezrebclan.javagame.java.models.RawModel;
import com.ezrebclan.javagame.java.models.TexturedModel;
import com.ezrebclan.javagame.java.renderEngine.DisplayManager;
import com.ezrebclan.javagame.java.textures.ModelTexture;
import com.ezrebclan.javagame.shaders.StaticShader;

@Deprecated
public class MainGameLoop extends GameLoop {

	RawModel model;
	StaticShader shader;
	ModelTexture texture;
	TexturedModel tmodel;
	Entity entity;
	Camera camera;
	float[] vertices = {
			-0.5f,0.5f,0,
			-0.5f,-0.5f,0,
			0.5f,-0.5f,0,
			0.5f,0.5f,0
	};
	
	int[] indices = {
			0,1,3,
			3,1,2
	};
	
	float[] texCoords = {
			0,0,
			0,1,
			1,1,
			1,0
	};
	
	float pos = 0.006f;
	float rot = 3;
	float z = 0.05f;
	@Override
	public Runnable getLoop() {
		return new Runnable() {
			
			@Override
			public void run() {
				entity.increasePosition(pos, 0, z);
				entity.increaseRotation(0, rot, 0);
				if(entity.getPosition().x >= 0.5) {
					pos = -0.006f;
				} else if(entity.getPosition().x <= -0.5) {
					pos = 0.006f;
				}
				if(entity.getPosition().x > 0) {
					z = 0.01f;
				} else if(entity.getPosition().x < 0) {
					z = -0.01f;
				}
				renderer.prepare();
				shader.start();
				renderer.render(entity, shader);
				shader.stop();
				DisplayManager.updateDisplay();
			}
		};
	}

	@Override
	public boolean shouldRun() {
		return !Display.isCloseRequested();
	}

	@Override
	public void init() {
		texture = new ModelTexture(loader.loadTexture("cat"));
		model = loader.loadToVAO(vertices, texCoords, null, indices);
		tmodel = new TexturedModel(model, texture, renderer);
		entity = new Entity(tmodel, new Vector3f(-0.5f, 0, -2f), 0, 0, 0, 1, renderer);
		camera = new Camera();
	}

	@Override
	public void exit() {
		loader.cleanUp();
		shader.cleanUp();
		DisplayManager.closeDisplay();
	}
	
	@Override
	public StaticShader getShader() {
		shader = new StaticShader();
		return shader;
	}

	
}
