package Shape;

import java.awt.Graphics;

public class AssoLine extends Line{
	private int arrowH = 10,arrowW = 10;
	
	
	public AssoLine(int x1,int y1,int x2,int y2) {
		// TODO Auto-generated constructor stub
		
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	
	
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawLine(x1, y1, x2, y2);
	
		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx*dx + dy*dy);
		double xm = D - arrowW, xn = xm, ym = arrowH, yn = -arrowH, x;
		double sin = dy/D, cos = dx/D;
				
		x = xm*cos - ym*sin + x1;
		ym = xm*sin + ym*cos + y1;
		xm = x;

		x = xn*cos - yn*sin + x1;
		yn = xn*sin + yn*cos + y1;
		
		xn = x;

		//¥k½bÀY »P ¥ª½bÀY
		g.drawLine(x2, y2, (int) xm, (int) ym);
		g.drawLine(x2, y2, (int) xn, (int) yn);

	
	}

}
