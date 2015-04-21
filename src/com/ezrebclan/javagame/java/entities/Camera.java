package com.ezrebclan.javagame.java.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private Vector3f position = new Vector3f(0, 0, 0);
	private float pitch;
	private float yaw;
	private float roll;
	public Camera() {
	}
	
	public void move() {
		float speed = 0.02f;
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			speed = 0.2f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.z -= speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.z += speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x += speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x -= speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			position.y += speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.y -= speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			position = new Vector3f(0, 0, 0);
		}
	}
	
	/**
	 * @return the position
	 */
	public Vector3f getPosition() {
		return position;
	}
	/**
	 * @return the pitch
	 */
	public float getPitch() {
		return pitch;
	}
	/**
	 * @return the yaw
	 */
	public float getYaw() {
		return yaw;
	}
	/**
	 * @return the roll
	 */
	public float getRoll() {
		return roll;
	}
	
	
}
