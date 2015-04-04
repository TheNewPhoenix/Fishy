package main.maths;

public class Vector2f {

	private float x, y;
	
	public Vector2f(float x, float y){
		this.x = x;
		this.y = y;
	}

	public float length(){
		return (float) Math.sqrt(x * x + y * y);
	}
	
	public float dot(Vector2f vec){
		return x * vec.getX() + y * vec.getY();
	}
	
	public Vector2f normalize(){
		
		float length = length();
		
		x /= length;
		y /= length;
		
		return this;
		
	}
	
	public Vector2f rotate(float angle){
		
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);
		
		return new Vector2f((float) (x * cos - y * sin), (float) (x * sin + y * cos));
		
	}
	
	public Vector2f add(Vector2f vec){
		return new Vector2f(x + vec.getX(), y + vec.getY());
	}
	
	public Vector2f add(float f){
		return new Vector2f(x + f, y + f);
	}
	
	public Vector2f sub(Vector2f vec){
		return new Vector2f(x - vec.getX(), y - vec.getY());
	}
	
	public Vector2f sub(float f){
		return new Vector2f(x - f, y - f);
	}
	
	public Vector2f mult(Vector2f vec){
		return new Vector2f(x * vec.getX(), y * vec.getY());
	}
	
	public Vector2f mult(float f){
		return new Vector2f(x * f, y * f);
	}
	
	public Vector2f div(Vector2f vec){
		return new Vector2f(x / vec.getX(), y / vec.getY());
	}
	
	public Vector2f div(float f){
		return new Vector2f(x / f, y / f);
	}
	
	public String toString(){
		return new String("(" + x + ", " + y + ")");
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
