package Shape;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Port extends Rectangle{
	/* 繼承rectangle -> 黑色長方形 */
	List<Line> lines = new ArrayList<Line>();
	
	
	
	public void setPort(int x, int y, int offset) {
		int x1 = x - offset;
		int y1 = y - offset;
		
		this.setBounds(x1, y1, offset*2, offset*2);
		
	}
	
	public void addLine(Line line) {
		lines.add(line);
	}
	
	public void removeLine(Line line) {
		lines.remove(line);
	}
	
	public void resetLine() {
		
		for(int i = 0; i < lines.size(); i++) {
			lines.get(i).resetLocation();
		}
		
	}
	
}
