package com.ezrebclan.javagame.java.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import com.ezrebclan.javagame.java.models.TexturedModel;
import com.ezrebclan.javagame.java.renderEngine.EntityRenderer;

public class Entity {

	private TexturedModel model;
	private Vector3f position;
	private float orX, orY, orZ;
	private float rotX,rotY,rotZ;
	private Vector3f scale;
	
	public Entity(TexturedModel model, Vector3f position, float rotX,
			float rotY, float rotZ, Vector3f scale, EntityRenderer render) {
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.orX = this.rotX;
		this.orY = this.rotY;
		this.orZ = this.rotZ;
		this.scale = scale;
		render.entities.get(model).add(this);
	}

	public Entity(TexturedModel model, Vector3f position, float rotX,
			float rotY, float rotZ, float scale, EntityRenderer render) {
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.orX = this.rotX;
		this.orY = this.rotY;
		this.orZ = this.rotZ;
		this.scale = new Vector3f(scale, scale, scale);
		render.entities.get(model).add(this);
	}

	public void increasePosition(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
	}
	
	public void increaseRotation(float dx, float dy, float dz) {
		this.rotX += dx;
		this.rotY += dy;
		this.rotZ += dz;
	}

	public void move() {
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			this.rotY += 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			this.rotY -= 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			this.rotX += 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			this.rotX -= 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)) {
			this.rotX = orX;
			this.rotY = orY;
			this.rotZ = orZ;
		}
	}
	
	/**
	 * @return the model
	 */
	public TexturedModel getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(TexturedModel model) {
		this.model = model;
	}

	/**
	 * @return the position
	 */
	public Vector3f getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Vector3f position) {
		this.position = position;
	}

	/**
	 * @return the rotX
	 */
	public float getRotX() {
		return rotX;
	}

	/**
	 * @param rotX the rotX to set
	 */
	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	/**
	 * @return the rotY
	 */
	public float getRotY() {
		return rotY;
	}

	/**
	 * @param rotY the rotY to set
	 */
	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	/**
	 * @return the rotZ
	 */
	public float getRotZ() {
		return rotZ;
	}

	/**
	 * @param rotZ the rotZ to set
	 */
	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	/**
	 * @return the scale
	 */
	public Vector3f getScale() {
		return scale;
	}

	/**
	 * @param scale the scale to set
	 */
	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
	
	/**
	 * @param scale the scale to set
	 */
	public void setScale(float scale) {
		this.scale = new Vector3f(scale, scale, scale);
	}
}
