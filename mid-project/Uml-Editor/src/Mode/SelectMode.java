package Mode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

import Shape.Line;
import Shape.Shape;

public class SelectMode extends Mode{
	private Point prev;
	private List<Shape> shapes;
	private String InsideChecker = null;
	private Line selectedLine = null;
	private String prev_InsideChecker = null;
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		prev = e.getPoint();
		this.shapes = this.canvas.getShapesList();
		
		//將selected的物件設為null
		canvas.reset();
//		System.out.println("pressed");
		
		//判斷滑鼠點是否在所有物件裡面
		for(int i = shapes.size() - 1; i >= 0; i--) {
			Shape shape = shapes.get(i);
			InsideChecker = shape.inside(e.getPoint());
			
			if(InsideChecker != null && InsideChecker != prev_InsideChecker) {
				canvas.selectedObj = shape;
				break;
			}
			
		}
		
		prev_InsideChecker = InsideChecker;
		
		canvas.repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int offsetX = e.getX() - prev.x;
		int offsetY = e.getY() - prev.y;
		
		//檢查滑鼠拖曳時產生的長方形有沒有包含物件或直線
		if(canvas.selectedObj != null) {
			// For lines
			if(InsideChecker == "Lineinside") {
				System.out.println("Line selected !!");
//				selectedLine = (Line)canvas.selectedObj;
//				selectedLine.resetStartEnd(e.getPoint());
				
				
			}
			//for objs
			else {
				canvas.selectedObj.resetLocation(offsetX, offsetY);
				
			}
			
			prev.x = e.getX();
			prev.y = e.getY();
		}
		else{
			System.out.println(e.getPoint() + " , " + prev);
			
			if(e.getX() < prev.x && e.getY() < prev.y) { //長方形往左上
//				System.out.println("left upside");
				canvas.selectedArea.setBounds(e.getX(),e.getY(), (Math.abs(offsetX)),(Math.abs(offsetY)));
			}else if(e.getX() > prev.x && e.getY() > prev.y){ //往右下
				canvas.selectedArea.setBounds(prev.x,prev.y, (Math.abs(offsetX)),(Math.abs(offsetY)));
			}else if(e.getX() < prev.x && e.getY() > prev.y) { //往左下
				canvas.selectedArea.setBounds(e.getX(),e.getY() - (Math.abs(offsetY)), (Math.abs(offsetX)),(Math.abs(offsetY)));
//				System.out.println("left down");
			}else { //往右下
				canvas.selectedArea.setBounds(prev.x,prev.y - (Math.abs(offsetY)), (Math.abs(offsetX)),(Math.abs(offsetY)));
			}
			
			

		}
		
		
		canvas.repaint();
//		canvas.selectedArea = null;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(canvas.selectedObj != null) {
			if(InsideChecker == "Lineinside") {
				selectedLine = (Line) canvas.selectedObj;
				reconnectLine(e.getPoint());
			}
		}else {
			canvas.selectedArea.setSize(Math.abs(e.getX() - prev.x),Math.abs(e.getY() - prev.y));
			
		}
		
		
		canvas.repaint();
		
		
	}
	
	private void reconnectLine(Point p) {
		for(int i = 0; i <= shapes.size() - 1; i++) {
			Shape shape = shapes.get(i);
			int portIndex;
			String InsideChecker = shape.inside(p);
			
			if(InsideChecker != null && InsideChecker != "Lineinside") {
				
				if(InsideChecker == "insideGroup") {
					shape = shape.getSelectedShape();
					portIndex = Integer.parseInt(shape.inside(p));
				}else {
					portIndex = Integer.parseInt(InsideChecker);
				}
				
				selectedLine.resetPorts(shape.getPort(portIndex),selectedLine);
				selectedLine.resetLocation();
				
			}
			
		}
	}
	
}
