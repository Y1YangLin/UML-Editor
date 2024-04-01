package Shape;

import java.awt.Color;
import java.awt.Graphics;

public class Obj_class extends Obj{
	
	public Obj_class(int x1,int y1) {
		this.x1 = x1;
		this.y1 = y1;
		
		this.width = 100;
		this.height = 120;
		
		this.x2 = x1 + width;
		this.y2 = y1 + height;
		
		
		createPorts();
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		
		
		
		g.drawRect(x1, y1, width, height);
		
		int third_one = height / 3;
		g.drawLine(x1, y1 + third_one, x2, y1 + third_one);
		g.drawLine(x1, y1 + third_one * 2, x2, y1 + 2 * third_one);
		
		
		//background color
		g.setColor(new Color(248,248,255)); //class background color
		
		g.fillRect(x1 + 1, y1 + 1,width - 2,third_one - 1);
		g.fillRect(x1 + 1, y1 + 1 + third_one,width - 2,third_one - 1);
		g.fillRect(x1 + 1, y1 + 1 + third_one * 2,width - 2,third_one - 1);
		g.setColor(Color.black);
		
		
		//Obj name長度
		int nameWidth = g.getFontMetrics(font).stringWidth(name);
		g.setFont(font);
		
		//這樣string 就可以印在class中間
		double gap_btw_x1x2 = (Math.abs(x1 - x2) - nameWidth) / 2;
		
		g.drawString(name, x1 + (int)gap_btw_x1x2, y1 + 20);
	}

}
