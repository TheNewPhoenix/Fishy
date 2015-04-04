package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Window {

	public static void create(int width, int height, String name){
		Display.setTitle(name);
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setResizable(true);
			Display.create();
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public static void render(){
		if(Display.wasResized()){
			GL11.glViewport(0, 0, getWidth(), getHeight());
		}
		Display.update();
	}
	
	public static void dispose(){
		Display.destroy();
	}
	
	public static boolean isCloseRequested(){
		return Display.isCloseRequested();
	}
	
	public static int getWidth(){
		return Display.getWidth();
	}
	
	public static int getHeight(){
		return Display.getHeight();
	}
	
}
