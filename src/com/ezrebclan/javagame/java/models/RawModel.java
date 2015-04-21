package com.ezrebclan.javagame.java.models;

public class RawModel {

	private int vaoID;
	private int vertexCount;
	
	public RawModel(int vaoID, int vertexCount) {
		super();
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}

	/**
	 * @return the vaoID
	 */
	public int getVaoID() {
		return vaoID;
	}

	/**
	 * @return the vertexCount
	 */
	public int getVertexCount() {
		return vertexCount;
	}
}
