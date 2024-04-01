package Shape;

import java.awt.Graphics;

public class CompLine extends Line{
	private int diamondW = 10,diamondH = 10;
	
	
	public CompLine(int x1,int y1,int x2,int y2) {
		
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		
	}
	
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawLine(x1, y1, x2, y2);
		
		//�T���Τ���
		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx*dx + dy*dy);
		double xm = D - diamondH,xn = xm,ym = diamondH,yn = -diamondH, x;
		double sin = dy/D, cos = dx/D;
		
		x = xm*cos - ym*sin + x1;
		
		ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;
		
     // ���I������X�u�W���I, �T���Ϊ��T���I�P�o���I�s�u�Y���@�ӵ٧�
        double xq = (diamondH*2/D)*x1 + ((D-diamondH*2)/D)*x2;
        double yq = (diamondH*2/D)*y1 + ((D-diamondH*2)/D)*y2;
   
        int[] xpoints = {x2, (int) xm, (int) xq, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yq, (int) yn};

        g.fillPolygon(xpoints, ypoints, 4);
        
        
	}

	
	
}
