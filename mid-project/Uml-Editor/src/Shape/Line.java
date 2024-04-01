package Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public abstract class Line extends Shape{
	protected Port ports[] = new Port[2]; // 存obj 連接的兩個ports
	
	public abstract void draw(Graphics g);
	
	private String selectedFlag = null;
	
	public void setPorts(Port p1, Port p2) {
		this.ports[0] = p1;
		this.ports[1] = p2;
	}
	
	
	
	public void show(Graphics g) {	// call draw()
//		g.setColor(Color.white);
		this.draw(g);
		g.setColor(Color.black);
		
	}

	public void resetLocation() { //將x y 重新改成ports 的中心點
		this.x1 = (int)ports[0].getCenterX();
		this.y1 = (int)ports[0].getCenterY();
		this.x2 = (int)ports[1].getCenterX();
		this.y2 = (int)ports[1].getCenterY();
		
	}
	
	public void resetStartEnd(Point p) { //
		if(selectedFlag == "start") {
			this.x1 = p.x;
			this.y1 = p.y;
		}else if(selectedFlag == "end"){
			this.x2 = p.x;
			this.y2 = p.y;
		}
	}
	
	public String inside(Point p) { //
		int tolerance = 5;
		if(getdistance(p) < tolerance) {
			double distTostart = Math.sqrt(Math.pow(p.x - x1, 2) - Math.pow(p.y - y1, 2));
			double distToEnd = Math.sqrt(Math.pow(p.x - x2, 2) - Math.pow(p.y - y2, 2));
			
			if(distTostart < distToEnd) {
				selectedFlag = "start";
			}else {
				selectedFlag = "end";
			}
			return "Lineinside";
			
		}else {
			return null;
		}
		
	}
	
	public void resetPorts(Port port, Line line) {
		port.addLine(line);
		if(selectedFlag == "start") {
			
			this.ports[0].removeLine(line);
			this.ports[0] = port;
		
		}else if(selectedFlag == "end") {
		
			this.ports[1].removeLine(line);
			this.ports[1] = port;
		}
	}
	
	private double getdistance(Point p) {
		Line2D line2d = new Line2D.Double(x1,y1,x2,y2);
		
		return line2d.ptLineDist(p.getX(), p.getY());
	}
	
}
