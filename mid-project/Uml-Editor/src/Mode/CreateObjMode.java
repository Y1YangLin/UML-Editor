package Mode;

import java.awt.Point;
import java.awt.event.MouseEvent;

import Shape.Obj;
import Shape.Obj_class;
import Shape.Obj_usecase;

public class CreateObjMode extends Mode{
	private String objname;
//	private ShapeCreatorInterface creator = new ShapeCreator();
	
	public CreateObjMode(String name) {
		objname = name;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Obj obj = null; 
		Point p = e.getPoint();
		
		switch(objname) {
			case "class":
				obj = new Obj_class(p.x, p.y);
				break;
			case "usecase":
				obj = new Obj_usecase(p.x, p.y);
				break;
			
			default:
				break;
		}
				

//		System.out.println("create" + this.objname);
		
		if(obj != null) {
			canvas.addshape(obj);
		}
		
		canvas.repaint();
		
	}
	

}

	


