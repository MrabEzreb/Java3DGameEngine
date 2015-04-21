package com.ezrebclan.javagame.java.textures;

public class ModelTexture {

	private int textureID;

	private float shineDamper = 1;
	private float reflectivity = 0;
	
	private boolean hasTransparency = false;
	
	public ModelTexture(int textureID) {
		this.textureID = textureID;
	}

	/**
	 * @return the ID
	 */
	public int getID() {
		return textureID;
	}

	/**
	 * @return the shineDamper
	 */
	public float getShineDamper() {
		return shineDamper;
	}

	/**
	 * @param shineDamper the shineDamper to set
	 */
	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	}

	/**
	 * @return the reflectivity
	 */
	public float getReflectivity() {
		return reflectivity;
	}

	/**
	 * @param reflectivity the reflectivity to set
	 */
	public void setReflectivity(float reflectivity) {
		this.reflectivity = reflectivity;
	}

	/**
	 * @return the hasTransparency
	 */
	public boolean isHasTransparency() {
		return hasTransparency;
	}

	/**
	 * @param hasTransparency the hasTransparency to set
	 */
	public void setHasTransparency(boolean hasTransparency) {
		this.hasTransparency = hasTransparency;
	}
}
