package Shape;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public abstract class Obj extends Shape{
	private int offset = 5;
	protected int width,height;
	protected String name = "Object Name";
	protected Font font = new Font(Font.DIALOG, Font.BOLD, 14);
	protected Port[] ports = new Port[4];
	
	
	
	public abstract void draw(Graphics g);
	
	public void show(Graphics g) {	//object的四個ports
		for(int i = 0; i < ports.length; i++) {
			g.fillRect(ports[i].x, ports[i].y, ports[i].width, ports[i].height);
		}
	}
	
	//判斷點
	public String inside(Point p) {
		
		Point centerpPoint = new Point();
		centerpPoint.x = (x1 + x2) / 2;
		centerpPoint.y = (y1 + y2) / 2;
		
		//�|�Ө����y��
		Point [] points = {new Point(x1, y1), new Point(x2,y1), new Point(x2,y2), new Point(x1,y2) };
		
		for(int i = 0; i < points.length; i++) {
			Polygon checkInside = new Polygon();
			
			//先new 一個多邊形物件Polygon，再用contains()方法來判斷是否在裡面
			int next_point_index = (i + 1) % 4;
			checkInside.addPoint(points[i].x, points[i].y);
			checkInside.addPoint(points[next_point_index].x, points[next_point_index].y);
			checkInside.addPoint(centerpPoint.x, centerpPoint.y);
			
			if(checkInside.contains(p)) {
//				System.out.println(i);
				return Integer.toString(i);
			}
			
		}
		
		
		return null;
	}
	
	protected void createPorts() {
		int[] xpoint = {(x1+x2)/2, x2 + offset, (x1+x2)/2, x1 - offset};
		int[] ypoint = {y1 - offset, (y1+y2)/2, y2 + offset, (y1+y2)/2};
		
		for(int i = 0; i < ports.length; i++) {
			Port port = new Port();
			port.setPort(xpoint[i],ypoint[i],offset);
			ports[i] = port;
		}
		
		
	}
	
	public Port getPort(int index) {
		return ports[index];
	}
	
	//obj mousedragged
	public void resetLocation(int offsetX,int offsetY) {
		
		//²��]�w�s��x1y1 , x2y2
		int x1 = this.x1 + offsetX;
		int y1 = this.y1 + offsetY;
		
		this.x1 = x1;
		this.y1 = y1;
		
		this.x2 = x1 + width;
		this.y2 = y1 + height;
		
		//�]�w�s��ports
		int [] xpoint = {(x1+x2)/2, x2 + offset, (x1+x2)/2, x1 - offset};
		int [] ypoint = {y1 - offset, (y1+y2)/2, y2 + offset, (y1+y2)/2}; 
		
		for(int i = 0; i < ports.length; i++) {
			ports[i].setPort(xpoint[i], ypoint[i], offset);
			ports[i].resetLine();
		}
		
	}
	
	public void changeName(String name) {
		this.name = name;
	}
	
	
}
