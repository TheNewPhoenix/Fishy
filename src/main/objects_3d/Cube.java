package main.objects_3d;

import main.maths.Vector3f;
import main.models.Mesh;
import main.models.Vertex;

public class Cube extends Mesh {

	public Cube(int x, int y, int z, int size){
		
		Vertex[] vertices = new Vertex[8];
		int vertexPointer = 0;
		for(int x1 = 0; x1 < 2; x1++){
			for(int y1 = 0; y1 < 2; y1++){
				for(int z1 = 0; z1 < 2; z1++){
					vertices[vertexPointer] = new Vertex(new Vector3f(x1 + x, y1 + y, z1 + z));
					System.out.println(x1 + "," +  y1 + "," + z1);
					vertexPointer++;
				}
			}
		}
		
		int[] indices = new int[]{
				0, 1, 2,
			    2, 3, 0,
			    // top
			    3, 2, 6,
			    6, 7, 3,
			    // back
			    7, 6, 5,
			    5, 4, 7,
			    // bottom
			    4, 5, 1,
			    1, 0, 4,
			    // left
			    4, 0, 3,
			    3, 7, 4,
			    // right
			    1, 5, 6,
			    6, 2, 1,
		};		
		
		addVertices(vertices, indices);
	}
	
}
