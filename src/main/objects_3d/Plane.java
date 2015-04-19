package main.objects_3d;

import main.maths.Vector3f;
import main.models.Mesh;
import main.models.Vertex;

public class Plane extends Mesh {
	
	public Plane(int x, int z, int size){
		
		Vertex[] vertices = new Vertex[(size + 1) * (size + 1)];
		int vertexPointer = 0;
		for(int x1 = 0; x1 < size + 1; x1++){
			for(int z1 = 0; z1 < size + 1; z1++){
				vertices[vertexPointer] = new Vertex(new Vector3f(x1 + x, 0, z1 + z));
				vertexPointer++;
			}
		}
		
		int[] indices = new int[6 * size * size];
		int pointer = 0;
		for(int gz = 0; gz < size; gz++){
			for(int gx = 0; gx < size; gx++){
				int bottomLeft = (gz*(size+1))+(gx);
				int bottomRight = bottomLeft + 1;
				int topLeft = ((gz+1) * (size + 1))+ gx;
				int topRight = topLeft + 1;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = bottomRight;
				indices[pointer++] = topLeft;
				indices[pointer++] = bottomRight;
				indices[pointer++] = topRight;
				indices[pointer++] = topLeft;
			}
		}
		
		addVertices(vertices, indices);
	}
	
}
