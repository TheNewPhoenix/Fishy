package main.maths;

public class Vector3f {

	private float x, y, z;

	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float length(){
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
	public float dot(Vector3f vec){
		return x * vec.getX() + y * vec.getY() + x * vec.getZ();
	}
	
	public Vector3f cross(Vector3f vec){
		float x_ = y * vec.getZ() - z * vec.getY();
		float y_ = z * vec.getX() - x * vec.getZ();
		float z_ = x * vec.getY() - y * vec.getX();
		
		return new Vector3f(x_, y_, z_);
	}
	
	public Vector3f normalize(){
		
		float length = length();
		
		x /= length;
		y /= length;
		z /= length;
		
		return this;
		
	}
	
	public Vector3f rotate(float angle, Vector3f axis){
		
		float sinHalfAngle = (float) Math.sin(Math.toRadians(angle / 2));
		float cosHalfAngle = (float) Math.cos(Math.toRadians(angle / 2));

		float rx = axis.getX() * sinHalfAngle;
		float ry = axis.getY() * sinHalfAngle;
		float rz = axis.getZ() * sinHalfAngle;
		float rw = cosHalfAngle;
		
		Quaternion rotation = new Quaternion(rx, ry, rz, rw);
		Quaternion conjugate = rotation.conjugate();
		
		Quaternion w = rotation.Mul(this).Mul(conjugate);
		
		x = w.getX();
		y = w.getY();
		z = w.getZ();
		
		return this;
	}
	
	public Vector3f add(Vector3f vec){
		return new Vector3f(x + vec.getX(), y + vec.getY(), z + vec.getZ());
	}
	
	public Vector3f add(float f){
		return new Vector3f(x + f, y + f, z + f);
	}
	
	public Vector3f sub(Vector3f vec){
		return new Vector3f(x - vec.getX(), y - vec.getY(), z - vec.getZ());
	}
	
	public Vector3f sub(float f){
		return new Vector3f(x - f, y - f, z - f);
	}
	
	public Vector3f mult(Vector3f vec){
		return new Vector3f(x * vec.getX(), y * vec.getY(), z * vec.getZ());
	}
	
	public Vector3f mult(float f){
		return new Vector3f(x * f, y * f, z * f);
	}
	
	public Vector3f div(Vector3f vec){
		return new Vector3f(x / vec.getX(), y / vec.getY(), z / vec.getZ());
	}
	
	public Vector3f div(float f){
		return new Vector3f(x / f, y / f, z / f);
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

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
}
