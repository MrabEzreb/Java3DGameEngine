package com.ezrebclan.javagame.java.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Light {

	private Vector3f position;
	private final Vector3f oposition;
	private Vector3f color;
	public Entity bulb; 
	public Light(Vector3f position, Vector3f color, Vector3f resetPosition, Entity bulb) {
		this.position = position;
		this.color = color;
		this.oposition = resetPosition;
		this.bulb = bulb;
	}
	public Light(Vector3f position, Vector3f oposition, Vector3f color) {
		this.position = position;
		this.oposition = oposition;
		this.color = color;
	}
	public void move() {
		if(Keyboard.isKeyDown(Keyboard.KEY_I)) {
			position.z -= 0.06f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_K)) {
			position.z += 0.06f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_J)) {
			position.x -= 0.06f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_L)) {
			position.x += 0.06f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_U)) {
			position.y += 0.06f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_O)) {
			position.y -= 0.06f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_BACKSLASH)) {
			position = new Vector3f(oposition);
		}
		if(bulb != null) {
			bulb.setPosition(position);
		}
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
	 * @return the color
	 */
	public Vector3f getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(Vector3f color) {
		this.color = color;
	}
	
}
