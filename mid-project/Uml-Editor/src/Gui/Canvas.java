package Gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import javax.swing.JPanel;

import Mode.Mode;
import Shape.Group;
import Shape.Obj;
import Shape.Shape;

/*
 ��ҼҦ� singleton
 �ت� : �O�Ҥ@�����O�u�|���ͤ@�Ӫ���A�ӥB�n���Ѧs���Ӫ��󪺲Τ@��k
 
 * */


public class Canvas extends JPanel{
	private static Canvas instance = null;	//singleton
	private EventListener listener = null;
	protected Mode current_Mode = null;
	
	private List<Shape> shapes = new ArrayList<Shape>(); // �h��
	
	public Shape tempLine = null;
	public Rectangle selectedArea = new Rectangle();
	public Shape selectedObj = null;
	
	
	
	private Canvas() {	//singleton
		
	}
	
	public static Canvas getInstance() {	
		
		if(instance == null) {
			instance = new Canvas();
		}
		
		return instance;
	}
	
	public void paint(Graphics g) {
		//canvas basic settings
		Dimension dim = getSize();
		g.setColor(Color.white);
		g.fillRect(0, 0, dim.width, dim.height);
	
		//set Painting color
		g.setColor(Color.black);
		Graphics2D g2d = (Graphics2D)g;
		
		
		g2d.setStroke(new BasicStroke(2));
		
		for(int i = 0; i <= shapes.size() - 1; i++) {
			Shape shape = shapes.get(i);
			shape.draw(g);
			shape.group_selected = false;
			
			if(!selectedArea.isEmpty() && checkSelectArea(shape)) {
				shape.show(g);
				shape.group_selected = true;
				
			}
			
			if(this.selectedObj == shape) { 
				shape.show(g);
			}
			
		}
		
//		if(this.selectedObj == null) {
//			tempLine = null;
//		}
		
		if(tempLine != null) {
			tempLine.draw(g);
		}
		
		
	
		if(!selectedArea.isEmpty()) { 
			
			int transparent = 50;
			g.setColor(new Color(204,204,254,transparent));
			g.fillRect(selectedArea.x,selectedArea.y,selectedArea.width,selectedArea.height);
			g.setColor(new Color(0,0,0,90));
			g.drawRect(selectedArea.x,selectedArea.y,selectedArea.width,selectedArea.height);
		}
		
		
		
	}

	public void changeObjName(String name) {
		if(selectedObj != null) {
			selectedObj.changeName(name);
			repaint();
		}
	}
	
	private boolean checkSelectArea(Shape shape) {
		// TODO Auto-generated method stub
		Point prev = new Point(shape.getX1(),shape.getY1());
		Point next = new Point(shape.getX2(),shape.getY2());
		
		
		//rectangle.contatins() 
		if(selectedArea.contains(prev) && selectedArea.contains(next)) {
			return true;
		}
		
		
		return false;
	}

	public void removeGroup() {
		
		Group group = (Group) selectedObj;
		List<Shape> groupShapes = group.getShapes();
		for(int i = 0; i < groupShapes.size(); i++){
			Shape shape = groupShapes.get(i);
			shapes.add(shape);
		}
		shapes.remove(selectedObj);
		
	}

	public void groupShape() {
		
		Group group = new Group();
		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = shapes.get(i);
			if (shape.group_selected) {
				group.addShapes(shape);
				shapes.remove(i);
				i--;
			}
		}
		
		group.setBounds();
		shapes.add(group);
		
	}

	//
	public void setCurrentMode() {
		removeMouseListener((MouseListener)listener);
		removeMouseMotionListener((MouseMotionListener)listener);
		
		listener = current_Mode;
		
		addMouseListener((MouseListener)listener);
		addMouseMotionListener((MouseMotionListener)listener);
		
	}

	public void reset() {
		
		if(selectedObj != null) {
			selectedObj.resetSelectedShape();
			selectedObj = null;
		}
		
		selectedArea.setBounds(0, 0, 0, 0);
	}

	public void addshape(Shape shape) {
		shapes.add(shape);
		
	}
	
	public List<Shape> getShapesList(){
		return shapes;
	}
	
}
