package main.models;

import main.maths.Vector3f;

public class Vertex {

	public static final int SIZE = 3;
	
	private Vector3f pos;

	public Vertex(Vector3f pos) {
		super();
		this.pos = pos;
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}
	
}
