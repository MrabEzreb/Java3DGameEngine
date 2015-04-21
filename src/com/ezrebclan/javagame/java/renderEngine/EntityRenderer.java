package com.ezrebclan.javagame.java.renderEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import com.ezrebclan.javagame.java.entities.Entity;
import com.ezrebclan.javagame.java.models.RawModel;
import com.ezrebclan.javagame.java.models.TexturedModel;
import com.ezrebclan.javagame.java.textures.ModelTexture;
import com.ezrebclan.javagame.java.utils.Maths;
import com.ezrebclan.javagame.settings.Graphics;
import com.ezrebclan.javagame.shaders.StaticShader;

public class EntityRenderer {

	private Matrix4f projectionMatrix;
	public StaticShader shader;
	public HashMap<TexturedModel, Vector<Entity>> entities = new HashMap<>();
	
	public EntityRenderer(StaticShader shader) {
		enableCull();
		createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
		this.shader = shader;
	}
	
	public void renderAll() {
		Set<TexturedModel> models = entities.keySet();
		for (TexturedModel texturedModel : models) {
			renderEntities(texturedModel);
		}
	}
	
	public void renderEntities(TexturedModel tmodel) {
		RawModel model = tmodel.getRawModel();
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		ModelTexture texture = tmodel.getTexture();
		if(texture.isHasTransparency()) {
			disableCull();
		}
		shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, tmodel.getTexture().getID());
		List<Entity> owned = entities.get(tmodel);
		for (Entity entity : owned) {
			Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
			shader.loadTransformationMatrix(transformationMatrix);
			GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		}
		enableCull();
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}
	
	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClearColor(0f, 0.25f, 0f, 1f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
	}
	public void render(Entity entity, StaticShader shader) {
	}
	
	private void createProjectionMatrix() {
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(Graphics.FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = Graphics.FAR_PLANE - Graphics.NEAR_PLANE;
		
		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((Graphics.FAR_PLANE + Graphics.NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * Graphics.NEAR_PLANE + Graphics.FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
	}
	public static void enableCull() {
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	public static void disableCull() {
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
}
