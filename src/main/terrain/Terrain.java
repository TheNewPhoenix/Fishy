package main.terrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.maths.Vector3f;
import main.models.Mesh;
import main.models.Vertex;

public class Terrain {
	
	private final int SECTIONSIZE = 8;
	private final Random random = new Random();
	private int size;
	private List<Mesh> meshes = new ArrayList<Mesh>();
	
	public Terrain(int size){
		this.size = size;
		generateTerrain();
	}
	
	private void generateTerrain(){		
		
		for(int width = 0; width < size; width++){
			for(int length = 0; length < size; length++){
				Mesh mesh = new Mesh();
				
				Vertex[] vertices = new Vertex[(SECTIONSIZE + 1) * (SECTIONSIZE + 1)];
				int vertexPointer = 0;
				for(int x = 0; x < SECTIONSIZE + 1; x++){
					for(int z = 0; z < SECTIONSIZE + 1; z++){
						vertices[vertexPointer] = new Vertex(new Vector3f(x + (8 * (width)), random.nextFloat(), z + (8 * (length))));
						//System.out.println(vertices[vertexPointer].getPos().getX() + ", " + vertices[vertexPointer].getPos().getZ());
						vertexPointer++;
					}
				}
				
				int[] indices = new int[6 * SECTIONSIZE * SECTIONSIZE];
				int pointer = 0;
				for(int gz = 0; gz < SECTIONSIZE; gz++){
					for(int gx = 0; gx < SECTIONSIZE; gx++){
						int bottomLeft = (gz*(SECTIONSIZE+1))+(gx);
						int bottomRight = bottomLeft + 1;
						int topLeft = ((gz+1) * (SECTIONSIZE + 1))+ gx;
						int topRight = topLeft + 1;
						indices[pointer++] = bottomLeft;
						indices[pointer++] = bottomRight;
						indices[pointer++] = topLeft;
						indices[pointer++] = bottomRight;
						indices[pointer++] = topRight;
						indices[pointer++] = topLeft;
						//System.out.println(topLeft + "," + topRight + "," + bottomLeft + "," + bottomRight);
						//System.out.println(bottomLeft + "," + bottomRight + "," + topLeft);
						//System.out.println(bottomRight + "," + topRight + "," + topLeft);
					}
				}
				
				mesh.addVertices(vertices, indices);
				
				meshes.add(mesh);
			}
		}
	}
	
	public void draw(){
		for(int i = 0; i < meshes.size(); i++){
			meshes.get(i).draw();
		}
	}
}
