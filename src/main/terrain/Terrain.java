package main.terrain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import main.maths.Vector3f;
import main.models.Mesh;
import main.models.Vertex;

public class Terrain {
	
	private static final float MAX_HEIGHT = 40;
	private static final float MAX_PIXEL_COLOR = 256 * 256 * 256;
	
	private final Random random = new Random();
	private List<Mesh> meshes = new ArrayList<Mesh>();
	
	public Terrain(String fileName){
		generateTerrain(fileName);
	}
	
	private void generateTerrain(String fileName){		
		
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int SECTIONSIZE = image.getWidth();
		
		Mesh mesh = new Mesh();
		
		Vertex[] vertices = new Vertex[(SECTIONSIZE + 1) * (SECTIONSIZE + 1)];
		int vertexPointer = 0;
		for(int x = 0; x < SECTIONSIZE + 1; x++){
			for(int z = 0; z < SECTIONSIZE + 1; z++){
				vertices[vertexPointer] = new Vertex(new Vector3f(x, getHeight(x - 1, z - 1, image), z));
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
			}
		}
		
		mesh.addVertices(vertices, indices);
		
		meshes.add(mesh);
	}
	
	private float getHeight(int x, int z, BufferedImage image){
		if(x < 0 || x >= image.getWidth() || z < 0 || z > image.getWidth()) 
			return 0;
		float height = image.getRGB(x, z);
		height += MAX_PIXEL_COLOR / 2f;
		height /= MAX_PIXEL_COLOR / 2f;
		height *= MAX_HEIGHT;
		return height;
	}
	
	public void draw(){
		for(int i = 0; i < meshes.size(); i++){
			meshes.get(i).draw();
		}
	}
}
