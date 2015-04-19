package main;

import main.camera.Camera;
import main.maths.Transform;
import main.maths.Vector3f;
import main.models.Shader;
import main.objects_3d.Cube;
import main.utils.ResourceLoader;

public class Game {
	
	private Shader shader;
	private Transform transform;
	private Camera camera;
	
	private Cube cube;
		
	public Game() {
		shader = new Shader();
		camera = new Camera(new Vector3f(0, 1, 0), new Vector3f(0, 0, 1), new Vector3f(0, 1, 0));
				
		cube = new Cube(0, 0, 0, 1);
		
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
		cube.draw();
	}
}
