package Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import Gui.Canvas;

public class Group extends Shape{
	private List<Shape> shapes = new ArrayList<Shape>();
	private Rectangle bounds = new Rectangle();
	private Shape selectedShape = null;
	private Canvas canvas = Canvas.getInstance();
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		for(int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			
			shape.draw(g);
			
//			if(canvas.selectedObj == shape) {
//				shape.show(g);
//			}
			
		}
	}

	public void show(Graphics g) {
		int transparent = 85;
		int offset = 10;
		
		g.setColor(new Color(204, 204, 255,transparent));
		g.fillRect(bounds.x - offset, bounds.y - offset, bounds.width + offset*2, bounds.height + 2 * offset);
		
		g.setColor(Color.black);
		
		if(selectedShape != null) {
			selectedShape.show(g);
		}
		
//		for(int i = 0; i < shapes.size(); i++) {
//			Shape shape = shapes.get(i);
//			
//			if(canvas.selectedObj == shape) {
//				shape.show(g);
//			}
//				
//		}
		
		
		
	}
	
	public void resetLocation(int offsetX,int offsetY) {
		for(int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			shape.resetLocation(offsetX,offsetY);
		}
		resetBounds(offsetX,offsetY);
	}
	
	public String inside(Point p) {
		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			String checkInside = shape.inside(p);
			
			if (checkInside != null) {
				selectedShape = shape;
				return "insideGroup";
			}
		
		}
		return null;
	}
	
	public void changeName(String name) {
		selectedShape.changeName(name);
	}

	public void resetSelectedShape() {
		selectedShape = null;
	}
	
	public Shape getSelectedShape() {
		return selectedShape;
	}
	
	public void setBounds() {
		// 找到組合物件的左右最大值
		Point upLeftP, bottomRightP;
		
		int leftX = Integer.MAX_VALUE, rightX = Integer.MIN_VALUE;
		int upY = Integer.MAX_VALUE, bottomY = Integer.MIN_VALUE;

		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			if (shape.getX1() < leftX) {
				leftX = shape.getX1();
			}
			if (shape.getX2() > rightX) {
				rightX = shape.getX2();
			}
			if (shape.getY1() < upY) {
				upY = shape.getY1();
			}
			if (shape.getY2() > bottomY) {
				bottomY = shape.getY2();
			}
		}

		upLeftP = new Point(leftX, upY);
		bottomRightP = new Point(rightX, bottomY);
		bounds.setBounds(upLeftP.x, upLeftP.y, Math.abs(upLeftP.x - bottomRightP.x),
				Math.abs(upLeftP.y - bottomRightP.y));
		// set parent shape coordinate
		x1 = bounds.x;
		y1 = bounds.y;
		x2 = bounds.x + bounds.width;
		y2 = bounds.y + bounds.height;
	}
	
	protected void resetBounds(int offsetX, int offsetY) {
		
		bounds.setLocation(bounds.x + offsetX, bounds.y + offsetY);
		x1 = bounds.x;
		y1 = bounds.y;
		x2 = bounds.x + bounds.width;
		y2 = bounds.y + bounds.height;
		
	}
	
	public void addShapes(Shape shape) {
		shapes.add(shape);
	}

	public List<Shape> getShapes() {
		return shapes;
	}
	
}
