package Shape;

import java.awt.Color;
import java.awt.Graphics;

public class Obj_usecase extends Obj{

	public Obj_usecase(int x,int y) {
		// TODO Auto-generated constructor stub
		this.x1 = x;
		this.y1 = y;
		this.width = 125;
		this.height = 90;
		this.x2 = x1 + width;
		this.y2 = y1 + height;
		
		createPorts();
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawOval(x1, y1, width, height);
		
		//set background
		g.setColor(new Color(248,248,255));
		g.fillOval(x1 + 1, y1 + 1, width - 2, height - 2);
		
		g.setColor(Color.black);
		
		int nameWidth = g.getFontMetrics(font).stringWidth(name);
		g.setFont(font);
		
		
		double gap_btw_x1x2 = (Math.abs(x1 - x2) - nameWidth) / 2;
		
		g.drawString(name, x1 + (int)gap_btw_x1x2, y1 + 45);
	}

}
