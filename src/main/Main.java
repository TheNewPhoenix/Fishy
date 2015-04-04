package main;

import main.input.Input;
import main.utils.RenderUtils;

public class Main {
	
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 600;
	public static final String NAME = "Game";
	public static final double FRAME_CAP = 60;
	
	private boolean running = false;
	private final Game game;
	
	public Main(){
		System.out.println(RenderUtils.getOpengGLVersion());
		RenderUtils.initGraphics();
		game = new Game();
	}
	
	/**
	 * Method to start game.
	 */
	public void start(){
		if(running)
			return;

		running = true;
		
		run();
	}
	
	public void stop(){
		if(!running)
			return;
		running = false;
	}
	
	private void run(){

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000D / FRAME_CAP;
		double delta = 0;

		int frames = 0;
		int ticks = 0;
				
		while(running){
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while(delta > 1){
				game.update();
				Input.update();
				ticks++;
				delta--;
			}
	
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(ticks + " ticks, " + frames + " frames");
				ticks = 0;
				frames = 0;
			}
			
			if(Window.isCloseRequested())
				stop();
		}
		
		cleanUp();
	}
	
	private void render(){
		RenderUtils.clearScreen();
		game.render();
		Window.render();
	}
	
	private void cleanUp(){
		Window.dispose();
	}
	
	public static void main(String[] args){
		Window.create(WIDTH, HEIGHT, NAME);
		
		Main main = new Main();
		
		main.start();
	}
	
}
