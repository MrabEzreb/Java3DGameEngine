package com.ezrebclan.javagame.java.models;

import java.util.Vector;

import com.ezrebclan.javagame.java.entities.Entity;
import com.ezrebclan.javagame.java.renderEngine.EntityRenderer;
import com.ezrebclan.javagame.java.textures.ModelTexture;

public class TexturedModel {

	private RawModel rawModel;
	private ModelTexture texture;
	public TexturedModel(RawModel rawModel, ModelTexture texture, EntityRenderer renderer) {
		super();
		this.rawModel = rawModel;
		this.texture = texture;
		renderer.entities.put(this, new Vector<Entity>());
	}
	/**
	 * @return the rawModel
	 */
	public RawModel getRawModel() {
		return rawModel;
	}
	/**
	 * @return the texture
	 */
	public ModelTexture getTexture() {
		return texture;
	}
}
