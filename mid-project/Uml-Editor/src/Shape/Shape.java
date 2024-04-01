package Shape;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Shape {
	protected int x1,y1,x2,y2;
	public boolean group_selected = false;
	
	public abstract void draw(Graphics g);
	
	
	public int getX1() {
		return x1;
	}
	
	public int getY1() {
		return y1;
	}
	
	public int getX2() {
		return x2;
	}
	
	public int getY2() {
		return y2;
	}
	
	// line 重設位置
	public void resetLocation() {}
	
	//obj重設位置
	public void resetLocation(int offsetX, int offsetY) {
		
	}
	
	public void changeName(String name) {
		
	}
	
	public void show(Graphics g) {
		
	}
	
	// 判斷點是否在object內?
	public String inside(Point p) {
		return null;
	}
	
	//for Obj
	public Port getPort(int index) {
		return null;
	}
	
	//for group
	public void resetSelectedShape() {
		
	}
	
	public Shape getSelectedShape() {
		return null;
	}
	
}
