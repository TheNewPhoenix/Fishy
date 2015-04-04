package main.models;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

import java.util.HashMap;

import main.maths.Matrix4f;
import main.maths.Vector3f;
import main.utils.Utils;

public class Shader {

	private int program;
	private HashMap<String, Integer> uniforms;
	
	public Shader(){
		
		program = glCreateProgram();
		uniforms = new HashMap<String, Integer>();
		
		if(program == 0){
			System.err.println("Shader creation failed: Could not find sufficient memory location");
			System.exit(1);
		}
		
	}
	
	public void bind(){
		glUseProgram(program);
	}
	
	public void addUniform(String uniform){
		
		int uniformLocation = glGetUniformLocation(program, uniform);
		
		if(uniformLocation == 0xFFFFFFFF){
			System.err.println("Error: Could not find uniform: " + uniform);
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		uniforms.put(uniform, uniformLocation);
		
	}
	
	public void addVertexShader(String text){
		addProgram(text, GL_VERTEX_SHADER);
	}
	
	public void addGeometryShader(String text){
		addProgram(text, GL_GEOMETRY_SHADER);
	}

	public void addFragmentShader(String text){
		addProgram(text, GL_FRAGMENT_SHADER);
	}
	
	public void compileShader(){
		
		glLinkProgram(program);
		
		if(glGetProgrami(program, GL_LINK_STATUS) == 0){
			System.err.println(glGetShaderInfoLog(program, 1024));
			System.exit(1);
		}
		
		glValidateProgram(program);
		
		if(glGetProgrami(program, GL_VALIDATE_STATUS) == 0){
			System.err.println(glGetShaderInfoLog(program, 1024));
			System.exit(1);
		}
		
	}
	
	private void addProgram(String text, int type){
		
		int shader = glCreateShader(type);
		
		if(program == 0){
			System.err.println("Shader creation failed: Could not find sufficient memory location when adding shader");
			System.exit(1);
		}
		
		glShaderSource(shader, text);
		glCompileShader(shader);
		
		if(glGetShaderi(shader, GL_COMPILE_STATUS) == 0){
			System.err.println(glGetShaderInfoLog(shader, 1024));
			System.exit(1);
		}
		
		glAttachShader(program, shader);
	}

	public void setUniformi(String uniformName, int i){
		glUniform1i(uniforms.get(uniformName), i);
	}
	
	public void setUniformf(String uniformName, float i){
		glUniform1f(uniforms.get(uniformName), i);
	}
	
	public void setUniform(String uniformName, Vector3f vec){
		glUniform3f(uniforms.get(uniformName), vec.getX(), vec.getY(), vec.getZ());
	}
	
	public void setUniform(String uniformName, Matrix4f mat){
		glUniformMatrix4(uniforms.get(uniformName), true, Utils.createFlippedBuffer(mat));
	}
	
}
