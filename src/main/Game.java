package main;

import main.camera.Camera;
import main.input.Input;
import main.maths.Transform;
import main.models.Shader;
import main.terrain.Terrain;
import main.utils.ResourceLoader;

public class Game {
	
	//private Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Camera camera;
	private Terrain terrain;
	
	public Game() {
		//mesh = ResourceLoader.loadMesh("cube.obj");
		shader = new Shader();
		camera = new Camera();
		terrain = new Terrain(4);
		
		Transform.setProjection(70f, Main.WIDTH, Main.HEIGHT, 0.1f, 1000);
		Transform.setCamera(camera);
		transform = new Transform();

		shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vs"));
		shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fs"));
		shader.compileShader();
		
		shader.addUniform("transform");
	}
	
	float temp = 0.0f;
	
	public void input(){
		camera.input();
	}
	
	public void update(){
		
		input();
		
		transform.setTranslation(0, 0, 5);
		transform.setRotation(0, temp, 0);
	}
	
	public void render(){
		shader.bind();
		shader.setUniform("transform", transform.getProjectedTransformation());
		terrain.draw();
		//mesh.draw();
	}
}
