package main.utils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import main.maths.Matrix4f;
import main.models.Vertex;

import org.lwjgl.BufferUtils;

public class Utils {

	public static FloatBuffer createFloatBuffer(int size){
		return BufferUtils.createFloatBuffer(size);
	}
	
	public static IntBuffer createIntBuffer(int size){
		return BufferUtils.createIntBuffer(size);
	}
	
	public static IntBuffer createFlippedBuffer(int[] i){
		IntBuffer buffer = createIntBuffer(i.length);
		
		buffer.put(i);
		buffer.flip();
		
		return buffer;
	}
	
	public static FloatBuffer createFlippedBuffer(Vertex[] vertices){
		
		FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);
		
		for(int i = 0; i < vertices.length; i++){
			buffer.put(vertices[i].getPos().getX());
			buffer.put(vertices[i].getPos().getY());
			buffer.put(vertices[i].getPos().getZ());
		}
		
		buffer.flip();
		
		return buffer;
		
	}
	
	public static FloatBuffer createFlippedBuffer(Matrix4f mat){
		
		FloatBuffer buffer = createFloatBuffer(4 * 4);
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				buffer.put(mat.get(i, j));
			}
		}
		
		buffer.flip();
		
		return buffer;
		
	}
	
	public static String[] removeEmptyStrings(String[] data){
		ArrayList<String> result = new ArrayList<String>();
		
		for(int i = 0; i < data.length; i++)
			if(!data[i].equals(""))
				result.add(data[i]);
		
		String[] res = new String[result.size()];
		result.toArray(res);
		
		return res;
	}
	
	public static int[] toIntArray(Integer[] data){
		int[] result = new int[data.length];
		
		for(int i = 0; i < data.length; i++)
			result[i] = data[i].intValue();
		
		return result;
	}
	
}
