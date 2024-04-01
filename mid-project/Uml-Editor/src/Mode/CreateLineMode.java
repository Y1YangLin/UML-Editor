package Mode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

import Shape.AssoLine;
import Shape.CompLine;
import Shape.GeneLine;
import Shape.Line;
import Shape.Shape;

public class CreateLineMode extends Mode{
	private String linename = null;

	private Point prev = null;
	private List<Shape> shapes;
	private int portIndex_1 = -1,portIndex_2 = -1;
	private Shape shape_1 = null, shape_2 = null;
	
	
	public CreateLineMode(String name){
		linename = name;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		shapes = canvas.getShapesList();
		prev = findConnectedObj(e.getPoint(),"first");
		
	}
	
	private Point findConnectedObj(Point point, String order) {
		for(int i = 0; i < shapes.size(); i++) {
		
			Shape shape = shapes.get(i);
			
			int portIndex;
			String checkInside = shape.inside(point);
			
			if(checkInside != null && checkInside != "Lineinside") {
				
				if(checkInside == "insideGroup") {
					shape = shape.getSelectedShape();
					portIndex = Integer.parseInt(shape.inside(point));
					
				}else {
					portIndex = Integer.parseInt(checkInside);
				}
			
				switch(order) {
					case "first" :
						shape_1 = shape;
						portIndex_1 = portIndex;
						break;
					case "second" :
						shape_2 = shape;
						portIndex_2 = portIndex;
						break;
				}
				
				Point portLocation = new Point();
				portLocation.setLocation(shape.getPort(portIndex).getCenterX(), shape.getPort(portIndex).getCenterY());
				
				return portLocation;
			}
			
		}
		
		
		return null;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(prev != null) {

			Line line = null;
			Point p = e.getPoint();
			
			switch(linename) {
				case "association":
					line = new AssoLine(prev.x, prev.y, p.x, p.y);
					break;
				case "generalization":
					line = new GeneLine(prev.x, prev.y, p.x, p.y);
					break;
			
				case "composition":
					line = new CompLine(prev.x, prev.y, p.x, p.y);
					break;
					
				default:
					break;
			}
			
			
			if(line != null) {
				canvas.tempLine = line;
			}
			
			canvas.repaint();
		
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		Point end = null;
		
		if(prev != null) {
		
			end = findConnectedObj(e.getPoint(),"second");
			
			if(end != null) {

				Line line = null;
				
				switch(linename) {
				case "association":
					line = new AssoLine(prev.x, prev.y, end.x, end.y);
					break;
				case "generalization":
					line = new GeneLine(prev.x, prev.y, end.x, end.y);
					break;
			
				case "composition":
					line = new CompLine(prev.x, prev.y, end.x, end.y);
					break;
					
				default:
					break;
			}
				
				if(line != null) {
					canvas.addshape(line);
				}
				
				
				line.setPorts(shape_1.getPort(portIndex_1),shape_2.getPort(portIndex_2));
				
				
				shape_1.getPort(portIndex_1).addLine(line);
				shape_2.getPort(portIndex_2).addLine(line);
				
			}
			
			canvas.tempLine = null;
			canvas.repaint();
			prev = null;
			
		}
	}
}
